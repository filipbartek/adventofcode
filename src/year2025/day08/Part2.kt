package year2025.day08

import kotlin.collections.map
import kotlin.math.abs

fun main() {
    val boxes = generateSequence(::readlnOrNull).map { it.split(",").map(String::toLong) }.toList()

    data class BoxPair(val first: Int, val second: Int) {
        val relativeCoordinates = boxes[first].zip(boxes[second]).map { (x, y) -> abs(x - y) }
        val distanceSquared = relativeCoordinates.sumOf { it * it }
        override fun toString(): String = "${boxes[first]}-${boxes[second]}:$relativeCoordinates:$distanceSquared"
    }
    
    val boxPairs = (0..boxes.lastIndex).flatMap { first -> (first + 1..boxes.lastIndex).mapNotNull { second ->
        assert(first < second)
        BoxPair(first, second)
    } }.sortedBy { it.distanceSquared }
    
    val circuits = DisjointSet(boxes.size)
    var res: Long = 0
    for (boxPair in boxPairs) {
        assert(boxPair.first != boxPair.second)
        if (circuits.union(boxPair.first, boxPair.second)) {
            res = boxes[boxPair.first].first() * boxes[boxPair.second].first()
        }
    }
    println(res)
}
