package com.example.tbcacademyhomework.domain.auth.usecase

import junit.framework.TestCase.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ValidateEmailUseCaseImplTest {

    private lateinit var validateEmailUseCase: ValidateEmailUseCase

    @Before
    fun setUp(){
        validateEmailUseCase = ValidateEmailUseCaseImpl()
    }

    @Test
    fun validEmailReturnsTrue() {
        assertTrue(validateEmailUseCase.invoke("test@example.com"))
    }

    @Test
    fun emailWithoutAtSymbolReturnsFalse() {
        assertFalse(validateEmailUseCase.invoke("test.example.com"))
    }

    @Test
    fun emailWithConsecutiveDotsReturnsFalse() {
        assertFalse(validateEmailUseCase.invoke("test..example@examplecom"))
    }

    @Test
    fun emailWithLeadingDotReturnsFalse() {
        assertFalse(validateEmailUseCase.invoke(" .test@example.com"))
    }

    @Test
    fun emailWithTrailingDotReturnsFalse() {
        assertFalse(validateEmailUseCase.invoke("test .@example.com"))
    }

    @Test
    fun emailWithSpecialCharactersReturnsFalse() {
        assertFalse(validateEmailUseCase.invoke("test@ex!ample.com"))
    }

    @Test
    fun emailWithSubdomainReturnsTrue() {
        assertTrue(validateEmailUseCase.invoke("test@mail.example.com"))
    }

    @Test
    fun emptyEmailReturnsFalse() {
        assertFalse(validateEmailUseCase.invoke(""))
    }

    @Test
    fun emailWithSpacesReturnsFalse() {
        assertFalse(validateEmailUseCase.invoke("test @example.com"))
    }



}
