package com.reminder

import android.icu.util.Calendar
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.facebook.react.bridge.Arguments
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.bridge.ReactContextBaseJavaModule
import com.facebook.react.bridge.ReactMethod
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReadableMap
import com.facebook.react.bridge.WritableMap
import com.reminder.model.DatabaseHandler
import com.reminder.model.ReminderModel
import com.reminder.utils.scheduleAlarm
import com.reminder.utils.getCalendarFromString


class ReminderModule(reactContext: ReactApplicationContext) :
  ReactContextBaseJavaModule(reactContext) {
  private val databaseHandler = DatabaseHandler(reactContext)
  override fun getName(): String {
    return NAME
  }

  // Example method
  // See https://reactnative.dev/docs/native-modules-android
  @ReactMethod
  fun multiply(a: Double, b: Double, promise: Promise) {
    promise.resolve(a * b)
  }

  @RequiresApi(Build.VERSION_CODES.N)
  @ReactMethod
  fun scheduleAlarm(data: ReadableMap, promise: Promise) {
    val metadata = data.getMap("metadata")
    val scheduleTime = data.getString("scheduleTime")
    val isRepeated = data.getBoolean("isRepeated")
    val id = data.getInt("id")
    val fireDate = scheduleTime?.let { getCalendarFromString(it) }

    if (fireDate != null) {
      scheduleAlarm(reactApplicationContext, id, fireDate, isRepeated)
    }

    val result = Arguments.createMap()
    fireDate?.let {
      result.putInt("hour", it.get(Calendar.HOUR))
      result.putInt("minute", it.get(Calendar.MINUTE))
    }
    promise.resolve(result)
  }

  companion object {
    const val NAME = "Reminder"
  }
}
