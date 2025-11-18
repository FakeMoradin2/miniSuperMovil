package com.example.project_miniMart.utils

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

/**
 * Crea un [NavType] personalizado para tipos [Parcelable] que se pueden pasar como argumentos de navegación.
 *
 * Este NavType se encarga de serializar y deserializar el objeto Parcelable a y desde una cadena JSON,
 * lo que permite pasarlo como parte de una ruta de navegación.
 *
 * @return Un [NavType] para el tipo [Parcelable] especificado.
 */
inline fun <reified T : Parcelable> createNavType(): NavType<T> {
    return object : NavType<T>(isNullableAllowed = false) {
        /**
         * Obtiene el valor del [Bundle].
         */
        override fun get(bundle: Bundle, key: String): T? {
            return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                bundle.getParcelable(key, T::class.java)
            } else {
                @Suppress("DEPRECATION")
                bundle.getParcelable(key)
            }
        }

        /**
         * Parsea el valor desde una cadena (JSON).
         */
        override fun parseValue(value: String): T {
            return Json.decodeFromString<T>(Uri.decode(value))
        }

        /**
         * Serializa el valor a una cadena (JSON) y la codifica para URI.
         */
        override fun serializeAsValue(value: T): String {
            return Uri.encode(Json.encodeToString(value))
        }

        /**
         * Pone el valor en el [Bundle].
         */
        override fun put(bundle: Bundle, key: String, value: T) {
            bundle.putParcelable(key, value)
        }
    }
}