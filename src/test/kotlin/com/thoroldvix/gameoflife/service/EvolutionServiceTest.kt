package com.thoroldvix.gameoflife.service

import com.thoroldvix.gameoflife.Dimensions
import com.thoroldvix.gameoflife.EvolutionService
import com.thoroldvix.gameoflife.Universe
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class EvolutionServiceTest {

    private val evolutionService = EvolutionService()

    @Test
    fun `should return next generation with cells within dimension bounds_1`() {
        val dimensions = Dimensions(3, 3)
        val initialUniverse = Universe(dimensions, emptySet())

        val actual = evolutionService.evolve(initialUniverse)

        assertThat(actual).allSatisfy { cell -> assertThat(cell).isBetween(0, dimensions.width * dimensions.height - 1) }
    }

    @Test
    fun `should return next generation with cells within dimension bounds_2`() {
        val dimensions = Dimensions(20, 20)
        val initialUniverse = Universe(dimensions, emptySet())

        val actual = evolutionService.evolve(initialUniverse)

        assertThat(actual).allSatisfy { cell -> assertThat(cell).isBetween(0, dimensions.width * dimensions.height - 1) }
    }

     @Test
    fun `should return next generation with cells within dimension bounds_3`() {
        val dimensions = Dimensions(100, 100)
        val initialUniverse = Universe(dimensions, emptySet())

        val actual = evolutionService.evolve(initialUniverse)

        assertThat(actual).allSatisfy { cell -> assertThat(cell).isBetween(0, dimensions.width * dimensions.height - 1) }
    }

    @Test
    fun `should return same universe when no cells are alive`() {
        val dimensions = Dimensions(3, 3)
        val initialUniverse = Universe(dimensions, emptySet())

        val actual = evolutionService.evolve(initialUniverse)

        assertThat(actual).isEqualTo(initialUniverse.cells)
    }

    @Test
    fun `should return all cells alive when every cell has exactly 3 neighbors`() {
        val dimensions = Dimensions(3, 3)
        /*
        * Alive | Alive | Dead
        * Alive | Alive | Dead
        * Dead  | Dead  | Dead
         */
        val initialUniverse = Universe(dimensions, setOf(0, 1, 3, 4))

        /*
       * Alive | Alive | Dead
       * Alive | Alive | Dead
       * Dead  | Dead  | Dead
        */
        val actual = evolutionService.evolve(initialUniverse)

        assertThat(actual).isEqualTo(setOf(0, 1, 3, 4))
    }

    @Test
    fun `should handle edge cases correctly`() {
        val dimensions = Dimensions(3, 3)
        /*
        * Alive | Dead | Alive
        * Dead  | Dead | Dead
        * Alive | Dead | Alive
        */
        val initialUniverse = Universe(dimensions, setOf(0, 2, 6, 8))
        /*
        * Dead | Dead | Dead
        * Dead  | Dead | Dead
        * Dead | Dead | Dead
        */
        val actual = evolutionService.evolve(initialUniverse)

        assertThat(actual).isEqualTo(emptySet<Int>())
    }

    @Test
    fun `should handle diagonal cases correctly_1`() {
        val dimensions = Dimensions(3, 3)
        /*
        * Alive | Dead | Dead
        * Dead  | Alive | Dead
        * Dead  | Dead  | Alive
        */
        val initialUniverse = Universe(dimensions, setOf(0, 4, 8))
        /*
        * Dead | Dead | Dead
        * Dead  | Alive | Dead
        * Dead  | Dead  | Dead
        */
        val actual = evolutionService.evolve(initialUniverse)

        assertThat(actual).isEqualTo(setOf(4))
    }

    @Test
    fun `should handle diagonal cases correctly_2`() {
       val dimensions = Dimensions(3, 3)
        /*
       * Dead | Dead | Alive
       * Dead  | Alive | Dead
       * Alive  | Dead  | Dead
       */
        val initialUniverse = Universe(dimensions, setOf(2, 4, 6))
        /*
        * Dead | Dead | Dead
        * Dead  | Alive | Dead
        * Dead  | Dead  | Dead
        */
        val actual = evolutionService.evolve(initialUniverse)

        assertThat(actual).isEqualTo(setOf(4))
    }

    @Test
    fun `should handle alive center correctly`() {
        val dimensions = Dimensions(5, 5)
        /*
        * Dead | Dead | Dead | Dead | Dead
        * Dead | Alive | Alive | Alive | Dead
        * Dead | Alive | Alive | Alive | Dead
        * Dead | Alive | Alive | Alive | Dead
        * Dead | Dead | Dead | Dead | Dead
        */
        val initialUniverse = Universe(dimensions, setOf(6, 7, 8, 11, 12, 13, 16, 17, 18))
        /*
         * Dead | Dead | Alive | Dead | Dead
         * Dead | Alive | Dead | Alive | Dead
         * Alive | Dead | Dead | Dead | Alive
         * Dead | Alive | Dead | Alive | Dead
         * Dead | Dead | Alive | Dead | Dead
         */
        val actual = evolutionService.evolve(initialUniverse)

        assertThat(actual).isEqualTo(setOf(2, 6, 8, 10, 14, 16, 18, 22))
    }

    @Test
    fun `should handle non-square universe correctly_1`() {
        val dimensions = Dimensions(4, 3)
        /*
        * Dead | Dead | Dead | Dead
        * Dead | Alive | Alive | Dead
        * Dead | Alive | Alive | Dead
        */
        val initialUniverse = Universe(dimensions, setOf(5, 6, 9, 10))
        /*
        * Dead | Dead | Dead | Dead
        * Dead | Alive | Alive | Dead
        * Dead | Alive | Alive | Dead
        */
        val actual = evolutionService.evolve(initialUniverse)

        assertThat(actual).isEqualTo(setOf(5, 6, 9, 10))
    }

    @Test
    fun `should handle non-square universe correctly_2`() {
        val dimensions = Dimensions(3, 4)
        /*
        * Dead | Dead | Dead
        * Dead | Alive | Alive
        * Dead | Alive | Alive
        * Dead | Dead | Dead
        */
        val initialUniverse = Universe(dimensions, setOf(4, 5, 7, 8))
        /*
        * Dead | Dead | Dead
        * Dead | Alive | Alive
        * Dead | Alive | Alive
        * Dead | Dead | Dead
        */
        val actual = evolutionService.evolve(initialUniverse)

        assertThat(actual).isEqualTo(setOf(4, 5, 7, 8))
    }

    @Test
    fun `should handle big universe correctly`() {
        val dimensions = Dimensions(50, 50)
        val initialUniverse = Universe(dimensions, setOf(0, 1, 2, 3, 4, 5, 6, 7, 8))

        val actual = evolutionService.evolve(initialUniverse)

        assertThat(actual).isEqualTo(setOf(1, 2, 3, 4, 5, 6, 7, 51, 52, 53, 54, 55, 56, 57))
    }

    @Test
    fun `should handle small universe correctly_1`() {
        val dimensions = Dimensions(2, 2)
        /*
        * Alive | Alive
        * Alive | Alive
         */
        val initialUniverse = Universe(dimensions, setOf(0, 1, 2, 3))

        /*
        * Alive | Alive
        * Alive | Alive
         */
        val actual = evolutionService.evolve(initialUniverse)

        assertThat(actual).isEqualTo(setOf(0, 1, 2, 3))
    }

    @Test
    fun `should handle small universe correctly_2`() {
        val dimensions = Dimensions(1, 1)
        /*
        * Alive
         */
        val initialUniverse = Universe(dimensions, setOf(0))

        /*
        * Dead
        */
        val actual = evolutionService.evolve(initialUniverse)

        assertThat(actual).isEqualTo(emptySet<Int>())
    }

}