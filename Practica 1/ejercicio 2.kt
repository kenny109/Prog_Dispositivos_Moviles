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
    val eleccionComputadora = elegirOpcionComputadora()
    println("la computadora eligio: ${eleccionComputadora}")

    println("Elige una opción: Piedra, Papel o Tijera: " )
    val eleccionUsuario = readLine()?.capitalize() ?: ""

    val resultado = determinarResultadoJuego(eleccionUsuario, eleccionComputadora)

    println("Resultado: $resultado")
}