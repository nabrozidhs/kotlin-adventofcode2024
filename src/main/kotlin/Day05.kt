package co.typecheck.adventofcode2024

private fun parse(input: String): Pair<Set<Pair<Int, Int>>, Sequence<List<Int>>> {
    val (rulesInput, updateInput) = input.split("\n\n")

    val rules: Set<Pair<Int, Int>> = rulesInput.lineSequence()
        .map {
            val (a, b) = it.splitToSequence("|").map(String::toInt).toList()
            a to b
        }
        .toSet()

    val updates = updateInput.lineSequence()
        .map { it.splitToSequence(",").map(String::toInt).toList() }

    return rules to updates
}

private fun isSorted(pages: List<Int>, rules: Set<Pair<Int, Int>>): Boolean {
    for (i in 0 until pages.size - 1) {
        val page = pages[i]
        for (j in i + 1 until pages.size) {
            if (page to pages[j] !in rules) {
                return false
            }
        }
    }
    return true
}

fun day05Part1(input: String): Int {
    val (rules, updates) = parse(input)
    return updates
        .filter { isSorted(it, rules) }
        .sumOf { it[it.size / 2] }
}

fun day05Part2(input: String): Int {
    val (rules, updates) = parse(input)
    return updates
        .filterNot { isSorted(it, rules) }
        .map { pages ->
            pages.sortedWith { left, right ->
                when {
                    left to right in rules -> -1
                    right to left in rules -> 1
                    else -> 0
                }
            }
        }
        .sumOf { it[it.size / 2] }
}