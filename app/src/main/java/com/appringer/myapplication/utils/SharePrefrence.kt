package com.appringer.myapplication.utils

import android.content.Context
import com.appringer.myapplication.model.NoteDO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object SharePref {

   open fun saveNotesToSharedPreferences(context: Context, notes: ArrayList<NoteDO>) {
        val sharedPreferences = context.getSharedPreferences("Notes", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        val gson = Gson()
        val notesJson = gson.toJson(notes)
        editor.putString("notes", notesJson)
        editor.apply()
    }

    fun loadNotesFromSharedPreferences(context: Context): ArrayList<NoteDO> {
        val sharedPreferences = context.getSharedPreferences("Notes", Context.MODE_PRIVATE)
        val gson = Gson()
        val notesJson = sharedPreferences.getString("notes", null)
        if (notesJson != null) {
            val notesType = object : TypeToken<ArrayList<NoteDO>>() {}.type
            return gson.fromJson(notesJson, notesType)
        }
        return ArrayList()
    }
}