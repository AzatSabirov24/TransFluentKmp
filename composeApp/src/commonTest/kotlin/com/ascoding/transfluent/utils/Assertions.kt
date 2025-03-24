package com.ascoding.transfluent.utils

interface Matcher<T> {

    fun test(value: T)
}

infix fun <T> T.should(matcher: Matcher<T>) = matcher.test(this)

fun beTrue(message: String? = null) = createMatcher<Boolean> { value ->
    if (value == null || value == false) {
        throw AssertionError(message)
    }
}

fun beFalse(message: String? = null) = createMatcher<Boolean> { value ->
    if (value == null || value == true) {
        throw AssertionError(message)
    }
}

fun beNull(message: String? = null) = createMatcher<Boolean> { value ->
    if (value != null) {
        throw AssertionError(message)
    }
}

fun equal(actual: Any?) = createMatcher<Any?> { value ->
    if (value != actual) {
        throw AssertionError("Expected is '$value', but actual is '$actual'")
    }
}

fun notEqual(actual: Any?) = createMatcher<Any?> { value ->
    if (value == actual) {
        throw AssertionError("Expected is '$value', but actual is '$actual'")
    }
}

fun <T> createMatcher(block: (T?) -> Unit) = object : Matcher<T?> {
    override fun test(value: T?) {
        block(value)
    }
}
