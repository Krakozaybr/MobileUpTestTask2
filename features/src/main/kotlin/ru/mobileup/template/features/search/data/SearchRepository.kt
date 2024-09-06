package ru.mobileup.template.features.search.data

import kotlinx.collections.immutable.ImmutableList
import me.aartikov.replica.keyed.KeyedReplica
import ru.mobileup.template.features.search.domain.SearchCoinInfo

interface SearchRepository {
    val searchCoinReplica: KeyedReplica<String, ImmutableList<SearchCoinInfo>>
}