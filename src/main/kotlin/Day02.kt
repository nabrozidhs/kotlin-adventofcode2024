package co.typecheck.adventofcode2024

private val regex = Regex("(\\d+) (red|green|blue)")
fun day02Part1(input: String): Int {
    val totalItems = mapOf(
        "red" to 12,
        "green" to 13,
        "blue" to 13,
    )
    return input.lines()
        .mapIndexedNotNull { index, line ->
            val isValid = regex.findAll(line)
                .map { it.groupValues[1].toInt() to it.groupValues[2] }
                .all { it.first <= totalItems[it.second]!! }
            if (isValid) {
                index + 1
            } else {
                null
            }
        }
        .sum()
}

fun day02Part2(input: String): Int =
    input.lines()
        .sumOf { line ->
            val items = mutableMapOf<String, Int>()
            line.split(";")
                .forEach { group ->
                    regex.findAll(group)
                        .forEach {
                            val key = it.groupValues[2]
                            val value = it.groupValues[1].toInt()
                            if (items.getOrDefault(key, 0) < value) {
                                items[key] = value
                            }
                        }
                }
            items.values.reduce { acc, v -> acc * v }
        }
