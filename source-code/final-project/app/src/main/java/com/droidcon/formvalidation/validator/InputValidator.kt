package com.droidcon.formvalidation.validator

interface InputValidator {
    fun validate(input: String): ValidationResult
}