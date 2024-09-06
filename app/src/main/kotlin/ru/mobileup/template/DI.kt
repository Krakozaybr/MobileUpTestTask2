package ru.mobileup.template

import ru.mobileup.template.core.coreModule
import ru.mobileup.template.features.details.detailsModule
import ru.mobileup.template.features.list.listModule

val allModules = listOf(
    coreModule(BuildConfig.BACKEND_URL),
    listModule,
    detailsModule
)