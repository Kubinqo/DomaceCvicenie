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
        private const val PREFS_NAME = "UserData"
        private const val KEY_OBTIAZNOST = "obtiaznost"
        private lateinit var sharedPreferences: SharedPreferences

        fun getObtiaznost(): String {
            return sharedPreferences.getString(KEY_OBTIAZNOST, "") ?: ""
        }

        fun setObtiaznost(obtiaznost: String) {
            sharedPreferences.edit().putString(KEY_OBTIAZNOST, obtiaznost).apply()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_vyber_pocet_klikov)

        zaciatocnik = findViewById(R.id.zaciatocnikView)
        strednePokrocily = findViewById(R.id.strednePokrocilyView)
        pokrocily = findViewById(R.id.pokrocilyView)
        buttonHotovo = findViewById(R.id.hotovoBtn)

        // Tlacidlo je defaultne deaktivovane
        buttonHotovo.alpha = 0.5f
        buttonHotovo.isEnabled = false

        // Inicializácia SharedPreferences
        sharedPreferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE)

        // Kontrola, či bola hodnota premennej obtiaznost uložená
        val savedObtiaznost = getObtiaznost()
        if (savedObtiaznost.isNotEmpty()) {
            // Premenná obtiaznost bola uložená, môžete vykonať príslušnú logiku
            // napríklad zobraziť ďalšiu aktivitu
            val intent = Intent(this, Apka::class.java)
            startActivity(intent)
            finish()
            return
        }

        // Listener pre obrazok zaciatocnik
        zaciatocnik.setOnClickListener {
            if (!isImageZaciatocnikSelected) {
                scaleImage(zaciatocnik)
                resetImage(strednePokrocily)
                resetImage(pokrocily)
                buttonHotovo.alpha = 1f

                setObtiaznost("zaciatocnik")
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

        // Listener pre obrazok stredne pokrocily
        strednePokrocily.setOnClickListener {
            if (!isImageStrednePokrocilySelected) {
                scaleImage(strednePokrocily)
                resetImage(zaciatocnik)
                resetImage(pokrocily)
                buttonHotovo.alpha = 1f

                setObtiaznost("stredne-pokrocily")
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

        // Listener pre obrazok pokrocily
        pokrocily.setOnClickListener {
            if (!isImagePokrocilySelected) {
                scaleImage(pokrocily)
                resetImage(zaciatocnik)
                resetImage(strednePokrocily)
                buttonHotovo.alpha = 1f

                setObtiaznost("pokrocily")
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

        // Listener pre tlacidlo pre prechod na dalsiu aktivitu
        buttonHotovo.setOnClickListener() {
            val intent = Intent(this, Apka::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroy() {
        // Uloženie hodnoty premennej obtiaznost pri ukončení aktivity
        setObtiaznost(getObtiaznost())
        super.onDestroy()
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