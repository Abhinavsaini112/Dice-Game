package com.example.dicegame.screens

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.dicegame.R
import com.example.dicegame.navigation.Routes
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DiceGameScreen(
    navHostController: NavHostController,
    Player1: String,
    Player2: String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Dice Roller Game") },
                actions = {
                    Button(
                        onClick = { },
                        modifier = Modifier.padding(16.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
                    ) {
                        Text(text = "New Game")
                    }
                }
            )
        }
    ) { innerPadding ->
        DiceGame(
            innerPadding = innerPadding,
            navHostController = navHostController,
            player1 = Player1,
            player2 = Player2
        )
    }
}

@Composable
fun DiceGame(
    innerPadding: PaddingValues,
    navHostController: NavHostController,
    player1: String,
    player2: String
) {
    val scrollState = rememberScrollState()
    val isPlayer1 = remember { mutableStateOf(true) }
    val playerScores = remember { mutableStateOf(Array(2) { 0 }) }
    val diceImages = listOf(
        R.drawable.dice_1,
        R.drawable.dice_2,
        R.drawable.dice_3,
        R.drawable.dice_4,
        R.drawable.dice_5,
        R.drawable.dice_6
    )
    val currentDiceImage = remember { mutableStateOf(R.drawable.dice_logo) }
    val coroutineScope = rememberCoroutineScope()
    val rotation = remember { Animatable(0f) }
    val scale = remember { Animatable(1f) }

    // BoxWithConstraints to make it responsive for orientation
    BoxWithConstraints(
        modifier = Modifier
            .fillMaxSize()
            .padding(innerPadding)
            .verticalScroll(scrollState)
            .navigationBarsPadding()
            .imePadding()
    ) {
        val isLandscape = maxWidth > maxHeight

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .then(if (isLandscape) Modifier.widthIn(max = 600.dp) else Modifier.fillMaxWidth()),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            Text(
                text = "Let's Play!",
                fontSize = if (isLandscape) 20.sp else 24.sp,
                fontWeight = FontWeight.Bold,
                fontStyle = FontStyle.Normal
            )


            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.Black)
                    .padding(8.dp),
                contentAlignment = Alignment.Center
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "$player1 Score: ${playerScores.value[0]}",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                    Text(
                        text = "$player2 Score: ${playerScores.value[1]}",
                        color = Color.White,
                        fontSize = 16.sp
                    )
                }
            }

            Row(verticalAlignment = Alignment.CenterVertically) {
                Image(
                    painter = painterResource(R.drawable.dice),
                    contentDescription = "Dice Icon",
                    modifier = Modifier.size(if (isLandscape) 25.dp else 35.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Winner Score ≥ 36",
                    fontSize = 16.sp,
                    color = Color.Black
                )
            }

            Image(
                painter = painterResource(currentDiceImage.value),
                contentDescription = "Current Dice Face",
                modifier = Modifier
                    .size(if (isLandscape) 150.dp else 200.dp)
                    .graphicsLayer(
                        rotationZ = rotation.value,
                        scaleX = scale.value,
                        scaleY = scale.value
                    )
            )

            OutlinedButton(
                onClick = {
                    coroutineScope.launch {
                        rotation.animateTo(
                            targetValue = 720f,
                            animationSpec = tween(durationMillis = 500, easing = LinearEasing)
                        )
                        rotation.snapTo(0f)
                        scale.animateTo(1.2f, animationSpec = tween(150))
                        scale.animateTo(1f, animationSpec = tween(150))

                        val randomNumber = (1..6).random()
                        currentDiceImage.value = diceImages[randomNumber - 1]

                        if (isPlayer1.value) {
                            playerScores.value[0] += randomNumber
                            if (randomNumber == 6) isPlayer1.value = !isPlayer1.value
                        } else {
                            playerScores.value[1] += randomNumber
                            if (randomNumber == 6) isPlayer1.value = !isPlayer1.value
                        }
                        isPlayer1.value = !isPlayer1.value

                        if (playerScores.value[0] >= 36) {
                            navHostController.navigate(Routes.WinnerScreen(player1))
                        } else if (playerScores.value[1] >= 36) {
                            navHostController.navigate(Routes.WinnerScreen(player2))
                        }
                    }
                },
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Text(
                    text = if (isPlayer1.value)
                        "Roll the Dice for $player1"
                    else
                        "Roll the Dice for $player2",
                    color = Color.Black
                )
            }

            OutlinedButton(
                onClick = {
                    playerScores.value = Array(2) { 0 }
                    isPlayer1.value = true
                    currentDiceImage.value = R.drawable.dice_logo
                },
                colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                modifier = Modifier.fillMaxWidth(0.9f)
            ) {
                Icon(
                    imageVector = Icons.Rounded.Refresh,
                    contentDescription = "Reset Button",
                    tint = Color.Black
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "Play Again", color = Color.Black)
            }

            Spacer(modifier = Modifier.height(32.dp))
        }
    }
}



