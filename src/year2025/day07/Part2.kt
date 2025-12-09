package year2025.day07

fun main() {
    val lines = generateSequence(::readlnOrNull).toList()
    var beams: List<ULong> = lines.first().map { if (it == 'S') 1u else 0u }
    val length = lines.first().length
    for (line in lines.drop(1)) {
        var nextBeams = beams.toMutableList()
        line.withIndex().filter { it.value == '^' }.forEach {
            val i = it.index
            nextBeams[i] = 0u
            if (i >= 1) nextBeams[i - 1] += beams[i]
            if (i < length) nextBeams[i + 1] += beams[i]
        }
        beams = nextBeams
    }
    println(beams.sum())
}
