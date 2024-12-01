package co.typecheck.adventofcode2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {

    @Test
    fun day01Part1() {
        assertEquals(
            11,
            day01Part1(
                """
                    3   4
                    4   3
                    2   5
                    1   3
                    3   9
                    3   3
                """.trimIndent(),
            )
        )
    }

    @Test
    fun day01Part2() {
        assertEquals(
            31,
            day01Part2(
                """
                    3   4
                    4   3
                    2   5
                    1   3
                    3   9
                    3   3
                """.trimIndent(),
            )
        )
    }
}
