package ru.mobileup.template.features.list.data.currency

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.persistentListOf
import kotlinx.collections.immutable.toImmutableList
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.single.Replica
import me.aartikov.replica.single.ReplicaSettings
import ru.mobileup.template.features.list.domain.Currency
import kotlin.time.Duration.Companion.minutes

class CurrencyRepositoryImpl(
    replicaClient: ReplicaClient,
    api: CurrencyApi
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
                }
            } catch (e: Exception) {
                listOf()
            }

            // Defaults should be leading
            return@createReplica (defaults + list.filterNot { it in defaults }).toImmutableList()
        }
}