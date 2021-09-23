package com.proway.workmanager.ui.main.work

import android.app.*
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.proway.workmanager.AuthenticationActivity
import com.proway.workmanager.R

const val CHANNEL_ID = "CHANNEL_EXPIRED_DOCS"
class NotificationHandler(private val context: Context) {

    init {
        createNotificationChannel()
    }

    private fun createNotificationChannel(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            val name= "Expired_Docs"
            val descriptionText = "Going to notify users when docs has been expired"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel= NotificationChannel(CHANNEL_ID, name, importance).apply {
                description = descriptionText
            }

            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

    private fun createPendingIntent(): PendingIntent{
        val intent= Intent(context, AuthenticationActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        return PendingIntent.getActivity(context,0 , intent, 0
        )
    }

    fun createNotification(title: String, message: String): Notification {
        val builder = NotificationCompat.Builder(context, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(message)
            .setSmallIcon(R.drawable.ic_android_black_24dp)
            .setContentIntent(createPendingIntent())
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        return builder.build()
    }
}