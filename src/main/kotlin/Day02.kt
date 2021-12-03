fun main() {

    fun part1(input: List<String>): Int {
        var hpos = 0
        var dpos = 0

        for (move in input.toMovementList()) {
            when (move.direction) {
                "forward" -> hpos += move.amount
                "down" -> dpos += move.amount
                "up" -> dpos -= move.amount
            }
        }

        return hpos * dpos
    }

    fun part2(input: List<String>): Int {
        var hpos = 0
        var dpos = 0
        var aim = 0

        for (move in input.toMovementList()) {
            when (move.direction) {
                "forward" -> {
                    hpos += move.amount
                    dpos += move.amount * aim
                }
                "down" -> aim += move.amount
                "up" -> aim -= move.amount
            }
        }

        return hpos * dpos
    }

    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}

data class Movement(val direction: String, val amount: Int)

fun List<String>.toMovementList(): List<Movement> =
    this.map {
        val (direction, amount) = it.split(" ")
        Movement(direction, amount.toInt())
    }