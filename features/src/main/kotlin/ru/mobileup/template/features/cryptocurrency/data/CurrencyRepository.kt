package ru.mobileup.template.features.cryptocurrency.data

import kotlinx.collections.immutable.ImmutableList
import me.aartikov.replica.single.Replica
import ru.mobileup.template.features.cryptocurrency.domain.Currency

interface CurrencyRepository {

    val currenciesReplica: Replica<ImmutableList<Currency>>

}