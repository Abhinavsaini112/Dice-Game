package com.example.dicegame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.horizontalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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
fun PlayerNameScreen(navHostController: NavHostController) {

    var Player_1 by remember { mutableStateOf("") }
    var Player_2 by remember { mutableStateOf("") }

    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        val isPortrait = maxHeight > maxWidth

        if (isPortrait) {
            //  Portrait Layout
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .imePadding() // Fixes keyboard overlap
                    .navigationBarsPadding(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                ScreenContent(
                    Player_1,
                    Player_2,
                    onPlayer1Change = { Player_1 = it },
                    onPlayer2Change = { Player_2 = it }
                ) {
                    if (Player_1.isNotBlank() && Player_2.isNotBlank()) {
                        navHostController.navigate(
                            Routes.DiceGameScreen(Player1 = Player_1, Player2 = Player_2)
                        )
                    }
                }
            }
        } else {
            //  Landscape Layout (scrollable)
            val scrollState = rememberScrollState()

            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .horizontalScroll(scrollState)
                    .padding(horizontal = 8.dp)
                    .imePadding()
                    .navigationBarsPadding(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {

                Spacer(modifier = Modifier.width(24.dp))

                Column(
                    modifier = Modifier
                        .fillMaxHeight()
                        .padding(end = 16.dp)
                        .widthIn(min = 300.dp, max = 500.dp)
                        .verticalScroll(rememberScrollState()), // allows scroll if fields overflow
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    ScreenContent(
                        Player_1,
                        Player_2,
                        onPlayer1Change = { Player_1 = it },
                        onPlayer2Change = { Player_2 = it }
                    ) {
                        if (Player_1.isNotBlank() && Player_2.isNotBlank()) {
                            navHostController.navigate(
                                Routes.DiceGameScreen(Player1 = Player_1, Player2 = Player_2)
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun ScreenContent(
    player1: String,
    player2: String,
    onPlayer1Change: (String) -> Unit,
    onPlayer2Change: (String) -> Unit,
    onStartClick: () -> Unit
) {
    Image(
        painter = painterResource(R.drawable.dice_name),
        contentDescription = "Dice Game logo",
        modifier = Modifier
            .size(150.dp)
            .clip(RoundedCornerShape(8.dp))
    )

    Spacer(modifier = Modifier.height(15.dp))

    Text(
        text = "Enter the name of Players",
        fontSize = 22.sp,
        fontWeight = FontWeight.Bold,
        fontStyle = FontStyle.Normal
    )

    Spacer(modifier = Modifier.height(20.dp))

    OutlinedTextField(
        value = player1,
        onValueChange = onPlayer1Change,
        label = { Text("Player 1") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Person, contentDescription = "Player Icon")
        }
    )

    Spacer(modifier = Modifier.height(15.dp))

    OutlinedTextField(
        value = player2,
        onValueChange = onPlayer2Change,
        label = { Text("Player 2") },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        leadingIcon = {
            Icon(imageVector = Icons.Filled.Person, contentDescription = "Player Icon")
        }
    )

    Spacer(modifier = Modifier.height(25.dp))

    Button(
        onClick = onStartClick,
        modifier = Modifier.fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
        enabled = player1.isNotBlank() && player2.isNotBlank() && player1 != player2
    ) {
        Text(text = "Start the Game")
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun PrePlayerNameScreen() {
    PlayerNameScreen(navHostController = rememberNavController())
}


//package com.example.dicegame.screens
//
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.shape.RoundedCornerShape
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.filled.Email
//import androidx.compose.material.icons.filled.Person
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.Icon
//import androidx.compose.material3.OutlinedTextField
//import androidx.compose.material3.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontFamily
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.dicegame.R
//import com.example.dicegame.navigation.Routes
//
//@Composable
//fun PlayerNameScreen(navHostController: NavHostController) {
//
//    var Player_1 by remember { mutableStateOf("") }
//    var Player_2 by remember { mutableStateOf("") }
//
//    Column (
//        modifier=Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally
//    ){
//        Spacer(modifier = Modifier.padding(40.dp))
//
//        Image(painter = painterResource(R.drawable.dice_name),
//            modifier=Modifier
//                .size(200.dp)
//                .clip(RoundedCornerShape(8.dp)),
//            contentDescription = "Dice Game logo"
//        )
//
//        Spacer(modifier=Modifier.padding(15.dp))
//
//        Text(text="Enter the name of Players",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            fontStyle = FontStyle.Normal
//        )
//
//        Spacer(modifier=Modifier.padding(25.dp))
//
//        OutlinedTextField(
//            value = Player_1,
//            onValueChange={Player_1=it},
//            label = {Text("Player_1")},
//            modifier=Modifier.fillMaxWidth(),
//            singleLine = true,
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Filled.Person,
//                    contentDescription = "Player Icon"
//                )
//            }
//        )
//
//        Spacer(modifier=Modifier.padding(15.dp))
//
//        OutlinedTextField(
//            value=Player_2,
//            onValueChange={Player_2=it},
//            label = {Text("Player_2")},
//            modifier=Modifier.fillMaxWidth(),
//            singleLine = true,
//            leadingIcon = {
//                Icon(
//                    imageVector = Icons.Filled.Person,
//                    contentDescription = "Player Icon"
//                )
//            }
//        )
//
//        Spacer(modifier=Modifier.padding(15.dp))
//
//        Button(
//            onClick = {
//                if(
//                    Player_1.isNotBlank() && Player_2.isNotBlank()
//                ){
//                    navHostController.navigate(Routes.DiceGameScreen(Player1=Player_1,Player2=Player_2))
//                }
//            },
//            modifier=Modifier.fillMaxWidth(),
//            colors = ButtonDefaults.buttonColors(
//                containerColor=Color.Black
//            ),
//            enabled=Player_1.isNotBlank() &&
//                    Player_2.isNotBlank() &&
//                    Player_1 != Player_2
//        ){
//            Text(text = "Start the Game")
//        }
//    }
//}
//
//@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun PrePlayerNameScreen( ) {
//    PlayerNameScreen(navHostController = rememberNavController())
//}
