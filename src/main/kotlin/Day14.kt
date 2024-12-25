package co.typecheck.adventofcode2024

private data class Day14Robot(
    val position: Pair<Long, Long>,
    val velocity: Pair<Long, Long>,
)

private fun parse(input: String): Day14Robot {
    val (p, v) = input.split(" ")
    val (px, py) = p.substring(2).split(",").map(String::toLong)
    val (vx, vy) = v.substring(2).split(",").map(String::toLong)
    return Day14Robot(position = px to py, velocity = vx to vy)
}

fun day14Part1(input: String, width: Int, height: Int): Long {
    val quadrantCounts = mutableMapOf<Int, Long>()
    input.lineSequence()
        .map(::parse)
        .forEach {
            val px = (it.position.first + 100 * it.velocity.first).mod(width)
            val py = (it.position.second + 100 * it.velocity.second).mod(height)
            if (px != width / 2 && py != height / 2) {
                quadrantCounts.compute(
                    (if (px > width / 2) 1 else 0) + (if (py > height / 2) 2 else 0),
                ) { _, v -> (v ?: 0) + 1 }
            }
        }

    return quadrantCounts.values.reduce { acc, l -> acc * l }
}

private fun printArrangement(robots: Set<Pair<Long, Long>>, width: Int, height: Int) {
    for (y in 0 until height) {
        for (x in 0 until width) {
            if (x.toLong() to y.toLong() in robots) {
                print("*")
            } else {
                print(".")
            }
        }
        println()
    }
}

fun day14Part2(input: String, width: Int, height: Int): Int {
    var robots = input.lineSequence()
        .map(::parse)
        .toList()
    var seconds = 0
    while (true) {
        if (robots.asSequence().map { it.position }.toSet().size == robots.size) {
            println("seconds: $seconds")
            printArrangement(robots.map { it.position }.toSet(), width, height)
            break
        }
        robots = robots.map {
            val px = (it.position.first + it.velocity.first).mod(width)
            val py = (it.position.second + it.velocity.second).mod(height)
            Day14Robot(px.toLong() to py.toLong(), it.velocity)
        }
        seconds += 1
    }
    return seconds
}
