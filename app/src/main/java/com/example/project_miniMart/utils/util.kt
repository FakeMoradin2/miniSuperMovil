package com.example.project_miniMart.utils

import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import kotlin.random.Random

fun someTime(): Long = Random.nextLong(500, 2_000)

//const val USER_DATA_DOMAIN = "USER_DATA_DOMAIN"

fun getCurrentDateFormatted(): String {
    val date = Date()
    val formatter = SimpleDateFormat("dd MMM yyyy", Locale.US)
    return formatter.format(date)
}

val USER_EMAIL = stringPreferencesKey("Email")
val USER_NAME = stringPreferencesKey("UserName")
val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
const val USER_PREFERENCES_NAME = "InfoUser"