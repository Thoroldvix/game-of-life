package com.thoroldvix.gameoflife.universe

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.IllegalArgumentException

@ResponseStatus(HttpStatus.BAD_REQUEST)
internal class CellOutOfBoundsException(message: String) : IllegalArgumentException(message)
