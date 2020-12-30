package com.example.kanbagisi

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class MyFirebaseMessagingService : FirebaseMessagingService() {

    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        try {
            if (remoteMessage.data.isNotEmpty()) {
                showNotification(remoteMessage.data["title"], remoteMessage.data["body"])
            }

//            if (remoteMessage.notification != null) {
//                showNotification(
//                    remoteMessage.notification?.title,
//                    remoteMessage.notification?.body
//                )
//            } else {
//                showNotification(remoteMessage.data["title"], remoteMessage.data["body"])
//            }

            val sp = getSharedPreferences("SP_USER", Context.MODE_PRIVATE)
            val currentUser = sp.getString("CURRENT_USER_ID", "NULL")

            val sent = remoteMessage.data["sent"]
            val user = remoteMessage.data["user"]

            val fUser = FirebaseAuth.getInstance().currentUser
            if (fUser != null && sent.equals(fUser.uid)) {
                if (!currentUser.equals(user)) {
                    showNotification(remoteMessage.data["title"], remoteMessage.data["body"])
                }
            }

        } catch (e: Exception) {
            println("Error error -->${e.localizedMessage}")
        }
    }

    private fun showNotification(
        title: String?,
        body: String?
    ) {
        val intent = Intent()
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(
            this, 0, intent,
            PendingIntent.FLAG_ONE_SHOT
        )

        val channelId = getString(R.string.channel_id)
        val channelName = getString(R.string.channel_name)
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.

        Log.d("NOTIFICATION", title + "\n" + body)
        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setContentTitle(title)
            .setContentText(body)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .setSound(soundUri)
            .setContentIntent(pendingIntent)

        notificationManager.notify(0, notificationBuilder.build())
    }
}
