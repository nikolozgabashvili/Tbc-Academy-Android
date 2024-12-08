package com.example.tbcacademyhomework.validators

class UserDataValidator {
    private val emailValidator = EmailValidator()
    private val nameValidator = NameValidator()

    fun validate(input: String, validationType: ValidationType): ValidationResult {
        return when (validationType) {
            ValidationType.Email -> emailValidator.validate(input)
            ValidationType.FullName -> nameValidator.validate(input)
        }
    }

}