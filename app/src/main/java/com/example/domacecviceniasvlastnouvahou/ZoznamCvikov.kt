package com.example.domacecviceniasvlastnouvahou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ZoznamCvikov : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var cvikyAdapter: CvikAdapter
    private lateinit var cvikyList: List<Cvik>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_zoznam_cvikov)

        // Inicializácia zoznamu cvikov
        cvikyList = generateCvikyList()

        // Inicializácia RecyclerView
        recyclerView = findViewById(R.id.recyclerViewCviky)
        recyclerView.layoutManager = LinearLayoutManager(this)
        cvikyAdapter = CvikAdapter(cvikyList)
        recyclerView.adapter = cvikyAdapter
    }

    private fun generateCvikyList(): List<Cvik> {
        // vygenerujte a vráťte zoznam cvikov
        val cviky = mutableListOf<Cvik>()
        cviky.add(Cvik(R.drawable.jumping_jacks, "Jumping Jacks", "warm up"))
        cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x14"))
        cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x16"))
        cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x12"))
        cviky.add(Cvik(R.drawable.squat, "Squats", "x20"))
        return cviky
    }
}
