import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.os.Build
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.domacecviceniasvlastnouvahou.R

/**
 * Pomocník pre prácu s oznámeniami v aplikácii.
 *
 * @param context kontext aplikácie
 */
class NotificationHelper(private val context: Context) {

    private val notificationManager = NotificationManagerCompat.from(context)
    private val channelId = "my_channel_id"

    init {
        createNotificationChannel()
    }

    /**
     * Vytvorí notifikačný kanál pre oznámenia.
     */
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.default_notification_channel_name)
            val descriptionText = context.getString(R.string.default_notification_channel_description)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channelId, name, importance).apply {
                description = descriptionText
                enableLights(true)
                lightColor = Color.RED
                enableVibration(true)
            }
            notificationManager.createNotificationChannel(channel)
        }
    }

    /**
     * Zobrazí oznámenie o spokojnosti s aplikáciou.
     */
    fun showAppSatisfactionNotification() {
        val title = "Ako sa vám páči naša aplikácia?"
        val content = "Ohodnoťte ju prosím na Play Store."

        val notification = NotificationCompat.Builder(context, channelId)
            .setSmallIcon(R.drawable.triceps_dips)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .build()

        if (ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return
        }
        notificationManager.notify(1, notification)
    }
}
