enum class Direction(val value: String) {
    FORWARD("forward"),
    DOWN("down"),
    UP("up")
}

data class Command(
    val direction: Direction,
    val amount: Int
)

data class CurrentCourse(
    val depth: Int,
    val x: Int, // horizontal
    val aim: Int
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

fun applyCommand(current: CurrentCourse, command: Command): CurrentCourse =
    when (command.direction) {
        Direction.FORWARD -> current.copy(
            x = current.x + command.amount,
            depth = current.depth + (current.aim * command.amount)
        )
        Direction.DOWN -> current.copy(aim = current.aim + command.amount)
        Direction.UP -> current.copy(aim = current.aim - command.amount)
    }

fun main() {
    val input = readInput("Day02")
    val commands = parseCommands(input)


    val finalCourse = commands.fold(CurrentCourse(0, 0, 0)) { current, command ->
        applyCommand(current, command)
    }

    val answerTwo = finalCourse.x * finalCourse.depth

    // for answer one, see history of this file
    println("Answer (part two): $answerTwo")
}
