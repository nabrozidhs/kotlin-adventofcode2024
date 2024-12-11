package co.typecheck.adventofcode2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day11Test {

    @Test
    fun day11Part1() {
        assertEquals(55312, day11Part1("125 17"))
    }

    @Test
    fun day11Part2() {
        assertEquals(65601038650482, day11Part2("125 17"))
    }
}