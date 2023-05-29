package com.example.domacecviceniasvlastnouvahou

import android.content.Context
import android.content.SharedPreferences

object AppStartManager {
    private const val PREFS_NAME = "AppStart"
    private const val KEY_VYBER_POHLAVIA_STARTED = "vyberPohlaviaStarted"
    private const val KEY_VYBER_POCET_KLIKOV_STARTED = "vyberPocetKlikovStarted"

    fun hasVyberPohlaviaStarted(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_VYBER_POHLAVIA_STARTED, false)
    }

    fun setVyberPohlaviaStarted(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(KEY_VYBER_POHLAVIA_STARTED, true)
        editor.apply()
    }

    fun hasVyberPocetKlikovStarted(context: Context): Boolean {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getBoolean(KEY_VYBER_POCET_KLIKOV_STARTED, false)
    }

    fun setVyberPocetKlikovStarted(context: Context) {
        val sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val editor: SharedPreferences.Editor = sharedPreferences.edit()
        editor.putBoolean(KEY_VYBER_POCET_KLIKOV_STARTED, true)
        editor.apply()
    }
}