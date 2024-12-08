package co.typecheck.adventofcode2024

private data class Input(
    val data: List<Char>,
    val width: Int,
    val height: Int,
    val frequencies: Map<Char, List<Pair<Int, Int>>>,
) {

    fun getXY(x: Int, y: Int): Char? =
        if (x in 0 until width && y in 0 until height) {
            data[x + y * width]
        } else {
            null
        }
}

private fun parse(input: String): Input {
    val lines = input.lines()
    var data = mutableListOf<Char>()
    var frequencies = mutableMapOf<Char, MutableList<Pair<Int, Int>>>()
    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, c ->
            data += c
            if (c != '.') {
                frequencies[c] = (frequencies[c] ?: mutableListOf()).also { it.add(x to y) }
            }
        }
    }

    return Input(data = data, height = lines.size, width = lines[0].length, frequencies = frequencies)
}

private fun day08(input: String, repeats: Boolean): Int {
    val data = parse(input)

    val antinodes = mutableSetOf<Pair<Int, Int>>()
    for (k in data.frequencies.keys) {
        val frequencies = data.frequencies[k]!!
        if (repeats) {
            frequencies.forEach(antinodes::add)
        }

        for (i in 0 until frequencies.size - 1) {
            val node1 = frequencies[i]

            for (k in i + 1 until frequencies.size) {
                val node2 = frequencies[k]
                val diff = (node1.first - node2.first) to (node1.second - node2.second)
                for (sign in sequenceOf(1, -1)) {
                    var i = 1
                    while (true) {
                        val newAntinode = if (sign == 1) {
                            (node1.first + sign * i * diff.first) to (node1.second + sign * i * diff.second)
                        } else {
                            (node2.first + sign * i * diff.first) to (node2.second + sign * i * diff.second)
                        }

                        if (data.getXY(newAntinode.first, newAntinode.second) == null) {
                            break
                        }

                        antinodes.add(newAntinode)

                        if (!repeats) {
                            break
                        }

                        i += 1
                    }
                }
            }
        }
    }

    return antinodes.size
}

fun day08Part1(input: String): Int = day08(input, repeats = false)

fun day08Part2(input: String): Int = day08(input, repeats = true)
