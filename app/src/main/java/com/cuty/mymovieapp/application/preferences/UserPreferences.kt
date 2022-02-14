package com.cuty.mymovieapp.application.preferences

import android.content.Context
import javax.inject.Inject

class UserPreferences @Inject constructor( mcontext:Context){
    val database_name="MYDB"
    val DATABASE_ACT = "ACTDB"
    val storage = mcontext.getSharedPreferences(database_name,0)

    /*** Save a boolean value, it indicates that a local db exist
     */
    fun saveDb(value:Boolean){
        storage.edit().putBoolean(DATABASE_ACT,value).apply()
    }

    /*** Return a boolean value, it indicates if exist a local database
     */
    fun getDb():Boolean{
        return storage.getBoolean(DATABASE_ACT,false)
    }


}