package com.picoder.practicecompose.compose

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.Placeholder
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.picoder.practicecompose.R

@Preview
@Composable
fun ScreenWithBottomNavigation() {
    val nc = rememberNavController()
    // create structure layout for screen (like co-ordinate layout)
    Scaffold(
        topBar = { TopAppBar(title = { Text(text = stringResource(id = R.string.app_name)) }) },
        bottomBar = { MyBottomBar(nc) }
    ) { padding ->
        NavHost(
            navController = nc,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(padding)
        ) {
            composable(Screen.Home.route) {
                HomeItemScreen()
            }

            composable(Screen.Profile.route) {
                ProfileItemScreen()
            }

            composable(Screen.Notification.route) {
                NotificationItemScreen()
            }
        }

    }
}

@Composable
fun NotificationItemScreen() {
    Placeholder(title = Screen.Notification.title, icon = Screen.Notification.icon)
}

@Composable
fun ProfileItemScreen() {
    Placeholder(title = Screen.Profile.title, icon = Screen.Profile.icon)
}

@Composable
fun HomeItemScreen() {
    Placeholder(title = Screen.Home.title, icon = Screen.Home.icon)
}

@Composable
fun Placeholder(title: String, icon: ImageVector, content: @Composable () -> Unit = {}) {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            title,
            style = MaterialTheme.typography.h3,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(16.dp)
        )
        Icon(icon, title, modifier = Modifier.fillMaxSize(0.3f))
        content()

    }
}

val mainScreens = listOf(Screen.Home, Screen.Profile, Screen.Notification)

@Composable
fun MyBottomBar(nc: NavHostController) {
    val currentBackStackEntry by nc.currentBackStackEntryAsState()
    val currentDestination = currentBackStackEntry?.destination
    BottomNavigation {
        for (screen in mainScreens) {
            BottomNavigationItem(selected = currentDestination?.hierarchy?.any { it.route == screen.route } == true,
                onClick = {
                    nc.navigate(screen.route) {
                        popUpTo(nc.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = { Icon(screen.icon, screen.title) },
                label = { Text(text = screen.title) })
        }
    }
}

sealed class Screen(val route: String, val icon: ImageVector, val title: String) {
    object Home : Screen("home", Icons.Outlined.Home, "Home")
    object Profile : Screen("profile", Icons.Outlined.Person, "Profile")
    object Notification : Screen("notification", Icons.Outlined.Notifications, "Notifications")
}
