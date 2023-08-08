//Kotlin Program to Find the Frequency of Character in a String

fun main() {
    val str = "krrisha"
    var frequency = 0
    for (n in str) {
       var ch = n
       if (ch in str) {
               ++frequency
           }
           println("Frequency of $ch = $frequency")
       }



}