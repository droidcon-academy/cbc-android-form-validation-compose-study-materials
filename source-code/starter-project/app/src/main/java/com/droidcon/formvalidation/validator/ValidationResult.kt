package com.droidcon.formvalidation.validator

import androidx.annotation.StringRes

data class ValidationResult(
    @StringRes val errorMessage: Int? = null
) {
    val isValid: Boolean
        get() = errorMessage == null
}