fun mostrarMenu(){
    println("==== Menú ====")
    println("1. Suma")
    println("2. Resta")
    println("3. Multiplicación")
    println("4. División")
    println("5. Salir")
    print("Seleccione una opción: ")
}


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

        // Aquí agregaremos más lógica para realizar las operaciones
    }
}