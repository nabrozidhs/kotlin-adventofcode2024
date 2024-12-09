package co.typecheck.adventofcode2024

fun toExpanded(input: String): List<Long> {
    val expanded = mutableListOf<Long>()
    input.map { it.digitToInt() }.forEachIndexed { index, c ->
        if (index % 2 == 0) {
            repeat(c) {
                expanded += index / 2L
            }
        } else {
            repeat(c) {
                expanded += -1
            }
        }
    }
    return expanded
}

fun day09Part1(input: String): Long {
    val expanded = toExpanded(input)
    var checksum = 0L

    var left = 0
    var right = expanded.size - 1

    while (left <= right) {
        if (expanded[left] != -1L) {
            checksum += expanded[left] * left
        } else {
            while (expanded[right] == -1L) {
                right -= 1
            }
            checksum += expanded[right] * left
            right -= 1
        }
        left += 1
    }

    return checksum
}

fun day09Part2(input: String): Long {
    val rearranged = toExpanded(input).toMutableList()

    var right = rearranged.size - 1

    while (right > 0) {
        if (rearranged[right] == -1L) {
            right -= 1
        } else {
            val rightGroup = (right downTo 0).asSequence().takeWhile { rearranged[it] == rearranged[right] }.count()
            var left = 0
            var found = false
            while (left < right) {
                while (left < rearranged.size && rearranged[left] != -1L) {
                    left += 1
                }
                val leftFreeGroup =
                    (left until rearranged.size).asSequence().takeWhile { rearranged[it] == -1L }.count()
                if (leftFreeGroup >= rightGroup) {
                    found = true
                    break
                }
                left += leftFreeGroup
            }

            if (found && left <= right - rightGroup) {
                repeat(rightGroup) {
                    rearranged[left + it] = rearranged[right - it]
                    rearranged[right - it] = -1
                }
            }
            right -= rightGroup
        }
    }

    return rearranged
        .mapIndexedNotNull { index, v -> v.takeIf { it != -1L }?.let { it * index } }
        .sum()
}
