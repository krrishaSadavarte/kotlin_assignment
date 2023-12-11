package com.example.shoopzi.Utils
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Patterns
import java.io.File
import java.io.FileInputStream
import java.io.IOException
import java.util.regex.Pattern

class Utils {

    companion object {

        const val PASSWORD_REGEX = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,20}$"

        fun isValidEmail(email: String): Boolean {
            return Patterns.EMAIL_ADDRESS.matcher(email).matches()
        }

        fun isValidContact(contact: String): Boolean {
            return contact.length == 10
        }

        fun isValidPassword(password:String) : Boolean{
            return Pattern.matches(PASSWORD_REGEX, password)
        }
        fun getBitmapFromAbsolutePath(filePath:String): Bitmap?{
            return try{
                val file = File(filePath)
                val inputStream = FileInputStream(file)
                val bitmap = BitmapFactory.decodeStream(inputStream)
                inputStream.close()
                bitmap
            }catch (e : IOException){
                e.printStackTrace()
                null
            }
        }

    }

}