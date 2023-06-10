package com.example.domacecviceniasvlastnouvahou

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

/**
 * Aktivita slúžiaca na výber pohlavia tréningového plánu.
 * Umožňuje používateľovi vybrať jednu z dvoch možných obtiažností:
 * muž alebo žena.
 * Po výbere obtiažnosti je možné prejsť na ďalšiu aktivitu pomocou tlačidla "Ďalej".
 */
class VyberPohlavia : AppCompatActivity() {

    private var isImageMuzSelected = false
    private var isImageZenaSelected = false
    private lateinit var muz: ImageView
    private lateinit var zena: ImageView
    private lateinit var buttonDalej: Button

    companion object {
        private const val PREFS_NAME = "UserData"
        private const val KEY_VYBRANE_POHLAVIE = "vybrane_pohlavie"
        private lateinit var sharedPreferences: SharedPreferences

        /**
         * Vráti vybrané pohlavie používateľa.
         *
         * @return vybrané pohlavie
         */
        fun getVybranePohlavie(): String {
            return sharedPreferences.getString(KEY_VYBRANE_POHLAVIE, "") ?: ""
        }

        /**
         * Nastaví vybrané pohlavie používateľa.
         *
         * @param pohlavie vybrané pohlavie
         */
        fun setVybranePohlavie(pohlavie: String) {
            sharedPreferences.edit().putString(KEY_VYBRANE_POHLAVIE, pohlavie).apply()
        }
    }

    /**
     * Metóda sa volá pri vytvorení aktivity.
     * Inicializuje sa UI, pridávajú sa listenery pre kliknutia a kontroluje sa uložená hodnota premennej pohlavie.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vyber_pohlavia)

        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        // Kontrola, ci bola hodnota vybraneho pohlavia ulozena
        val vybranePohlavie = getVybranePohlavie()
        if (vybranePohlavie.isNotEmpty()) {
            spustiDalsiuAktivitu()
            return
        }

        muz = findViewById(R.id.pohlavieMuzView)
        zena = findViewById(R.id.pohlavieZenaView)
        buttonDalej = findViewById(R.id.dalejBtn)

        //Tlacidlo je defaultne deaktivovane
        buttonDalej.alpha = 0.5f
        buttonDalej.isEnabled = false

        //Listener pre obrazok muza
        muz.setOnClickListener {
            if (!isImageMuzSelected) {
                //Ak je obrazok muza zvacseny, zmensi sa a tlacidlo sa aktivuje, inak sa zvacsi a obrazok zeny sa zmensi
                scaleImage(muz)
                resetImage(zena)
                buttonDalej.alpha = 1f

                setVybranePohlavie("muz")
                buttonDalej.isEnabled = true
            } else {
                resetImage(muz)
                buttonDalej.alpha = 0.5f
                buttonDalej.isEnabled = false
            }
            //Zmena hodnot pre kontrolu zvacsenia obrazkov
            isImageMuzSelected = !isImageMuzSelected
            isImageZenaSelected = false
            checkImagesScaled()
        }

        //Listener pre obrazok zeny
        zena.setOnClickListener {
            if (!isImageZenaSelected) {
                //Ak je obrazok zeny zvacseny, zmensi sa a tlacidlo sa aktivuje, inak sa zvacsi a obrazok muza sa zmensi
                scaleImage(zena)
                resetImage(muz)
                buttonDalej.alpha = 1f

                setVybranePohlavie("zena")
                buttonDalej.isEnabled = true
            } else {
                resetImage(zena)
                buttonDalej.alpha = 0.5f
                buttonDalej.isEnabled = false
            }
            //Zmena hodnot pre kontrolu zvacsenia obrazkov
            isImageZenaSelected = !isImageZenaSelected
            isImageMuzSelected = false
            checkImagesScaled()
        }

        //Listener pre tlacidlo pre prechod na dalsiu aktivitu
        buttonDalej.setOnClickListener {
            spustiDalsiuAktivitu()
        }
    }

    /**
     * Spustí ďalšiu aktivitu.
     */
    private fun spustiDalsiuAktivitu() {
        val intent = Intent(this, VyberPocetKlikov::class.java)
        startActivity(intent)
        finish()
    }

    /**
     * Funkcia, ktorá zmenší veľkosť ImageView pomocou animácie.
     *
     * @param imageView ImageView, ktorého veľkosť sa zmení
     */
    private fun scaleImage(imageView: ImageView) {
        val animSet = AnimatorSet()
        val scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 0.8f)
        val scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 0.8f)
        animSet.playTogether(scaleX, scaleY)
        animSet.duration = 300
        animSet.start()
    }

    /**
     * Funkcia, ktorá obnoví veľkosť ImageView pomocou animácie.
     *
     * @param imageView ImageView, ktorého veľkosť sa obnoví
     */
    private fun resetImage(imageView: ImageView) {
        val animSet = AnimatorSet()
        val scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 1f)
        val scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 1f)
        animSet.playTogether(scaleX, scaleY)
        animSet.duration = 300
        animSet.start()
    }

    /**
     * Funkcia kontroluje, či je aspoň jeden z obrazkov "Muž" a "Žena" je zväčšený.
     * Ak nie, tlačidlo "Ďalej" bude deaktivované.
     */
    private fun checkImagesScaled() {
        if (!isImageMuzSelected && !isImageZenaSelected) {
            buttonDalej.alpha = 0.5f
            buttonDalej.isEnabled = false
        }
    }
}