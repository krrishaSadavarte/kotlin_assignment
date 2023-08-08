//WAP to find max value using function as express
fun main(){

    var a : Int = readln().toInt()
    var b : Int = readln().toInt()
    var c : Int = readln().toInt()

    if ( a > b ){
        if( a > c ){
            println("$a is greater number")
        }
    }
    else{
        if (b > c)
            println("$b is greater number")

        else
            println("$c is greater number")
    }

}