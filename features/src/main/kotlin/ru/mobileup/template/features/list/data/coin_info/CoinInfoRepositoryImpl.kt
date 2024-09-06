package ru.mobileup.template.features.list.data.coin_info

import kotlinx.collections.immutable.toImmutableList
import me.aartikov.replica.algebra.paged.map
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed_paged.KeyedPagedFetcher
import me.aartikov.replica.keyed_paged.KeyedPagedReplicaSettings
import me.aartikov.replica.paged.PagedData
import me.aartikov.replica.paged.PagedReplicaSettings
import ru.mobileup.template.core.paging_tools.SimplePage
import ru.mobileup.template.features.list.data.dto.CoinInfoResponse
import ru.mobileup.template.features.list.domain.CoinList
import ru.mobileup.template.features.list.domain.Currency
import kotlin.time.Duration.Companion.minutes

class CoinInfoRepositoryImpl(
    replicaClient: ReplicaClient,
    api: CoinInfoApi
) : CoinInfoRepository {
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
            fetcher = object :
                KeyedPagedFetcher<Currency, CoinInfoResponse, SimplePage<CoinInfoResponse>> {
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
}