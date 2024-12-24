package co.typecheck.adventofcode2024

sealed interface Day24Result {
    data class Connection(val a: String, val b: String, val op: String) : Day24Result

    @JvmInline
    value class Value(val result: Boolean) : Day24Result
}

private fun parse(input: String): MutableMap<String, Day24Result> {
    val parsed = mutableMapOf<String, Day24Result>()
    var (inputs, connections) = input.split("\n\n")
    inputs.lineSequence().forEach { line ->
        val (gate, result) = line.split(": ")
        parsed[gate] = Day24Result.Value(result == "1")
    }
    connections.lineSequence().forEach { line ->
        val (gate1, op, gate2, _, output) = line.split(" ")
        parsed[output] = Day24Result.Connection(gate1, gate2, op)
    }

    return parsed
}

private fun eval(key: String, input: MutableMap<String, Day24Result>): Boolean =
    when (val r = input[key]!!) {
        is Day24Result.Value -> r.result
        is Day24Result.Connection -> {
            val a = eval(r.a, input)
            val b = eval(r.b, input)
            val result = when (r.op) {
                "AND" -> a && b
                "OR" -> a || b
                "XOR" -> a != b
                else -> throw IllegalArgumentException()
            }
            input[key] = Day24Result.Value(result)
            result
        }
    }

private fun output(input: MutableMap<String, Day24Result>): Long =
    input.keys.asSequence()
        .filter { it.startsWith("z") }
        .sorted()
        .mapIndexed { index, key ->
            val result = eval(key, input)
            if (result) {
                1L shl index
            } else {
                0L
            }
        }
        .sum()

fun day24Part1(input: String): Long = output(parse(input))

fun day24Part2(input: String): Long {
    val parsed: MutableMap<String, Day24Result> = parse(input)
    parsed.keys.asSequence()
        .filter { it.startsWith("x") or it.startsWith("y") }
        .forEach { parsed[it] = Day24Result.Value(false) }
    val gateSize = parsed.keys.count {
        it.startsWith("x")
    }
    parsed["fdv"] = Day24Result.Connection("x06", "y06", "XOR")
    parsed["dbp"] = Day24Result.Connection("x06", "y06", "AND")
    parsed["z39"] = Day24Result.Connection("vbt", "vqr", "XOR")
    parsed["rpp"] = Day24Result.Connection("vbt", "vqr", "AND")
    parsed["ckj"] = Day24Result.Connection("x15", "y15", "AND")
    parsed["z15"] = Day24Result.Connection("qbw", "fqf", "XOR")
    parsed["kdf"] = Day24Result.Connection("rqt", "rdt", "OR")
    parsed["z23"] = Day24Result.Connection("nsr", "gsd", "XOR")
    repeat(gateSize) { i ->
        val parsedClone = parsed.toMutableMap()
        for (gate in sequenceOf("x", "y")) {
            parsedClone["%s%02d".format(gate, i)] = Day24Result.Value(true)
        }
        val result = output(parsedClone)
        val expected = 1L shl (i + 1)
        if (result != expected) {
            println("gate %02d produces invalid output, expected %s got %s".format(i, expected, result))
        }
    }
    println(sequenceOf("fdv", "dbp", "z39", "rpp", "ckj", "z15", "kdf", "z23").sorted().joinToString(","))
    return 0
}
