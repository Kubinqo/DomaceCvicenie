package com.example.domacecviceniasvlastnouvahou

import android.content.Context
import android.content.SharedPreferences
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.SeekBar

class Settings : AppCompatActivity() {
    private lateinit var audioManager: AudioManager
    private lateinit var volumeSeekBar: SeekBar
    //private lateinit var casovyLimitEditText: EditText
    private lateinit var sharedPreferences: SharedPreferences

    private lateinit var settingsManager: SettingsManager

    companion object {
        private const val DEFAULT_CASOVY_LIMIT = 30
        private const val PREFS_NAME = "MyPrefs"
        private const val KEY_CASOVY_LIMIT = "casovyLimit"
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        settingsManager = SettingsManager(this)

        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        volumeSeekBar = findViewById(R.id.volumeSeekBar)
        //casovyLimitEditText = findViewById(R.id.casovyLimitEditText)

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val casovyLimitEditText = findViewById<EditText>(R.id.casovyLimitEditText)

        // Nastavenie aktuálnej hodnoty hlasitosti
        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        volumeSeekBar.progress = currentVolume

        // Nastavenie maximálnej hodnoty hlasitosti
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        volumeSeekBar.max = maxVolume

        // Nastavenie poslucháča pre zmenu hodnoty hlasitosti
        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
                }
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Metóda sa volá, keď používateľ začne meniť hodnotu hlasitosti
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Metóda sa volá, keď používateľ skončí meniť hodnotu hlasitosti
            }
        })


        // Získajte uloženú hodnotu časového limitu
        val savedCasovyLimit = sharedPreferences.getInt(KEY_CASOVY_LIMIT, DEFAULT_CASOVY_LIMIT)
        casovyLimitEditText.setText(savedCasovyLimit.toString())

        casovyLimitEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            override fun afterTextChanged(s: Editable?) {
                val casovyLimitText = casovyLimitEditText.text.toString()
                val casovyLimit = if (casovyLimitText.isNotEmpty()) {
                    casovyLimitText.toInt()
                } else {
                    DEFAULT_CASOVY_LIMIT
                }
                saveCasovyLimit(casovyLimit)
            }
        })

    }
    private fun saveCasovyLimit(casovyLimit: Int) {
        settingsManager.saveCasovyLimit(casovyLimit)
    }

}
