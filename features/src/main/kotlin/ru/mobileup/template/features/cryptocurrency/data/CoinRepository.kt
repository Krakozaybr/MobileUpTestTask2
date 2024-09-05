package ru.mobileup.template.features.cryptocurrency.data

import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.keyed_paged.KeyedPagedReplica
import ru.mobileup.template.features.cryptocurrency.domain.CoinDetails
import ru.mobileup.template.features.cryptocurrency.domain.CoinId
import ru.mobileup.template.features.cryptocurrency.domain.CoinList
import ru.mobileup.template.features.cryptocurrency.domain.Currency

interface CoinRepository {

    val coinListReplica: KeyedPagedReplica<Currency, CoinList>

    val coinDetailsReplica: KeyedReplica<CoinId, CoinDetails>
}