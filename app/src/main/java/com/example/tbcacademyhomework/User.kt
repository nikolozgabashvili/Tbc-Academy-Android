package com.example.tbcacademyhomework

import com.example.tbcacademyhomework.validators.AgeValidator
import com.example.tbcacademyhomework.validators.EmailValidator
import com.example.tbcacademyhomework.validators.NameValidator
import com.example.tbcacademyhomework.validators.UserNameValidator
import com.example.tbcacademyhomework.validators.ValidationResult

class User(
    val name: String,
    val lastName: String,
    val age: String,
    val username: String,
    val email: String
) {
    val firstNameState: ValidationResult
        get() {
            val validator = NameValidator()
            return validator.validate(name)
        }

    val lastNameState: ValidationResult
        get() {
            val validator = NameValidator()
            return validator.validate(lastName)
        }

    val userNameState: ValidationResult
        get() {
            val validator = UserNameValidator()
            return validator.validate(username)
        }
    val ageState: ValidationResult
        get() {
            val validator = AgeValidator()
            return validator.validate(age)
        }
    val emailState: ValidationResult
        get() {
            val validator = EmailValidator()
            return validator.validate(email)
        }

    val isUserValid: Boolean
        get() {
            return !emailState.isError && !userNameState.isError && !firstNameState.isError && !ageState.isError && !lastNameState.isError
        }


}
