package com.thoroldvix.gameoflife

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("api/v1/universe")
internal class UniverseController(@Autowired private val evolutionService: EvolutionService,
                                  private val godService: GodService) {

    @CrossOrigin
    @PostMapping("/random")
    fun createRandomUniverse(@RequestBody dimensions: Dimensions): ResponseEntity<Set<Int>> =
        ResponseEntity.ok(godService.createRandomUniverse(dimensions))

    @CrossOrigin
    @PostMapping("/next")
    fun nextGeneration(@RequestBody universe: Universe): ResponseEntity<Set<Int>> =
        ResponseEntity.ok(evolutionService.evolve(universe))
}