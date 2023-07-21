package com.thoroldvix.gameoflife

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class GameOfLifeApplication

fun main(args: Array<String>) {
    runApplication<GameOfLifeApplication>(*args)
}
