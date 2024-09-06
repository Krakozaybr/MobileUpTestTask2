package ru.mobileup.template.features.list.data.coin_info

import me.aartikov.replica.paged.Page

data class SimplePage<T : Any>(
    override val hasNextPage: Boolean,
    override val hasPreviousPage: Boolean,
    override val items: List<T>
) : Page<T>
