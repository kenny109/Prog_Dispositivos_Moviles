fun obtenerNivelRendimiento(puntuacion: Int): String {
    return when (puntuacion) {
        in 0..3 -> "Inaceptable"
        in 4..6 -> "Aceptable"
        in 7..10 -> "Meritorio"
        else -> "Puntuación inválida"
    }
}

fun calcularDinero(puntuacion: Int, salario: Int): Double {
    return salario * (puntuacion / 10.0)
}

fun main() {
    println("Ingrese su puntuación: ")
    val puntuacion = readLine()?.toIntOrNull()
    if (puntuacion == null || puntuacion < 0 || puntuacion > 10) {
        println("Puntuación inválida")
        return
    }

    println("Ingrese su salario mensual: ")
    val salario = readLine()?.toIntOrNull()
    if (salario == null || salario <= 0) {
        println("Salario inválido")
        return
    }

    val nivel = obtenerNivelRendimiento(puntuacion)
    val dinero = calcularDinero(puntuacion, salario)

    println("Nivel de rendimiento: $nivel")
    println("Cantidad de dinero recibido: $${dinero.toInt()}")
}