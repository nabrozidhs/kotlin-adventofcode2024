package co.typecheck.adventofcode2024

import kotlin.math.absoluteValue
import kotlin.math.sign

fun day02(input: String, canSkipOne: Boolean): Int =
    input.lineSequence()
        .map { it.split(" ").map(String::toInt) }
        .filter { line ->
            if (canSkipOne) {
                line.indices.any { isValid(line.take(it) + line.drop(it + 1)) }
            } else {
                isValid(line)
            }
        }
        .count()

private fun isValid(line: List<Int>): Boolean {
    val sign = (line[1] - line[0]).sign
    if (sign == 0) {
        return false
    }

    return (1 until line.size).all {
        val diff = line[it] - line[it - 1]
        diff.sign == sign && diff.absoluteValue <= 3
    }
}
