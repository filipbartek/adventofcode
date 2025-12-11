package year2025.day11

import me.tongfei.progressbar.ProgressBar
import org.jetbrains.kotlinx.multik.api.d2arrayIndices
import org.jetbrains.kotlinx.multik.api.linalg.dot
import org.jetbrains.kotlinx.multik.api.mk
import org.jetbrains.kotlinx.multik.ndarray.data.get
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

    fun countPathsLinalg(source: String, target: String): Long {
        assert(source in devices)
        val sourceI = devices.indexOf(source)
        assert(target in devices)
        val targetI = devices.indexOf(target)
        var paths = adjacency.copy()
        var count = 0.toLong()
        ProgressBar("$source->$target", devices.size.toLong()).use { pb ->
            while (paths.sum() > 0) {
                count += paths[sourceI, targetI]
                paths = paths dot adjacency
                pb.step()
                pb.extraMessage = "${paths.sum()}:$count"
            }
        }
        System.out.flush()
        return count
    }

    fun countPathsDfs(source: String, target: String = "out"): Int {
        if (source == target) return 1
        if (source !in attachments) return 0
        return attachments[source]!!.sumOf { next ->
            countPathsDfs(next, target)
        }
    }
}
