package com.droidcon.formvalidation.createaccount

import androidx.annotation.StringRes

data class CreateAccountState(
    val name: String = "",
    @StringRes val nameError: Int? = null,

    val email: String = "",
    @StringRes val emailError: Int? = null,
)
