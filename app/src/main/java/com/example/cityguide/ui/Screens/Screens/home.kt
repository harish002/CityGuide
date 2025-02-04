package com.example.cityguide.ui.Screens.Screens

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Parcelable
import android.util.Log
import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.CityStateData
import com.Data
import com.example.cityguideapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.gson.Gson
import kotlinx.android.parcel.Parcelize

//
//@SuppressLint("RestrictedApi")
//@OptIn(ExperimentalMaterial3Api::class)
//@Composable
//fun User(
//    mAuth: FirebaseAuth,
//    navController: NavHostController
//) {
//
//    val name = mAuth.currentUser?.email
////    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
//
//
//    Scaffold(
//        topBar = {
//            TopAppBar(
//                title = {/* No title */ }, // Leave the title empty or omit this line
//                navigationIcon = {
////                    if (scrollBehavior.state.collapsedFraction < 1f) {
//                    CircularProfile(name) // Replace with dynamic data from api
////                    }
//                },
////                scrollBehavior = scrollBehavior,
//                colors = TopAppBarDefaults.topAppBarColors(
//                    containerColor = MaterialTheme.colorScheme.background,
//                    navigationIconContentColor = Color.Black,
//                    actionIconContentColor = Color.LightGray,
//                )
//            )
//        }
//    ) {
//        val gson = Gson()
//
//    // Deserialize JSON into StateCity object
//        val stateCity: StateCity = gson.fromJson(Data, StateCity::class.java)
//
//    // Extract state names manually (list of property names)
//        val stateNames = listOf(
//            "Andaman and Nicobar Islands",
//            "Andhra Pradesh",
//            "Arunachal Pradesh",
//            "Assam",
//            "Bihar",
//            "Chandigarh",
//            "Chhattisgarh",
//            "Dadra and Nagar Haveli",
//            "Delhi",
//            "Goa",
//            "Gujarat",
//            "Haryana",
//            "Himachal Pradesh",
//            "Jammu and Kashmir",
//            "Jharkhand",
//            "Karnataka",
//            "Kerala",
//            "Madhya Pradesh",
//            "Maharashtra",
//            "Manipur",
//            "Meghalaya",
//            "Mizoram",
//            "Nagaland",
//            "Odisha",
//            "Puducherry",
//            "Punjab",
//            "Rajasthan",
//            "Tamil Nadu",
//            "Telangana",
//            "Tripura",
//            "Uttar Pradesh",
//            "Uttarakhand",
//            "West Bengal"
//        )
//
//        LazyColumn(
//            modifier = Modifier
//                .padding(it)
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            item {
//                Text("City Guide App Dashboard")
//            }
//
//            items(stateNames) { state ->
//                Text(state)
//            }
//        }
//
//    }
//}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun User(
    mAuth: FirebaseAuth,
    navController: NavHostController
) {

    val name = mAuth.currentUser?.email


    var selectedState by remember { mutableStateOf<String?>(null) }
    val gson = Gson()
    // Deserialize JSON into StateCity object
//  val stateCity: StateCity = gson.fromJson(Data, StateCity::class.java)

    val stateCity1: StateCityDataC =
        gson.fromJson(CityStateData, StateCityDataC::class.java)

    val groupedByState = stateCity1.groupBy { it.State } // Group cities by state



    Scaffold(
        topBar = {
            TopAppBar(
                title = {/* No title */ }, // Leave the title empty or omit this line
                navigationIcon = {
//                    if (scrollBehavior.state.collapsedFraction < 1f) {
                    CircularProfile(name,mAuth) // Replace with dynamic data from api
//                    }
                },
//                scrollBehavior = scrollBehavior,
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.background,
                    navigationIconContentColor = Color.Black,
                    actionIconContentColor = Color.LightGray,
                )
            )
        }
    ) {
//        LazyColumn(
//            modifier = Modifier
//                .padding(it)
//                .padding(16.dp)
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            item {
//                Text("City Guide App Dashboard", fontSize = 20.sp, fontWeight = FontWeight.Bold)
//            }
//
//
//            Log.d("test statecity", stateCity1.toString())
//
//            groupedByState.forEach { (state, cities) ->
//                item {
//                    Text(state, fontWeight = FontWeight.Bold,
//                        fontSize = 18.sp,
//                        modifier = Modifier
//                            .fillMaxWidth()
//                            .clickable {
//                                val stateData = ArrayList(cities) // Convert to ArrayList (Parcelable requirement)
//                                navController.currentBackStackEntry?.savedStateHandle?.set("stateData", stateData)
//                                navController.navigate("state_details")
//                            }
//                            .padding(8.dp)) // State Name
//                }
//
//            }
////                items(stateCity1){
////                        i->
////                    Text(i.State)
////                }
//
//            // Convert StateCity object into a map for easier access
////            val stateMap = mapOf(
////                "Andaman and Nicobar Islands" to stateCity.AndamanandNicobarIslands,
////                "Andhra Pradesh" to stateCity.AndhraPradesh,
////                "Arunachal Pradesh" to stateCity.ArunachalPradesh,
////                "Assam" to stateCity.Assam,
////                "Bihar" to stateCity.Bihar,
////                "Chandigarh" to stateCity.Chandigarh,
////                "Chhattisgarh" to stateCity.Chhattisgarh,
////                "Dadra and Nagar Haveli" to stateCity.DadraandNagarHaveli,
////                "Delhi" to stateCity.Delhi,
////                "Goa" to stateCity.Goa,
////                "Gujarat" to stateCity.Gujarat,
////                "Haryana" to stateCity.Haryana,
////                "Himachal Pradesh" to stateCity.HimachalPradesh,
////                "Jammu and Kashmir" to stateCity.JammuandKashmir,
////                "Jharkhand" to stateCity.Jharkhand,
////                "Karnataka" to stateCity.Karnataka,
////                "Kerala" to stateCity.Kerala,
////                "Madhya Pradesh" to stateCity.MadhyaPradesh,
////                "Maharashtra" to stateCity.Maharashtra,
////                "Manipur" to stateCity.Manipur,
////                "Meghalaya" to stateCity.Meghalaya,
////                "Mizoram" to stateCity.Mizoram,
////                "Nagaland" to stateCity.Nagaland,
////                "Odisha" to stateCity.Odisha,
////                "Puducherry" to stateCity.Puducherry,
////                "Punjab" to stateCity.Punjab,
////                "Rajasthan" to stateCity.Rajasthan,
////                "Tamil Nadu" to stateCity.TamilNadu,
////                "Telangana" to stateCity.Telangana,
////                "Tripura" to stateCity.Tripura,
////                "Uttar Pradesh" to stateCity.UttarPradesh,
////                "Uttarakhand" to stateCity.Uttarakhand,
////                "West Bengal" to stateCity.WestBengal
////            )
////
////
////
////            // Display all states
////            stateMap.forEach { (state, cities) ->
////                item {
////                    Column {
////                        Text(
////                            text = state,
////                            fontSize = 18.sp,
////                            fontWeight = FontWeight.SemiBold,
////                            modifier = Modifier
////                                .fillMaxWidth()
////                                .clickable {
////                                    navController.navigate("city_list/${state}")
//////                                selectedState = if (selectedState == state) null else state
////                                }
////                                .padding(8.dp)
////                        )
////
////                        // Show cities if the state is selected
////                        if (selectedState == state) {
////                            cities.forEach { city ->
////                                Text(
////                                    text = "• $city",
////                                    fontSize = 16.sp,
////                                    modifier = Modifier
////                                        .fillMaxWidth()
////                                        .padding(start = 16.dp, top = 4.dp)
////                                )
////                            }
////                        }
////                    }
////                }
////            }
//
//
//        }
        Column (Modifier.fillMaxSize()
            .padding(it)){

//            Text(modifier = Modifier.fillMaxWidth(),
//                textAlign = TextAlign.Center,
//                text = "Select State to see Cities",
//                fontSize = 20.sp, fontWeight = FontWeight.Bold)

            StateGridScreen(navController, stateCity1)
        }
    }
}


