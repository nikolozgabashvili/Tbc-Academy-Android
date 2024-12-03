package com.example.tbcacademyhomework

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.AppCompatToggleButton
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.updatePadding
import com.example.tbcacademyhomework.helper.Language
import com.example.tbcacademyhomework.number_converters.NumberConverter

class MainActivity : AppCompatActivity() {
    private lateinit var toggleButton: AppCompatToggleButton
    private lateinit var generateButton: AppCompatButton
    private lateinit var inputField: AppCompatEditText
    private lateinit var outputField: AppCompatTextView
    private lateinit var numberConverter: NumberConverter
    private lateinit var errorString: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        toggleButton = findViewById(R.id.btn_toggle)
        generateButton = findViewById(R.id.btn_generate_number)
        inputField = findViewById(R.id.et_text_input)
        outputField = findViewById(R.id.tv_output)
        errorString = getString(R.string.error_text_geo)
        numberConverter = NumberConverter()

        updateToggleButton(numberConverter.selectedLanguage)

        toggleButton.setOnClickListener {

            if (toggleButton.isChecked) {
                numberConverter.setConverterLanguage(language = Language.English)
                errorString = getString(R.string.error_text_en)
                inputField.hint = getString(R.string.et_hint_en)
            } else {
                numberConverter.setConverterLanguage(language = Language.Georgian)
                errorString = getString(R.string.error_text_geo)
                inputField.hint = getString(R.string.et_hint_geo)
            }
            updateToggleButton(numberConverter.selectedLanguage)

        }

        generateButton.setOnClickListener {
            val txt = inputField.text.toString()
            if (txt.isEmpty()) {
                outputField.text = errorString
            } else {
                try {
                    outputField.text = numberConverter.generateNumber(txt.toLong())
                } catch (_: Exception) {
                    outputField.text = errorString
                }
            }

        }


    }

    private fun updateToggleButton(language: Language) {
        toggleButton.text = getString(language.displayName)
    }

}