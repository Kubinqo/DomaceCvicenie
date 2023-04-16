package com.example.domacecviceniasvlastnouvahou

class Pouzivatel(pMeno: String, pPohlavie: String, pPushUps: Int, pWorkouty: List<Workout>) {
    private val meno: String = pMeno
    private val pohlavie: String = pPohlavie
    private val pushUps: Int = pPushUps
    private var workouty: List<Workout> = pWorkouty
}