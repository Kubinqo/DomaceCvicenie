package com.example.domacecviceniasvlastnouvahou

import Cvik
import android.content.Intent
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView

class Trening : AppCompatActivity() {

    private lateinit var nazovCvikuTextView: TextView
    private lateinit var popisCvikuTextView: TextView
    private lateinit var hotovoButton: Button
    private lateinit var videoView: VideoView
    private lateinit var mediaController: MediaController
    private lateinit var cvikyList: List<Cvik>
    private var currentIndex: Int = 0
    private var casovac: CountDownTimer? = null
    private var startTime: Long = 0
    private var endTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_trening)

        nazovCvikuTextView = findViewById(R.id.nazovCvikuTextViewT)
        popisCvikuTextView = findViewById(R.id.popisCvikuTextViewT)
        hotovoButton = findViewById(R.id.hotovoButton)
        videoView = findViewById(R.id.videoView)

        mediaController = MediaController(this)
        videoView.setMediaController(mediaController)

        val zoznamCvikov = ZoznamCvikov()
        cvikyList = zoznamCvikov.getCvikyList()

        zacniTrening()

        hotovoButton.setOnClickListener {
            ukonciCvik()
        }
    }

    private fun zacniTrening() {
        // Získajte aktuálny čas pri začiatku tréningu
        startTime = System.currentTimeMillis()
        val mediaPlayer = MediaPlayer.create(this, R.raw.pisk)
        mediaPlayer.start()
        zobrazCvik()
    }

    private fun zobrazCvik() {
        if (currentIndex < cvikyList.size) {
            val cvik = cvikyList[currentIndex]
            nazovCvikuTextView.text = cvik.nazov

            val videoResource = resources.getIdentifier(cvik.videoName, "raw", packageName)
            val videoPath = "android.resource://$packageName/$videoResource"

            mediaController = MediaController(this)
            mediaController.setMediaPlayer(videoView)
            videoView.setMediaController(mediaController)

            videoView.setVideoPath(videoPath)
            videoView.setOnPreparedListener { mediaPlayer ->
                mediaPlayer.isLooping = true
                mediaPlayer.start()

                if (currentIndex == 0) {
                    casovac = object : CountDownTimer(30000, 1000) {
                        override fun onTick(millisUntilFinished: Long) {
                            val seconds = millisUntilFinished / 1000
                            popisCvikuTextView.text = seconds.toString()
                        }

                        override fun onFinish() {
                            videoView.stopPlayback()
                            zobrazDalsiCvik()
                        }
                    }.start()
                } else {
                    popisCvikuTextView.text = cvik.popis
                }
            }
        } else {
            videoView.stopPlayback()
            ukonciTrening()
        }
    }

    private fun zobrazDalsiCvik() {
        currentIndex++
        val mediaPlayer = MediaPlayer.create(this, R.raw.pisk)
        mediaPlayer.start()
        zobrazCvik()
    }

    private fun ukonciCvik() {
        casovac?.cancel()
        zobrazDalsiCvik()
    }

    private fun ukonciTrening() {
        // Získajte aktuálny čas pri ukončení tréningu
        endTime = System.currentTimeMillis()

        // Vypočítajte čas trvania tréningu
        val casTreningu = endTime - startTime

        // Prevezmite výsledný čas späť do aktivity Apka
        val intent = Intent(this, Apka::class.java)
        intent.putExtra("casTreningu", casTreningu)
        startActivity(intent)

        // Ukončite aktivitu Trening
        finish()
    }
}
