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
        (2..s.length).any {
            if (s.length % it != 0) return@any false
            val substringLength = s.length / it
            val first = s.substring(0, substringLength)
            for (i in 1..it - 1) {
                val substring = s.substring(i * substringLength, (i+1) * substringLength)
                if (substring != first) return@any false
            }
            true
        }
    }
    val res = invalidIds.sum()
    println(res)
}
