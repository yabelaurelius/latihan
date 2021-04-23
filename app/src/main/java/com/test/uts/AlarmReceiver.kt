package com.test.uts

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import java.text.SimpleDateFormat
import java.util.*

class AlarmReceiver : BroadcastReceiver() {
    @SuppressLint("NewApi")
    override fun onReceive(context: Context?, intent: Intent?) {
        val notifyid = 30103
        val channel_id = "my_channel_01"
        val name = "car rent"
        val importance = NotificationManager.IMPORTANCE_HIGH
        val nNotifyChannel = NotificationChannel(
            channel_id,
            name,
            importance
        )

        val mBuilder = NotificationCompat.Builder(context!!, channel_id)
            .setContentText("car arrive")
            .setContentTitle("${SimpleDateFormat("dd/MM/yyyy", Locale.US).format(Calendar.getInstance().time)})")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        val mNotificationManager = context
            .getSystemService(Context.NOTIFICATION_SERVICE)
                as NotificationManager

        for (s in mNotificationManager.notificationChannels) {
            mNotificationManager.deleteNotificationChannel(s.id)
        }

        mNotificationManager.createNotificationChannel(nNotifyChannel)
        mNotificationManager.notify(notifyid, mBuilder.build())
    }
}