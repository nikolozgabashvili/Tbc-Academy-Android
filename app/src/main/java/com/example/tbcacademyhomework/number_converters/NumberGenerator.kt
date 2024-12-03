package com.example.tbcacademyhomework.number_converters

interface NumberGenerator {
    fun generateStringForNum(number: Long): String
    fun generateNumberUnderThousand(number: Int, wholeNumber: Long = 0L): String
    val ones: Map<Int, String>
    val twos: Map<Int, String>
    val threes: Map<Int, String>

}