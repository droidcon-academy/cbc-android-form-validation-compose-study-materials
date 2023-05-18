package com.droidcon.formvalidation.createaccount.validation

import com.droidcon.formvalidation.R
import com.droidcon.formvalidation.validator.InputValidator
import com.droidcon.formvalidation.validator.ValidationResult
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter

class DateOfBirthValidator : InputValidator {

    override fun validate(input: String): ValidationResult {
        return try {
            val dateOfBirth = LocalDate.parse(input, DateTimeFormatter.ofPattern("d/M/yyyy"))
            val age = Period.between(dateOfBirth, LocalDate.now()).years
            if (age < 18) {
                ValidationResult(R.string.error_not_enough_age)
            } else {
                ValidationResult()
            }
        } catch (e: Exception) {
            ValidationResult(R.string.error_invalid_date)
        }
    }
}