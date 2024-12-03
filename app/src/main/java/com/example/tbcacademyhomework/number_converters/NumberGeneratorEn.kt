package com.example.tbcacademyhomework.number_converters

class NumberGeneratorEn : NumberGenerator {

    operator fun invoke(number: Long) = generateStringForNum(number)

    override fun generateStringForNum(number: Long): String {
        if (number == 0L) return "zero"

        var currentNumber = number
        val strBuilder = StringBuilder()
        var index = 0

        while (currentNumber > 0) {
            val word = (currentNumber % 1000).toInt()
            if (word != 0) {
                val chunkWords = generateNumberUnderThousand(word, 0L)
                val scaleWord = threes[index] ?: ""
                strBuilder.insert(0, " ")
                strBuilder.insert(
                    0,
                    if (scaleWord.isNotEmpty())
                        "$chunkWords $scaleWord"
                    else
                        chunkWords
                )
            }

            currentNumber /= 1000
            index++
        }

        return strBuilder.toString().trim()
    }


    override fun generateNumberUnderThousand(number: Int, wholeNumber: Long): String {
        return when {
            number < 20 -> ones[number] ?: ""
            number < 100 -> {
                val twosDigit = number / 10
                val onesDigit = number % 10
                if (onesDigit == 0) twos[twosDigit]
                    ?: "" else "${twos[twosDigit]}-${ones[onesDigit]}"

            }

            else -> {
                val hundredsDigit = number / 100
                val remainder = number % 100
                if (remainder == 0) "${ones[hundredsDigit]} hundred" else "${ones[hundredsDigit]} hundred and ${
                    generateNumberUnderThousand(
                        remainder,
                        wholeNumber
                    )
                }"

            }
        }
    }


    override val ones = mapOf(
        1 to "one", 2 to "two", 3 to "three", 4 to "four", 5 to "five",
        6 to "six", 7 to "seven", 8 to "eight", 9 to "nine",
        10 to "ten", 11 to "eleven", 12 to "twelve", 13 to "thirteen",
        14 to "fourteen", 15 to "fifteen", 16 to "sixteen",
        17 to "seventeen", 18 to "eighteen", 19 to "nineteen"
    )


    override val twos = mapOf(
        2 to "twenty", 3 to "thirty", 4 to "forty",
        5 to "fifty", 6 to "sixty", 7 to "seventy", 8 to "eighty", 9 to "ninety"
    )


    override val threes = mapOf(
        1 to "thousand", 2 to "million", 3 to "billion",
        4 to "trillion", 5 to "quadrillion", 6 to "quintillion",
        7 to "sextillion", 8 to "septillion", 9 to "octillion",
        10 to "nonillion", 11 to "big number"
    )
}
