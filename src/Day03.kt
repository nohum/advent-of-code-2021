import java.util.*

fun conquerInput(preferredInput: Char, inputs: List<String>, column: Int): String {
    val ones = inputs.filter { line ->
        line[column] == '1'
    }
    val zeros = inputs.filter { line ->
        line[column] == '0'
    }

    val candidate: List<String> = if (preferredInput == '1') {
        when {
            ones.size > zeros.size -> ones
            ones.size < zeros.size -> zeros
            else -> ones
        }
    } else {
        when {
            ones.size < zeros.size -> ones
            ones.size > zeros.size -> zeros
            else -> zeros
        }
    }

    if (candidate.size == 1) {
        return candidate[0]
    }

    return conquerInput(preferredInput, candidate, column + 1)
}

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

    val oxygenInput = conquerInput('1', input, 0)
    println("Oxygen input: $oxygenInput")

    val co2ScrubberInput = conquerInput('0', input, 0)
    println("CO2 scrubber input: $co2ScrubberInput")

    val oxygen = Integer.parseInt(oxygenInput, 2)
    val co2Scrubber = Integer.parseInt(co2ScrubberInput, 2)

    println("Answer (part two): ${oxygen * co2Scrubber}")
}
