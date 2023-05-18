package com.droidcon.formvalidation.createaccount

sealed class CreateAccountEvent {
    data class NameChanged(val name: String) : CreateAccountEvent()
    data class EmailChanged(val email: String) : CreateAccountEvent()
    data class DateOfBirthChanged(val dateOfBirth: String) : CreateAccountEvent()
    data class PasswordChanged(val password: String) : CreateAccountEvent()
    object CreateAccount : CreateAccountEvent()
}
