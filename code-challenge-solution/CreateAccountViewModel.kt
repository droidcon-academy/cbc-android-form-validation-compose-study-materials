package com.droidcon.formvalidation.createaccount

import androidx.lifecycle.ViewModel
import com.droidcon.formvalidation.createaccount.validation.CreateAccountParams
import com.droidcon.formvalidation.createaccount.validation.ValidatorFactory
import com.droidcon.formvalidation.validator.InputValidator
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class CreateAccountViewModel(
    private val validators: Map<CreateAccountParams, InputValidator> = ValidatorFactory.create()
) : ViewModel() {

    private val _state = MutableStateFlow(CreateAccountState())
    val state: StateFlow<CreateAccountState> = _state

    fun onEvent(event: CreateAccountEvent) {
        when (event) {
            is CreateAccountEvent.NameChanged -> _state.value = _state.value.copy(name = event.name)
            is CreateAccountEvent.EmailChanged -> _state.value =
                _state.value.copy(email = event.email)
            is CreateAccountEvent.PasswordChanged -> _state.value =
                _state.value.copy(password = event.password)
            is CreateAccountEvent.DateOfBirthChanged -> _state.value =
                _state.value.copy(dateOfBirth = event.dateOfBirth)
            CreateAccountEvent.CreateAccount -> {
                if (areInputsValid()) {
                    createAccount()
                }
            }
        }
    }

    private fun areInputsValid(): Boolean {
        val nameError = validators[CreateAccountParams.FULL_NAME]?.validate(_state.value.name)
        val emailError = validators[CreateAccountParams.EMAIL]?.validate(_state.value.email)
        val passwordError =
            validators[CreateAccountParams.PASSWORD]?.validate(_state.value.password)
        val dateOfBirthError =
            validators[CreateAccountParams.DATE_OF_BIRTH]?.validate(_state.value.dateOfBirth)

        val hasError = listOf(
            nameError,
            emailError,
            passwordError,
            dateOfBirthError
        ).any { it?.isValid == false }

        _state.value = _state.value.copy(
            nameError = nameError?.errorMessage,
            emailError = emailError?.errorMessage,
            passwordError = passwordError?.errorMessage,
            dateOfBirthError = dateOfBirthError?.errorMessage
        )

        return hasError.not()
    }

    private fun createAccount() {
        //@Todo Create Account
    }
}