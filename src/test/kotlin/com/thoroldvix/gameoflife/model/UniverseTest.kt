package com.thoroldvix.gameoflife.model

import com.thoroldvix.gameoflife.CellOutOfBoundsException
import com.thoroldvix.gameoflife.Dimensions
import com.thoroldvix.gameoflife.Universe
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Test

internal class UniverseTest {

    @Test
    fun `should correctly count alive neighbours for a given cell_1`() {
        val dimensions = Dimensions(3, 3)
        val universe = Universe(dimensions, setOf(0, 1, 3, 4))
        val actual = universe.countAliveNeighbors(1)

        assertThat(actual).isEqualTo(3)
    }

    @Test
    fun `should correctly count alive neighbours for a given cell_2`() {
        val dimensions = Dimensions(3, 3)
        val universe = Universe(dimensions, setOf(0, 1, 3, 4))
        val actual = universe.countAliveNeighbors(0)

        assertThat(actual).isEqualTo(3)
    }

    @Test
    fun `should correctly count alive neighbours for a given cell_3`() {
        val dimensions = Dimensions(3, 3)
        val universe = Universe(dimensions, setOf(0, 1, 3, 4))
        val actual = universe.countAliveNeighbors(4)

        assertThat(actual).isEqualTo(3)
    }

    @Test
    fun `should correctly return all neighbours for a given cell_1`() {
        val dimensions = Dimensions(3, 3)
        val universe = Universe(dimensions, setOf(0, 1, 3, 4))
        val actual = universe.getAllNeighbors(1)

        assertThat(actual).isEqualTo(listOf(0, 2, 3, 4, 5))
    }

    @Test
    fun `should correctly return all neighbours for a given cell_2`() {
        val dimensions = Dimensions(3, 3)
        val universe = Universe(dimensions, setOf(0, 1, 3, 4))
        val actual = universe.getAllNeighbors(0)

        assertThat(actual).isEqualTo(listOf(1, 3, 4))
    }

    @Test
    fun `should correctly return all neighbours for a given cell_3`() {
        val dimensions = Dimensions(3, 3)
        val universe = Universe(dimensions, setOf(0, 1, 3, 4))
        val actual = universe.getAllNeighbors(4)

        assertThat(actual).isEqualTo(listOf(0, 1, 2, 3, 5, 6, 7, 8))
    }

    @Test
    fun `should throw CellOutOfBoundsException if cell index is less than 0`() {
        val dimensions = Dimensions(3, 3)
        val universe = Universe(dimensions, setOf(0, 1, 3, 4))

        assertThatThrownBy { universe.countAliveNeighbors(-1) }
            .isInstanceOf(CellOutOfBoundsException::class.java)
            .hasMessage("Cell must be within the universe")
    }

    @Test
    fun `should throw CellOutOfBoundsException if cell index is out of universe boundaries`() {
        val dimensions = Dimensions(3, 3)
        val universe = Universe(dimensions, setOf(0, 1, 3, 4))

        assertThatThrownBy { universe.countAliveNeighbors(9) }
            .isInstanceOf(CellOutOfBoundsException::class.java)
            .hasMessage("Cell must be within the universe")
    }

    @Test
    fun `should throw CellOutOfBoundException when trying to create a universe with negative cells`() {
        val dimensions = Dimensions(1, 1)
        assertThatThrownBy { Universe(dimensions, setOf(-1)) }
            .isInstanceOf(CellOutOfBoundsException::class.java)
            .hasMessage("Cell must be within the universe")
    }

    @Test
    fun `should throw CellOutOfBoundException when trying to create a universe with cells out of universe boundaries`() {
        val dimensions = Dimensions(1, 1)
        assertThatThrownBy { Universe(dimensions, setOf(1)) }
            .isInstanceOf(CellOutOfBoundsException::class.java)
            .hasMessage("Cell must be within the universe")
    }
}