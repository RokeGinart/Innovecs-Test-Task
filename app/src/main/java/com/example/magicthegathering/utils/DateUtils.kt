package com.example.magicthegathering.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


object DateUtils {
    private const val serverDateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
    private const val outputDateFormat = "yyyy-MM-dd"
    private const val EMPTY = ""

    fun parseDate(inputDate : String) : String {
        return try {
            val date = SimpleDateFormat(serverDateFormat, Locale.getDefault()).parse(inputDate)
            if (date != null) {
               return SimpleDateFormat(outputDateFormat, Locale.getDefault()).format(date)
            } else {
                EMPTY
            }
        } catch (e: Exception) {
            Log.d(DateUtils::class.java.name, e.toString())
            EMPTY
        }
    }
}