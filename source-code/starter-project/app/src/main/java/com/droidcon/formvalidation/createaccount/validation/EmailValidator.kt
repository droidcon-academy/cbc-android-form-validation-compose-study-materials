package com.droidcon.formvalidation.createaccount.validation

import com.droidcon.formvalidation.R
import com.droidcon.formvalidation.validator.InputValidator
import com.droidcon.formvalidation.validator.ValidationResult

class EmailValidator : InputValidator {

    private val emailPattern = Regex(
        "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,}$",
        RegexOption.IGNORE_CASE
    )

    override fun validate(input: String): ValidationResult {
        return if (input.isEmpty()) {
            ValidationResult(R.string.error_email_empty)
        } else if (!emailPattern.matches(input)) {
            return ValidationResult(R.string.error_email_invalid)
        } else {
            ValidationResult()
        }
    }
}