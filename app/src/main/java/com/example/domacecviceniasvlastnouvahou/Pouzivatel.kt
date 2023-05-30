package com.example.domacecviceniasvlastnouvahou

class Pouzivatel(pMeno: String, pPohlavie: String, pPushUps: Int, pWorkouty: List<Cvik>) {
    private val meno: String = pMeno
    private val pohlavie: String = pPohlavie
    private val pushUps: Int = pPushUps
    private var workouty: List<Cvik> = pWorkouty
}