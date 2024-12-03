package com.example.tbcacademyhomework.number_converters

import com.example.tbcacademyhomework.helper.Language

class NumberConverter {

    val onLanguageChange: (Language) -> Unit = {}

    val numberConverterGeo = NumberGeneratorGe()
    val numberGeneratorEn = NumberGeneratorEn()

    var selectedLanguage: Language = Language.Georgian
        private set

    fun setConverterLanguage(language: Language) {
        selectedLanguage = language
        onLanguageChange(language)
    }


    fun generateNumber(number: Long): String {
        return when (selectedLanguage) {
            Language.English -> numberGeneratorEn(number)
            Language.Georgian -> numberConverterGeo(number)
        }
    }


}

