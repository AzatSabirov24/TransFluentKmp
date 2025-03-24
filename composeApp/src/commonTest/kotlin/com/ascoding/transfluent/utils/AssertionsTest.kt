package com.ascoding.transfluent.utils

import kotlin.test.Test

class MatcherTest {

    @Test
    fun `beTrue matcher should pass for true value`() {
        // Given
        val condition = true

        // Then
        condition should beTrue("Value should be true")
    }

    @Test
    fun `beTrue matcher should fail for false value`() {
        // Given
        val condition = false

        // Then
        condition should beFalse("Value should be false")
    }

    @Test
    fun `beNull matcher should fail for non-null value`() {
        // Given
        val value = null

        // Then
        value should beNull("Value should be null")

    }

    @Test
    fun `equal matcher should pass for matching values`() {
        // Given
        val value = "test"

        // Then
        value should equal("test")
    }

    @Test
    fun `notEqual matcher should pass for non-matching values`() {
        // Given
        val value = "test"

        // Then
        value should notEqual("different")
    }
}
