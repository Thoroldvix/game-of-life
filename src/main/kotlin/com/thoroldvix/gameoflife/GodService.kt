package com.thoroldvix.gameoflife

import org.springframework.stereotype.Service
import kotlin.random.Random

@Service
internal class GodService {

    fun createRandomUniverse(dimensions: Dimensions): Set<Int> {
        return generateRandomCells(dimensions)
    }

    private fun generateRandomCells(dimensions: Dimensions): Set<Int> {
        with(dimensions) {
            val totalCells = width * height
            val aliveCellPercentage = Random.nextDouble(0.01, 0.5)
            val numAliveCells = (totalCells * aliveCellPercentage).toInt()

            return (0 until totalCells).shuffled().take(numAliveCells).toSet()
        }
    }
}