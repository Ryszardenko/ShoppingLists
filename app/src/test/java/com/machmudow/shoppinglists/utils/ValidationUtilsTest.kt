package com.machmudow.shoppinglists.utils

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidationUtilsTest {

    @Test
    fun `blank title returns false`() {
        val result = ValidationUtils.validateTitle(" ")
        assertThat(result).isFalse()
    }

    @Test
    fun `blank quantity returns false`() {
        val result = ValidationUtils.validateQuantity("")
        assertThat(result).isFalse()
    }

    @Test
    fun `quantity less than 1 returns false`() {
        val result = ValidationUtils.validateQuantity("-5")
        assertThat(result).isFalse()
    }

    @Test
    fun `quantity more than 1 returns true`() {
        val result = ValidationUtils.validateQuantity("5")
        assertThat(result).isTrue()
    }
}