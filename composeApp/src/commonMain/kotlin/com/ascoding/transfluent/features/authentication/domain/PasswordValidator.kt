package com.ascoding.transfluent.features.authentication.domain

class PasswordValidator {

    fun validatePassword(password: String) =
        containsAtLeastSixCharacters(password) &&
                containsAtLeastOneDigit(password) &&
                containsAtLeastOneLetter(password)

    fun containsAtLeastSixCharacters(password: String) = password.length >= 6

    fun containsAtLeastOneLetter(password: String) = password.any { it.isLetter() }

    fun containsAtLeastOneDigit(password: String) = password.any { it.isDigit() }
}