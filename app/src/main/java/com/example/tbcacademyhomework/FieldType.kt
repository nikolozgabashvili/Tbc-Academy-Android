package com.example.tbcacademyhomework

enum class FieldType(val type: String) {
    INPUT("input"),
    CHOOSER("chooser");

    companion object{
        fun getType(type: String?): FieldType {
            return entries.find { it.type == type } ?: INPUT
        }
    }
}