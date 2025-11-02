package com.example.minimartapp.utils

import kotlin.random.Random

object Util {
    fun someTime(): Long = Random.nextLong(500, 2_000)
}