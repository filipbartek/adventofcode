package year2025.day08

import kotlin.collections.map
import kotlin.math.abs

fun main() {
    var pairsRemaining = 1000
    val circuitsCount = 3
    
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
    
    println((0..<boxPairs.lastIndex).filter { boxPairs[it + 1].distanceSquared - boxPairs[it].distanceSquared == 0.toLong() })
    for (i in (0..<boxPairs.lastIndex).sortedBy { boxPairs[it + 1].distanceSquared - boxPairs[it].distanceSquared }.take(20)) {
        val pair0 = boxPairs[i]
        val pair1 = boxPairs[i + 1]
        println("$pair0 ::: $pair1")
    }
    
    val circuits = DisjointSet(boxes.size)
    for (boxPair in boxPairs) {
        if (pairsRemaining == 0) break
        assert(boxPair.first != boxPair.second)
        if (circuits.union(boxPair.first, boxPair.second)) print('-') else print('.')
        pairsRemaining--
    }
    println()
    val circuitSizes = circuits.setSizes().values.sortedDescending()
    println(circuitSizes.take(circuitsCount).fold(1) { acc, size -> acc * size })
}
