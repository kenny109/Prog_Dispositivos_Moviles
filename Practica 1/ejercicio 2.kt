import kotlin.random.Random

fun elegirOpcionComputadora(): String {
    val opciones = listOf("Piedra", "Papel", "Tijera")
    return opciones[Random.nextInt(opciones.size)]
}
fun main (){
    val eleccionComputadora = elegirOpcionComputadora()

    println("la computadora eligio: ${eleccionComputadora}")
}