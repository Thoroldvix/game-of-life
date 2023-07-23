package com.thoroldvix.gameoflife.universe

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
internal class UniverseController(@Autowired private val evolutionService: EvolutionService,
                                  private val godService: GodService) {


    @MessageMapping("/random")
    @SendTo("/topic/universe")
    fun createRandomUniverse(@Payload dimensions: Dimensions): Set<Int> {
        return godService.createRandomUniverse(dimensions)
    }


    @MessageMapping("/next")
    @SendTo("/topic/universe")
    fun nextGeneration(@Payload universe: Universe): Set<Int> {
        return evolutionService.evolve(universe)
    }
}