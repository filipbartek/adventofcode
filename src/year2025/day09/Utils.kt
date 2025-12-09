package year2025.day09

import kotlin.math.abs
import kotlin.text.split

typealias Coordinate = Long

data class Position(val col: Coordinate, val row: Coordinate) {
    fun area() = (abs(col) + 1) * (abs(row) + 1)

    fun dominates(other: Position) = col >= other.col && row >= other.row
    
    fun within(rectangle: Rectangle) = col.within(rectangle.first.col, rectangle.second.col) && row.within(rectangle.first.row, rectangle.second.row)

    operator fun times(other: Position) = Position(col * other.col, row * other.row)

    operator fun minus(other: Position) = Position(col - other.col, row - other.row)

    operator fun unaryMinus() = Position(-col, -row)
    
    companion object {
        fun fromString(s: String) = s.split(",").map { it.toLong() }.let {
            assert(it.size == 2)
            Position(it.first(), it.last())
        }
    }
}

fun String.toPosition() = Position.fromString(this)

fun Coordinate.within(first: Coordinate, second: Coordinate): Boolean {
    if (first <= second) return this in (first + 1)..<second
    return this in (second + 1)..<first
}

typealias Rectangle = Pair<Position, Position>

fun Rectangle.area() = (second - first).area()
