package com.example.tbcacademyhomework.data.local.database.converter

import android.net.Uri
import kotlinx.serialization.json.Json


object MapConverter {

    fun mapToString(map: Map<String?, String?>?): String? {
        return map?.let {
            Uri.encode(Json.encodeToString(it))
        }
    }

    fun stringToMap(value: String?): Map<String?, String?>? {
        return value?.let {
            return Json.decodeFromString(Uri.decode(it))
        }
    }
}