package co.typecheck.adventofcode2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day02Test {

    @Test
    fun day02Part1() {
        assertEquals(
            2,
            day02(
                """
                    7 6 4 2 1
                    1 2 7 8 9
                    9 7 6 2 1
                    1 3 2 4 5
                    8 6 4 4 1
                    1 3 6 7 9
                """.trimIndent(),
                canSkipOne = false,
            )
        )
    }

    @Test
    fun day02Part2() {
        assertEquals(
            4,
            day02(
                """
                    7 6 4 2 1
                    1 2 7 8 9
                    9 7 6 2 1
                    1 3 2 4 5
                    8 6 4 4 1
                    1 3 6 7 9
                """.trimIndent(),
                canSkipOne = true,
            )
        )
    }
}