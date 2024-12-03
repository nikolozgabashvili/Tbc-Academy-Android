package com.example.tbcacademyhomework

class NumberGeneratorGe {


    operator fun invoke(number: Long) = generateStringForNum(number)

    private fun generateStringForNum(number: Long): String {
        val list = getSplitNumberList(number).also { println(it) }
        var numberString = ""
        if (number == 0L) return "ნული"
        list.forEachIndexed { index, number ->
            val indexFromLast = list.size - index - 1
            val isLasIndexOfList = indexFromLast == 0

            if (number != 0L || isLasIndexOfList) {
                numberString += numberGenUnderThousand(
                    wholeNumber = number,
                    shouldUsePrefixOne = indexFromLast != 1
                ) + numberT[indexFromLast] + (if (index < list.size - 1 && (list[index + 1] % 1000 == 0L)) "" else " ")
            }

        }
        return numberString.trim()
    }

    private fun getSplitNumberList(number: Long): List<Long> {
        if (number < 1000) return listOf(number)

        val lastChunk = number % 1000
        val remainingNumber = number / 1000

        return getSplitNumberList(remainingNumber) + lastChunk
    }


    private fun numberGenUnderThousand(
        wholeNumber: Long,
        indexFromLast: Int = wholeNumber.toString().length - 1,
        shouldUsePrefixOne: Boolean
    ): String {
        val numberLength = wholeNumber.toString().length
        val currentNumberIndex = numberLength - indexFromLast - 1
        val previousNumberIndex = currentNumberIndex - 1
        val currentNumber = wholeNumber.toString()[currentNumberIndex].toString().toInt()
        val nextNumber =
            if (currentNumberIndex in 0..<numberLength - 1) wholeNumber.toString()[currentNumberIndex + 1].toString()
                .toInt() else null
        val previousNumber =
            if (previousNumberIndex in 0..<numberLength) wholeNumber.toString()[previousNumberIndex].toString()
                .toInt() else null


        return when (indexFromLast) {

            0 -> {
                if (currentNumber == 0) {
                    if (previousNumber?.mod(2) == 0 || previousNumber == null) "ი " else "ათი"
                } else if (previousNumber == null || previousNumber % 2 == 0) {
                    if (shouldUsePrefixOne || wholeNumber > 1) correspondingNumbersForOnes[currentNumber]!! + " "
                    else correspondingPrefixForThousand[currentNumber]!! + " "
                } else {
                    correspondingNumbersForTwos[currentNumber]!! + " "
                }
            }

            1 -> {
                if (currentNumber == 1) {
                    numberGenUnderThousand(
                        wholeNumber, 0, shouldUsePrefixOne
                    )
                } else {
                    correspondingPrefixForTens[currentNumber]!! + (if ((nextNumber != 0 || currentNumber.mod(
                            2
                        ) == 1) && currentNumber != 0
                    ) "და" else "") + numberGenUnderThousand(
                        wholeNumber, 0, shouldUsePrefixOne
                    )
                }
            }

            2 -> {
                val endsWith00 = wholeNumber % 100 == 0L
                correspondingPrefixForHundreds[currentNumber]!! + "ას" + (if (endsWith00) "" else " ") + numberGenUnderThousand(
                    wholeNumber, 1, shouldUsePrefixOne
                )
            }

            else -> ""


        }

    }

    private val correspondingNumbersForOnes = mapOf(
        0 to "ნული", 1 to "ერთი", 2 to "ორი",
        3 to "სამი", 4 to "ოთხი", 5 to "ხუთი",
        6 to "ექვსი", 7 to "შვიდი", 8 to "რვა", 9 to "ცხრა"
    )
    private val correspondingNumbersForTwos = mapOf(
        0 to "", 1 to "თერთმეტი", 2 to "თორმეტი", 3 to "ცამეტი",
        4 to "თოთხმეტი", 5 to "თხუთმეტი", 6 to "თექვსმეტი",
        7 to "ჩვიდმეტი", 8 to "თრვმეტი", 9 to "ცხრმეტი"
    )

    private val correspondingPrefixForTens = mapOf(
        0 to "",
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

    private val correspondingPrefixForHundreds = mapOf(
        1 to "",
        2 to "ორ",
        3 to "სამ",
        4 to "ოთხ",
        5 to "ხუთ",
        6 to "ექვს",
        7 to "შვიდ",
        8 to "რვა",
        9 to "ცხრა"
    )

    private val correspondingPrefixForThousand = mapOf(
        1 to "",
        2 to "ორი",
        3 to "სამი",
        4 to "ოთხი",
        5 to "ხუთი",
        6 to "ექვსი",
        7 to "შვიდი",
        8 to "რვა",
        9 to "ცხრა"
    )

    private val numberT = mapOf(
        0 to "",
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