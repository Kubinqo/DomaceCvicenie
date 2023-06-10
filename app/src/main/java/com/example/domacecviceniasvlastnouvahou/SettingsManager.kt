package com.example.domacecviceniasvlastnouvahou

import android.content.Context
import android.content.SharedPreferences

/**
 * Trieda slúži na správu nastavení a ukladanie hodnôt v aplikácii.
 *
 * @property context Kontext aplikácie.
 */
class SettingsManager(private val context: Context) {

    companion object {
        private const val PREFS_NAME = "MyPrefs"
        private const val KEY_CASOVY_LIMIT = "casovyLimit"
        private const val DEFAULT_CASOVY_LIMIT = 30
    }

    private val sharedPreferences: SharedPreferences by lazy {
        context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    /**
     * Vráti hodnotu aktuálneho časového limitu.
     *
     * @return Aktuálny časový limit.
     */
    fun getCasovyLimit(): Int {
        return sharedPreferences.getInt(KEY_CASOVY_LIMIT, DEFAULT_CASOVY_LIMIT)
    }

    /**
     * Metóda uloží hodnotu časového limitu.
     *
     * @param casovyLimit Nová hodnota časového limitu.
     */
    fun saveCasovyLimit(casovyLimit: Int) {
        val editor = sharedPreferences.edit()
        editor.putInt(KEY_CASOVY_LIMIT, casovyLimit)
        editor.apply()
    }
}