package com.thoroldvix.gameoflife

internal data class Universe(val dimensions: Dimensions, val cells: Set<Int>) {
    init {
        validateCells()
    }

    private fun validateCells() {
        cells.forEach { validateCell(it) }
    }

    private fun validateCell(cell: Int) {
        with(dimensions) {
            if (cell !in 0 until width * height)
                throw CellOutOfBoundsException("Cell must be within the universe")
        }
    }

    fun countAliveNeighbors(cell: Int): Int {
        validateCell(cell)
        return getAllNeighbors(cell).intersect(cells).size
    }

    fun getAllNeighbors(cell: Int): List<Int> {
        validateCell(cell)
        return cell.toPosition().neighbourPositions()
            .filter { it.inBounds() }
            .map { it.toCellNumber() }
    }

    private fun Int.toPosition(): Pair<Int, Int> =
        dimensions.let {
            this / it.width to this % it.width
        }

    private fun Pair<Int, Int>.neighbourPositions(): List<Pair<Int, Int>> =
        (first - 1..first + 1).asSequence()
            .flatMap { i -> (second - 1..second + 1).map { j -> i to j }.asSequence() }
            .filterNot { it == this }
            .toList()

    private fun Pair<Int, Int>.inBounds(): Boolean =
        dimensions.let {
            first in 0 until it.height && second in 0 until it.width
        }

    private fun Pair<Int, Int>.toCellNumber(): Int =
        dimensions.let {
            first * it.width + second
        }

}


