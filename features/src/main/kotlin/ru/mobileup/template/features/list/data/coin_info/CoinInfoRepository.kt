package ru.mobileup.template.features.list.data.coin_info

import me.aartikov.replica.keyed_paged.KeyedPagedReplica
import ru.mobileup.template.features.list.domain.CoinList
import ru.mobileup.template.features.list.domain.Currency

interface CoinInfoRepository {

    val coinListReplica: KeyedPagedReplica<Currency, CoinList>
}