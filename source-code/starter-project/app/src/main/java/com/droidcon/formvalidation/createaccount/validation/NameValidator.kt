package com.droidcon.formvalidation.createaccount.validation

import com.droidcon.formvalidation.R
import com.droidcon.formvalidation.validator.InputValidator
import com.droidcon.formvalidation.validator.ValidationResult

class NameValidator : InputValidator {

    override fun validate(input: String): ValidationResult {
        return if (input.length < 3) {
            ValidationResult(R.string.error_name_length)
        } else {
            ValidationResult()
        }
    }
}