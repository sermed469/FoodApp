package com.sermedkerim.bitirmeprojesi

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringSetPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

class AppPref(var context:Context) {
    val Context.ds : DataStore<Preferences> by preferencesDataStore("Favoriler")

    companion object{
        val FAVOURITE_LIST = stringSetPreferencesKey("FAVOURITE_LIST")
    }

    suspend fun saveFavourite(favouriteFood:Set<String>){
        context.ds.edit {
            it[FAVOURITE_LIST] = favouriteFood
        }
    }

    suspend fun getFavourite():Set<String>?{
        val preferences = context.ds.data.first()
        return preferences[FAVOURITE_LIST]
    }

    suspend fun deleteFavourite(){
        context.ds.edit {
            it.remove(FAVOURITE_LIST)
        }
    }
}