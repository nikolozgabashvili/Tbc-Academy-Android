package com.example.tbcacademyhomework

class AnagramDatabase {

    private val anagrams = mutableListOf<String>()
    private val anagramMap = mutableMapOf<String, List<String>>()

    fun addAnagram(anagram: String) {
        anagrams.add(anagram)
        anagramMap[formatAnagram(anagram)] = getListForAnagram(anagram) + anagram
    }

    private fun getListForAnagram(anagram: String): List<String> {
        val formattedAnagram = formatAnagram(anagram)
        return anagramMap[formattedAnagram] ?: emptyList()
    }

    private fun formatAnagram(anagram: String): String {
        return anagram.toCharArray().sorted().joinToString()
    }

    fun anagramAlreadyAdded(anagram: String): Boolean {
        return anagrams.contains(anagram)
    }

    fun getAnagrams(): String {
        val strBuilder = StringBuilder()
        anagramMap.values.forEachIndexed { index, anagramList ->
            strBuilder.append("${index + 1} :")
            strBuilder.append(anagramList)
            strBuilder.append("\n")
        }
        return strBuilder.toString()
    }

    fun clearDatabase() {
        anagrams.clear()
        anagramMap.clear()
    }


}