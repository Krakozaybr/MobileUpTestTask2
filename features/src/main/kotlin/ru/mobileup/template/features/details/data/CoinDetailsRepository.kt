package ru.mobileup.template.features.details.data

import me.aartikov.replica.keyed.KeyedReplica
import ru.mobileup.template.features.details.domain.CoinDetails
import ru.mobileup.template.core.common_domain.cryptocurrency.CoinId

interface CoinDetailsRepository {

    val coinDetailsReplica: KeyedReplica<CoinId, CoinDetails>
}