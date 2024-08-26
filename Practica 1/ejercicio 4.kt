/*
Ejercicio 4: Programa que le solicita al usuario que adivine el numero random que
genera la computadora
Autor: Kenny Luis Flores Chacón
Fecha de inicio: 25/08/2024
Ultima modificación:25/08/2024
*/
import kotlin.random.Random

fun generarNumeroAleatorio ():Int{
    return Random.nextInt(1,31)
}

fun main(){
    val numeroRandom = generarNumeroAleatorio()
    var intentos = 5
    println("He generado un número entre 1 y 30. ¡Intenta adivinarlo!")
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
        if (intentos > 0) {
            println("Te quedan $intentos intentos!")
        } else {
            println("Game Over. El número era $numeroRandom.")
        }
    }
}