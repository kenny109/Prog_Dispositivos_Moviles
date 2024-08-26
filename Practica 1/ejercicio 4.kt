import kotlin.random.Random

fun generarNumeroAleatorio ():Int{
    return Random.nextInt(1,31)
}

fun main(){
    val numeroRandom = generarNumeroAleatorio()
    println(numeroRandom)
}