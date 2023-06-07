package com.example.domacecviceniasvlastnouvahou

import Cvik

class TreningovyPlan {
    var spaleneKcal = 0
    fun vytvorTreningovyPlan(pohlavie: String, urovenSkusenosti: String): List<Cvik> {
        val cviky = mutableListOf<Cvik>()

        if (pohlavie == "muz") {
            when (urovenSkusenosti) {
                "zaciatocnik" -> {
                    spaleneKcal = 37
                    cviky.add(Cvik(R.drawable.jumping_jacks, "Jumping Jacks", "00:30", "jumping_jacks"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x10", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x5", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x8", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x15", "squats"))
                }
                "stredne-pokrocily" -> {
                    spaleneKcal = 46
                    cviky.add(Cvik(R.drawable.jumping_jacks, "Jumping Jacks", "00:30", "jumping_jacks"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x12", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x10", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x14", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x20", "squats"))
                }
                "pokrocily" -> {
                    spaleneKcal = 54
                    cviky.add(Cvik(R.drawable.jumping_jacks, "Jumping Jacks", "00:30", "jumping_jacks"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x14", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x15", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x20", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x25", "squats"))
                }
            }
        } else if (pohlavie == "zena") {
            when (urovenSkusenosti) {
                "zaciatocnik" -> {
                    spaleneKcal = 38
                    cviky.add(Cvik(R.drawable.jumping_jacks, "Jumping Jacks", "00:30", "jumping_jacks"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x12", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x3", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x12", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x20", "squats"))
                }
                "stredne-pokrocily" -> {
                    spaleneKcal = 47
                    cviky.add(Cvik(R.drawable.jumping_jacks, "Jumping Jacks", "00:30", "jumping_jacks"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x14", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x6", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x12", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x25", "squats"))
                }
                "pokrocily" -> {
                    spaleneKcal = 56
                    cviky.add(Cvik(R.drawable.jumping_jacks, "Jumping Jacks", "00:30", "jumping_jacks"))
                    cviky.add(Cvik(R.drawable.mountain_climber, "Mountain climber", "x16", "mountain_climber"))
                    cviky.add(Cvik(R.drawable.push_ups, "Push-ups", "x9", "push_ups"))
                    cviky.add(Cvik(R.drawable.triceps_dips, "Triceps dips", "x18", "triceps_dips"))
                    cviky.add(Cvik(R.drawable.squat, "Squats", "x30", "squats"))
                }
            }
        }

        return cviky
    }
}
