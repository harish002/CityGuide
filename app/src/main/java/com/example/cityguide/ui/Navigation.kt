package com.example.fooddonateapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cityguide.ui.Screens.Screens.FeedbackForm
import com.example.cityguide.ui.Screens.Screens.LoginScreen
import com.example.cityguide.ui.Screens.Screens.RegisterScreen
import com.example.cityguide.ui.Screens.Screens.StateDetailsScreen
import com.example.cityguide.ui.Screens.Screens.StateGridScreen
import com.example.cityguide.ui.Screens.Screens.User
import com.example.fooddonateapp.ui.Screens.Screens.AboutUsScreen
import com.example.fooddonateapp.ui.Screens.Screens.ContactUsScreen
import com.example.fooddonateapp.ui.Screens.Screens.EmergencyContactsScreen
import com.google.firebase.auth.FirebaseAuth


@Composable
fun NavGraph(navController: NavHostController) {
    val votesList = mutableMapOf<String, Boolean>()

    Surface(modifier = Modifier.fillMaxSize()) {
        NavHost(
            navController = navController,
            startDestination = "login"
        ) {
            composable("login") {
                LoginScreen(votesList, navController)
            }

            composable("state_details") {
                StateDetailsScreen(navController)
            }

            composable("register") {
                RegisterScreen(navController)
            }

            composable("user") {
                User(FirebaseAuth.getInstance(), navController)
            }

            composable("explore")
            {
                StateGridScreen(navController)
            }

            composable("about_us")
            {
                AboutUsScreen(navController)
            }

            composable("contact_us")
            {
                ContactUsScreen(navController)
            }

            composable("feedback")
            {
                FeedbackForm(navController)
            }

            composable("emergency")
            {
                EmergencyContactsScreen(navController)
            }


        }
    }
}
