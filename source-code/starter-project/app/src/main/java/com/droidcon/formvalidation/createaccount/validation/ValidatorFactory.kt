package com.droidcon.formvalidation.createaccount.validation

import com.droidcon.formvalidation.validator.InputValidator

object ValidatorFactory {
    fun create(): Map<CreateAccountParams, InputValidator> = mapOf(
        CreateAccountParams.FULL_NAME to NameValidator(),
        CreateAccountParams.EMAIL to EmailValidator()
    )
}