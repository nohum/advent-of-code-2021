fun main() {
    val input = readInput("Day01")

    val simpleIncreases = input.map { value -> Integer.parseInt(value) }
        .zipWithNext()
        .map { (first, second) -> second > first }
        .count { result -> result };

    println("Simple increases (part one): $simpleIncreases")

    val windowedIncreases = input.map { value -> Integer.parseInt(value) }
        .windowed(3)
        .map { (first, second, third) -> first + second + third }
        .zipWithNext()
        .map { (first, second) -> second > first }
        .count { result -> result };

    println("Windowed increases (part two): $windowedIncreases")
}
