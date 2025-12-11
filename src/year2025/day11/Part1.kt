package year2025.day11

fun main() {
    val tangle = Tangle(generateSequence(::readlnOrNull))
    println(tangle.countPathsDfs("you")) // 714
}
