package com.example.project_miniMart.utils.extensions

import java.math.RoundingMode.DOWN
import java.text.DecimalFormat
import java.util.Locale

fun Double.formatMoney(): String {
    val format = DecimalFormat.getCurrencyInstance(Locale.forLanguageTag("es-MX"))
    format.minimumFractionDigits = 2
    format.roundingMode = DOWN
    return format.format(this).trim()
}