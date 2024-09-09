package ru.mobileup.template.features.search.data

import kotlinx.collections.immutable.ImmutableList
import kotlinx.collections.immutable.toImmutableList
import me.aartikov.replica.client.ReplicaClient
import me.aartikov.replica.keyed.KeyedReplica
import me.aartikov.replica.keyed.KeyedReplicaSettings
import me.aartikov.replica.single.ReplicaSettings
import ru.mobileup.template.features.list.data.coin_info.CoinInfoApi
import ru.mobileup.template.features.search.data.dto.CoinItemSearchResponse.Companion.toDomain
import ru.mobileup.template.features.search.domain.SearchCoinInfo
import kotlin.time.Duration.Companion.minutes

class SearchRepositoryImpl(
    replicaClient: ReplicaClient,
    api: SearchApi
) : SearchRepository {
    override val searchCoinReplica: KeyedReplica<String, ImmutableList<SearchCoinInfo>> =
        replicaClient.createKeyedReplica(
            name = "coinListByQuery",
            childName = { it },
            settings = KeyedReplicaSettings(),
            childSettings = { ReplicaSettings(staleTime = 10.minutes) },
        ) { query ->
            api.searchCoins(query).coins.map { it.toDomain() }.toImmutableList()
        }
}