package year2025.day11

fun main() {
    val tangle = Tangle(generateSequence(::readlnOrNull))
    assert(tangle.countPathsDfs("dac", "fft") == 0)
    val checkpoints = listOf("svr", "fft", "dac", "out")
    val segments = checkpoints.dropLast(1).zip(checkpoints.drop(1))
    val pathCounts = tangle.countPathsLinalg(segments)
    println(pathCounts.reduce(Long::times)) // 333852915427200
}
