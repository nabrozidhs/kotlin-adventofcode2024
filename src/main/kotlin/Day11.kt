package co.typecheck.adventofcode2024

private fun solution(input: String, n: Int): Long {
    var stones: Map<Long, Long> = input.splitToSequence(" ").map(String::toLong)
        .groupBy { it }
        .mapValues { it.value.size.toLong() }
    repeat(n) {
        val newStones = stones.toMutableMap()
        for ((key, value) in stones.entries) {
            when {
                key == 0L -> newStones.compute(1) { _, v -> (v ?: 0) + value }
                key.toString().length % 2 == 0 -> {
                    val kString = key.toString()
                    val first = kString.substring(0, kString.length / 2).toLong()
                    val second = kString.substring(kString.length / 2, kString.length).toLong()
                    newStones.compute(first) { _, v -> (v ?: 0) + value }
                    newStones.compute(second) { _, v -> (v ?: 0) + value }
                }

                else -> newStones.compute(key * 2024) { _, v -> (v ?: 0) + value }
            }
            if (newStones[key] == value) {
                newStones.remove(key)
            } else {
                newStones.compute(key) { _, v -> v!! - value }
            }
        }
        stones = newStones
    }
    return stones.values.sumOf { it }
}

fun day11Part1(input: String): Long = solution(input, 25)

fun day11Part2(input: String): Long = solution(input, 75)
