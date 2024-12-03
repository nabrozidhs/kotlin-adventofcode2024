package co.typecheck.adventofcode2024

import kotlin.test.Test
import kotlin.test.assertEquals

class Day03Test {

    @Test
    fun day03Part1() {
        assertEquals(161, day03Part1("xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))"))
    }

    @Test
    fun day03Part2() {
        assertEquals(48, day03Part2("xmul(2,4)&mul[3,7]!^don't()_mul(5,5)+mul(32,64](mul(11,8)undo()?mul(8,5))"))
    }
}