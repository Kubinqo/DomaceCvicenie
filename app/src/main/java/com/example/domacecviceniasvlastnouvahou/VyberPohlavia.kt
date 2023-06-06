package com.example.domacecviceniasvlastnouvahou

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView


class VyberPohlavia : AppCompatActivity() {

    private var isImageMuzSelected = false
    private var isImageZenaSelected = false
    private lateinit var muz: ImageView
    private lateinit var zena: ImageView
    private lateinit var buttonDalej: Button

    companion object {
        private lateinit var vybranePohlavie: String
        fun getVybranePohlavie(): String {
            return vybranePohlavie
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vyber_pohlavia)

        //Nastavenie premennych pre muz, zena a tlacidlo Dalej
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

                vybranePohlavie = "muz"
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

                vybranePohlavie = "zena"
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
            val intent = Intent(this, VyberPocetKlikov::class.java)
            startActivity(intent)
        }

        // Kontrola spustenia aktivity VyberPohlavia
        if (!AppStartManager.hasVyberPohlaviaStarted(this)) {
            AppStartManager.setVyberPohlaviaStarted(this)
        } else {
            // Aktivita už bola spustená, takže môžete pokračovať s aktuálnou aktivitou
            val intent = Intent(this, VyberPocetKlikov::class.java)
            startActivity(intent)
            finish()
        }


    }

    //Funkcia, ktora zmensi velkost ImageView pomocou animacie
    //Ak nie, tlacidlo "Dalej" bude deaktivovane.
    private fun scaleImage(imageView: ImageView) {
        val animSet = AnimatorSet()
        val scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 0.8f)
        val scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 0.8f)
        animSet.playTogether(scaleX, scaleY)
        animSet.duration = 300
        animSet.start()
    }

    //Funkcia, ktora resetuje velkosť ImageView pomocou animacie
    private fun resetImage(imageView: ImageView) {
        val animSet = AnimatorSet()
        val scaleX = ObjectAnimator.ofFloat(imageView, "scaleX", 1f)
        val scaleY = ObjectAnimator.ofFloat(imageView, "scaleY", 1f)
        animSet.playTogether(scaleX, scaleY)
        animSet.duration = 300
        animSet.start()
    }

    //Funkcia kontroluje, ci je aspon jeden z obrazkov "Muz" a "Zena" zvacseny.
    private fun checkImagesScaled() {
        if (!isImageMuzSelected && !isImageZenaSelected) {
            buttonDalej.alpha = 0.5f
            buttonDalej.isEnabled = false
        }
    }
}