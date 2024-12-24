package co.typecheck.adventofcode2024

private data class Day13Input(
    val a: Pair<Long, Long>,
    val b: Pair<Long, Long>,
    val prize: Pair<Long, Long>,
)

private fun parseItem(input: String, part2: Boolean): Day13Input {
    val (a, b, prize) = input.lines()
    val aSplit = a.split(" ")
    val bSplit = b.split(" ")
    val prizeSplit = prize.split(" ")
    val p = if (part2) {
        (10000000000000 + prizeSplit[1].substring(2, prizeSplit[1].length - 1).toLong()) to
                (10000000000000 + prizeSplit[2].substring(2).toLong())
    } else {
        prizeSplit[1].substring(2, prizeSplit[1].length - 1).toLong() to prizeSplit[2].substring(2).toLong()
    }
    return Day13Input(
        a = aSplit[2].substring(2, aSplit[2].length - 1).toLong() to aSplit[3].substring(2).toLong(),
        b = bSplit[2].substring(2, bSplit[2].length - 1).toLong() to bSplit[3].substring(2).toLong(),
        prize = p,
    )
}

private fun findCombination(input: Day13Input, part2: Boolean): Long {
    val b: Long = (input.prize.first * input.a.second - input.prize.second * input.a.first) / (input.b.first * input.a.second - input.a.first * input.b.second)
    if (b < 0 || (!part2 && b !in 0..100)) {
        return 0
    }
    val a: Long = (input.prize.first - input.b.first * b) / input.a.first
    if (a < 0 || (!part2 && a !in 0..100)) {
        return 0
    }
    if ((input.a.first * a + input.b.first * b != input.prize.first) ||
            (input.a.second * a + input.b.second * b != input.prize.second)) {
        return 0
    }
    return 3 * a + b
}

private fun solve(input: String, part2: Boolean): Long =
    input.split("\n\n")
        .map { parseItem(it, part2) }
        .sumOf { findCombination(it, part2) }

fun day13Part1(input: String): Long = solve(input, false)
fun day13Part2(input: String): Long = solve(input, true)
