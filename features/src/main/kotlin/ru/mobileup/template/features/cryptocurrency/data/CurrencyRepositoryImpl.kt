package ru.mobileup.template.features.cryptocurrency.data

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.single.Replica
import me.aartikov.replica.single.ReplicaSettings
import ru.mobileup.template.features.cryptocurrency.domain.Currency
import kotlin.time.Duration.Companion.minutes

class CurrencyRepositoryImpl(
    replicaClient: ReplicaClient,
    api: CryptocurrencyApi
) : CurrencyRepository {

    companion object {
        private val defaults = persistentListOf(
            Currency("RUB"),
            Currency("USD"),
        )
    }

    override val currenciesReplica: Replica<ImmutableList<Currency>> =
        replicaClient.createReplica(
            name = "currencies",
            settings = ReplicaSettings(
                staleTime = 30.minutes,
                clearTime = 1.minutes
            )
        ) {
            val list = try {
                api.supportedCurrencies().map {
                    Currency(it)
                }.toMutableList()
            } catch (e: Exception) {
                mutableListOf()
            }

            list.removeAll(defaults)

            // Defaults should be leading
            return@createReplica (defaults + list).toImmutableList()
        }
}