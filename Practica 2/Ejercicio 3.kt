// Descripción: Clase abstracta Shape para representar figuras geométricas con área y perímetro.
// Autor: Kenny Luis Flores Chacón
// Fecha creación: 01/09/2024
// Fecha última modificación: 01/09/2024

abstract class Shape {
    abstract fun calcularArea(): Double
    abstract fun calcularPerimetro(): Double

    fun imprimirResultados() {
        println("Área: ${calcularArea()}")
        println("Perímetro: ${calcularPerimetro()}")
    }
}

class Cuadrado(private val lado: Double) : Shape() {

    override fun calcularArea(): Double {
        return lado * lado
    }

    override fun calcularPerimetro(): Double {
        return 4 * lado
    }
}