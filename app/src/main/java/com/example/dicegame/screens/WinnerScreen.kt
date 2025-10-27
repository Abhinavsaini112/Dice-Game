package com.example.dicegame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.dicegame.R
import com.example.dicegame.navigation.Routes

@Composable
fun WinnerScreen(
    navHostController: NavHostController,
    Winner: String
) {
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val isPortrait = maxHeight > maxWidth
        val scrollState = rememberScrollState()

        if (isPortrait) {
            // 📱 Portrait layout
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(scrollState)
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(R.drawable.winner_logo),
                    contentDescription = "Winner Logo",
                    modifier = Modifier.size(350.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "Congratulations!",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp,
                    fontStyle = FontStyle.Italic
                )

                Spacer(modifier = Modifier.height(10.dp))

                Text(
                    text = "$Winner won the game 🎉",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 20.sp,
                    fontStyle = FontStyle.Normal
                )

                Spacer(modifier = Modifier.height(24.dp))

                Button(
                    onClick = {
                        navHostController.navigate(Routes.PlayerNameScreen)
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                ) {
                    Text(text = "Play Again", color = Color.White)
                }
            }
        } else {
            // 💻 Landscape layout (scrollable)
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .verticalScroll(scrollState),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(R.drawable.winner_logo),
                    contentDescription = "Winner Logo",
                    modifier = Modifier
                        .size(250.dp)
                        .padding(end = 24.dp)
                )

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = "Congratulations!",
                        fontWeight = FontWeight.Bold,
                        fontSize = 24.sp,
                        fontStyle = FontStyle.Italic
                    )

                    Spacer(modifier = Modifier.height(8.dp))

                    Text(
                        text = "$Winner won the game 🎊",
                        fontWeight = FontWeight.Medium,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(20.dp))

                    Button(
                        onClick = {
                            navHostController.navigate(Routes.PlayerNameScreen)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                    ) {
                        Text(text = "Play Again", color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PreWinnerScreen() {
    WinnerScreen(navHostController = rememberNavController(), Winner = "Abhinav")
}




//package com.example.dicegame.screens
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonColors
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import com.example.dicegame.R
//import com.example.dicegame.navigation.Routes
//
//
////@Preview(showSystemUi =true, showBackground = true)
//@Composable
//fun WinnerScreen(navHostController: NavHostController,
//                 Winner:String ) {
//
//    Column (
//        modifier=Modifier.fillMaxSize().padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ){
//
//        Spacer(modifier=Modifier.padding(20.dp))
//
//        Image(painter = painterResource(R.drawable.winner_logo),
//            contentDescription = "Winner Logo",
//            modifier=Modifier.size(400.dp))
//
//        Spacer(modifier=Modifier.padding(8.dp))
//
//        Text(text="Congratulation !",
//            fontWeight = FontWeight.Bold,
//            fontSize = 24.sp,
//            fontStyle = FontStyle.Italic)
//
//        Spacer(modifier=Modifier.padding(8.dp))
//
//        Text(text="${Winner} won the game",
//            fontWeight = FontWeight.SemiBold,
//            fontSize = 20.sp,
//            fontStyle = FontStyle.Normal)
//
//        Spacer(modifier= Modifier.padding(20.dp))
//
//        Button(
//            onClick ={
//                navHostController.navigate(Routes.PlayerNameScreen)
//            },
//            modifier=Modifier.fillMaxWidth(),
//            colors = ButtonDefaults.buttonColors(
//                containerColor = Color.Black
//            )
//        ) { Text(text = "Play Again")}
//    }
//}