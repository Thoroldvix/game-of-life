package com.thoroldvix.gameoflife.universe

import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class DimensionsTest {

    @Test
    fun `should throw InvalidDimensionsException when trying to create a dimension with negative width`() {
        assertThatThrownBy { Dimensions(-1, 1) }
            .isInstanceOf(InvalidDimensionsException::class.java)
            .hasMessage("Width and height must be greater than 0")

    }

    @Test
    fun `should throw InvalidDimensionsException when trying to create a dimension with negative height`() {
        assertThatThrownBy { Dimensions(1, -1) }
            .isInstanceOf(InvalidDimensionsException::class.java)
            .hasMessage("Width and height must be greater than 0")

    }
}