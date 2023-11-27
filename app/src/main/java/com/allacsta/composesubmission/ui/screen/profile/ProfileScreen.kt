package com.allacsta.composesubmission.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.allacsta.composesubmission.R
import com.allacsta.composesubmission.ui.theme.CoffeeBrown
import com.allacsta.composesubmission.ui.theme.CoffeeShopTheme
import com.allacsta.composesubmission.ui.theme.Shapes

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center,
    ) {
        Image(
            painter = painterResource(R.drawable.background_profile),
            contentDescription = "Profile Background",
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize())
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier.align(Alignment.Center)
        ) {
            Image(
                painter = painterResource(R.drawable.profile_picture),
                contentDescription = "Profile Picture",
                modifier = Modifier
                    .border(4.dp, CoffeeBrown, CircleShape)
                    .padding(8.dp)
                    .clip(CircleShape)
                    .size(260.dp)
            )
            Text(
                text = stringResource(R.string.profile_name),
                fontSize = 24.sp,
                modifier = Modifier
                    .padding(top = 10.dp, bottom = 4.dp)
                    .clip(Shapes.medium)
                    .background(CoffeeBrown)
                    .padding(start = 10.dp, end = 10.dp, top = 4.dp, bottom = 4.dp),
                color = Color.White,
            )
            Text(
                text = stringResource(R.string.profile_email),
                fontSize = 12.sp,
                modifier = Modifier
                    .clip(Shapes.small)
                    .background(Color.White)
                    .padding(start = 4.dp, end = 4.dp, top = 2.dp, bottom = 2.dp),
                color = CoffeeBrown,
            )
        }
    }
}

@Preview
@Composable
fun ProfilePreview(){
    CoffeeShopTheme {
        ProfileScreen()
    }
}