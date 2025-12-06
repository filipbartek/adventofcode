package year2025.day04

fun main() {
    val rows = generateSequence(::readlnOrNull).toList()

    fun isRoll(row: Int, column: Int): Boolean {
        if (row < 0 || row > rows.lastIndex || column < 0 || column > rows[row].lastIndex) return false
        return rows[row][column] == '@'
    }

    fun isAccessible(row: Int, column: Int): Boolean {
        var score = 0
        for (rowOffset in -1..1) {
            for (columnOffset in -1..1) {
                if (rowOffset == 0 && columnOffset == 0) continue
                if (isRoll(row + rowOffset, column + columnOffset)) score++
            }
        }
        return score < 4
    }

    var accessibleCount = 0
    for (row in 0..rows.lastIndex) {
        for (column in 0..rows[row].lastIndex) {
            if (!isRoll(row, column)) continue
            if (isAccessible(row, column)) accessibleCount++
        }
    }
    println(accessibleCount)
}
