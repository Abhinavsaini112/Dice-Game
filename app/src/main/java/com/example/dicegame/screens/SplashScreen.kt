package com.example.dicegame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dicegame.navigation.Routes
import kotlinx.coroutines.delay
import com.example.dicegame.R

@Composable
fun SplashScreen( navHostController: NavHostController) {
    LaunchedEffect(true) {
        delay(3000)
        navHostController.navigate(Routes.PlayerNameScreen){
            popUpTo(Routes.SplashScreen){ inclusive=true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id=R.color.white))
    ){
        Column (
            modifier=Modifier
                .align(Alignment.Center),
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Image(
                painter = painterResource(R.drawable.splash_screen_dice),
                contentDescription = null,
                modifier = Modifier.size(400.dp)
            )
            Spacer(modifier = Modifier.height(16.dp))
        }
        Text(
            text = "Made with ❤ by Abhinav",
            fontSize = 14.sp,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 75.dp)
        )
    }
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun preSplashScreen() {
    SplashScreen(navHostController = rememberNavController())
}