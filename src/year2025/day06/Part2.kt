package year2025.day06

fun main() {
    val lines = generateSequence(::readlnOrNull).toList()
    val numberLines = lines.dropLast(1)
    val operations = lines.last()
    val lastColumn = numberLines.first().lastIndex
    
    var total: ULong = 0u
    var problemResult: ULong = 0u
    var operation: Char? = null
    for (column in 0..lastColumn + 1) {
        if (column < lastColumn && operations[column + 1] != ' ') continue
        if (column == lastColumn + 1 || operations[column] != ' ') {
            // The previous problem has finished
            total += problemResult
            if (column <= lastColumn) {
                operation = operations[column]
                problemResult = if (operation == '*') 1u else 0u
            }
            else break
        }
        var number: ULong = 0u
        for (row in 0..lines.lastIndex - 1) {
            val char = lines[row][column]
            if (char == ' ') continue
            val digit = char.toString().toULong()
            number = number * 10u + digit
        }
        if (operation == '*') problemResult *= number else problemResult += number
    }
    println(total)
}
