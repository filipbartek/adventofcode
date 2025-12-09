package year2025.day09

import kotlin.math.max
import kotlin.math.min

fun main() {
    val redTiles = generateSequence(::readlnOrNull).map(String::toPosition).toList()
    val redTilePairs = redTiles.withIndex().flatMap { (index, first) -> redTiles.subList(index + 1, redTiles.size).map { second -> first to second } }
    val edges = redTiles.zip(redTiles.drop(1) + redTiles.take(1))
    var maxArea = 0.toLong()
    for (rectangle in redTilePairs) {
        val area = rectangle.area()
        if (area <= maxArea) continue
        if (edges.any { it.crosses(rectangle) }) continue
        maxArea = area
    }
    println(maxArea) // 1540060480
}

typealias Edge = Pair<Position, Position>

fun Edge.crosses(rectangle: Rectangle): Boolean {
    if (first == rectangle.first || first == rectangle.second || second == rectangle.first || second == rectangle.second) return false
    if (first.col == second.col) {
        val col = first.col
        if (!col.within(rectangle.first.col, rectangle.second.col)) return false
        val rowMin = min(rectangle.first.row, rectangle.second.row)
        if (first.row <= rowMin && second.row <= rowMin) return false
        val rowMax = max(rectangle.first.row, rectangle.second.row)
        if (first.row >= rowMax && second.row >= rowMax) return false
    } else if (first.row == second.row) {
        val row = first.row
        if (!row.within(rectangle.first.row, rectangle.second.row)) return false
        val colMin = min(rectangle.first.col, rectangle.second.col)
        if (first.col <= colMin && second.col <= colMin) return false
        val colMax = max(rectangle.first.col, rectangle.second.col)
        if (first.col >= colMax && second.col >= colMax) return false
        
    } else throw IllegalStateException()
    return true
}
