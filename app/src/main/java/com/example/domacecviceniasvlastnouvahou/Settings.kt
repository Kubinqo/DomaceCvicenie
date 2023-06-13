package com.example.domacecviceniasvlastnouvahou

import android.content.Context
import android.content.SharedPreferences
import android.media.AudioManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.EditText
import android.widget.ImageView
import android.widget.SeekBar

/**
 * Aktivita pre nastavenia aplikácie.
 */
class Settings : AppCompatActivity() {
    private lateinit var audioManager: AudioManager
    private lateinit var volumeSeekBar: SeekBar
    private lateinit var sharedPreferences: SharedPreferences
    private lateinit var settingsManager: SettingsManager
    private lateinit var treningImgView: ImageView
    companion object {
        private const val DEFAULT_CASOVY_LIMIT = 30
        private const val PREFS_NAME = "MyPrefs"
        private const val KEY_CASOVY_LIMIT = "casovyLimit"
    }

    /**
     * Metóda `onCreate` sa volá pri vytvorení aktivity `Settings`.
     *
     * @param savedInstanceState Uchováva stav aktivity v prípade obnovenia.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        settingsManager = SettingsManager(this)

        audioManager = getSystemService(Context.AUDIO_SERVICE) as AudioManager
        volumeSeekBar = findViewById(R.id.volumeSeekBar)
        treningImgView = findViewById((R.id.treningImgView))

        sharedPreferences = getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        val casovyLimitEditText = findViewById<EditText>(R.id.timeLimitEditText)

        // Nastavenie aktualnej hodnoty hlasitosti
        val currentVolume = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC)
        volumeSeekBar.progress = currentVolume

        // Nastavenie maximalnej hodnoty hlasitosti
        val maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC)
        volumeSeekBar.max = maxVolume

        /**
         * Nastavenie poslúchača pre zmenu hodnoty hlasitosti
         */
        volumeSeekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                if (fromUser) {
                    audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0)
                }
            }

            /**
             * Metóda sa volá, keď používateľ začne meniť hodnotu hlasitosti
             */
            override fun onStartTrackingTouch(seekBar: SeekBar?) {}

            /**
             * Metóda sa volá, keď používateľ prestane meniť hodnotu hlasitosti
             */
            override fun onStopTrackingTouch(seekBar: SeekBar?) {}
        })

        /**
         * Získa uloženú hodnotu časového limitu
         */
        val savedCasovyLimit = sharedPreferences.getInt(KEY_CASOVY_LIMIT, DEFAULT_CASOVY_LIMIT)
        casovyLimitEditText.setText(savedCasovyLimit.toString())

        /**
         * Nastavenie poslúchača pre zmenu hodnoty času
         */
        casovyLimitEditText.addTextChangedListener(object : TextWatcher {

            /**
             * Metóda sa volá pred zmenou textu v poli EditText
             */
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            /**
             * Metoda sa vola počas zmeny textu v poli EditText
             */
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

            /**
             * Táto metóda sa volá po dokončení zmeny textu v poli EditText.
             * Získava text z EditText a ukladá ho ako časový limit, ak je k dispozícii,
             * ak nie je k dispozícii žiadny text, používa predvolený časový limit.
             * Ukladá nový časový limit volaním metódy saveCasovyLimit(casovyLimit)
             */
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

        treningImgView.setOnClickListener {
            animateImageView(treningImgView)
            finish()
        }
    }

    /**
     * Spustí animáciu na zmenšenie a následne zväčšenie zadaného obrázka.
     *
     * @param imageView Obrázok, na ktorom sa má animácia spustiť
     */
    private fun animateImageView(imageView: ImageView) {
        val scaleDownAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_down)
        val scaleUpAnimation = AnimationUtils.loadAnimation(this, R.anim.scale_up)

        /**
         * Zachytenie udalosti animácie pre vrátenie obrazka do pôvodnej veľkosti po skončení animácie zväčšenia
         */
        scaleUpAnimation.setAnimationListener(object : Animation.AnimationListener {

            /**
             * Metóda sa volá na začiatku animácie.
             *
             * @param animation Animácia, ktorá sa spustila.
             */
            override fun onAnimationStart(animation: Animation) {}

            /**
             * Metóda sa volá po skončení animácie.
             *
             * @param animation Animácia, ktorá skončila.
             */
            override fun onAnimationEnd(animation: Animation) {}

            /**
             * Metóda sa volá pri opakovaní animácie.
             *
             * @param animation Animácia, ktorá sa opakuje.
             */
            override fun onAnimationRepeat(animation: Animation) {}
        })

        imageView.startAnimation(scaleDownAnimation)
        imageView.startAnimation(scaleUpAnimation)
    }

    /**
     * Uloží časový limit do správcu nastavení.
     *
     * @param casovyLimit Časový limit na uloženie
     */
    private fun saveCasovyLimit(casovyLimit: Int) {
        settingsManager.saveCasovyLimit(casovyLimit)
    }
}
