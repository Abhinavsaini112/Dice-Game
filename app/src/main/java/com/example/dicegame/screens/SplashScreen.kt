package com.example.dicegame.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dicegame.navigation.Routes
import kotlinx.coroutines.delay
import com.example.dicegame.R

@Composable
fun SplashScreen(navHostController: NavHostController) {

    val scale = remember { Animatable(0f) }

    // Automatically navigate after 3 seconds
    LaunchedEffect(true) {
        delay(3000)
        navHostController.navigate(Routes.PlayerNameScreen) {
            popUpTo(Routes.SplashScreen) { inclusive = true }
        }
    }

    // Responsive & scrollable layout
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .background(colorResource(id = R.color.white))
    ) {
        val isPortrait = maxHeight > maxWidth
        val scrollState = rememberScrollState()

        if (isPortrait) {
            // 📱 Portrait mode
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.dice_icon),
                    contentDescription = "Splash Image",
                    modifier = Modifier
                        .size(350.dp)
                        .padding(16.dp)
                )
                // ✨ Animated "Dice Game" text

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Made with ❤ by Abhinav",
                    fontSize = 14.sp,
                    modifier = Modifier.padding(bottom = 75.dp)
                )
            }
        } else {
            // 💻 Landscape mode (scrollable)
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.splash_screen_dice),
                    contentDescription = "Splash Image",
                    modifier = Modifier
                        .size(250.dp)
                        .padding(16.dp)
                )

                Spacer(modifier = Modifier.width(24.dp))

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Made with ❤ by Abhinav",
                        fontSize = 16.sp,
                        modifier = Modifier.padding(top = 16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreSplashScreen() {
    SplashScreen(navHostController = rememberNavController())
}




//package com.example.dicegame.screens
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.res.colorResource
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.dicegame.navigation.Routes
//import kotlinx.coroutines.delay
//import com.example.dicegame.R
//
//@Composable
//fun SplashScreen( navHostController: NavHostController) {
//    LaunchedEffect(true) {
//        delay(3000)
//        navHostController.navigate(Routes.PlayerNameScreen){
//            popUpTo(Routes.SplashScreen){ inclusive=true }
//        }
//    }
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(colorResource(id=R.color.white))
//    ){
//        Column (
//            modifier=Modifier
//                .align(Alignment.Center),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ){
//            Image(
//                painter = painterResource(R.drawable.splash_screen_dice),
//                contentDescription = null,
//                modifier = Modifier.size(400.dp)
//            )
//            Spacer(modifier = Modifier.height(16.dp))
//        }
//        Text(
//            text = "Made with ❤ by Abhinav",
//            fontSize = 14.sp,
//            modifier = Modifier
//                .align(Alignment.BottomCenter)
//                .padding(bottom = 75.dp)
//        )
//    }
//}
//
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun preSplashScreen() {
//    SplashScreen(navHostController = rememberNavController())
//}