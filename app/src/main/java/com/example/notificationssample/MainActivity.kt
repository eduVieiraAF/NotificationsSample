package com.example.notificationssample

import android.app.Notification
import android.app.PendingIntent
import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val NOTIFICATION_ID = 888
    private lateinit var mNotificationManagerCompat: NotificationManagerCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mNotificationManagerCompat = NotificationManagerCompat.from(this@MainActivity)

        val btnInboxStyleNotification = findViewById<Button>(R.id.btn_inbox_style)
        btnInboxStyleNotification.setOnClickListener(this@MainActivity)

        val btnBigImageStyleNotification = findViewById<Button>(R.id.btn_big_image_style)
        btnBigImageStyleNotification.setOnClickListener(this@MainActivity)
    }

    override fun onClick(v: View) {
        when (v.id) {

            R.id.btn_inbox_style -> {
                generateInboxStyleNotification()
                return
            }

            R.id.btn_big_image_style -> {
                generateBigPictureStyleNotification()
                return
            }
        }
    }

    private fun generateInboxStyleNotification() {

        val notificationChannelId: String =
            NotificationUtil().createInboxStyleNotificationChannel(this)

        val inboxStyle =
            NotificationCompat.InboxStyle()
                .setBigContentTitle(InboxStyleMockData.mBigContentTitle)
                .setSummaryText(InboxStyleMockData.mSummaryText)

        for (summary in InboxStyleMockData.mIndividualEmailSummary()) {
            inboxStyle.addLine(summary)
        }

        val mainIntent = Intent(this, MainActivity::class.java)
        val mainPendingIntent = PendingIntent.getActivity(
            this,
            0,
            mainIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationCompatBuilder = NotificationCompat.Builder(
            applicationContext,
            notificationChannelId
        )

        notificationCompatBuilder
            .setStyle(inboxStyle)
            .setContentTitle(InboxStyleMockData.mContentTitle)
            .setContentText(InboxStyleMockData.mContentText)
            .setSmallIcon(R.drawable.ic_email)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.ic_person
                )
            )
            .setContentIntent(mainPendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.secondary_color_light
                )
            )
            .setSubText(InboxStyleMockData.mNumberOfNewEmails.toString())
            .setCategory(Notification.CATEGORY_EMAIL)
            .setPriority(InboxStyleMockData.mPriority)
            .setVisibility(InboxStyleMockData.mChannelLockscreenVisibility)

        for (name in InboxStyleMockData.mParticipants()) {
            notificationCompatBuilder.addPerson(name)
        }
        val notification = notificationCompatBuilder.build()
        mNotificationManagerCompat.notify(NOTIFICATION_ID, notification)
    }

    private fun generateBigPictureStyleNotification() {

        val notificationChannelId: String =
            NotificationUtil().createBigPictureStyleNotificationChannel(this@MainActivity)

        val bigPictureStyle =
            NotificationCompat.BigPictureStyle()
                .bigPicture(
                    BitmapFactory.decodeResource(
                        resources,
                        BigPictureStyleMockData.mBigImage
                    )
                )
                .setBigContentTitle(BigPictureStyleMockData.mBigContentTitle)
                .setSummaryText(BigPictureStyleMockData.mSummaryText)

        val mainIntent = Intent(this, MainActivity::class.java)
        val mainPendingIntent = PendingIntent.getActivity(
            this,
            0,
            mainIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        val notificationCompatBuilder = NotificationCompat.Builder(
            applicationContext, notificationChannelId
        )

        notificationCompatBuilder
            .setStyle(bigPictureStyle)
            .setContentTitle(BigPictureStyleMockData.mContentTitle)
            .setContentText(BigPictureStyleMockData.mContentText)
            .setSmallIcon(R.drawable.ic_person)
            .setLargeIcon(
                BitmapFactory.decodeResource(
                    resources,
                    R.drawable.ic_person
                )
            )
            .setContentIntent(mainPendingIntent)
            .setDefaults(NotificationCompat.DEFAULT_ALL)
            .setColor(
                ContextCompat.getColor(
                    applicationContext,
                    R.color.secondary_color_light
                )
            )
            .setSubText(1.toString())
            .setCategory(Notification.CATEGORY_SOCIAL)
            .setPriority(BigPictureStyleMockData.mPriority)
            .setVisibility(BigPictureStyleMockData.mChannelLockscreenVisibility)

        for (name in BigPictureStyleMockData.mParticipants()) {
            notificationCompatBuilder.addPerson(name)
        }

        val notification = notificationCompatBuilder.build()
        mNotificationManagerCompat.notify(NOTIFICATION_ID, notification)
    }
}