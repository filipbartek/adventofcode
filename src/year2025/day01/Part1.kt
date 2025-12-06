package year2025.day01

fun main() {
    var dial = 50
    var count = 0
    while (true) {
        val line = readlnOrNull() ?: break
        val direction = line[0]
        val distance = line.substring(1).toInt()
        dial = (dial + (if (direction == 'R') distance else -distance)) % 100
        if (dial == 0) count++
    }
    print(count)
}
