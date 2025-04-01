package com.example.tbcacademyhomework.presentation.designSystem

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.times
import com.airbnb.lottie.LottieProperty
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.animateLottieCompositionAsState
import com.airbnb.lottie.compose.rememberLottieComposition
import com.airbnb.lottie.compose.rememberLottieDynamicProperties
import com.airbnb.lottie.compose.rememberLottieDynamicProperty
import com.example.tbcacademyhomework.R

@Composable
fun AppButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    enabled: Boolean = true,
    loading: Boolean = false,
    border: BorderStroke? = null,
    buttonColor: Color = Color.Black,
    textColor: Color = Color.White,
    textSize: TextUnit = 12.sp,
    onclick: () -> Unit
) {

    val perceived = buttonColor.red * 0.299f +
            buttonColor.green * 0.587f +
            buttonColor.blue * 0.114f

    val showDarkLoader = perceived > 0.5


    val lottieComposition by rememberLottieComposition(
        spec = LottieCompositionSpec.RawRes(if (showDarkLoader) R.raw.loader_secondary else R.raw.loader_primary),
    )

    val progress by animateLottieCompositionAsState(
        composition = lottieComposition,
        isPlaying = loading,
        iterations = Int.MAX_VALUE
    )
    val loaderSize = with(LocalDensity.current) {
        2 * textSize.toDp()
    }

    val buttonEnabled = enabled && !loading

    Button(
        modifier = modifier,
        border = border,
        enabled = buttonEnabled,
        colors = ButtonColors(
            containerColor = buttonColor,
            disabledContainerColor = buttonColor.copy(alpha = 0.5f),
            disabledContentColor = textColor.copy(alpha = 0.5f),
            contentColor = textColor
        ),
        onClick = onclick
    ) {

        Box(contentAlignment = Alignment.Center) {
            var alpha = 1f
            if (loading) {
                alpha = 0f
                LottieAnimation(
                    composition = lottieComposition,
                    progress = { progress },
                    modifier = Modifier
                        .align(Alignment.Center)
                        .size(loaderSize),
                )

            }
            Box(modifier = Modifier.alpha(alpha)) {
                text?.let {
                    Text(
                        fontSize = textSize,
                        textAlign = TextAlign.Center,
                        text = text
                    )
                }
            }
        }
    }


}


@Preview(showBackground = true, device = Devices.NEXUS_5)
@Composable
private fun AppButtonPrev() {
    MaterialTheme {
        Box(modifier = Modifier.fillMaxSize()) {
            AppButton(
                modifier = Modifier.fillMaxWidth(),
                text = "hello",
                onclick = {})
        }
    }
}