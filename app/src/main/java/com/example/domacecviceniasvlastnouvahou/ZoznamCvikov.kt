package com.example.domacecviceniasvlastnouvahou

import Cvik
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ZoznamCvikov : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cvikyAdapter: CvikAdapter
    private lateinit var cvikyList: List<Cvik>
    private lateinit var zacatTreningButton: Button
    private var currentCvikIndex: Int = 0

    private lateinit var settingsManager: SettingsManager
    private var casovyLimit: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoznam_cvikov)

        settingsManager = SettingsManager(this)
        casovyLimit = settingsManager.getCasovyLimit()

        // Inicializácia zoznamu cvikov
        cvikyList = getCvikyList()

        // Inicializácia RecyclerView
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

    // Verejná metóda pre získanie zoznamu cvikov
    fun getCvikyList(): List<Cvik> {
        // Vytvoríte nový zoznam cvikov
        val cviky = mutableListOf<Cvik>()

        // Použitie vybraného pohlavia a obtiaznosti
        val pohlavie = VyberPohlavia.getVybranePohlavie()
        val obtiaznost = VyberPocetKlikov.getObtiaznost()

        // Vytvoríte tréningový plán na základe zvoleného pohlavia a skúseností
        val treningovyPlan = TreningovyPlan()
        val plan = treningovyPlan.vytvorTreningovyPlan(pohlavie, obtiaznost, casovyLimit)

        // Pridáte cviky z tréningového plánu do zoznamu cvikov
        cviky.addAll(plan)

        return cviky
    }

    private fun spustiTrening() {
        // Skontrolujte, či existuje ďalší cvik
        if (currentCvikIndex < cvikyList.size) {
            val cvik = cvikyList[currentCvikIndex]

            val intent = Intent(this, Trening::class.java)
            // Preneste informácie o cviku do aktivity TreningActivity
            intent.putExtra("cvik", cvik)
            startActivity(intent)

            // Inkrementujte index aktuálneho cviku
            currentCvikIndex++
        } else {
            finish()
        }
    }
}
