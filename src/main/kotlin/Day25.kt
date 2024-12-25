package co.typecheck.adventofcode2024

private sealed interface Day25Item {

    val heights: List<Int>

    data class Lock(override val heights: List<Int>) : Day25Item
    data class Key(override val heights: List<Int>) : Day25Item
}

private fun parseItem(input: String): Day25Item {
    val lines = input.lines()
    val heights = mutableListOf<Int>()
    var firstChar = lines[0][0]
    for (x in 0 until lines[0].length) {
        var height = 0
        for (y in 0 until lines.size) {
            if (lines[y][x] != firstChar) {
                break
            }
            height += 1
        }
        heights.add(height)
    }

    return if (firstChar == '#') {
        Day25Item.Lock(heights)
    } else {
        Day25Item.Key(heights)
    }
}

fun day25(input: String): Long {
    val locks = mutableListOf<Day25Item.Lock>()
    val keys = mutableListOf<Day25Item.Key>()
    input.splitToSequence("\n\n")
        .map(::parseItem)
        .forEach {
            when (it) {
                is Day25Item.Key -> keys.add(it)
                is Day25Item.Lock -> locks.add(it)
            }
        }

    var fit = 0L
    for (lock in locks) {
        for (key in keys) {
            if (key.heights.zip(lock.heights).all { (k, l) -> k >= l }) {
                fit += 1
            }
        }
    }

    return fit
}
