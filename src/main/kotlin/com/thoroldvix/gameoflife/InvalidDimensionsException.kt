package com.thoroldvix.gameoflife


import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus
import java.lang.IllegalArgumentException

@ResponseStatus(HttpStatus.BAD_REQUEST)
internal class InvalidDimensionsException(message: String) : IllegalArgumentException(message)


