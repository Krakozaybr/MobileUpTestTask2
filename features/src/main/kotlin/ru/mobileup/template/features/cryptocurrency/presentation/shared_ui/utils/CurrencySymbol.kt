package ru.mobileup.template.features.cryptocurrency.presentation.shared_ui.utils

import java.util.Currency

val ru.mobileup.template.features.cryptocurrency.domain.Currency.symbol
    get() = try {
        Currency.getInstance(name.uppercase()).symbol
    } catch (e: IllegalArgumentException) {
        "?"
    }
