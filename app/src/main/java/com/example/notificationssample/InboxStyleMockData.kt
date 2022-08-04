package com.example.notificationssample

import android.app.NotificationManager
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat

object InboxStyleMockData {
    const val mContentTitle = "5 new emails"
    const val mContentText = "from Janet, Jon, Tony +2 more"
    const val mNumberOfNewEmails = 5
    const val mPriority = NotificationCompat.PRIORITY_DEFAULT

    const val mBigContentTitle = "5 new emails from Jane, Jay, Alex +2"
    const val mSummaryText = "New emails"

    fun mIndividualEmailSummary(): ArrayList<String> {
        val list = ArrayList<String>()

        list.add("Janet Farber - B-day Party is here...")
        list.add("Jon Walker - There's a pelican on the server!")
        list.add("Tony Chang - Check this on  out...")
        list.add("Amanda Mendez - Check in email?")
        list.add("Johnathan Cooper - Dinner later....")

        return list
    }

    fun mParticipants(): ArrayList<String> {
        // If the phone is in "Do not disturb mode, the user will still be notified if
        // the user(s) is starred as a favorite.
        val list = ArrayList<String>()

        list.add("Janet Farber")
        list.add("Jon Walker")
        list.add("Tony Chang")
        list.add("JAmanda Mendez")
        list.add("Johnathan Cooper")

        return list
    }

    const val mChannelId = "channel_email_1"
    const val mChannelName = "Sample Email"

    // The user-visible description of the channel.
    const val mChannelDescription = "Sample Email Notifications"
    @RequiresApi(Build.VERSION_CODES.N)
    //const val mChannelImportance = NotificationManager.IMPORTANCE_DEFAULT
    const val mChannelImportance = NotificationManager.IMPORTANCE_HIGH
    const val mChannelEnableVibrate = true
    const val mChannelLockscreenVisibility = NotificationCompat.VISIBILITY_PRIVATE
}