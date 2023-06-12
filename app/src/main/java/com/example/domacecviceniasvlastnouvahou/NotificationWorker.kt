package com.example.domacecviceniasvlastnouvahou

import android.Manifest
import android.app.*
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.app.*
import androidx.work.*
import kotlin.random.Random


class NotificationWorker(val context: Context, params: WorkerParameters) : Worker(context, params) {
    override fun doWork(): Result {
        // Vytvorte notifikáciu s odkazom na stránku obchodu s aplikáciami
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("https://play.google.com/store/apps/details?id=homeworkout.homeworkouts.noequipment&hl=en_US")
        val pendingIntent = PendingIntent.getActivity(context, 0, intent, 0)

        val notification = NotificationCompat.Builder(context, "default").apply {
            setContentTitle("Ako sa vám páči naša aplikácia?")
                .setContentText("Ohodnoťte ju prosím na Play Store.")
                .setSmallIcon(R.drawable.triceps_dips)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
        }.build()

        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.POST_NOTIFICATIONS) == PackageManager.PERMISSION_GRANTED) {
            NotificationManagerCompat.from(context).notify(Random.nextInt(), notification)
        }

        return Result.success()
    }

}
