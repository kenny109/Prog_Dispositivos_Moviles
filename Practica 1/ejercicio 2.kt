/*
Ejercicio 1: Programa para simular el juego de piedra papel o tijera contra la computadora
Autor: Kenny Luis Flores Chacón
Fecha creación: 24/08/2024
Fecha de ultima modificación: 25/08/2024
*/
import kotlin.random.Random

fun elegirOpcionComputadora(): String {
    val opciones = listOf("Piedra", "Papel", "Tijera")
    return opciones[Random.nextInt(opciones.size)]
}

fun determinarResultadoJuego(eleccionUser: String, eleccionComputer: String): String{
    return when{
        eleccionUser == eleccionComputer -> "Empate"
        (eleccionUser == "Piedra" && eleccionComputer == "Tijera") ||
                (eleccionUser == "Papel" && eleccionComputer == "Piedra") ||
                (eleccionUser == "Tijera" && eleccionComputer == "Papel") -> "Ganaste"
        else -> "Perdiste"
    }

}
fun main (){

    println("Elige una opción: Piedra, Papel o Tijera: " )
    val eleccionUsuario = readLine()?.capitalize() ?: ""

    if (eleccionUsuario !in listOf("Piedra", "Papel", "Tijera")) {
        println("Elección inválida. Debes elegir Piedra, Papel o Tijera.")
        return
    }
    val eleccionComputadora = elegirOpcionComputadora()
    println("la computadora eligio: ${eleccionComputadora}")

    val resultado = determinarResultadoJuego(eleccionUsuario, eleccionComputadora)

    println("Resultado: $resultado")
}