package year2025.day01

fun main() {
    var dial = 50
    var count = 0
    while (true) {
        val line = readlnOrNull() ?: break
        val direction = line[0]
        val distance = line.substring(1).toInt()
        count += distance / 100
        if (dial != 0) {
            if (direction == 'L' && dial - distance % 100 < 0) count++
            if (direction == 'R' && dial + distance % 100 > 100) count++
        }
        dial = (dial + (if (direction == 'R') distance else -distance)).mod(100)
        if (dial == 0) count++
    }
    println(count)
}
