package com.example.tbcacademyhomework.number_converters

class NumberGeneratorGe : NumberGenerator {


    operator fun invoke(number: Long) = generateStringForNum(number)


    override fun generateStringForNum(number: Long): String {
        if (number == 0L) return "ნული"

        var currentNumber = number
        val strBuilder = StringBuilder()
        var index = 0
        var lastThousandsWord: String? = null

        while (currentNumber > 0) {
            val chunk = (currentNumber % 1000).toInt()
            if (chunk != 0) {
                val partialWord = generateNumberUnderThousand(chunk, number)
                val thousandsWord = threes[index] ?: ""

                strBuilder.insert(0, " ")

                strBuilder.insert(
                    0,

                    if ( partialWord.trim() != "ერთი") {
                        lastThousandsWord = thousandsWord
                        "$partialWord $thousandsWord"
                    } else if (thousandsWord.isNotEmpty()) {
                        thousandsWord
                    } else {
                        partialWord
                    }

                )
            }

            currentNumber /= 1000
            index++
        }
        var finalValue = strBuilder.toString().trim()
        if (!lastThousandsWord.isNullOrEmpty() && finalValue.endsWith(lastThousandsWord)) {
            finalValue += "ი"
        }

        return finalValue
    }


    override fun generateNumberUnderThousand(number: Int, wholeNumber: Long): String {
        return when {
            number < 20 -> endingOnes[number] ?: ""
            number < 100 -> {
                val twosDigit = number / 10
                val onesDigit = number % 20

                if (onesDigit == 0) {
                    (twos[twosDigit] + endingOnes[onesDigit])
                } else {
                    "${twos[twosDigit] ?: ""}და${endingOnes[onesDigit] ?: ""}"
                }

            }

            else -> {
                val hundredsDigit = number / 100
                val remainder = number % 100
                if (remainder == 0) {
                    "${ones[hundredsDigit] ?: ""}ასი"
                } else {
                    "${ones[hundredsDigit] ?: ""}ას ${
                        generateNumberUnderThousand(
                            remainder, wholeNumber
                        )
                    }"
                }
            }
        }
    }


    override val ones: Map<Int, String>
        get() = mapOf(
            2 to "ორ", 3 to "სამ", 4 to "ოთხ", 5 to "ხუთ",
            6 to "ექვს", 7 to "შვიდ", 8 to "რვა", 9 to "ცხრა", 10 to "ათი",
            11 to "თერთმეტი", 12 to "თორმეტი", 13 to "ცამეტი", 14 to "თოთხმეტი",
            15 to "თხუთმეტი", 16 to "თექვსმეტი", 17 to "ჩვიდმეტი",
            18 to "თვრამეტი", 19 to "ცხრამეტი",
        )

    private val endingOnes = mapOf(
        0 to "ი", 1 to "ერთი", 2 to "ორი",
        3 to "სამი", 4 to "ოთხი", 5 to "ხუთი",
        6 to "ექვსი", 7 to "შვიდი", 8 to "რვა", 9 to "ცხრა",
        10 to "ათი", 11 to "თერთმეტი", 12 to "თორმეტი", 13 to "ცამეტი", 14 to "თოთხმეტი",
        15 to "თხუთმეტი", 16 to "თექვსმეტი", 17 to "ჩვიდმეტი", 18 to "თვრამეტი", 19 to "ცხრამეტი",
    )

    override val twos = mapOf(
        1 to "",
        2 to "ოც",
        3 to "ოც",
        4 to "ორმოც",
        5 to "ორმოც",
        6 to "სამოც",
        7 to "სამოც",
        8 to "ოთხმოც",
        9 to "ოთხმოც"
    )

    override val threes = mapOf(
        1 to "ათას",
        2 to "მილიონ",
        3 to "მილიარდ",
        4 to "ტრილიონ",
        5 to "კვადრილიონ",
        6 to "კვინტილიონ",
        7 to "სექსტილიონ",
        8 to "სეპტილიონ",
        9 to "ოქტალიონ",
        10 to "ნონალიონ",
        11 to "ძალიან დიდი რიცხვი"
    )
}