package com.picoder.practicecompose.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController

@Composable
fun MyNestedNavigationScreens() {
    var navController = rememberNavController()
    NavHost(navController = navController, startDestination = "A") {
        composable(route = "A") {
            Screen(label = it.destination.route.toString()) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Button(
                        onClick = { navController.navigate("B") },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Go to screen B")
                    }

                    Button(
                        onClick = { navController.navigate("C") },
                        modifier = Modifier.padding(8.dp)
                    ) {
                        Text(text = "Go to screen C")
                    }
                }
            }
        }

        //  nested navigation in route B with start screen is B1
        navigation(route = "B", startDestination = "B1") {
            // screen B1
            composable(route = "B1") {
                Screen(label = it.destination.route.toString()) {
                    Column {
                        Button(onClick = { navController.navigate("B1_1") }) {
                            Text(text = "Go to B1_1")
                        }

                        Button(onClick = { navController.navigate("B1_2") }) {
                            Text(text = "Go to B1_2")
                        }
                    }

                }
            }

            // children screens of B1
            composable(route = "B1_1") {
                Screen(label = it.destination.route.toString())
            }

            composable(route = "B1_2") {
                Screen(label = it.destination.route.toString())
            }
        }

        composable(route = "C") {
            Screen(label = "C")
        }
    }
}

@Composable
fun Screen(label: String, content: @Composable () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            fontSize = 30.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
        // invoke the passed content here
        content()
    }

}