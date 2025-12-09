package year2025.day07

fun main() {
    val lines = generateSequence(::readlnOrNull).toList()
    val beams = lines.first().toBitSet('S')
    val length = lines.first().length
    var splits = 0
    for (line in lines.drop(1)) {
        val splitters = line.toBitSet('^')
        splitters.and(beams)
        splits += splitters.cardinality()
        beams.andNot(splitters)
        for (i in splitters.stream()) {
            if (i >= 1) beams.set(i - 1)
            if (i <= length) beams.set(i + 1)
        }
    }
    println(splits)
}
