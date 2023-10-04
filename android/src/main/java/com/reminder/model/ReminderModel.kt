package com.reminder.model

import android.icu.util.Calendar

class ReminderModel(
  ownerId: Int,
  metadata: String,
  hour: Int,
  minute: Int,
  second: Int,
  date: Int,
  month: Int,
  year: Int,
  isRepeated: Boolean,
  title: String,
  message: String,
  notificationId: Int,
  id: Int
  ) {
}
