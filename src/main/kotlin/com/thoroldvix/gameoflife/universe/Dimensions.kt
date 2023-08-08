package com.thoroldvix.gameoflife.universe

internal data class Dimensions(val width: Int, val height: Int) {
    init {
        validateDimensions()
    }

    private fun validateDimensions() {
        if (width < 1 || height < 1)
            throw InvalidDimensionsException("Width and height must be greater than 0")
    }
}
