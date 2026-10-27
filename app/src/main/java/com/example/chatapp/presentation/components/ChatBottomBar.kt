package com.example.chatapp.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavDestination.Companion.hasRoute
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.chatapp.R
import com.example.chatapp.presentation.navigation.Call
import com.example.chatapp.presentation.navigation.People
import com.example.chatapp.presentation.navigation.Profile
import com.example.chatapp.presentation.navigation.Add
import com.example.chatapp.presentation.navigation.Home

@Composable
fun ChatBottomBar(
    navController : NavHostController
){

    val navBackStackEntry by
    navController.currentBackStackEntryAsState()

    val destination = navBackStackEntry?.destination
    BottomAppBar(
        modifier = Modifier.padding(vertical = 8.dp).background(Color.White).clip(RoundedCornerShape(16.dp)),
//        tonalElevation = 16.dp
    ) {
        NavigationBar {
            NavigationBarItem(

                onClick = {
                    navController.navigate(Home){
                        launchSingleTop = true
                    }
                },
                selected = destination?.hasRoute<Home>() == true,
                label = {
                    Text(
                        text = "Chats"
                    )
                },
                icon = {
                    Icon(
                        painterResource(R.drawable.baseline_chat_24),
                        contentDescription = "chat icon"
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    selectedTextColor = Color.Blue,
                    unselectedIconColor = Color.Black,
                    unselectedTextColor = Color.Black
                )
            )

            NavigationBarItem(
                onClick = {
                    navController.navigate(People){
                        launchSingleTop = true
                    }
                },
                selected = destination?.hasRoute<People>() == true,
                label = {
                    Text(
                        text = "People"
                    )
                },
                icon = {
                    Icon(
                        painterResource(R.drawable.baseline_people_alt_24),
                        contentDescription = "chat icon"
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    selectedTextColor = Color.Blue,
                    unselectedIconColor = Color.DarkGray,
                    unselectedTextColor = Color.DarkGray
                )
            )

            NavigationBarItem(
                onClick = {
                    navController.navigate(Add){
                        launchSingleTop = true
                    }
                },
                selected = destination?.hasRoute<Add>() == true,
                label = {
                    Text(
                        text = "Add"
                    )
                },
                icon = {
                    Icon(
                        painterResource(R.drawable.outline_add_circle_24),
                        contentDescription = "chat icon"
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.Blue,
                    selectedTextColor = Color.Blue,
                    unselectedIconColor = Color.DarkGray,
                    unselectedTextColor = Color.DarkGray
                )
            )

            NavigationBarItem(
                onClick = {
                    navController.navigate(Call){
                        launchSingleTop = true
                    }
                },
                selected = destination?.hasRoute<Call>() == true,
                label = {
                    Text(
                        text = "Calls"
                    )
                },
                icon = {
                    Icon(
                        painterResource(R.drawable.outline_call_24),
                        contentDescription = "chat icon"
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.Blue,
                    unselectedIconColor = Color.DarkGray,
                    unselectedTextColor = Color.DarkGray
                )
            )

            NavigationBarItem(
                onClick = {
                    navController.navigate(Profile){
                        launchSingleTop = true
                    }
                },
                selected = destination?.hasRoute<Profile>() == true,
                label = {
                    Text(
                        text = "Profile"
                    )
                },
                icon = {
                    Icon(
                        painterResource(R.drawable.round_person_24),
                        contentDescription = "chat icon"
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = Color.White,
                    selectedTextColor = Color.Blue,
                    unselectedIconColor = Color.DarkGray,
                    unselectedTextColor = Color.DarkGray
                )
            )
        }
    }
}