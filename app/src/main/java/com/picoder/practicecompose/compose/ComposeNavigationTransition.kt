package com.picoder.practicecompose.compose

//import androidx.navigation.compose.*
import android.content.Intent
import androidx.compose.animation.*
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.navigation
import com.google.accompanist.navigation.animation.rememberAnimatedNavController

val screens = listOf(
    Screen.Home,
    Screen.Cart,
    Screen.Favorites,
    Screen.More,
)

@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun MainScreenNavigationTransition() {
    val navController = rememberAnimatedNavController()
    val currBackStackEntry by navController.currentBackStackEntryAsState()
    val currDest = currBackStackEntry?.destination
    Scaffold(bottomBar = {
        BottomNavigation {
            screens.forEach { screen ->
                BottomNavigationItem(
                    selected = currDest?.hierarchy?.any { dest -> dest.route == screen.route } == true,
                    onClick = {
                        navController.navigate(screen.route) {
                            popUpTo(navController.graph.findStartDestination().id) {
                                saveState = true
                            }

                            restoreState = true
                            launchSingleTop = true
                        }
                    },
                    icon = { Icon(screen.icon, screen.title) },
                    label = { Text(screen.title) }
                )
            }
        }
    }) {
        AnimatedNavHost(navController = navController,
            startDestination = Screen.Home.route,
            enterTransition = {
                when (this.targetState.destination.route) {
                    Screen.Favorites.route -> slideInVertically(
                        initialOffsetY = { height -> -height },
                        animationSpec = spring(Spring.DampingRatioMediumBouncy)
                    )
                    else -> slideInHorizontally(
                        initialOffsetX = { width -> -width },
                        animationSpec = tween(1000)
                    )
                }
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { width -> width },
                    animationSpec = tween(1000)
                )
            },
            popExitTransition = {
                shrinkOut(
                    shrinkTowards = Alignment.Center,
                    targetSize = { size -> IntSize(0, size.height) },
                    animationSpec = tween(300)
                )
            },
            popEnterTransition = {
                slideIntoContainer(
                    towards = AnimatedContentScope.SlideDirection.Up,
                    animationSpec = tween(1000, delayMillis = 300)
                )

            }
        ) {
            // com.google.accompanist.navigation.animation.composable
            composable(Screen.Home.route, exitTransition = {
                when (this.targetState.destination.route) {
                    Screen.Cart.route -> shrinkVertically(
                        shrinkTowards = Alignment.Bottom,
                        targetHeight = { height -> 0 },
                        animationSpec = tween(1000)
                    )
                    else -> null
                }
            }) {
                MyScreen(Screen.Home.title, Screen.Home.icon)
            }
            composable(Screen.Cart.route,
                enterTransition = {
                    when (initialState.destination.route) {
                        Screen.Home.route -> expandIn(
                            expandFrom = Alignment.Center,
                            initialSize = { size -> IntSize(0, size.height) },
                            animationSpec = spring(Spring.DampingRatioMediumBouncy)
                        )
                        else -> null // use default animation defined in AnimatedNavHost
                    }
                }) {
                MyScreen(Screen.Cart.title, Screen.Cart.icon)
            }
            // com.google.accompanist.navigation.animation.navigation
            navigation(route = Screen.More.route, startDestination = Screen.Profile.route,
                enterTransition = {
                    fadeIn(animationSpec = tween(1000))
                    // todo tween != spring ???
                },
                exitTransition = {
                    fadeOut(animationSpec = tween(1000))
                }) {
                composable(Screen.Profile.route) {
                    MyScreen(title = Screen.Profile.title, icon = Screen.Profile.icon) {
                        val ctx = LocalContext.current
                        Column(
                            Modifier
                                .align(Alignment.TopCenter)
                                .padding(8.dp)
                                .padding(top = 32.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Button(
                                onClick = {
                                    navController.navigate(Screen.Contact.route)
                                },
                                modifier = Modifier
                                    .padding(8.dp)
                            ) {
                                Text("Email")
                            }

                            Button(modifier = Modifier
                                .padding(8.dp),
                                onClick = {
                                    val intent = Intent(Intent.ACTION_VIEW).apply {
                                        data = "https://example.com".toUri()
                                    }
                                    ContextCompat.startActivity(ctx, intent, null)

                                }) {
                                Text("Website")
                            }

                            Column(
                                modifier = Modifier
                                    .padding(4.dp)
                                    .background(Color.LightGray, RoundedCornerShape(4.dp))
                            ) {
                                Text(
                                    "Email: info@example.com", Modifier
                                        .padding(4.dp)
                                )
                                Text(
                                    "JOHN \"GULLIBLE\" DOE\n" +
                                            "CENTER FOR FINANCIAL ASSISTANCE\n" +
                                            "421 E DRACHMAN\n" +
                                            "TUCSON AZ 85705-7598\n" +
                                            "USA\n", Modifier
                                        .padding(4.dp)
                                )
                            }

                        }
                    }
                }
                composable(Screen.Contact.route) {
                    MyScreen(Screen.Contact.title, Screen.Contact.icon)
                }
            }
            composable(Screen.Favorites.route) {
                MyScreen(Screen.Favorites.title, Screen.Favorites.icon)
            }

        }
    }
}

@Composable
fun MyScreen(
    title: String,
    icon: ImageVector,
    extra_content: @Composable BoxScope.() -> Unit = {}
) {
    Box(
        Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    ) {
        Icon(
            icon, title, Modifier
                .fillMaxSize(0.3f)
                .align(Alignment.Center)
        )
        Text(
            title, style = MaterialTheme.typography.h2, fontWeight = FontWeight.Bold,
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(16.dp)
                .padding(bottom = 100.dp)
        )

        extra_content()
    }
}