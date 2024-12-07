package co.typecheck.adventofcode2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day07Test {

    @Test
    fun day07Part1() {
        assertEquals(
            3749,
            day07Part1(
                """
                    190: 10 19
                    3267: 81 40 27
                    83: 17 5
                    156: 15 6
                    7290: 6 8 6 15
                    161011: 16 10 13
                    192: 17 8 14
                    21037: 9 7 18 13
                    292: 11 6 16 20
                """.trimIndent(),
            ),
        )
    }

    @Test
    fun day07Part2() {
        assertEquals(
            11387,
            day07Part2(
                """
                    190: 10 19
                    3267: 81 40 27
                    83: 17 5
                    156: 15 6
                    7290: 6 8 6 15
                    161011: 16 10 13
                    192: 17 8 14
                    21037: 9 7 18 13
                    292: 11 6 16 20
                """.trimIndent(),
            ),
        )
    }
}