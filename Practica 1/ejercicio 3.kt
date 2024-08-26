fun mostrarMenu(){
    println("==== Menú ====")
    println("1. Suma")
    println("2. Resta")
    println("3. Multiplicación")
    println("4. División")
    println("5. Salir")
    print("Seleccione una opción: ")
}

fun sumar(a: Double, b: Double): Double = a + b
fun restar(a: Double, b: Double): Double = a - b
fun multiplicar(a: Double, b: Double): Double = a * b
fun dividir(a: Double, b: Double): Double? = if (b != 0.0) a / b else null

fun main(){
    while (true) {
        mostrarMenu()
        val opcion = readLine()?.toIntOrNull()

        if (opcion == null || opcion !in 1..5) {
            println("Opción no válida. Inténtelo de nuevo.")
            continue
        }

        if (opcion == 5) {
            println("Saliendo del programa.")
            break
        }

        //Solicitando al usuario los numeros sobre los que desea operar segun su eleccion
        print("Ingrese el primer número: ")
        val num1 = readLine()?.toDoubleOrNull() ?: 0.0

        print("Ingrese el segundo número: ")
        val num2 = readLine()?.toDoubleOrNull() ?: 0.0

        //Llamamos a las funciones de nuestra calculadora segun la elección del usuario
        val resultado = when (opcion) {
            1 -> sumar(num1, num2)
            2 -> restar(num1, num2)
            3 -> multiplicar(num1, num2)
            4 -> dividir(num1, num2)
            else -> null
        }

        if (opcion == 4 && num2 == 0.0) {
            println("Error: No se puede dividir entre cero.")
        } else {
            println("El resultado es: $resultado")
        }
    }
}