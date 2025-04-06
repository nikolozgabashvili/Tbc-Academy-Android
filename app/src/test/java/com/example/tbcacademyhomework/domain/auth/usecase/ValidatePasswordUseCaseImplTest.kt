package com.example.tbcacademyhomework.domain.auth.usecase

import junit.framework.TestCase.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class ValidatePasswordUseCaseImplTest {

    private lateinit var validatePasswordUseCase: ValidatePasswordUseCase

    @Before
    fun setUp() {
        validatePasswordUseCase = ValidatePasswordUseCaseImpl()
    }
    @Test
    fun passwordWithSufficientLengthReturnsTrue() {
        val result = validatePasswordUseCase("validPassword")
        assertTrue(result)
    }
    @Test
    fun passwordWithExactMinLengthReturnsTrue() {
        val result = validatePasswordUseCase("abc")
        assertTrue(result)
    }
    @Test
    fun passwordWithLessThanMinLengthReturnsFalse() {
        val result = validatePasswordUseCase("ab")
        assertFalse(result)
    }
    @Test
    fun emptyPasswordReturnsFalse() {
        val result = validatePasswordUseCase("")
        assertFalse(result)
    }
    @Test
    fun passwordWithSpacesOnlyReturnsFalse() {
        val result = validatePasswordUseCase("   ")
        assertFalse(result)
    }



}
