package co.typecheck.adventofcode2024

private fun parse(input: String): Sequence<Pair<Long, List<Long>>> =
    input.lineSequence()
        .map { line ->
            val (a, b) = line.split(": ")

            a.toLong() to b.splitToSequence(" ").map(String::toLong).toList()
        }

private fun hasSolution(expected: Long, acc: Long, rem: List<Long>, hasConcat: Boolean): Boolean {
    if (acc > expected) {
        return false
    }

    if (rem.isEmpty()) {
        return expected == acc
    }

    val next = rem[0]
    val rest = rem.subList(1, rem.size)
    if (hasSolution(expected, acc + next, rest, hasConcat)) {
        return true
    }
    if (hasConcat && hasSolution(expected, (acc.toString() + next.toString()).toLong(), rest, true)) {
        return true
    }
    return hasSolution(expected, acc * next, rest, hasConcat)
}

fun day07Part1(input: String): Long =
    parse(input)
        .filter { (expected, rem) -> hasSolution(expected, rem.first(), rem.drop(1), false) }
        .sumOf { (result, _) -> result }

fun day07Part2(input: String): Long =
    parse(input)
        .filter { (expected, rem) -> hasSolution(expected, rem.first(), rem.drop(1), true) }
        .sumOf { (result, _) -> result }
