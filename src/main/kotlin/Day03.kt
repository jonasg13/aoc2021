fun main() {

    fun part1(input: List<String>): Int {
        // get the most common bit value for each bit in all input
        val β = input[0].indices.map { input.charFreqByCol(it).entries.maxByOrNull { it.value }!!.key }
        val ɣ = β.joinToString("")
        val ε = β.map { c -> (c.code xor 1).toChar() }.joinToString("") // epsilon = xor'd gamma
        println("ɣ: $ɣ (${ɣ.toInt(2)}); ε: $ε (${ε.toInt(2)}); power: ${ɣ.toInt(2) * ε.toInt(2)}")
        return ɣ.toInt(2) * ε.toInt(2)
    }

    fun part2(input: List<String>): Int {
        val o2 = input.filterColsForChars { z, o -> if (z > o) '0' else '1' }
        val co2 = input.filterColsForChars { z, o -> if (z > o) '1' else '0' }
        println("o2: $o2 (${o2.toInt(2)}); co2: $co2 (${co2.toInt(2)}); rate: ${o2.toInt(2) * co2.toInt(2)}")
        return o2.toInt(2) * co2.toInt(2)
    }

    val testInput = readInput("Day03_test")
    check(part1(testInput) == 198)
    check(part2(testInput) == 230)

    val input = readInput("Day03")
    println(part1(input))
    println(part2(input))
}

fun List<String>.charFreqByCol(col: Int): Map<Char, Int> = this.groupingBy { it[col] }.eachCount()

fun List<String>.filterColsForChars(bitCriteria: (z: Int, o: Int) -> Char): String {
    var candList = this // candidate list
    for (col in this[0].indices) {
        val charFreq = candList.charFreqByCol(col)
        val (zeros, ones) = Pair(charFreq['0'] ?: 0, charFreq['1'] ?: 0)
        // throw away unwanted candidates until only one is present
        candList = candList.filter { it[col] == bitCriteria(zeros, ones) }
        if (candList.size == 1) break
    }
    return candList.single()
}
