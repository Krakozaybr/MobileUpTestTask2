package ru.mobileup.template.features.cryptocurrency.data

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.single.ReplicaSettings
import ru.mobileup.template.features.cryptocurrency.domain.CoinDetails
import ru.mobileup.template.features.cryptocurrency.domain.CoinId
import ru.mobileup.template.features.cryptocurrency.domain.CoinInfo
import ru.mobileup.template.features.cryptocurrency.domain.Currency
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class CoinRepositoryImpl(
    replicaClient: ReplicaClient,
    api: CryptocurrencyApi
) : CoinRepository {
    override val coinListReplica: KeyedReplica<Currency, ImmutableList<CoinInfo>> =
        replicaClient.createKeyedReplica(
            name = "coinListByCurrency",
            childName = { it.name },
            childSettings = {
                ReplicaSettings(
                    staleTime = 1.minutes,
                    clearTime = 30.seconds,
                )
            }
        ) { currency ->
            api
                .getList(currency.name)
                .map { it.map(currency) }
                .toImmutableList()
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