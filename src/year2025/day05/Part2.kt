package year2025.day05

fun main() {
    val ranges =
        generateSequence(::readlnOrNull)
            .takeWhile { it.isNotBlank() }
            .map { it.split("-").map { it.toULong() } }.map { it[0] to it[1] }
    var finalRanges = mutableListOf<Pair<ULong, ULong>>()
    for (range in ranges) {
        var newRange = range
        var doAdd = true
        var tbr = mutableListOf<Pair<ULong, ULong>>()
        for (prevRange in finalRanges) {
            if (prevRange.second < newRange.first) continue
            if (newRange.second < prevRange.first) continue
            if (prevRange.first <= newRange.first && newRange.second <= prevRange.second) {
                // newRange is completely contained in prevRange
                doAdd = false
                break
            }
            tbr.add(prevRange)
            if (newRange.first <= prevRange.first && prevRange.second <= newRange.second) continue
            if (prevRange.first <= newRange.first && newRange.first <= prevRange.second + 1u) newRange = prevRange.first to newRange.second
            else if (prevRange.second >= newRange.second && newRange.second + 1u >= prevRange.first) newRange = newRange.first to prevRange.second
        }
        finalRanges.removeAll(tbr)
        if (doAdd) finalRanges.add(newRange)
    }
    val count = finalRanges.sumOf { (start, end) -> (end - start + 1u) }
    println(count)
}
