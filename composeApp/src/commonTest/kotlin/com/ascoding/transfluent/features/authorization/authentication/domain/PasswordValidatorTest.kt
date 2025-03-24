package com.ascoding.transfluent.features.authorization.authentication.domain

import com.ascoding.transfluent.features.authentication.domain.PasswordValidator
import com.ascoding.transfluent.utils.beFalse
import com.ascoding.transfluent.utils.beTrue
import com.ascoding.transfluent.utils.should
import kotlin.test.Test

class PasswordValidatorTest {

    private val validator = PasswordValidator()

    @Test
    fun testPasswordIsValid() {
        val password = "passkk1"
        validator.validatePassword(password) should beTrue()
    }

    @Test
    fun testPasswordIsNotValid() {
        val password = "passkk"
        validator.validatePassword(password) should beFalse()
    }

    @Test
    fun testPasswordContainsAtLeastSixCharacters() {
        val password = "password"
        validator.containsAtLeastSixCharacters(password) should beTrue()
    }

    @Test
    fun testPasswordWithoutAtLeastSixCharacters() {
        val password = "passw"
        validator.containsAtLeastSixCharacters(password) should beFalse()
    }

    @Test
    fun testPasswordContainsAtLeastOneDigit() {
        val password = "passw1"
        validator.containsAtLeastOneDigit(password) should beTrue()
    }

    @Test
    fun testPasswordWithoutAtLeastOneDigit() {
        val password = "password"
        validator.containsAtLeastOneDigit(password) should beFalse()
    }

    @Test
    fun testPasswordContainsAtLeastOneLetter() {
        val password = "f1223331"
        validator.containsAtLeastOneLetter(password) should beTrue()
    }

    @Test
    fun testPasswordWithoutAtLeastOneLetter() {
        val password = "1223331"
        validator.containsAtLeastOneLetter(password) should beFalse()
    }
}
