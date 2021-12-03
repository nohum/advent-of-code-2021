import java.util.*

fun main() {
    val input = readInput("Day03")
    val width = input[0].length
    val columns = 0 until width

    val gamma = BitSet(width)
    for (column in columns) {
        val highLinesCount = input.count { line ->
            // high = bit at position is 1
            line[column] == '1'
        }

        // more 1's than 0's?
        if (highLinesCount > input.size - highLinesCount) {
            // set corresponding column bit to true
            gamma.set(columns.last - column)
        }
    }

    // expect that the result fits into a long
    val gammaRate = gamma.toLongArray()[0]
    gamma.flip(0, width);
    val epsilonRate = gamma.toLongArray()[0];
    val answerOne = gammaRate * epsilonRate

    println("Gamma rate: $gammaRate")
    println("Epsilon rate: $epsilonRate")
    println("Answer (part one): $answerOne")


}
