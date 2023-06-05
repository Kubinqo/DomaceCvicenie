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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoznam_cvikov)

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
        }
    }

    // Verejná metóda pre získanie zoznamu cvikov
    fun getCvikyList(): List<Cvik> {
        // Vygenerujte a vráťte zoznam cvikov
        val cviky = mutableListOf<Cvik>()
        cviky.add(Cvik(R.drawable.jumping_jacks, "Jumping Jacks", "warm up", "jumping_jacks"))
        cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x14", "mountain_climbers"))
        cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x16", "push_ups"))
        cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x12", "triceps_dips"))
        cviky.add(Cvik(R.drawable.squat, "Squats", "x20", "squats"))
        return cviky
    }

    private fun spustiTrening() {
        // Skontrolujte, či existuje ďalší cvik
        if (currentCvikIndex < cvikyList.size) {
            val cvik = cvikyList[currentCvikIndex]

            // Vytvorte intent pre aktivitu TreningActivity
            val intent = Intent(this, Trening::class.java)
            // Preneste informácie o cviku do aktivity TreningActivity
            intent.putExtra("cvik", cvik)

            // Spustite aktivitu TreningActivity
            startActivity(intent)

            // Inkrementujte index aktuálneho cviku
            currentCvikIndex++
        }
    }
}
