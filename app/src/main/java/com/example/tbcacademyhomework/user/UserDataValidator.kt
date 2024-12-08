package com.example.tbcacademyhomework.user

import com.example.tbcacademyhomework.validators.EmailValidator
import com.example.tbcacademyhomework.validators.NameValidator
import com.example.tbcacademyhomework.validators.ValidationResult
import com.example.tbcacademyhomework.validators.ValidationType

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