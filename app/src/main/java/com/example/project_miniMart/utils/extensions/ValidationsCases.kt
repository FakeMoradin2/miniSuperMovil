package com.example.project_miniMart.utils.extensions

import com.example.project_miniMart.utils.extensions.Patterns.regexSpecialCharacter
import com.example.project_miniMart.utils.extensions.Patterns.regexUppercase

private fun validateRegex(textValue: String, regex: String): Boolean =
    textValue.matches(regex.toRegex())

fun String.validEmail(): Boolean {
    val emailRegex = Regex("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")
    return emailRegex.matches(this.trim())
}

fun String.validateLength(minValue: Int): Boolean {
    return this.length >= minValue
}

fun String.validateUppercase(): Boolean {
    return validateRegex(this, regexUppercase)
}

fun String.validateSpecialCharacter(): Boolean {
    return validateRegex(this, regexSpecialCharacter)
}

fun String.validateThreeNumbersInSequence(): Boolean {
    this.forEachIndexed { index, _ ->
        when (index) {
            in 1..this.length - 2 -> {
                if (this[index - 1].isDigit() && this[index].isDigit() && this[index + 1].isDigit()) {
                    if ((this[index].code - this[index - 1].code == 1) && (this[index + 1].code - this[index].code == 1)) {
                        return false
                    }
                }
            }
        }
    }
    return this.isNotEmpty() && this.matches(".*[0-9].*".toRegex())
}

fun String.validatePhone(): Boolean {
    return  this.matches("^\\+?\\d{10,12}\$".toRegex())
}