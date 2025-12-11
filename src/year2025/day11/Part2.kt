package year2025.day11

fun main() {
    val tangle = Tangle(generateSequence(::readlnOrNull))
    assert(tangle.countPathsDfs("dac", "fft") == 0)
    val checkpoints = listOf("svr", "fft", "dac", "out")
    val res = checkpoints.dropLast(1).zip(checkpoints.drop(1)).fold(1.toLong()) { acc, (source, target) ->
        val paths = tangle.countPathsLinalg(source, target)
        println("$source->$target: $paths. ${checkpoints.first()}->$target: ${acc * paths}.")
        acc * paths
    }
    println(res) // 333852915427200
}