//package com.example.dicegame.screens
//
//import android.R.attr.rotation
//import androidx.compose.material.icons.Icons
//import androidx.compose.material.icons.rounded.Refresh
//import androidx.compose.animation.core.Animatable
//import androidx.compose.animation.core.LinearEasing
//import androidx.compose.animation.core.tween
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.PaddingValues
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.aspectRatio
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.rememberScrollState
//import androidx.compose.material3.Button
//import androidx.compose.material3.ButtonDefaults
//import androidx.compose.material3.ExperimentalMaterial3Api
//import androidx.compose.material3.Icon
//import androidx.compose.material3.OutlinedButton
//import androidx.compose.material3.Scaffold
//import androidx.compose.material3.Text
//import androidx.compose.material3.TopAppBar
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.mutableIntStateOf
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.rememberCoroutineScope
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.graphics.graphicsLayer
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.text.font.FontStyle
//import androidx.compose.ui.text.font.FontSynthesis
//import androidx.compose.ui.text.font.FontWeight
//import androidx.compose.ui.text.style.TextAlign
//import androidx.compose.ui.tooling.preview.Preview
//import androidx.compose.ui.unit.dp
//import androidx.compose.ui.unit.sp
//import androidx.navigation.NavHostController
//import androidx.navigation.compose.rememberNavController
//import com.example.dicegame.R
//import com.example.dicegame.navigation.Routes
//import kotlinx.coroutines.coroutineScope
//import kotlinx.coroutines.launch
//import kotlin.contracts.contract
//
//
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun DiceGameScreen(navHostController: NavHostController,
//                   Player1:String,
//                   Player2:String) {
//    Scaffold (
//        topBar = {
//            TopAppBar(
//                title = { Text("Dice Roller Game") },
//                actions = {
//                    Button(
//                        onClick = { },
//                        modifier = Modifier.padding(16.dp),
//                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black)
//                    ) {
//                        Text(text = "New Game")
//                    }
//                }
//            )
//        }
//    ) { innerPadding ->
//        DiceGame(
//            innerPadding = innerPadding,
//            navHostController,
//            player1 = Player1,
//            player2 = Player2
//        )
//    }
//}
//
////@Preview(showBackground = true, showSystemUi = true)
//@Composable
//fun DiceGame(
//    innerPadding: PaddingValues,
//    navHostController: NavHostController,
//    player1:String,
//    player2:String
//) {
//    val scrollState = rememberScrollState()
//    val isPlayer1 = remember { mutableStateOf(true) }
//    val PlayerScores = remember { mutableStateOf(Array(2) { 0 }) }
//    val diceImages = listOf(
//        R.drawable.dice_1,
//        R.drawable.dice_2,
//        R.drawable.dice_3,
//        R.drawable.dice_4,
//        R.drawable.dice_5,
//        R.drawable.dice_6
//    )
//    val currentDiceImage = remember { mutableStateOf(R.drawable.dice_logo) }
//    val coroutineScope = rememberCoroutineScope()
//    val rotation = remember { Animatable(0f) }
//    val scale = remember { Animatable(1f) }
//
//    Column (
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.SpaceEvenly
//    ) {
//        Text(
//            text = "Lets Play !",
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            fontStyle = FontStyle.Normal
//        )
//
//        Spacer(modifier = Modifier.padding(20.dp))
//
//        Box(
//            modifier = Modifier
//                .fillMaxWidth()
//                .background(Color.Black),
//            contentAlignment = Alignment.Center
//        ) {
//            Row(
//                modifier = Modifier
//                    .fillMaxWidth()
//                    .padding(16.dp),
//                horizontalArrangement = Arrangement.SpaceBetween
//            ) {
//                Text(
//                    text = "${player1} Score : ${PlayerScores.value[0]}",
//                    color = Color.White,
//                    fontSize = 16.sp
//                )
//                Text(
//                    text = "${player2} Score : ${PlayerScores.value[1]}",
//                    color = Color.White,
//                    fontSize = 16.sp
//                )
//            }
//        }
//
//        Spacer(modifier=Modifier.padding(16.dp))
//
//        Row(
//            verticalAlignment = Alignment.CenterVertically
//        ) {
//            Image(
//                painter = painterResource(R.drawable.dice),
//                contentDescription = "Dice",
//                modifier = Modifier
//                    .size(35.dp)
//                    .aspectRatio(1f)
//            )
//            Spacer(modifier = Modifier.width(8.dp))
//            Text(
//                text = "Winner Score ≥ 36",
//                fontSize = 16.sp,
//                color = Color.Black
//            )
//        }
//
//
//        Spacer(modifier=Modifier.padding(16.dp))
//
//        Image(
//            painter = painterResource(currentDiceImage.value),
//            contentDescription = null,
//            modifier = Modifier
//                .size(200.dp)
//                .graphicsLayer(
//                    rotationZ = rotation.value,
//                    scaleX = scale.value,
//                    scaleY = scale.value
//                )
//        )
//
//        Spacer(modifier=Modifier.padding(16.dp))
//
//        OutlinedButton(
//            onClick = {
//                coroutineScope.launch {
//
//                    rotation.animateTo(
//                        targetValue = 720f,
//                        animationSpec = tween( durationMillis=500,easing=LinearEasing )
//                    )
//
//                    rotation.snapTo(0f)
//
//                    scale.animateTo(1.2f, animationSpec = tween(150))
//                    scale.animateTo(1f, animationSpec = tween(150))
//
//                    val randomNumber = (1..6).random()
//                    currentDiceImage.value = diceImages.get(randomNumber - 1)
//
//                    if (isPlayer1.value) {
//                        PlayerScores.value[0] += randomNumber
//                        if(randomNumber==6){
//                            isPlayer1.value=!isPlayer1.value
//                        }
//                    } else {
//                        PlayerScores.value[1] += randomNumber
//                        if (randomNumber==6){
//                            isPlayer1.value=!isPlayer1.value
//                        }
//                    }
//                    isPlayer1.value = !isPlayer1.value
//
//                    if (PlayerScores.value[0]>=36) {
//                        navHostController.navigate(Routes.WinnerScreen(player1))
//                    }
//                    else if (PlayerScores.value[1]>=36){
//                        navHostController.navigate(Routes.WinnerScreen(player2))
//                    }
//                }
//
//            }
//        ) {
//            if (isPlayer1.value)
//                Text(text = "Roll the Dice for ${player1}", color = Color.Black)
//            else
//                Text(text = "Roll the Dice for ${player2}",color=Color.Black)
//        }
//
//        Spacer(modifier = Modifier.padding(16.dp))
//
//        OutlinedButton(
//            onClick = {
//                PlayerScores.value = Array(2) { 0 }
//                isPlayer1.value = true
//                currentDiceImage.value = R.drawable.dice_logo
//            }, colors = ButtonDefaults.buttonColors(
//                containerColor = Color.White
//            ),
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        ) {
//            Text(text = "Play Again",color=Color.Black)
//            Spacer(modifier = Modifier.width(8.dp))
//            Icon(imageVector = Icons.Rounded.Refresh, contentDescription = "Reset Button",tint=Color.Black)
//
//        }
//    }
//}




//@Preview(showBackground = true , showSystemUi = true)
//@Composable
//fun preDiceGame( ) {
//    DiceGameScreen(navHostController = rememberNavController(),
//        Player1="Abhinav",
//        Player2="Saini"
//    )
//}

//{
//    val isPlayer1 = remember { mutableStateOf(true) }
//    val PlayerScores = remember { mutableStateOf(Array(2) { 0 }) }
//    val rotation = remember { Animatable(0f) }
//    val scale = remember { Animatable(1f) }
//    val coroutineScope = rememberCoroutineScope()
//
//
//    val diceImages = listOf(
//        R.drawable.dice_1,
//        R.drawable.dice_2,
//        R.drawable.dice_3,
//        R.drawable.dice_4,
//        R.drawable.dice_5,
//        R.drawable.dice_6
//    )
//
//    val currentDiceImage = remember { mutableStateOf(R.drawable.dice_logo) }
//
//    Column(
//        modifier = Modifier.fillMaxSize(),
//        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
//    ) {
//        Text(
//            text = "Dice  Game",
//            textAlign = TextAlign.Center,
//            modifier = Modifier.fillMaxWidth(),
//            fontSize = 26.sp,
//            fontStyle = FontStyle.Italic
//        )
////      way to use mutlitcolor in text
////        Text(
////            text = buildAnnotatedString {
////                withStyle(style = SpanStyle(color = Color.Black)) {
////                    append("Welcome ")
////                }
////                withStyle(style = SpanStyle(color = Color.Red)) {
////                    append("to the ")
////                }
////                withStyle(style = SpanStyle(color = Color.Blue)) {
////                    append("Dice Game")
////                }
////            }
//
//        Spacer(modifier = Modifier.padding(15.dp))
//
//        Row(
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(horizontal = 50.dp),
//            horizontalArrangement = Arrangement.SpaceBetween
//        ) {
//            Text("Player 1 \nScore  ${PlayerScores.value.get(0)}", textAlign = TextAlign.Center, fontSize=15.sp, color = Color.Red)
//            Text("Player 2 \nScore  ${PlayerScores.value.get(1)}", textAlign = TextAlign.Center, fontSize = 15.sp,color=Color.Red)
//        }
//
//        Spacer(modifier = Modifier.padding(8.dp))
//
//        Row (modifier=Modifier
//            .fillMaxWidth()
//            .padding(horizontal = 60.dp),
//            horizontalArrangement = Arrangement.Center){
//            Text(text = "Winning Score : 36",textAlign= TextAlign.Center)
//        }
//
//        Spacer(modifier=Modifier.padding(16.dp))
//
//        Image(
//            painter = painterResource(currentDiceImage.value),
//            contentDescription = null,
//            modifier = Modifier
//                .size(350.dp)
//                .graphicsLayer(
//                    rotationZ = rotation.value,
//                    scaleX = scale.value,
//                    scaleY = scale.value
//                )
//        )
//
//        Spacer(modifier = Modifier.height(8.dp))
//
//        OutlinedButton(
//            onClick = {
//                coroutineScope.launch {
//                    // Animate dice rotation (720° spin)
//                    rotation.animateTo(
//                        targetValue = 720f,
//                        animationSpec = tween(durationMillis = 500, easing = LinearEasing)
//                    )
//
//                    // Reset rotation
//                    rotation.snapTo(0f)
//
//                    // Slight bounce animation
//                    scale.animateTo(1.2f, animationSpec = tween(150))
//                    scale.animateTo(1f, animationSpec = tween(150))
//
//
//                val randomNumber = (1..6).random()
//                currentDiceImage.value = diceImages.get(randomNumber - 1)
//                if (isPlayer1.value) {
//                    PlayerScores.value[0] += randomNumber
//                } else {
//                    PlayerScores.value[1] += randomNumber
//                }
//                isPlayer1.value = !isPlayer1.value
//                }
//            },
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        ) {
//            if (isPlayer1.value)
//                Text(text = "Roll the Dice for Player1")
//            else
//                Text(text = "Roll the Dice for Player2")
//        }
//
//        Spacer(modifier = Modifier.padding(8.dp))
//
//        Button(
//            onClick = {
//                PlayerScores.value = Array(2) { 0 }
//                isPlayer1.value = true
//                currentDiceImage.value = R.drawable.dice_logo
//            },
//            modifier = Modifier.align(Alignment.CenterHorizontally)
//        ) {
//            Text(text = "Play Again")
//            Spacer(modifier = Modifier.width(8.dp))
//            Icon(imageVector = Icons.Rounded.Refresh, contentDescription = "Reset Button")
//        }
//
//
//    }
//}