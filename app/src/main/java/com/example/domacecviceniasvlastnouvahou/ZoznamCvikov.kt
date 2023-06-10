package com.example.domacecviceniasvlastnouvahou

import Cvik
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

/**
 * Aktivita zobrazujúca zoznam cvikov. Po prejdení z aktivity "Apka"
 */
class ZoznamCvikov : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var cvikyAdapter: CvikAdapter
    private lateinit var cvikyList: List<Cvik>
    private lateinit var zacatTreningButton: Button
    private var currentCvikIndex: Int = 0
    private lateinit var settingsManager: SettingsManager
    private var casovyLimit: Int = 0

    /**
     * Metóda volaná pri vytvorení aktivity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoznam_cvikov)

        settingsManager = SettingsManager(this)
        casovyLimit = settingsManager.getCasovyLimit()

        cvikyList = getCvikyList()

        // Inicializacia RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCviky)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cvikyAdapter = CvikAdapter(cvikyList)
        recyclerView.adapter = cvikyAdapter

        zacatTreningButton = findViewById(R.id.zacatTreningButton)
        zacatTreningButton.setOnClickListener {
            spustiTrening()
            finish()
        }
    }

    /**
     * Získa zoznam cvikov pre zobrazenie v RecyclerView.
     *
     * @return zoznam cvikov
     */
    fun getCvikyList(): List<Cvik> {
        val cviky = mutableListOf<Cvik>()
        val pohlavie = VyberPohlavia.getVybranePohlavie()
        val obtiaznost = VyberPocetKlikov.getObtiaznost()

        // Vytvorenie treningoveho planu na zaklade zvoleneho pohlavia a skúsenosti a nastavenia casu pre prvy cvik
        val treningovyPlan = TreningovyPlan()
        val plan = treningovyPlan.vytvorTreningovyPlan(pohlavie, obtiaznost, casovyLimit)

        // Prida cviky z tréningoveho planu do zoznamu cvikov
        cviky.addAll(plan)

        return cviky
    }

    /**
     * Spustí tréning pre ďalší cvik.
     */
    private fun spustiTrening() {
        // Skontrolujte, ci existuje dalsí cvik
        if (currentCvikIndex < cvikyList.size) {
            val cvik = cvikyList[currentCvikIndex]

            val intent = Intent(this, Trening::class.java)
            // Prenesie informacie o cviku do aktivity Trening
            intent.putExtra("cvik", cvik)
            startActivity(intent)

            currentCvikIndex++
        } else {
            finish()
        }
    }
}