@SuppressLint("RestrictedApi")
@Composable
fun CircularProfile(userData: String?, mAuth: FirebaseAuth) {
    var userName = userData
    val id = mAuth.currentUser?.uid
    val initials = userName?.split(" ")?.joinToString("") { it.take(1) }?.uppercase()

    val role = remember { mutableStateOf<String?>(null) }

    // State to manage the dialog visibility
    val showProfileDialog = remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .navigationBarsPadding()
            .padding(top = 30.dp, start = 8.dp)
    ) {
        // Circular Profile Icon
        Box(
            modifier = Modifier
                .size(50.dp)
                .background(
                    Color.White,
                    shape = CircleShape
                )
                .border(2.dp, Color.Black, shape = CircleShape)
                .padding(8.dp)
                .clickable { showProfileDialog.value = true }, // Show dialog on tap
            contentAlignment = Alignment.Center
        ) {
            if (initials != null) {
                Text(
                    text = initials,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 24.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.width(8.dp))

        // Welcome Message
        Column {
            Text(
                text = "Welcome",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold
            )

            userData?.let {
                Text(
                    text = it,
                    color = MaterialTheme.colorScheme.onSurface,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Normal
                )
            }
        }
    }

    // Profile Dialog
    if (showProfileDialog.value) {
        AlertDialog(
            onDismissRequest = { showProfileDialog.value = false },
            title = { Text("Profile Details") },
            text = {
                Column(
                    horizontalAlignment = Alignment.Start
                ) {
                    var number = remember { mutableStateOf<String?>(null) }
                    var name = remember { mutableStateOf<String?>(null) }
                    val databaseRef = id?.let { FirebaseDatabase.getInstance().getReference("users").child(it) }
                    if (databaseRef != null) {
                        databaseRef.child("phone").get().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val phone = task.result?.value as? String
                                // Use the fetched phone number
                                number.value = phone

                                Log.d("PhoneNumber", "Fetched phone: $phone")
                            } else {
                                // Handle failure
                                number.value = null
                                Log.e("PhoneNumber", "Failed to fetch phone number", task.exception)
                            }
                        }

                        databaseRef.child("username").get().addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                val username = task.result?.value as? String
                                // Use the fetched phone number
                                name.value = username

                                Log.d("Username", "Fetched phone: $username")
                            } else {
                                // Handle failure
                                name.value = null
                                Log.e("PhoneNumber", "Failed to fetch phone number", task.exception)
                            }
                        }
                    }



                    ProfileItem(icon = Icons.Default.AccountCircle, text = "Username", value = "${name.value?.capitalize()}")

                    Spacer(modifier = Modifier.height(8.dp))

                    ProfileItem(icon = Icons.Default.Email, text = "Email-Id", value = mAuth.currentUser?.email ?: "Not available")

                    Spacer(modifier = Modifier.height(8.dp))

                    ProfileItem(icon = Icons.Default.Phone, text = "Phone", value = number.value ?: "Not available")

                    Spacer(modifier = Modifier.height(8.dp))



                    // Add more details as needed
                }
            },
            confirmButton = {
                TextButton(onClick = { showProfileDialog.value = false }) {
                    Text("Close")
                }
            },
            modifier = Modifier.padding(16.dp)
        )
    }
}

