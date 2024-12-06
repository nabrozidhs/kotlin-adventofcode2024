package co.typecheck.adventofcode2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day06Test {

    @Test
    fun day06Part1() {
        assertEquals(
            41,
            day06Part1(
                """
                    ....#.....
                    .........#
                    ..........
                    ..#.......
                    .......#..
                    ..........
                    .#..^.....
                    ........#.
                    #.........
                    ......#...
                """.trimIndent(),
            ),
        )
    }

    @Test
    fun day06Part2() {
        assertEquals(
            6,
            day06Part2(
                """
                    ....#.....
                    .........#
                    ..........
                    ..#.......
                    .......#..
                    ..........
                    .#..^.....
                    ........#.
                    #.........
                    ......#...
                """.trimIndent(),
            ),
        )
    }
}