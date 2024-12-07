package co.typecheck.adventofcode2024

private data class UserMap(
    val width: Int,
    val height: Int,
    val data: List<Char>,
    val startPosition: Pair<Int, Int>,
)

private fun parse(input: String): UserMap {
    val lines = input.lines()
    val width = lines.first().length
    val height = lines.size
    val data = ArrayList<Char>(width * height)
    var startPosition = Pair(0, 0)
    lines.forEachIndexed { y, line ->
        line.forEachIndexed { x, c ->
            data.add(c)
            if (c == '^') {
                startPosition = Pair(x, y)
            }
        }
    }

    return UserMap(width, height, data, startPosition)
}

private fun findPath(userMap: UserMap): Set<Pair<Int, Int>>? {
    var direction = 0 to -1
    val visited = mutableSetOf(userMap.startPosition)
    val visitedWithDirection = mutableSetOf(userMap.startPosition to direction)
    var currentPosition = userMap.startPosition

    while (true) {
        val nextPosition = Pair(currentPosition.first + direction.first, currentPosition.second + direction.second)
        if (nextPosition.first !in 0 until userMap.width ||
            nextPosition.second !in 0 until userMap.height
        ) {
            break
        } else if (userMap.data[nextPosition.first + nextPosition.second * userMap.width] == '#') {
            direction = -direction.second to direction.first
        } else {
            currentPosition = nextPosition
            if (nextPosition to direction in visitedWithDirection) {
                return null
            }
            visitedWithDirection += nextPosition to direction
            visited += nextPosition
        }
    }

    return visited
}

fun day06Part1(input: String): Int = findPath(parse(input))!!.size

fun day06Part2(input: String): Int {
    val userMap = parse(input)
    val path = findPath(userMap)!!

    return path.drop(1).count { p ->
        findPath(
            userMap.copy(
                data = userMap.data.toMutableList().apply { set(p.first + p.second * userMap.width, '#') },
            ),
        ) == null
    }
}
