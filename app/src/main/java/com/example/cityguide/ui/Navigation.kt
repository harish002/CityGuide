package com.example.fooddonateapp.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.cityguide.ui.Screens.Screens.LoginScreen
import com.example.cityguide.ui.Screens.Screens.RegisterScreen
import com.example.cityguide.ui.Screens.Screens.StateDetailsScreen
import com.example.cityguide.ui.Screens.Screens.User
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

//            composable(
//                "city_list/{stateName}",
//                arguments = listOf(navArgument("stateName") { type = NavType.StringType })
//            ) { backStackEntry ->
//                val stateName = backStackEntry.arguments?.getString("stateName") ?: ""
//                val gson = Gson()
//                val stateCity: StateCity = gson.fromJson(Data, StateCity::class.java)
//
//                // Get the list of cities for the selected state
//                val cities = when (stateName) {
//                    "Andaman and Nicobar Islands" -> stateCity.AndamanandNicobarIslands
//                    "Andhra Pradesh" -> stateCity.AndhraPradesh
//                    "Arunachal Pradesh" -> stateCity.ArunachalPradesh
//                    "Assam" -> stateCity.Assam
//                    "Bihar" -> stateCity.Bihar
//                    "Chandigarh" -> stateCity.Chandigarh
//                    "Chhattisgarh" -> stateCity.Chhattisgarh
//                    "Dadra and Nagar Haveli" -> stateCity.DadraandNagarHaveli
//                    "Delhi" -> stateCity.Delhi
//                    "Goa" -> stateCity.Goa
//                    "Gujarat" -> stateCity.Gujarat
//                    "Haryana" -> stateCity.Haryana
//                    "Himachal Pradesh" -> stateCity.HimachalPradesh
//                    "Jammu and Kashmir" -> stateCity.JammuandKashmir
//                    "Jharkhand" -> stateCity.Jharkhand
//                    "Karnataka" -> stateCity.Karnataka
//                    "Kerala" -> stateCity.Kerala
//                    "Madhya Pradesh" -> stateCity.MadhyaPradesh
//                    "Maharashtra" -> stateCity.Maharashtra
//                    "Manipur" -> stateCity.Manipur
//                    "Meghalaya" -> stateCity.Meghalaya
//                    "Mizoram" -> stateCity.Mizoram
//                    "Nagaland" -> stateCity.Nagaland
//                    "Odisha" -> stateCity.Odisha
//                    "Puducherry" -> stateCity.Puducherry
//                    "Punjab" -> stateCity.Punjab
//                    "Rajasthan" -> stateCity.Rajasthan
//                    "Tamil Nadu" -> stateCity.TamilNadu
//                    "Telangana" -> stateCity.Telangana
//                    "Tripura" -> stateCity.Tripura
//                    "Uttar Pradesh" -> stateCity.UttarPradesh
//                    "Uttarakhand" -> stateCity.Uttarakhand
//                    "West Bengal" -> stateCity.WestBengal
//                    else -> emptyList()
//                }
//
//                CityListScreen(stateName, cities, navController)
//            }

            composable("register") {
                RegisterScreen(navController)
            }
            composable("user") {
                User(FirebaseAuth.getInstance(), navController)
//                DonationList(FirebaseAuth.getInstance()) // Ensure 'voting' is @Composable
            }
        }
    }
}
