package com.ascoding.transfluent.features.authentication.domain

class EmailValidator {

    fun validateEmail(email: String) =
        emailContainsAtSymbol(email) &&
                emailContainsAtLeastTwoLettersBeforeAt(email) &&
                emailContainsAtLeastTwoLettersAfterAt(email) &&
                emailContainsAtLeastTwoLettersAfterDot(email) &&
                emailContainsRightSymbolsBeforeAt(email) &&
                emailContainsRightSymbolsAfterAt(email)

    fun emailContainsAtSymbol(email: String) = email.trim().contains("@")

    fun emailContainsAtLeastTwoLettersBeforeAt(email: String): Boolean {
        val symbolsBeforeAt = email.trim().substringBefore("@")
        return symbolsBeforeAt.length >= 2
    }

    fun emailContainsAtLeastTwoLettersAfterAt(email: String): Boolean {
        val symbolsBeforeAt = email.trim().substringAfter("@")
        val symbolsAfterAt = symbolsBeforeAt.substringBefore(".")
        return symbolsAfterAt.length >= 2
    }

    fun emailContainsAtLeastTwoLettersAfterDot(email: String): Boolean {
        val symbolsAfterLastDot = email.trim().substringAfterLast(".")
        return symbolsAfterLastDot.length >= 2
    }

    fun emailContainsRightSymbolsBeforeAt(email: String): Boolean {
        val symbolsBeforeAt = email.trim().substringBefore("@")
        val regex = "^[a-zA-Z0-9!#\$%&'*+/=?^_`{|}~.-]+\$"
        return symbolsBeforeAt.isNotEmpty() &&
                !symbolsBeforeAt.startsWith(".") &&
                !symbolsBeforeAt.endsWith(".") &&
                !symbolsBeforeAt.contains("..") &&
                Regex(regex).matches(symbolsBeforeAt)
    }

    fun emailContainsRightSymbolsAfterAt(email: String): Boolean {
        val domain = email.substringAfter("@").trim()
        if (email.contains("@ ") || domain.isEmpty()) return false
        val domainRegex = "^[a-zA-Z0-9]([a-zA-Z0-9-]*[a-zA-Z0-9])?(\\.[a-zA-Z]{2,})+$"
        return !domain.contains(" ") &&
                !domain.contains("..") &&
                Regex(domainRegex).matches(domain)
    }
}