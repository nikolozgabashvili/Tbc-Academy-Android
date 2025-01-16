package com.example.tbcacademyhomework

import androidx.lifecycle.ViewModel
import kotlinx.serialization.json.Json

class RegisterViewModel : ViewModel() {

    private val inputResponse = parseData().toResponseDataUiList()
    private val userInputData = mutableMapOf<Int?, String>()

    fun updateUserInputData(data: InputItemCallback) {
        userInputData[data.id] = data.value
    }

    fun validateData(): String? {
        val fieldKeys = inputResponse.flatten().mapNotNull { it.id }
        println(userInputData)
        fieldKeys.forEach { key ->
            if (inputResponse.flatten()
                    .find { it.id == key }?.required == true && userInputData[key].isNullOrEmpty()
            ) {
                return inputResponse.flatten().find { it.id == key }?.hint
            }
        }
        return null


    }

    fun getResponseList() = inputResponse


    private fun parseData(): Response {
        val json = Json {
            explicitNulls = false
        }
        return json.decodeFromString(RESPONSE_JSON)

    }

    companion object {
        private const val RESPONSE_JSON = """[
            [
      {
         "field_id":1,
         "hint":"UserName",
         "field_type":"input",
         "keyboard":"text",
         "required":false,
         "is_active":true,
         "icon":"https://jemala.png/"
      },
      {
         "field_id":2,
         "hint":"Email",
         "field_type":"input",
         "required":true,
         "keyboard":"text",
         "is_active":true,
         "icon":"https://jemala.png/"
      },
      {
         "field_id":3,
         "hint":"phone",
         "field_type":"input",
         "required":true,
         "keyboard":"number",
         "is_active":true,
         "icon":"https://jemala.png/"
      }
   ],
   [
      {
         "field_id":4,
         "hint":"FullName",
         "field_type":"input",
         "keyboard":"text",
         "required":true,
         "is_active":true,
         "icon":"https://jemala.png/"
      },
      {
         "field_id":14,
         "hint":"Jemali",
         "field_type":"input",
         "keyboard":"text",
         "required":false,
         "is_active":true,
         "icon":"https://jemala.png/"
      },
      {
         "field_id":89,
         "hint":"Birthday",
         "field_type":"chooser",
         "required":false,
         "is_active":true,
         "icon":"https://jemala.png/"
      },
      {
         "field_id":898,
         "hint":"Gender",
         "field_type":"chooser",
         "required":false,
         "is_active":true,
         "icon":"https://jemala.png/"
      }
   ]
]
        """


    }
}