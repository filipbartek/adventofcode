package year2025.day11

import me.tongfei.progressbar.ProgressBar
import org.jetbrains.kotlinx.multik.api.d2arrayIndices
import org.jetbrains.kotlinx.multik.api.linalg.dot
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.ndarray.data.get
import org.jetbrains.kotlinx.multik.ndarray.operations.count
import org.jetbrains.kotlinx.multik.ndarray.operations.sum

class Tangle(val attachments: Map<String, List<String>>) {
    constructor(lines: Sequence<String>) : this(lines.associate {
        val words = it.split(" ")
        assert(words.first().last() == ':')
        val source = words.first().dropLast(1)
        val targets = words.drop(1)
        source to targets
    })

    val devices = (attachments.keys + attachments.values.flatten().toSet()).sorted()
    val adjacency = mk.d2arrayIndices(devices.size, devices.size) { i, j ->
        if (isConnected(
                devices[i],
                devices[j]
            )
        ) 1.toLong() else 0.toLong()
    }

    init {
        println("Devices: ${devices.size}")
        println("Maximum outputs per device: ${attachments.values.maxOf { it.size }}")
        println("Edges: ${adjacency.sum()}")
    }

    fun isConnected(source: String, target: String) = attachments[source]?.contains(target) ?: false

    fun countPathsLinalg(segments: List<Pair<String, String>>): LongArray {
        val sources = IntArray(segments.size) { devices.indexOf(segments[it].first) }
        val targets = IntArray(segments.size) { devices.indexOf(segments[it].second) }
        var counts = LongArray(segments.size) { 0 }
        var paths = adjacency.copy()
        val pbb = ProgressBar.builder().setInitialMax(devices.size.toLong()).setTaskName("Counting paths")
            .setMaxRenderedLength(132)
        pbb.build().use { pb ->
            while (true) {
                var remaining = 0.toLong()
                sources.zip(targets).forEachIndexed { i, (source, target) ->
                    counts[i] += paths[source, target]
                    remaining += paths[source].count { it > 0 }
                }
                assert(remaining >= 0)
                if (remaining == 0.toLong()) break
                paths = paths dot adjacency
                pb.step()
                pb.extraMessage = "${remaining}:${counts.reduce(Long::times)}"
            }
        }
        System.out.flush()
        return counts
    }

    fun countPathsDfs(source: String, target: String = "out"): Int {
        if (source == target) return 1
        if (source !in attachments) return 0
        return attachments[source]!!.sumOf { next ->
            countPathsDfs(next, target)
        }
    }
}
