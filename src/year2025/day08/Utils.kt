package year2025.day08

class DisjointSet(size: Int) {
    // https://kotlin.algorithmexamples.com/web/datastructures/DisjointSet.html
    private val parent = IntArray(size)
    private val rank = ByteArray(size)
    var count = size
        private set

    init {
        for (i in parent.indices) {
            parent[i] = i
        }
    }

    fun connected(v: Int, w: Int): Boolean {
        return find(v) == find(w)
    }

    fun find(v: Int): Int {
        var v = v
        while (parent[v] != v) {
            parent[v] = parent[parent[v]]
            v = parent[v]
        }
        return v
    }

    fun union(v: Int, w: Int): Boolean {
        val rootV = find(v)
        val rootW = find(w)
        if (rootV == rootW) return false
        if (rank[rootV] > rank[rootW]) {
            parent[rootW] = rootV
        } else if (rank[rootW] > rank[rootV]) {
            parent[rootV] = rootW
        } else {
            parent[rootV] = rootW
            rank[rootW]++
        }
        count--
        return true
    }

    fun setSizes(): Map<Int, Int> {
        val sizes = mutableMapOf<Int, Int>().withDefault { 0 }
        for (i in parent.indices) {
            val curParent = find(i)
            sizes[curParent] = sizes.getOrDefault(curParent, 0) + 1
        }
        assert(count == sizes.size)
        assert(parent.size == sizes.values.sum())
        return sizes
    }
}
