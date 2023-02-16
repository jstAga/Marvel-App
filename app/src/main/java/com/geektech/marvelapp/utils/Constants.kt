package com.geektech.marvelapp.utils

import com.geektech.marvelapp.BuildConfig
import com.geektech.marvelapp.data.remote.model.Board
import java.math.BigInteger
import java.security.MessageDigest
import java.sql.Timestamp

class Constants {
    companion object {
        const val FILM_ID = "film.id"
        val timeStamp = Timestamp(System.currentTimeMillis()).time.toString()
        const val GOOGLE_AUTH_OK = 222
        const val LIMIT = "20"
        fun hash(): String{
            val input = "$timeStamp${BuildConfig.MARVEL_PRIVATE_KEY}${BuildConfig.MARVEL_API_KEY}"
            val md = MessageDigest.getInstance("MD5")
            return BigInteger(1,md.digest(input.toByteArray())).toString(16).padStart(32,'0')
        }

        val boardingData = listOf(
            Board(
                "Welcome to the Marvel Universe",
                "https://i.pinimg.com/564x/b4/c4/ab/b4c4ab99761386b5c0b5fbb4dbdef625.jpg"
            ),
            Board(
                "You can read comic books, learn about movies",
                "https://i.pinimg.com/564x/c5/98/c8/c598c856c57cc9e0ff6f685a6082b25d.jpg"
            ),
            Board(
                "Lorem ipsum dolor sit amet, consectetur adipiscing elite",
                "https://i.pinimg.com/564x/7a/6e/29/7a6e296532e4187d50c2f2e9efd11157.jpg"
            ),
        )
    }
}