package ru.mobileup.template

import ru.mobileup.template.core.coreModule
import ru.mobileup.template.features.cryptocurrency.cryptocurrencyModule
import ru.mobileup.template.features.pokemons.pokemonsModule

val allModules = listOf(
    coreModule(BuildConfig.BACKEND_URL),
    pokemonsModule,
    cryptocurrencyModule
)