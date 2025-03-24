package com.ascoding.transfluent.features.authorization.authentication.domain

import com.ascoding.transfluent.features.authentication.domain.EmailValidator
import com.ascoding.transfluent.utils.beFalse
import com.ascoding.transfluent.utils.beTrue
import com.ascoding.transfluent.utils.should
import kotlin.test.Test

class EmailValidatorTest {

    private val validator = EmailValidator()

    @Test
    fun testValidEmail() {
        val email = "william.johnson@example-pet-store.co"
        validator.validateEmail(email) should beTrue()
    }

    @Test
    fun testInvalidEmail() {
        val email = "william.johnsonexample-pet-store.co"
        validator.validateEmail(email) should beFalse()
    }

    @Test
    fun testEmailContainsAtSymbol() {
        val email = "john32@example.com"
        validator.emailContainsAtSymbol(email) should beTrue()
    }

    @Test
    fun testEmailWithoutAtSymbol() {
        val email = "john32example.com"
        validator.emailContainsAtSymbol(email) should beFalse()
    }

    @Test
    fun testEmailContainsAtLeastTwoLettersBeforeAt() {
        val email = "john2@example.com"
        validator.emailContainsAtLeastTwoLettersBeforeAt(email) should beTrue()
    }

    @Test
    fun testEmailWithoutAtLeastTwoLettersBeforeAt() {
        val email = "john32@c.com"
        validator.emailContainsAtLeastTwoLettersAfterAt(email) should beFalse()
    }

    @Test
    fun testEmailContainsAtLeastTwoLettersAfterDot() {
        val email = "john32@mail.co"
        validator.emailContainsAtLeastTwoLettersAfterDot(email) should beTrue()
    }

    @Test
    fun testEmailWithoutAtLeastTwoLettersAfterDot() {
        val email = "john32@mail.c"
        validator.emailContainsAtLeastTwoLettersAfterDot(email) should beFalse()
    }

    @Test
    fun testEmailContainsRightSymbolsBeforeAt() {
        val email = "joh3n@mail.com"
        validator.emailContainsRightSymbolsBeforeAt(email) should beTrue()
    }

    @Test
    fun testEmailWithoutRightSymbolsBeforeAt() {
        val email = "joh<3n@mail.com"
        validator.emailContainsRightSymbolsBeforeAt(email) should beFalse()
    }

    @Test
    fun testEmailContainsRightSymbolsAfterAt() {
        val email = "joh3n@mail.com"
        validator.emailContainsRightSymbolsAfterAt(email) should beTrue()
    }

    @Test
    fun testEmailWithoutRightSymbolsAfterAt() {
        val email = "joh3n@mai>l.com"
        validator.emailContainsRightSymbolsAfterAt(email) should beFalse()
    }
}
