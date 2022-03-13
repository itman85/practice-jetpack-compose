package com.picoder.practicecompose.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

@Composable
fun MyNavigationDeeplink() {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "home") {
        composable("home") {
            HomeScreen1(navController)
        }

        composable(
            "greeting/{name}",
            arguments = listOf(navArgument("name") {}),
            deepLinks = listOf(navDeepLink { uriPattern = "myapp://greeting/{name}" }) // host not need to same route
        ) {
            GreetingScreen(it.arguments?.getString("name").toString())
        }
        /*
        to open deeplink, other app should implement like this
        val deepIntent = Intent(
                Intent.ACTION_VIEW,
                "myapp://greeting/$text".toUri()
            )
            startActivity(deepIntent)
         */
    }
}

@Composable
fun GreetingScreen(name: String) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    ) {
        Text(
            "Hello $name", style = MaterialTheme.typography.h3, modifier = Modifier
                .padding(16.dp)
        )
    }
}

@Composable
fun HomeScreen1(navController: NavController) {
    Box(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Text(
            "Welcome To Home Screen", fontSize = 30.sp,
            textAlign = TextAlign.Center, modifier = Modifier
                .align(Alignment.Center)
        )
        Button(onClick = { navController.navigate("greeting/MichaelP") }) {
            Text(text = "Open Greeting")
        }
    }
}