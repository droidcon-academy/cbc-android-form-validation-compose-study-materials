package com.droidcon.formvalidation.createaccount.validation

import com.droidcon.formvalidation.R
import com.droidcon.formvalidation.validator.InputValidator
import com.droidcon.formvalidation.validator.ValidationResult

class PasswordValidator : InputValidator {
    override fun validate(input: String): ValidationResult {
        return if (input.length < 6) {
            ValidationResult(R.string.error_password)
        } else {
            ValidationResult()
        }
    }
}