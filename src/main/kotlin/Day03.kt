package co.typecheck.adventofcode2024

private val regex = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)")
fun day03Part1(input: String): Int =
    regex.findAll(input)
        .map { it.groupValues[1].toInt() * it.groupValues[2].toInt() }
        .sum()

private val regex2 = Regex("mul\\((\\d{1,3}),(\\d{1,3})\\)|do\\(\\)|don't\\(\\)")
fun day03Part2(input: String): Int {
    var active = true
    return regex2.findAll(input)
        .map {
            when {
                it.value.startsWith("do()") -> {
                    active = true
                    0
                }
                it.value.startsWith("don't()") -> {
                    active = false
                    0
                }
                active -> it.groupValues[1].toInt() * it.groupValues[2].toInt()
                else -> 0
            }
        }
        .sum()
}