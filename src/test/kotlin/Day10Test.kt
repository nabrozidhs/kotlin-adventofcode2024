package co.typecheck.adventofcode2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day10Test {

    @Test
    fun day10Part1() {
        assertEquals(
            36,
            day10Part1(
                """
                    89010123
                    78121874
                    87430965
                    96549874
                    45678903
                    32019012
                    01329801
                    10456732
                """.trimIndent(),
            ),
        )
    }

    @Test
    fun day10Part2() {
        assertEquals(
            81,
            day10Part2(
                """
                    89010123
                    78121874
                    87430965
                    96549874
                    45678903
                    32019012
                    01329801
                    10456732
                """.trimIndent(),
            ),
        )
    }
}