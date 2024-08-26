import kotlin.random.Random

fun generarNumeroAleatorio ():Int{
    return Random.nextInt(1,31)
}

fun main(){
    val numeroRandom = generarNumeroAleatorio()
    var intentos = 5

    while(intentos>0){
        println("Ingresa tu numero: ")
        val numero = readLine()?.toIntOrNull()

        if(numero == numeroRandom){
            println("Felicidades! haz ganado")
            break
        }
        else{
            println("Intente de nuevo")
        }
        intentos--
    }

    println(numeroRandom)
}