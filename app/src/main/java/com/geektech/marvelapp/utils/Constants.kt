package com.geektech.marvelapp.utils

import com.geektech.marvelapp.BuildConfig
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {
    companion object {
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val GOOGLE_AUTH_OK = 222
        const val limit = "20"
        fun hash(): String{
            val input = "$timeStamp${BuildConfig.MARVEL_PRIVATE_KEY}${BuildConfig.MARVEL_API_KEY}"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }
    }
}