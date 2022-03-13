package com.picoder.practicecompose.compose

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import kotlin.reflect.typeOf

@Composable
fun MyScreensNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = "home") {
        composable(route = "home") {
            HomeScreen(navController = navController)
        }

        composable(
            route = "b_route/{count}?id={id}", // arguments like url with params
            arguments = listOf(navArgument("count") { type = NavType.IntType }, // how about  argument is object?
                navArgument("id") { type = NavType.LongType; defaultValue = -1 })
        ) { backStackEntry ->
            val count = backStackEntry.arguments?.getInt("count") ?: 0
            val id = backStackEntry.arguments!!.getLong("id")
            B_Screen(count,id)
        }

        composable(route = "c_route") {
            C_Screen(navController = navController)
        }

        composable(route = "d_route") {
            D_Screen()
        }

        composable(route = "e_route") {
            E_Screen()
        }
    }
}

@Preview
@Composable
fun PreviewMyScreensNavigation() {
    MyScreensNavigation()
}

@Composable
fun HomeScreen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val id = 12345
        Text(text = "Home Screen", style = MaterialTheme.typography.h1)

        Button(onClick = { navController.navigate(route = "b_route/100?id=$id") }) {
            Text(text = "Screen B")
        }

        Button(onClick = { navController.navigate(route = "c_route") }) {
            Text(text = "Screen C")
        }

    }
}

@Composable
fun B_Screen(count:Int, id:Long) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)
    ) {
        Text(
            text = "Screen B", style = MaterialTheme.typography.h1,
            modifier = Modifier.align(Alignment.Center)
        )
        Text(text = "Total $count, id $id", style = MaterialTheme.typography.body1.copy(color = Color.Green))
    }
}

@Composable
fun C_Screen(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Text(
            text = "Screen C", style = MaterialTheme.typography.h1
        )

        Button(onClick = { navController.navigate(route = "d_route") }) {
            Text(text = "Screen D")
        }

        Button(onClick = { navController.navigate(route = "e_route") }) {
            Text(text = "Screen E")
        }

        Button(onClick = { navController.navigate(route = "home") }) {
            Text(text = "Home Screen")
        }
    }
}

@Composable
fun D_Screen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Text(
            text = "Screen D", style = MaterialTheme.typography.h1,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}

@Composable
fun E_Screen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Cyan)
    ) {
        Text(
            text = "Screen E", style = MaterialTheme.typography.h1,
            modifier = Modifier.align(Alignment.Center)
        )
    }
}