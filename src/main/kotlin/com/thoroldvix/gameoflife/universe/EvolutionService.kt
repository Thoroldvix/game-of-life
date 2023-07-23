package com.thoroldvix.gameoflife.universe

import org.springframework.stereotype.Service

@Service
internal class EvolutionService {

    fun evolve(currentUniverse: Universe): Set<Int> {
        return currentUniverse.run {
            val nextGenerationCells = activeCells(this)
            val cellCandidatesForNextGeneration = inactiveCellCandidates(this)
            nextGenerationCells + cellCandidatesForNextGeneration
        }
    }

    private fun Universe.activeCells(universe: Universe): Set<Int> {
        return cells.filter { universe.countAliveNeighbors(it) in 2..3 }.toSet()
    }

    private fun Universe.inactiveCellCandidates(universe: Universe): Set<Int> {
        return cells.flatMap { universe.getAllNeighbors(it) }
            .subtract(cells)
            .filter { universe.countAliveNeighbors(it) == 3 }
            .toSet()
    }
}