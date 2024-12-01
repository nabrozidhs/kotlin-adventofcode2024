package co.typecheck.adventofcode2024

import kotlin.math.abs

fun day01Part1(input: String): Int {
    var left = mutableListOf<Int>()
    val right = mutableListOf<Int>()
    input.lines().forEach {
        val (a, b) = it.split("   ").map(String::toInt)
        left += a
        right += b
    }
    left.sort()
    right.sort()
    return left.zip(right).sumOf { (a, b) -> abs(a - b) }
}

fun day01Part2(input: String): Int {
    var left = mutableListOf<Int>()
    val right = mutableMapOf<Int, Int>()
    input.lines().forEach {
        val (a, b) = it.split("   ").map(String::toInt)
        left += a
        right[b] = right.getOrDefault(b, 0) + 1
    }
    return left.sumOf { it * right.getOrDefault(it, 0) }
}
