package year2025.day07

import java.util.BitSet

fun String.toBitSet(char: Char): BitSet {
    val bitSet = BitSet(this.length)
    this.asSequence().withIndex().filter { it.value == char }.forEach { bitSet.set(it.index) }
    return bitSet
}

fun BitSet.prettyString(t: Char = '1', f: Char = '.', size: Int = this.size()): String {
    require(size <= this.size())
    val builder = StringBuilder()
    for (i in 0 until size) builder.append(if (this.get(i)) t else f)
    return builder.toString()
}
