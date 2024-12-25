package co.typecheck.adventofcode2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day25Test {

    @Test
    fun day25() {
        assertEquals(
            3,
            day25(
                """
                    #####
                    .####
                    .####
                    .####
                    .#.#.
                    .#...
                    .....

                    #####
                    ##.##
                    .#.##
                    ...##
                    ...#.
                    ...#.
                    .....

                    .....
                    #....
                    #....
                    #...#
                    #.#.#
                    #.###
                    #####

                    .....
                    .....
                    #.#..
                    ###..
                    ###.#
                    ###.#
                    #####

                    .....
                    .....
                    .....
                    #....
                    #.#..
                    #.#.#
                    #####
                """.trimIndent(),
            ),
        )
    }
}