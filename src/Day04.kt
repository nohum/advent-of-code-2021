
class Board constructor(private val rows: List<List<Int>>) {

    private val marked = mutableListOf<Int>()

    fun mark(number: Int) {
        marked.add(number)
    }

    fun hasBingo(): Boolean {
        val bingoRows = this.rows
            .map { row -> marked.containsAll(row) }
            .count()
        if (bingoRows > 0) {
            return true;
        }

        val bingoCols = this.rows.map { rows -> 

        }
    }
}

fun main() {
    val input = readInput("Day04")

}
