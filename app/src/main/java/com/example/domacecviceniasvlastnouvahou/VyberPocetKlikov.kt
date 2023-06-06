package com.example.domacecviceniasvlastnouvahou

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.content.SharedPreferences
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView

class VyberPocetKlikov : AppCompatActivity() {

    private var isImageZaciatocnikSelected = false
    private var isImageStrednePokrocilySelected = false
    private var isImagePokrocilySelected = false
    private lateinit var zaciatocnik: ImageView
    private lateinit var strednePokrocily: ImageView
    private lateinit var pokrocily: ImageView
    private lateinit var buttonHotovo: Button

    companion object {
        private lateinit var obtiaznost: String
        fun getObtiaznst(): String {
            return obtiaznost
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vyber_pocet_klikov)

        zaciatocnik = findViewById(R.id.zaciatocnikView)
        strednePokrocily = findViewById(R.id.strednePokrocilyView)
        pokrocily = findViewById(R.id.pokrocilyView)
        buttonHotovo = findViewById(R.id.hotovoBtn)


        //Tlacidlo je defaultne deaktivovane
        buttonHotovo.alpha = 0.5f
        buttonHotovo.isEnabled = false

        //Listener pre obrazok zaciatocnik
        zaciatocnik.setOnClickListener {
            if (!isImageZaciatocnikSelected) {
                scaleImage(zaciatocnik)
                resetImage(strednePokrocily)
                resetImage(pokrocily)
                buttonHotovo.alpha = 1f

                obtiaznost = "zaciatocnik"
                buttonHotovo.isEnabled = true
            } else {
                resetImage(zaciatocnik)
                buttonHotovo.alpha = 0.5f
                buttonHotovo.isEnabled = false
            }
            isImageZaciatocnikSelected = !isImageZaciatocnikSelected
            isImageStrednePokrocilySelected = false
            isImagePokrocilySelected = false
            checkImagesScaled()
        }

        //Listener pre obrazok stredne pokrocily
        strednePokrocily.setOnClickListener {
            if (!isImageStrednePokrocilySelected) {
                scaleImage(strednePokrocily)
                resetImage(zaciatocnik)
                resetImage(pokrocily)
                buttonHotovo.alpha = 1f

                obtiaznost = "stredne-pokrocily"
                buttonHotovo.isEnabled = true
            } else {
                resetImage(strednePokrocily)
                buttonHotovo.alpha = 0.5f
                buttonHotovo.isEnabled = false
            }
            isImageStrednePokrocilySelected = !isImageStrednePokrocilySelected
            isImageZaciatocnikSelected = false
            isImagePokrocilySelected = false
            checkImagesScaled()
        }

        //Listener pre obrazok pokrocily
        pokrocily.setOnClickListener {
            if (!isImagePokrocilySelected) {
                scaleImage(pokrocily)
                resetImage(zaciatocnik)
                resetImage(strednePokrocily)
                buttonHotovo.alpha = 1f

                obtiaznost = "pokrocily"
                buttonHotovo.isEnabled = true
            } else {
                resetImage(pokrocily)
                buttonHotovo.alpha = 0.5f
                buttonHotovo.isEnabled = false
            }
            isImagePokrocilySelected = !isImagePokrocilySelected
            isImageZaciatocnikSelected = false
            isImageStrednePokrocilySelected = false
            checkImagesScaled()
        }

        //Listener pre tlacidlo pre prechod na dalsiu aktivitu
        buttonHotovo.setOnClickListener() {
            val intent = Intent(this, Apka::class.java)
            startActivity(intent)
        }

        // Kontrola spustenia aktivity VyberPocetKlikov
        if (!AppStartManager.hasVyberPocetKlikovStarted(this)) {
            AppStartManager.setVyberPocetKlikovStarted(this)
        } else {
            // Aktivita už bola spustená, takže môžete pokračovať s aktuálnou aktivitou
            val intent = Intent(this, Apka::class.java)
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
        if (!isImageZaciatocnikSelected && !isImageStrednePokrocilySelected && !isImagePokrocilySelected) {
            buttonHotovo.alpha = 0.5f
            buttonHotovo.isEnabled = false
        }
    }



}