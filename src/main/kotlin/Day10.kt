package co.typecheck.adventofcode2024

private data class Day10Input(
    val data: List<Int>,
    val width: Int,
    val height: Int,
) {

    operator fun contains(p: Pair<Int, Int>): Boolean =
        p.first in 0 until width && p.second in 0 until height

    operator fun get(x: Int, y: Int): Int = data[x + y * width]
    operator fun get(p: Pair<Int, Int>): Int = this[p.first, p.second]
}

private fun parse(input: String): Day10Input {
    val lines = input.lines()
    val data = mutableListOf<Int>()
    lines.forEach { line ->
        line.forEach { c ->
            data.add(c.digitToIntOrNull() ?: -1)
        }
    }

    return Day10Input(data = data, height = lines.size, width = lines[0].length)
}

private fun findStartingPositions(input: Day10Input): List<Pair<Int, Int>> {
    val startingPosition = mutableListOf<Pair<Int, Int>>()
    for (y in 0 until input.height) {
        for (x in 0 until input.width) {
            if (input[x, y] == 0) {
                startingPosition.add(Pair(x, y))
            }
        }
    }
    return startingPosition
}

private fun search(input: String, onFound: (Pair<Pair<Int, Int>, Pair<Int, Int>>) -> Unit) {
    val data = parse(input)
    val startingPositions = findStartingPositions(data)

    val queue = startingPositions.map { listOf(it) }.toMutableList()
    while (queue.isNotEmpty()) {
        val path = queue.removeAt(0)
        if (path.size == 10) {
            onFound(path.first() to path.last())
            continue
        }

        sequenceOf(-1 to 0, 1 to 0, 0 to -1, 0 to 1)
            .map { it.first + path.last().first to it.second + path.last().second }
            .filter{ it in data }
            .filter { data[it] == path.size }
            .forEach { queue += path + it }
    }
}

fun day10Part1(input: String): Int {
    var found = mutableSetOf<Pair<Pair<Int, Int>, Pair<Int, Int>>>()
    search(input, found::add)
    return found.size
}

fun day10Part2(input: String): Int {
    var found = 0
    search(input) { found += 1 }
    return found
}