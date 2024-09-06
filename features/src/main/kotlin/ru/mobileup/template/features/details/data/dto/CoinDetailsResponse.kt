package ru.mobileup.template.features.details.data.dto

import kotlinx.collections.immutable.toImmutableList
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.jsonPrimitive
import ru.mobileup.template.features.details.domain.CoinDetails
import ru.mobileup.template.core.common_domain.cryptocurrency.CoinId
import java.util.Locale

@Serializable
data class CoinDetailsResponse(
    @SerialName("id") val id: String,
    @SerialName("image") val imageLink: ImageHolder,
    @SerialName("description") val description: JsonObject,
    @SerialName("categories") val categories: List<String>
) {
    companion object {
        fun CoinDetailsResponse.toDomain() = CoinDetails(
            id = CoinId(id),
            imageLink = imageLink.large,
            description = description.parse(Locale.getDefault().language) ?: description.parse("en"),
            categories = categories.toImmutableList()
        )

        private fun JsonObject.parse(locale: String): String? {
            return get(locale)?.jsonPrimitive
                ?.takeIf { it.isString }
                ?.content
                ?.takeIf { it.isNotEmpty() && it.isNotBlank() }
        }
    }
}

@Serializable
data class ImageHolder(
    @SerialName("thumb") val thumb: String,
    @SerialName("small") val small: String,
    @SerialName("large") val large: String
)
