package com.example.project_miniMart.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.Dp
import androidx.core.content.FileProvider
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.project_miniMart.BuildConfig
import com.example.project_miniMart.R
import kotlinx.coroutines.CoroutineScope
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import java.text.SimpleDateFormat
import java.time.format.DateTimeFormatter
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

private fun createImageFile(context: Context, bitmap: Bitmap): File {
    val imagesDir = File(context.cacheDir, "images").apply { mkdirs() }
    val imageFile = File(imagesDir, "s_shot.jpg")

    FileOutputStream(imageFile).use { stream ->
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream)
    }

    return imageFile
}


private fun getFileUri(context: Context, file: File): Uri? {
    return try {
        FileProvider.getUriForFile(
            context,
            "${BuildConfig.APPLICATION_ID}.provider",
            file
        )
    } catch (e: Exception) {
        Log.w("ShareError", "Error getting file URI", e)
        null
    }
}

private fun createShareIntent(contentUri: Uri): Intent {
    return Intent().apply {
        action = Intent.ACTION_SEND
        type = "image/png"
        putExtra(Intent.EXTRA_STREAM, contentUri)
        addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
    }
}

private fun startShareActivity(
    activity: Activity,
    context: Context,
    shareIntent: Intent
) {
    val chooser = Intent.createChooser(shareIntent, context.getString(R.string.action_share))
    activity.startActivity(chooser)
}


fun share(context: Context, activity: Activity, bitmapScreen: Bitmap) {
    try {
        val imageFile = createImageFile(context, bitmapScreen)
        val contentUri = getFileUri(context, imageFile)

        contentUri?.let { uri ->
            val shareIntent = createShareIntent(uri)
            startShareActivity(activity, context, shareIntent)
        }
    } catch (e: IOException) {
        Log.w("ShareError", "Error sharing image", e)
    }
}


suspend fun processBitMap(width:Dp,mainScope: CoroutineScope, currentActivity: Context, screenDensity: Density, content: @Composable () -> Unit){
    val bitmapComposer = BitmapComposer(mainScope)


    val bitmap = bitmapComposer.composableToBitmap(
        currentActivity as Activity,
        width = width,
        screenDensity = screenDensity,
        content = {
            content()
        }
    )

    share(currentActivity.baseContext, currentActivity, bitmap)
}

val USER_EMAIL = stringPreferencesKey("Email")
val USER_NAME = stringPreferencesKey("UserName")
val IS_LOGGED_IN = booleanPreferencesKey("is_logged_in")
const val USER_PREFERENCES_NAME = "InfoUser"

const val SHOPPING_ENTITY = "ShoppingEntity"
const val SALE_ENTITY = "SaleEntity"
const val DATABASE_NAME = "MiniMartDataBase"