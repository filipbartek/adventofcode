package year2025.day05

fun main() {
    val ranges =
        generateSequence(::readlnOrNull)
            .takeWhile { it.isNotBlank() }
            .map { it.split("-").map { it.toULong() } }
            .toList()
    val count = generateSequence(::readlnOrNull).count {
        ranges.any { (start, end) -> it.toULong() in start..end }
    }
    println(count)
}
