package year2025.day06

fun main() {
    val lines = generateSequence(::readlnOrNull).map { it.trim().split("\\s+".toRegex()) }.toList()
    val numberLines = lines.asSequence().take(lines.size - 1).map { it.map(String::toULong) }
    val operations = lines.last()

    val cache: MutableList<ULong> = operations.map {
        if (it == "*") 1.toULong() else 0.toULong()
    }.toMutableList()
    for (numbers in numberLines) {
        for ((i, number) in numbers.withIndex()) {
            if (operations[i] == "*") {
                cache[i] *= number
            } else {
                cache[i] += number
            }
        }
    }
    println(cache.sum())
}