@Composable
fun ProfileItem(icon: ImageVector, text: String, value: String) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start,
            modifier = Modifier.padding(vertical = 4.dp)
        ) {
            Icon(imageVector = icon, contentDescription = text, modifier = Modifier.size(24.dp))

            Spacer(modifier = Modifier.width(6.dp))

            Text(text = text, style = MaterialTheme.typography.bodyLarge,fontSize = 18.sp)

        }

        Spacer(modifier = Modifier.width(8.dp))

        Text(text = value, style = MaterialTheme.typography.bodyLarge, fontSize = 20.sp, modifier = Modifier.padding(start = 6.dp), fontWeight = FontWeight.Bold)
    }

}

class StateCityDataC : ArrayList<StateCityDataCItem>()


@Parcelize
data class StateCityDataCItem(
    val City: String,
    val Latitude: Double,
    val Longitude: Double,
    val Name: String,
    val State: String,
    val Type: String,
) : Parcelable


//data class StateCity(
//    val AndamanandNicobarIslands: List<String> = emptyList(),
//    val AndhraPradesh: List<String> = emptyList(),
//    val ArunachalPradesh: List<String> = emptyList(),
//    val Assam: List<String> = emptyList(),
//    val Bihar: List<String> = emptyList(),
//    val Chandigarh: List<String> = emptyList(),
//    val Chhattisgarh: List<String> = emptyList(),
//    val DadraandNagarHaveli: List<String> = emptyList(),
//    val Delhi: List<String> = emptyList(),
//    val Goa: List<String> = emptyList(),
//    val Gujarat: List<String> = emptyList(),
//    val Haryana: List<String> = emptyList(),
//    val HimachalPraddesh: List<String> = emptyList(),
//    val HimachalPradesh: List<String> = emptyList(),
//    val JammuandKashmir: List<String> = emptyList(),
//    val Jharkhand: List<String> = emptyList(),
//    val Karnataka: List<String> = emptyList(),
//    val Kerala: List<String> = emptyList(),
//    val MadhyaPradesh: List<String> = emptyList(),
//    val Maharashtra: List<String> = emptyList(),
//    val Manipur: List<String> = emptyList(),
//    val Meghalaya: List<String> = emptyList(),
//    val Mizoram: List<String> = emptyList(),
//    val Nagaland: List<String> = emptyList(),
//    val Odisha: List<String> = emptyList(),
//    val Puducherry: List<String> = emptyList(),
//    val Punjab: List<String> = emptyList(),
//    val Rajasthan: List<String> = emptyList(),
//    val TamilNadu: List<String> = emptyList(),
//    val Telangana: List<String> = emptyList(),
//    val Tripura: List<String> = emptyList(),
//    val UttarPradesh: List<String> = emptyList(),
//    val Uttarakhand: List<String> = emptyList(),
//    val WestBengal: List<String> = emptyList()
//)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CityListScreen(stateName: String, cities: List<String>, navController: NavHostController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stateName) },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        }
    ) { paddingValues ->
        LazyColumn(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            items(cities) { city ->
                Text(
                    text = city,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                )
            }
        }
    }
}



