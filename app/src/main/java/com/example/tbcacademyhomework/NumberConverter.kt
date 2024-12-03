package com.example.tbcacademyhomework

class NumberConverter {

    val numberConverterGeo = NumberGeneratorGe()
    val numberGeneratorEn = NumberGeneratorEn()

    private var selectedLanguage: Language = Language.Georgian

    fun setConverterLanguage(language: Language) {
        selectedLanguage = language
    }

    fun generateNumber(number: Long): String {
        return when (selectedLanguage) {
            Language.English -> TODO()
            Language.Georgian -> numberConverterGeo(number)
        }
    }


}

