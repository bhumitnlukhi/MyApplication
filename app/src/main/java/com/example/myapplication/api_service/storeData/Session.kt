package com.example.myapplication.api_service.storeData
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class Session @Inject constructor(private val dataStore: DataStore<Preferences>) {
    companion object {
        const val DATA = "Data"
        private const val NAME = "Name"
        private const val TOKEN = "AccessToken"
        val name = stringPreferencesKey(NAME)
        val accessToken = stringPreferencesKey(TOKEN)
    }



    fun getUserName(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { preferences ->
            preferences[name] ?: ""
        }
    }

    suspend fun setUserName(userName: String) {
        dataStore.edit { preference ->
            preference[name] = userName
        }
    }

    fun getAccessToken(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map { preferences ->
            preferences[accessToken] ?: ""
        }
    }

    suspend fun setAccessToken(accessTokenUser: String) {
        dataStore.edit { preference ->
            preference[accessToken] = accessTokenUser
        }
    }


}