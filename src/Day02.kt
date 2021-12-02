enum class Direction(val value: String) {
    FORWARD("forward"),
    DOWN("down"),
    UP("up")
}

data class Command(
    val direction: Direction,
    val amount: Int
)

data class Location(
    val depth: Int,
    val x: Int, // horizontal
)

fun parseCommands(input: List<String>): List<Command> = input.map { raw ->
    val (rawCommand, rawAmount) = raw.split(" ")
    val command = when (rawCommand) {
        Direction.FORWARD.value -> Direction.FORWARD
        Direction.DOWN.value -> Direction.DOWN
        Direction.UP.value -> Direction.UP
        else -> throw Exception("unknown command: $rawCommand")
    }

    Command(command, rawAmount.toInt())
}

fun applyCommand(current: Location, command: Command): Location = when (command.direction) {
    Direction.FORWARD -> current.copy(x = current.x + command.amount)
    Direction.DOWN -> current.copy(depth = current.depth + command.amount)
    Direction.UP -> current.copy(depth = current.depth - command.amount)
}

fun main() {
    val input = readInput("Day02")
    val commands = parseCommands(input)


    val finalLocation = commands.fold(Location(0, 0)) { current, command ->
        applyCommand(current, command)
    }

    val answerOne = finalLocation.x * finalLocation.depth

    println("Answer (part one): $answerOne")
}
