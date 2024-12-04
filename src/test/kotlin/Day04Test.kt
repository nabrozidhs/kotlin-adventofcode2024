package co.typecheck.adventofcode2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day04Test {

    @Test
    fun day04Part1() {
        assertEquals(
            18,
            day04Part1(
                """
                    MMMSXXMASM
                    MSAMXMSMSA
                    AMXSXMAAMM
                    MSAMASMSMX
                    XMASAMXAMM
                    XXAMMXXAMA
                    SMSMSASXSS
                    SAXAMASAAA
                    MAMMMXMMMM
                    MXMXAXMASX
                """.trimIndent(),
            ),
        )
    }

    @Test
    fun day04Part2() {
        assertEquals(
            9,
            day04Part2(
                """
                    MMMSXXMASM
                    MSAMXMSMSA
                    AMXSXMAAMM
                    MSAMASMSMX
                    XMASAMXAMM
                    XXAMMXXAMA
                    SMSMSASXSS
                    SAXAMASAAA
                    MAMMMXMMMM
                    MXMXAXMASX
                """.trimIndent(),
            ),
        )
    }
}