//  WORKING WALA
//@Composable
//fun StateDetailsScreen(navController: NavController) {
//    val navBackStackEntry = navController.previousBackStackEntry
//    val stateData = navBackStackEntry?.savedStateHandle?.get<List<StateCityDataCItem>>("stateData") ?: emptyList()
//
//    if (stateData.isEmpty()) {
//        Text("No data found", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
//        return
//    }
//
//    val stateName = stateData.first().State
//
//    Column(modifier = Modifier
//        .fillMaxSize()
//        .padding(16.dp)) {
//        Text(text = "State: $stateName", fontWeight = FontWeight.Bold, fontSize = 22.sp)
//
//        LazyColumn {
//            items(stateData) { city ->
//                Card(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .padding(8.dp)
//                        .clickable {
//
//                        },
//                ) {
//                    Column(modifier = Modifier.padding(16.dp)) {
//                        Text(text = "Name: ${city.Name}", fontSize = 18.sp)
//                        Text(text = "Type: ${city.Type}", fontSize = 14.sp)
////                        Text(text = "Latitude: ${city.Latitude}", fontSize = 14.sp)
////                        Text(text = "Longitude: ${city.Longitude}", fontSize = 14.sp)
//                        Text(text = "City: ${city.City}", fontSize = 18.sp)
//                    }
//                }
//            }
//        }
//    }
//}

