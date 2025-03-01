package com.example.tbcacademyhomework.presentation.core.managers.theme

import androidx.lifecycle.ViewModel
import com.example.tbcacademyhomework.domain.managers.theme.Theme
import com.example.tbcacademyhomework.domain.managers.theme.usecase.ChangeThemeUseCase
import com.example.tbcacademyhomework.domain.managers.theme.usecase.GetAvailableThemesUseCase
import com.example.tbcacademyhomework.domain.managers.theme.usecase.GetSelectedThemeUseCase
import com.example.tbcacademyhomework.presentation.core.util.launchCoroutineScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ThemeViewModel @Inject constructor(
    private val getSelectedThemeUseCase: GetSelectedThemeUseCase,
    private val setThemeUseCase: ChangeThemeUseCase,
    getAvailableThemesUseCase: GetAvailableThemesUseCase
) : ViewModel() {

    private val availableThemes = getAvailableThemesUseCase()


    private val _state = MutableStateFlow(ThemeScreenState())
    val state = _state.asStateFlow()

    init {
        launchCoroutineScope {
            getSelectedThemeUseCase().collect { selectedTheme ->
                val themes = availableThemes.map {
                    ThemeItem(
                        theme = it,
                        isSelected = it == selectedTheme,
                        hasUnderLine = it != availableThemes.last()
                    )
                }
                _state.update { it.copy(themes = themes) }
            }
        }
    }

    fun onThemeSelected(theme: Theme) {
        launchCoroutineScope {
            setThemeUseCase(theme)
        }
    }


}