package ru.mobileup.template.features.details.data

import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.single.ReplicaSettings
import ru.mobileup.template.features.details.data.dto.CoinDetailsResponse.Companion.toDomain
import ru.mobileup.template.features.details.domain.CoinDetails
import ru.mobileup.template.core.common_domain.cryptocurrency.CoinId
import kotlin.time.Duration.Companion.minutes
import kotlin.time.Duration.Companion.seconds

class CoinDetailsRepositoryImpl(
    replicaClient: ReplicaClient,
    api: CoinDetailsApi
) : CoinDetailsRepository {
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
            api.getDetails(id.value).toDomain()
        }
}