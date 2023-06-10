package com.example.domacecviceniasvlastnouvahou

import Cvik

/**
 * Trieda slúži na vytvorenie tréningového plánu, ktorý obsahuje cviky podľa zvolených parametrov.
 */
class TreningovyPlan {

    /**
     * Metóda `vytvorTreningovyPlan` vytvára tréningový plán na základe zvoleného pohlavia, úrovne skúseností a hodnoty času.
     *
     * @param pohlavie            Pohlavie pre výber vhodných cvikov. Môže byť "muz" alebo "zena".
     * @param urovenSkusenosti    Úroveň skúseností pre výber vhodných cvikov. Môže byť "zaciatocnik", "stredne-pokrocily" alebo "pokrocily".
     * @param hodnotaCasu         Hodnota času pre nastavenie doby trvania cviku.
     * @return                    Zoznam cvikov vytvorených na základe zvolených parametrov.
     */
    fun vytvorTreningovyPlan(pohlavie: String, urovenSkusenosti: String, hodnotaCasu: Int): List<Cvik> {
        val cviky = mutableListOf<Cvik>()
        if (pohlavie == "muz") {
            when (urovenSkusenosti) {
                "zaciatocnik" -> {
                    cviky.add(Cvik(R.drawable.jumping_jacks, "Jumping Jacks", "${hodnotaCasu}s", "jumping_jacks"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x10", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x5", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x8", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x15", "squats"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x12", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x8", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x10", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x18", "squats"))
                }
                "stredne-pokrocily" -> {
                    cviky.add(Cvik(R.drawable.jumping_jacks, "Jumping Jacks", "${hodnotaCasu}s", "jumping_jacks"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x12", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x10", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x14", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x20", "squats"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x14", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x12", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x16", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x22", "squats"))
                }
                "pokrocily" -> {
                    cviky.add(Cvik(R.drawable.jumping_jacks, "Jumping Jacks", "${hodnotaCasu}s", "jumping_jacks"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x14", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x15", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x20", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x25", "squats"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x18", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x18", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x22", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x28", "squats"))
                }
            }
        } else if (pohlavie == "zena") {
            when (urovenSkusenosti) {
                "zaciatocnik" -> {
                    cviky.add(Cvik(R.drawable.jumping_jacks, "Jumping Jacks", "${hodnotaCasu}s", "jumping_jacks"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x12", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x3", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x6", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x20", "squats"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x14", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x4", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x8", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x22", "squats"))
                }
                "stredne-pokrocily" -> {
                    cviky.add(Cvik(R.drawable.jumping_jacks, "Jumping Jacks", "${hodnotaCasu}s", "jumping_jacks"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x14", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x6", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x12", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x25", "squats"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x16", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x8", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x15", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x28", "squats"))
                }
                "pokrocily" -> {
                    cviky.add(Cvik(R.drawable.jumping_jacks, "Jumping Jacks", "${hodnotaCasu}s", "jumping_jacks"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x16", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x9", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x18", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x30", "squats"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x18", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x11", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x20", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x35", "squats"))
                }
            }
        }
        return cviky
    }
}
