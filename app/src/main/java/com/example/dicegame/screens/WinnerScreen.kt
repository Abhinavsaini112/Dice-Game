package com.example.dicegame.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
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
import com.example.dicegame.R
import com.example.dicegame.navigation.Routes


//@Preview(showSystemUi =true, showBackground = true)
@Composable
fun WinnerScreen(navHostController: NavHostController,
                 Winner:String ) {

    Column (
        modifier=Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier=Modifier.padding(20.dp))

        Image(painter = painterResource(R.drawable.winner_logo),
            contentDescription = "Winner Logo",
            modifier=Modifier.size(400.dp))

        Spacer(modifier=Modifier.padding(8.dp))

        Text(text="Congratulation !",
            fontWeight = FontWeight.Bold,
            fontSize = 24.sp,
            fontStyle = FontStyle.Italic)

        Spacer(modifier=Modifier.padding(8.dp))

        Text(text="${Winner} won the game",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp,
            fontStyle = FontStyle.Normal)

        Spacer(modifier= Modifier.padding(20.dp))

        Button(
            onClick ={
                navHostController.navigate(Routes.PlayerNameScreen)
            },
            modifier=Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.Black
            )
        ) { Text(text = "Play Again")}
    }
}