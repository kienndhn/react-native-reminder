package com.reminder.utils

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.icu.util.Calendar
import android.icu.util.GregorianCalendar
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.ReactApplicationContext
import com.reminder.services.ReminderReceiver
import java.text.SimpleDateFormat
import kotlin.math.log


fun setBootReceiver (context: Context) {
  val receiver = ComponentName(context, ReminderReceiver::class.java)
  context.packageManager.setComponentEnabledSetting(
    receiver,
    PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
    PackageManager.DONT_KILL_APP
  )
}

@RequiresApi(Build.VERSION_CODES.N)
fun scheduleAlarm (context: Context, id: Int, fireDate: Calendar, isRepeated: Boolean){
  val intent = Intent(context, ReminderReceiver::class.java)
  intent.putExtra("reminder_id", id)
  intent.putExtra("reminder_data", fireDate.timeInMillis)

  val reminderIntentRTC: PendingIntent = PendingIntent.getBroadcast(
    context,
    id,
    intent,
    PendingIntent.FLAG_MUTABLE
  )
  val reminderManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

  val alarmClock = AlarmManager.AlarmClockInfo(fireDate.timeInMillis, reminderIntentRTC)

  reminderManager.setAlarmClock(
    alarmClock,
    reminderIntentRTC
  )

  setBootReceiver(context)
}

@SuppressLint("SimpleDateFormat")
@RequiresApi(Build.VERSION_CODES.N)
fun getCalendarFromString(date: String): Calendar? {
  val simpleDateFormat = SimpleDateFormat("dd-MM-yyyy HH:mm:ss")
  val calendar =  GregorianCalendar.getInstance()
  calendar.time = simpleDateFormat.parse(date)
  calendar.set(Calendar.SECOND, 0)
  calendar.set(Calendar.MILLISECOND, 0)
  return calendar
}

