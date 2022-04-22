package com.example.appbindecinv

// Bases Númericas a utilizar en la transformación
val baseHexadecimal = 16
val baseOctal = 8
val baseDiez = 10

/**
 * Función para convertir de Decimal a binario
 */
fun convertirDeDecimalABinario(num: Int): String {
    var operando: Int = num
    var rest: Int
    var resultado = ""

    while (operando >= 1) {
        rest = if ((operando % 2) == 0) 0 else 1
        operando /= 2

        resultado = rest.toString() + resultado
        //resultado += rest.toString()
    }

    return resultado
    //return resultado.reversed()
}

/**
 * Función para convertir de Binario a decimal
 */
fun convertirDeBinarioADecimal(num: Int): Int {
    val binario: String = (num.toString()).reversed()
    var base = 1
    var resultado = 0;
    for (i in binario.indices) {
        resultado += if (binario[i].toString() == "0") 0 else base

        base *= 2
    }

    return resultado
}

/**
 * Función para convertir de Decimal a Octal
 */
fun convertirDeDecimalAOctal(num: Int): String{
    var cociente = num
    var resultado = ""

    while(cociente != 0){
        val resto: Int = cociente % baseOctal
        cociente /= baseOctal

        resultado = resto.toString() + resultado
    }

    return resultado
}

/**
 * Función para convertir de Octal a Decimal
 */
fun convertirDeOctalADecimal(cadena: String): Int{
    var conv = cadena.reversed()
    var ciclo: Int = cadena.length - 1
    var resultado = 0

    while (ciclo >= 0){
        resultado += conv[ciclo].toString().toInt() * (Math.pow(baseOctal.toDouble(), ciclo.toDouble())).toInt()
        ciclo -= 1
    }

    return resultado
}

/**
 * Función para convertir de Decimal a Hexadecimal
 */
fun convertirDeDecimalAHexadecimal(num: Int): String{
    var cociente = num
    var resultado = ""

    while(cociente != 0){
        val resto: Int = cociente % baseHexadecimal
        cociente /= baseHexadecimal

        val agregar = letrasHexadecimal(resto)
        resultado = agregar + resultado
    }

    return resultado
}

/**
 * Función para convertir de Hexadecimal a Decimal
 */
fun convertirDeHexadecimalADecimal(cadena: String): Int{
    var conv = cadena.reversed()
    var ciclo: Int = cadena.length - 1
    var resultado = 0

    while (ciclo >= 0){
        resultado += valoresHexadecimal(conv[ciclo].toString()) * (Math.pow(baseHexadecimal.toDouble(), ciclo.toDouble())).toInt()
        ciclo -= 1
    }

    return resultado
}

/**
 * Retorna un valor de acuerdo a la tabla de valores hexadecimales
 */
fun valoresHexadecimal(a: String): Int = when(a) {
    "A" -> 10
    "B" -> 11
    "C" -> 12
    "D" -> 13
    "E" -> 14
    "F" -> 15
    else -> a.toInt()
}

/**
 * Retorna un valor de acuerdo a la tabla de valores hexadecimales
 */
fun letrasHexadecimal(a: Int): String = when {
    a == 10 -> "A"
    a == 11 -> "B"
    a == 12 -> "C"
    a == 13 -> "D"
    a == 14 -> "E"
    a == 15 -> "F"
    else -> a.toString()
}