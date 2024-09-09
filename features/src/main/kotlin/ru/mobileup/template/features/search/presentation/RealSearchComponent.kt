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
import ru.mobileup.kmm_form_validation.validation.control.ValidationResult
import ru.mobileup.kmm_form_validation.validation.control.minLength
import ru.mobileup.kmm_form_validation.validation.control.regex
import ru.mobileup.kmm_form_validation.validation.form.RevalidateOnValueChanged
import ru.mobileup.kmm_form_validation.validation.form.ValidateOnFocusLost
import ru.mobileup.template.core.error_handling.ErrorHandler
import ru.mobileup.template.core.utils.InputControl
import ru.mobileup.template.core.utils.Resource
import ru.mobileup.template.core.utils.componentScope
import ru.mobileup.template.core.utils.dynamicValidationResult
import ru.mobileup.template.core.utils.formValidator
import ru.mobileup.template.core.utils.observe
import ru.mobileup.template.features.R
import ru.mobileup.template.features.search.data.SearchRepository
import ru.mobileup.template.features.search.domain.SearchCoinInfo

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
        private const val SEARCH_REGEX = "[a-zA-Z]+"
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
                    number = MINIMAL_INPUT_SIZE,
                    args = arrayOf(MINIMAL_INPUT_SIZE)
                )
            )
            regex(
                regex = Regex(SEARCH_REGEX),
                errorMessage = StringDesc.Resource(
                    R.string.must_consists_from_letters
                )
            )
        }
    }

    private val dynamicResult = dynamicValidationResult(formValidator)

    init {
        componentScope.launch {
            dynamicResult.collectLatest {
                when (val validationResult = it.controlResults[query]) {
                    is ValidationResult.Invalid -> {
                        query.error.value = validationResult.errorMessage
                    }

                    else -> {}
                }
            }
        }
        componentScope.launch {
            query.text.collectLatest {
                delay(MINIMAL_UPDATE_MILLIS)
                if (query.error.value == null) {
                    requestQuery.value = it
                }
            }
        }
    }

    private val requestQuery = MutableStateFlow("")

    private val searchReplica = searchRepository.searchCoinReplica.withKey(requestQuery)

    override val items = searchReplica.observe(this, errorHandler)

    override fun onCoinInfoClick(coinInfo: SearchCoinInfo) {
        onOutput(
            SearchComponent.Output.CoinDetailsRequested(
                coinInfo = coinInfo
            )
        )
    }

    override fun onRetryClick() {
        searchReplica.refresh()
    }
}