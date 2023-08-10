package com.thoroldvix.gameoflife.universe

import com.thoroldvix.gameoflife.universe.GodService
import com.thoroldvix.gameoflife.universe.Dimensions
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class GodServiceTest {

    private val godService = GodService()

    @Test
    fun `should return cells within dimension bounds_1`() {
        val dimensions = Dimensions(3, 3)
        val totalCellAmount = dimensions.width * dimensions.height
        val actual = godService.createRandomUniverse(dimensions)


        assertThat(actual).allSatisfy { cell -> assertThat(cell).isBetween(0, totalCellAmount - 1) }
    }

    @Test
    fun `should return cells within dimension bounds_2`() {
        val dimensions = Dimensions(20, 20)
        val totalCellAmount = dimensions.width * dimensions.height
        val actual = godService.createRandomUniverse(dimensions)


        assertThat(actual).allSatisfy { cell -> assertThat(cell).isBetween(0, totalCellAmount - 1) }
    }

    @Test
    fun `should return cells within dimension bounds_3`() {
        val dimensions = Dimensions(100, 100)
        val totalCellAmount = dimensions.width * dimensions.height
        val actual = godService.createRandomUniverse(dimensions)


        assertThat(actual).allSatisfy { cell -> assertThat(cell).isBetween(0, totalCellAmount - 1) }
    }

    @Test
    fun `should return random universe with random percent of alive cells`() {
        val dimensions = Dimensions(3, 3)
        val totalCellAmount = dimensions.width * dimensions.height
        val lowerBoundCellAmount = totalCellAmount * 0.01
        val upperBoundCellAmount = totalCellAmount * 0.5

        val actual = godService.createRandomUniverse(dimensions)


        assertThat(actual.size).isGreaterThanOrEqualTo(lowerBoundCellAmount.toInt())
            .isLessThanOrEqualTo(upperBoundCellAmount.toInt())
    }

}