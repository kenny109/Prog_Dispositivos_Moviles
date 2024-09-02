// Descripción: Clase para representar una cuenta bancaria con saldo y límite de retiro.
// Autor: Kenny Luis Flores Chacón
// Fecha creación: 01/09/2024
// Fecha última modificación: 01/09/2024

class CuentaBancaria(private var saldo: Double, private var limiteRetiro: Double) {
    //Metodo get para obtener el saldo
    fun getSaldo(): Dobble{
        return saldo
    }
    //Metodo set para establecer un nuevo saldo
    fun setSaldo(nuevoSaldo: Double){
        if (nuevoSaldo >= 0) {
            saldo = nuevoSaldo
        } else {
            println("El saldo no puede ser negativo")
        }
    }
}
