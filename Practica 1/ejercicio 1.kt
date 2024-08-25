fun calcularDineroRecibido(salarioMensual: Double, puntuacion: Int): Double {
    return salarioMensual * (puntuacion / 10.0)
}


fun main(){
    println("Ingrese la puntuación (0 a 10)")
    val puntuación = readln()?.toIntOrNull() ?:0

    println("Ingrese el salario")
    val salarioMensual = readln()?.toDoubleOrNull() ?:0


}