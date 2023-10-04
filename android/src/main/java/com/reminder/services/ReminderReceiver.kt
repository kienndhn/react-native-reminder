package com.reminder.services

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class ReminderReceiver : BroadcastReceiver(){
  private val TAG = "ReminderReceiver"
  override fun onReceive(context: Context?, intent: Intent?) {
    val extra = intent?.extras
    val id = extra?.getInt("reminder_id")

    Log.d(TAG, "onReceive: ")
    Toast.makeText(context, "receive reminder " + id, Toast.LENGTH_LONG).show()
  }
}
