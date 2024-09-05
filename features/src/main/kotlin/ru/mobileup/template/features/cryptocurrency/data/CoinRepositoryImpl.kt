package ru.mobileup.template.features.cryptocurrency.data

import kotlinx.collections.immutable.toImmutableList
import me.aartikov.replica.algebra.paged.map
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.keyed_paged.KeyedPagedFetcher
import me.aartikov.replica.keyed_paged.KeyedPagedReplicaSettings
import me.aartikov.replica.paged.PagedData
import me.aartikov.replica.paged.PagedReplicaSettings
import me.aartikov.replica.single.ReplicaSettings
import ru.mobileup.template.features.cryptocurrency.data.dto.CoinInfoResponse
import ru.mobileup.template.features.cryptocurrency.domain.CoinDetails
import ru.mobileup.template.features.cryptocurrency.domain.CoinId
import ru.mobileup.template.features.cryptocurrency.domain.CoinList
import ru.mobileup.template.features.cryptocurrency.domain.Currency
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class CoinRepositoryImpl(
    replicaClient: ReplicaClient,
    api: CryptocurrencyApi
) : CoinRepository {

    companion object {

        private const val CoinPageSize = 30
    }

    override val coinListReplica =
        replicaClient.createKeyedPagedReplica(
            name = "coinPagedListByCurrency",
            settings = KeyedPagedReplicaSettings(),
            idExtractor = { it.id },
            childName = { it.name },
            childSettings = { PagedReplicaSettings(staleTime = 3.minutes) },
            fetcher = object : KeyedPagedFetcher<Currency, CoinInfoResponse, SimplePage<CoinInfoResponse>> {
                override suspend fun fetchFirstPage(key: Currency): SimplePage<CoinInfoResponse> {
                    val list = api.getList(
                        currency = key.name,
                        page = 1,
                        pageSize = CoinPageSize
                    )

                    return SimplePage(
                        hasNextPage = list.size == CoinPageSize,
                        hasPreviousPage = false,
                        items = list
                    )
                }

                override suspend fun fetchNextPage(
                    key: Currency,
                    currentData: PagedData<CoinInfoResponse, SimplePage<CoinInfoResponse>>
                ): SimplePage<CoinInfoResponse> {
                    val page = currentData.pages.size + 1
                    val list = api.getList(
                        currency = key.name,
                        page = page,
                        pageSize = CoinPageSize
                    )

                    return SimplePage(
                        hasNextPage = list.size == CoinPageSize,
                        hasPreviousPage = page > 1,
                        items = list
                    )
                }
            }
        ).map { currency, pagedData ->
            CoinList(
                list = pagedData.items.map { it.map(currency) }.toImmutableList(),
                hasNextPage = pagedData.hasNextPage
            )
        }

    override val coinDetailsReplica: KeyedReplica<CoinId, CoinDetails> =
        replicaClient.createKeyedReplica(
            name = "coinDetailsById",
            childName = { it.value },
            childSettings = {
                ReplicaSettings(
                    // Description is not likely to change often
                    staleTime = 15.minutes,
                    clearTime = 30.seconds,
                )
            }
        ) { id ->
            api.getDetails(id.value).map()
        }
}