package ru.mobileup.template.features.search.presentation

import com.arkivanov.decompose.ComponentContext
import dev.icerock.moko.resources.PluralsResource
import dev.icerock.moko.resources.desc.PluralFormatted
import dev.icerock.moko.resources.desc.StringDesc
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.aartikov.replica.algebra.normal.withKey
import ru.mobileup.kmm_form_validation.options.ImeAction
import ru.mobileup.kmm_form_validation.options.KeyboardOptions
import ru.mobileup.kmm_form_validation.options.KeyboardType
import ru.mobileup.kmm_form_validation.validation.control.isNotBlank
import ru.mobileup.kmm_form_validation.validation.control.minLength
import ru.mobileup.kmm_form_validation.validation.form.RevalidateOnValueChanged
import ru.mobileup.kmm_form_validation.validation.form.ValidateOnFocusLost
import ru.mobileup.template.core.error_handling.ErrorHandler
import ru.mobileup.template.core.utils.InputControl
import ru.mobileup.template.core.utils.Resource
import ru.mobileup.template.core.utils.ResourceFormatted
import ru.mobileup.template.core.utils.componentScope
import ru.mobileup.template.core.utils.dynamicValidationResult
import ru.mobileup.template.core.utils.formValidator
import ru.mobileup.template.core.utils.observe
import ru.mobileup.template.features.R
import ru.mobileup.template.features.search.data.SearchRepository
import ru.mobileup.template.features.search.domain.SearchCoinInfo
import kotlin.math.max

class RealSearchComponent(
    componentContext: ComponentContext,
    private val onOutput: (SearchComponent.Output) -> Unit,
    searchRepository: SearchRepository,
    errorHandler: ErrorHandler,
) : ComponentContext by componentContext, SearchComponent {

    companion object {
        private const val MINIMAL_UPDATE_MILLIS = 300L
        private const val MINIMAL_INPUT_SIZE = 2
        private const val MAXIMUM_INPUT_SIZE = 30
    }

    override val query = InputControl(
        maxLength = MAXIMUM_INPUT_SIZE,
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Search
        ),
    )

    private val formValidator = formValidator {
        features = listOf(
            ValidateOnFocusLost,
            RevalidateOnValueChanged,
        )

        input(query) {
            minLength(
                length = MINIMAL_INPUT_SIZE,
                errorMessage = StringDesc.PluralFormatted(
                    pluralsRes = PluralsResource(R.plurals.input_min_length_error),
                    number = MAXIMUM_INPUT_SIZE,
                    args = arrayOf(MAXIMUM_INPUT_SIZE)
                )
            )
        }
    }

    private val dynamicResult = dynamicValidationResult(formValidator)

    init {
        componentScope.launch {
            dynamicResult.collectLatest {
                if (it.isValid) {
                    delay(MINIMAL_UPDATE_MILLIS)
                    requestQuery.value = query.text.value
                }
            }
        }
    }

    private val requestQuery = MutableStateFlow("")

    private val searchReplica = searchRepository.searchCoinReplica

    override val items = searchReplica.withKey(requestQuery).observe(this, errorHandler)

    override fun onCoinInfoClick(coinInfo: SearchCoinInfo) {
        onOutput(
            SearchComponent.Output.CoinDetailsRequested(
                coinInfo = coinInfo
            )
        )
    }
}