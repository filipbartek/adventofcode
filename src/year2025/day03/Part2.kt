package year2025.day03

import kotlin.math.pow

fun main() {
    println(generateSequence(::readlnOrNull).sumOf { bank ->
        var start = 0
        (11 downTo 0).sumOf { order ->
            val substring = bank.substring(start, bank.length - order)
            val nextDigit = substring.max()
            start += substring.indexOf(nextDigit) + 1
            nextDigit.toString().toULong() * (10.0).pow(order).toULong()
        }
    })
}
