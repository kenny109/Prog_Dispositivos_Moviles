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
        if (numero == null || numero !in 1..30) {
            println("Por favor, ingresa un número válido entre 1 y 30.")
            continue
        }

        if(numero == numeroRandom){
            println("Felicidades! haz ganado")
            break
        }
        else if(numero< numeroRandom){
            println("El número es mayor que $numero.")
        }
        else {
            println("El numero es menor que $numero")
        }

        intentos--
        println("Te quedan $intentos intentos! ")
    }

    println(numeroRandom)
}