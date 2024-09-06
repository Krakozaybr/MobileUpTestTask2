package ru.mobileup.template.features.list.data.currency

import kotlinx.collections.immutable.ImmutableList
import me.aartikov.replica.single.Replica
import ru.mobileup.template.features.list.domain.Currency

interface CurrencyRepository {

    val currenciesReplica: Replica<ImmutableList<Currency>>
}