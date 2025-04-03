package com.example.tbcacademyhomework.presentation.screen.users.component

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.tbcacademyhomework.presentation.screen.users.models.UserUi
import com.example.tbcacademyhomework.presentation.theme.AppColor

@Composable
fun UserItem(
    modifier: Modifier = Modifier,
    userUi: UserUi
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(10.dp),
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .fillMaxWidth()
            .padding(10.dp)
            .background(AppColor.canvas, shape = RoundedCornerShape(20))
            .border(width = 1.dp, color = AppColor.primary, shape = RoundedCornerShape(20))
            .padding(20.dp)

    ) {
        AsyncImage(
            modifier = Modifier
                .size(70.dp)
                .clip(RoundedCornerShape(100)),
            model = userUi.avatar,
            contentScale = ContentScale.Crop,
            contentDescription = null,
        )
        Column(verticalArrangement = Arrangement.SpaceBetween) {
            userUi.firstName?.let {
                Text(text = it)
            }
            userUi.email?.let {

                Text(text = it)
            }

        }

    }
}