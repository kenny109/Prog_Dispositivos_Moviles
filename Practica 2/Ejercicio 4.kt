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

class Revista(
    titulo: String,
    autor: String,
    anioPublicacion: Int,
    val issn: String,
    val volumen: Int,
    val numero: Int,
    val editorial: String
) : Material(titulo, autor, anioPublicacion) {

    override fun mostrarDetalles() {
        println("Revista: $titulo")
        println("Autor: $autor")
        println("Año de Publicación: $anioPublicacion")
        println("ISSN: $issn")
        println("Volumen: $volumen")
        println("Número: $numero")
        println("Editorial: $editorial")
    }
}

data class Usuario(val nombre: String, val apellido: String, val edad: Int)

interface IBiblioteca {
    fun registrarMaterial(material: Material)
    fun registrarUsuario(usuario: Usuario)
    fun prestarMaterial(material: Material, usuario: Usuario)
    fun devolverMaterial(material: Material, usuario: Usuario)
    fun mostrarMaterialesDisponibles()
    fun mostrarMaterialesReservadosPorUsuario(usuario: Usuario)
}

class Biblioteca : IBiblioteca {
    private val materialesDisponibles = mutableListOf<Material>()
    private val usuarios = mutableMapOf<Usuario, MutableList<Material>>()

    override fun registrarMaterial(material: Material) {
        materialesDisponibles.add(material)
    }

    override fun registrarUsuario(usuario: Usuario) {
        usuarios[usuario] = mutableListOf()
    }

    override fun prestarMaterial(material: Material, usuario: Usuario) {
        if (material in materialesDisponibles) {
            materialesDisponibles.remove(material)
            usuarios[usuario]?.add(material)
            println("Material prestado: ${material.titulo} a ${usuario.nombre} ${usuario.apellido}")
        } else {
            println("El material no está disponible.")
        }
    }

    override fun devolverMaterial(material: Material, usuario: Usuario) {
        if (material in usuarios[usuario] ?: mutableListOf()) {
            usuarios[usuario]?.remove(material)
            materialesDisponibles.add(material)
            println("Material devuelto: ${material.titulo} por ${usuario.nombre} ${usuario.apellido}")
        } else {
            println("El usuario no tiene este material en préstamo.")
        }
    }

    override fun mostrarMaterialesDisponibles() {
        println("Materiales disponibles:")
        materialesDisponibles.forEach { it.mostrarDetalles() }
    }

    override fun mostrarMaterialesReservadosPorUsuario(usuario: Usuario) {
        println("Materiales reservados por ${usuario.nombre} ${usuario.apellido}:")
        usuarios[usuario]?.forEach { it.mostrarDetalles() }
    }
}

fun main() {
    val libro = Libro("1984", "George Orwell", 1949, "Distopía", 328)
    val revista = Revista("National Geographic", "Varios", 2021, "0027-9358", 196, 5, "National Geographic Society")

    val usuario = Usuario("Juan", "Pérez", 30)

    val biblioteca = Biblioteca()
    biblioteca.registrarMaterial(libro)
    biblioteca.registrarMaterial(revista)
    biblioteca.registrarUsuario(usuario)

    biblioteca.prestarMaterial(libro, usuario)
    biblioteca.mostrarMaterialesReservadosPorUsuario(usuario)
    biblioteca.devolverMaterial(libro, usuario)
    biblioteca.mostrarMaterialesDisponibles()
}
