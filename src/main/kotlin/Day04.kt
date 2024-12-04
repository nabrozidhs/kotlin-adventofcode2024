package co.typecheck.adventofcode2024


private fun buildLettersMap(lines: List<String>): Map<Pair<Int, Int>, Char> {
    val letters = mutableMapOf<Pair<Int, Int>, Char>()
    lines.forEachIndexed { y, line -> line.forEachIndexed { x, c -> letters[x to y] = c } }
    return letters
}


fun day04Part1(input: String): Int {
    val word = "XMAS"
    // array would be faster but needs bounds check which I'm lazy to do
    val lines = input.lines()
    val letters = buildLettersMap(lines)

    var count = 0
    for (y in lines.indices) {
        for (x in lines[y].indices) {
            if (letters[x to y] != word[0]) {
                continue
            }

            for (direction in sequenceOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to 1, 1 to -1, 1 to 0, 1 to 1)) {
                val matchesWord: Boolean = (1 until word.length).all {
                    letters[(x + direction.first * it) to (y + direction.second * it)] == word[it]
                }
                if (matchesWord) {
                    count += 1
                }
            }
        }
    }

    return count
}

fun day04Part2(input: String): Int {
    // array would be faster but needs bounds check which I'm lazy to do
    val lines = input.lines()
    val letters = buildLettersMap(lines)

    var count = 0
    for (y in lines.indices) {
        for (x in lines[y].indices) {
            if (letters[x to y] != 'A') {
                continue
            }

            for (direction in sequenceOf(1 to 1, 1 to -1, -1 to 1, -1 to -1)) {
                val matchesWord: Boolean = sequenceOf(-1, 1).all {
                    letters[(x + direction.first * it) to (y + direction.second * it)] == "MAS"[it + 1] &&
                            letters[(x - direction.second * it) to (y + direction.first * it)] == "MAS"[it + 1]
                }
                if (matchesWord) {
                    count += 1
                }
            }
        }
    }

    return count
}