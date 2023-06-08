package com.example.domacecviceniasvlastnouvahou

import android.content.Context
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.SeekBar

class Settings : AppCompatActivity() {
    private lateinit var audioManager: AudioManager
    private lateinit var volumeSeekBar: SeekBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        volumeSeekBar = findViewById(R.id.volumeSeekBar)

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
    }
}