package com.thoroldvix.gameoflife.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.thoroldvix.gameoflife.UniverseController
import com.thoroldvix.gameoflife.Dimensions
import com.thoroldvix.gameoflife.Universe
import com.thoroldvix.gameoflife.EvolutionService
import com.thoroldvix.gameoflife.GodService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@WebMvcTest(controllers = [UniverseController::class])
internal class UniverseControllerTest {

    private val baseUrl = "http://localhost:8080/api/v1/universe"

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var godService: GodService

    @MockBean
    private lateinit var evolutionService: EvolutionService

    @Autowired
    private lateinit var mapper: ObjectMapper

    @Test
    fun `should return universe with random state`() {
        val dimensions = Dimensions(20, 20)

        val expected = setOf(1, 2, 3)

        `when`(godService.createRandomUniverse(dimensions)).thenReturn(expected)

        with(mockMvc) {
            perform(
                post("$baseUrl/random").apply {
                    contentType("application/json")
                    content(mapper.writeValueAsString(dimensions))
                }).apply {
                andExpect(status().isOk)
                andExpect(content().json(mapper.writeValueAsString(expected)))
            }
        }
    }

    @Test
    fun `should return next generation for the given universe`() {
        val aliveCells = setOf(1, 2, 3)
        val dimensions = Dimensions(20, 20)
        val request = Universe(dimensions, aliveCells)
        val expected = setOf(4, 5, 6)
        `when`(evolutionService.evolve(request)).thenReturn(expected)


        with(mockMvc) {
            perform(
                post("$baseUrl/next").apply {
                    contentType("application/json")
                    content(mapper.writeValueAsString(request))
                }).apply {
                andExpect(status().isOk)
                andExpect(content().json(mapper.writeValueAsString(expected)))
            }
        }
    }

    @Test
    fun `should return bad request when trying to get random universe for width less than 1`() {
        val request = """
            {
                "width": 0,
                "height": 2
            }
        """.trimIndent()
        with(mockMvc) {
            perform(
                post("$baseUrl/random").apply {
                    contentType("application/json")
                    content(request)
                }).apply {
                andExpect(status().isBadRequest)
            }
        }
    }

    @Test
    fun `should return bad request when trying to get random universe for height less than 1`() {
        val request = """
            {
                "width": 2,
                "height": 0
            }
        """.trimIndent()
        with(mockMvc) {
            perform(
                post("$baseUrl/random").apply {
                    contentType("application/json")
                    content(request)
                }).apply {
                andExpect(status().isBadRequest)
            }
        }
    }

    @Test
    fun `should return bad request when trying to get next generation for width less than 1`() {
        val request = """
            {
                "dimensions": {
                    "width": 0,
                    "height": 2
                },
                "cells": [1, 2, 3]
            }
        """.trimIndent()
        with(mockMvc) {
            perform(
                post("$baseUrl/next").apply {
                    contentType("application/json")
                    content(request)
                }).apply {
                andExpect(status().isBadRequest)
            }
        }
    }

    @Test
    fun `should return bad request when trying to get next generation for height less than 1`() {
        val request = """
            {
                "dimensions": {
                    "width": 2,
                    "height": 0
                },
                "cells": [1, 2, 3]
            }
        """.trimIndent()
        with(mockMvc) {
            perform(
                post("$baseUrl/next").apply {
                    contentType("application/json")
                    content(request)
                }).apply {
                andExpect(status().isBadRequest)
            }
        }
    }

    @Test
    fun `should return bad request when trying to get next generation for negative cells`() {
        val request = """
            {
                "dimensions": {
                    "width": 2,
                    "height": 2
                },
                "cells": [-1, 2, 3]
            }
        """.trimIndent()
        with(mockMvc) {
            perform(
                post("$baseUrl/next").apply {
                    contentType("application/json")
                    content(request)
                }).apply {
                andExpect(status().isBadRequest)
            }
        }
    }

    @Test
    fun `should return bad request when trying to get next generation for cells out of universe boundaries`() {
        val request = """
            {
                "dimensions": {
                    "width": 2,
                    "height": 2
                },
                "cells": [1, 2, 3, 4]
            }
        """.trimIndent()
        with(mockMvc) {
            perform(
                post("$baseUrl/next").apply {
                    contentType("application/json")
                    content(request)
                }).apply {
                andExpect(status().isBadRequest)
            }
        }
    }
}
