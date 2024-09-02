// Descripción: Clase abstracta Material para representar materiales en una biblioteca.
// Autor: Kenny Luis Flores Chacón
// Fecha creación: 01/09/2024
// Fecha última modificación: 01/09/2024

abstract class Material(
    val titulo: String,
    val autor: String,
    val anioPublicacion: Int
) {
    abstract fun mostrarDetalles()
}

class Libro(
    titulo: String,
    autor: String,
    anioPublicacion: Int,
    val genero: String,
    val numeroPaginas: Int
) : Material(titulo, autor, anioPublicacion) {

    override fun mostrarDetalles() {
        println("Libro: $titulo")
        println("Autor: $autor")
        println("Año de Publicación: $anioPublicacion")
        println("Género: $genero")
        println("Número de Páginas: $numeroPaginas")
    }
}