@Composable
fun StateDetailsScreen(navController: NavController) {
    val context = LocalContext.current
    val navBackStackEntry = navController.previousBackStackEntry
    val stateData = navBackStackEntry?.savedStateHandle?.get<List<StateCityDataCItem>>("stateData") ?: emptyList()

    if (stateData.isEmpty()) {
        Text("No data found", modifier = Modifier.fillMaxSize(), textAlign = TextAlign.Center)
        return
    }

    val stateName = stateData.first().State

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp)
            .padding(top = 30.dp)
    ) {
        Text(
            text = "State: $stateName",
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp,
            modifier = Modifier.padding(16.dp)
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            modifier = Modifier.padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            items(stateData) { city ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(4.dp)
                        .clickable {
                            val uri = Uri.parse("geo:${city.Latitude},${city.Longitude}?q=${city.Latitude},${city.Longitude}(${city.Name})")
                            val intent = Intent(Intent.ACTION_VIEW, uri).apply {
                                setPackage("com.google.android.apps.maps") // Open in Google Maps
                            }
                            context.startActivity(intent)
                        },
                    shape = RoundedCornerShape(12.dp)

                ) {
                    Column(modifier = Modifier.padding(8.dp)) {

                        Image(
                            painter = painterResource(id = R.drawable.location),
                            contentDescription = "App Logo",
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(120.dp)
                                .clip(RoundedCornerShape(12.dp)),
                            contentScale = ContentScale.Crop
                        )

                        Spacer(modifier = Modifier.padding(vertical = 8.dp))

                        Text(
                            text = city.Name,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp),
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )

                        Text(
                            text = city.City,
                            fontSize = 16.sp,
                            color = Color.Gray,
                            modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp)
                        )
                    }
                }
            }
        }
    }
}




@Composable
fun StateGridScreen(navController: NavController, stateCityList: StateCityDataC) {
    val groupedByState = stateCityList.groupBy { it.State }
    val gridState = rememberLazyGridState()

    LazyVerticalGrid(
        columns = GridCells.Fixed(2), // 2 columns per row
        state = gridState,
        modifier = Modifier
            .fillMaxSize()
            .padding(8.dp),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        groupedByState.forEach { (state, cities) ->
            item {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(240.dp)
                        .padding(6.dp)
                        .clickable {
                            val stateData = ArrayList(cities) // Convert to Parcelable list
                            navController.currentBackStackEntry?.savedStateHandle?.set(
                                "stateData",
                                stateData
                            )
                            navController.navigate("state_details")
                        },
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(6.dp),
                    colors = CardDefaults.cardColors(containerColor = Color.White)
                ) {
                    Box(modifier = Modifier.padding(12.dp)) {
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            // Use the function to get the image for each state
                            Image(
                                painter = painterResource(id = getStateImage(state)),
                                contentDescription = "$state Image",
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(130.dp)
                                    .clip(RoundedCornerShape(12.dp)),
                                contentScale = ContentScale.Crop
                            )

                            Spacer(modifier = Modifier.padding(top = 12.dp))

                            Text(
                                text = state,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold,
                                color = Color.Black,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 4.dp),
                                textAlign = TextAlign.Center
                            )
                        }
                    }
                }
            }
        }
    }
}

@DrawableRes
fun getStateImage(state: String): Int {
    return when (state) {
        "Delhi" -> R.drawable.delhi
        "Maharashtra" -> R.drawable.maharashtra
        "Uttar Pradesh" -> R.drawable.uttarpradesh
        "Rajasthan" -> R.drawable.rajasthan
        "Karnataka" -> R.drawable.karnataka
        "Gujarat" -> R.drawable.gujarat
        "West Bengal" -> R.drawable.westbengal
        "Tamil Nadu" -> R.drawable.tamilnadu
        "Madhya Pradesh" -> R.drawable.madhyapradesh
        "Punjab" -> R.drawable.punjab
        "Haryana" -> R.drawable.haryana
        "Bihar" -> R.drawable.bihar
        "Kerala" -> R.drawable.kerela
        "Telangana" -> R.drawable.telengana
        "Odisha" -> R.drawable.odisha
        "Andhra Pradesh" -> R.drawable.andhra
        "Assam" -> R.drawable.assam
        "Uttarakhand" -> R.drawable.uttarakhand
        "Ladakh" -> R.drawable.ladakh
        "Jharkhand" -> R.drawable.jharkhand
        "Meghalaya" -> R.drawable.meghalaya
        "Daman and Diu" -> R.drawable.daman
        "Jammu and Kashmir" -> R.drawable.jammukashmir
        "Goa" -> R.drawable.goa
        else -> R.drawable.states // Default Image for Unknown States
    }
}