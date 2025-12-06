package year2025.day02

fun main() {
    val line = readln()
    val ranges = line.split(',')
    val ids = ranges.asSequence().flatMap { range ->
        val (start, end) = range.split('-').map { it.toULong() }
        start..end
    }
    val invalidIds = ids.filter { id ->
        val s = id.toString()
        (s.length % 2 == 0) && (s.substring(0, s.length / 2) == s.substring(s.length / 2))
    }
    val res = invalidIds.sum()
    println(res)
}
