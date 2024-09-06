package ru.mobileup.template.core.paging_tools

import me.aartikov.replica.paged.Page

data class SimplePage<T : Any>(
    override val hasNextPage: Boolean,
    override val items: List<T>,
    override val hasPreviousPage: Boolean = false,
) : Page<T>
