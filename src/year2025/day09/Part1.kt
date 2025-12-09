package year2025.day09

fun main() {
    val directions1d = listOf((-1).toLong(), 1.toLong())
    val dominators = directions1d.flatMap { col -> directions1d.map { row -> Position(col, row) } }.associateWith { mutableSetOf<Position>() }
    for (position in generateSequence(::readlnOrNull).map(String::toPosition)) {
        for ((direction, dominatorSet) in dominators) {
            if (dominatorSet.none { (it * direction).dominates(position * direction) }) {
                dominatorSet.removeAll { (position * direction).dominates(it * direction) }
                dominatorSet.add(position)
            }
        }
    }
    val res = dominators.keys.take(2).flatMap { direction ->
        dominators[direction]!!.flatMap { first -> dominators[-direction]!!.map { second ->
            (second - first).area()
        } }
    }.max()
    println(res) // 4782896435
}
