package com.example.project_miniMart.datasource.local.preferences

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import com.example.project_miniMart.utils.IS_LOGGED_IN
import com.example.project_miniMart.utils.USER_EMAIL
import com.example.project_miniMart.utils.USER_NAME
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject


class DataStorePref @Inject constructor(
    private val dataStore: DataStore<Preferences>
) {

    val getEmail: Flow<String> = dataStore.data
        .map { pref ->
            pref[USER_EMAIL] ?: ""
        }

    val getUserName: Flow<String> = dataStore.data
        .map { pref ->
            pref[USER_NAME] ?: ""
        }

    val getIsLoggedIn: Flow<Boolean> = dataStore.data
        .map { pref ->
            pref[IS_LOGGED_IN] ?: false
        }

    suspend fun saveDataUser(email: String, userName: String) {
        dataStore.edit {
            it[USER_EMAIL] = email
            it[USER_NAME] = userName
            it[IS_LOGGED_IN] = true
        }
    }
}