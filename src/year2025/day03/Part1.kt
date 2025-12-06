package year2025.day03

fun main() {
    println(generateSequence(::readlnOrNull).sumOf { bank ->
        val firstDigit = bank.substring(0, bank.length - 1).max()
        val firstDigitIndex = bank.indexOf(firstDigit)
        val secondDigit = bank.substring(firstDigitIndex + 1).max()
        firstDigit.toString().toInt() * 10 + secondDigit.toString().toInt()
    })
}
