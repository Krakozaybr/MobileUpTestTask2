package ru.mobileup.template.features.cryptocurrency.data

import kotlinx.collections.immutable.ImmutableList
import me.aartikov.replica.keyed.KeyedReplica
import ru.mobileup.template.features.cryptocurrency.domain.CoinDetails
import ru.mobileup.template.features.cryptocurrency.domain.CoinId
import ru.mobileup.template.features.cryptocurrency.domain.CoinInfo
import ru.mobileup.template.features.cryptocurrency.domain.Currency

interface CoinRepository {

    val coinListReplica: KeyedReplica<Currency, ImmutableList<CoinInfo>>

    val coinDetailsReplica: KeyedReplica<CoinId, CoinDetails>
}