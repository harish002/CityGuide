package com.example.cityguide.ui.Screens.Screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.cityguide.ui.Component.custTextInput
import com.example.cityguideapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

@Composable
fun RegisterScreen(navController: NavHostController) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    val context = LocalContext.current
    val mAuth = FirebaseAuth.getInstance()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        item {
            Box(
                modifier = Modifier
                    .padding(top = 30.dp)
                    .size(200.dp),
                contentAlignment = Alignment.BottomCenter

            ) {
                Image(
                    painter = painterResource(R.drawable.app_logo),
                    contentDescription = "app logo",
                    modifier = Modifier.fillMaxWidth()
                        .align(Alignment.BottomCenter)
                )
                Text(
                    "City Guide App",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                )
            }
            custTextInput(label = "Username", value = username, onValueChange = { username = it })
            Spacer(modifier = Modifier.padding(10.dp))

            custTextInput(label = "Password", value = password, onValueChange = { password = it })
            Spacer(modifier = Modifier.padding(10.dp))

            custTextInput(label = "Email", value = email, onValueChange = { email = it })
            Spacer(modifier = Modifier.padding(10.dp))

            custTextInput(label = "Phone", value = phone, onValueChange = { phone = it })
            Spacer(modifier = Modifier.padding(10.dp))





            Spacer(modifier = Modifier.padding(20.dp))

            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                Button(onClick = {
                    navController.navigate("login")
                }) {
                    Text("Login")
                }

                Spacer(modifier = Modifier.padding(10.dp))

                Button(
                    enabled = (password.isNotBlank() && username.isNotBlank() && email.isNotBlank() && phone.isNotBlank()),
                    onClick = {
                        registerNewUser(
                            context,
                            mAuth,
                            UserRegister(username, password, phone, email),
                            navController
                        )
                    }) {
                    Text("Register")
                }
            }
        }
    }
}


fun registerNewUser(
    context: Context,
    mAuth: FirebaseAuth,
    user: UserRegister,
    navController: NavHostController
) {
    mAuth.createUserWithEmailAndPassword(user.email, user.password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Save user data with role in Firebase Realtime Database or Firestore
                val userId = mAuth.currentUser?.uid
                val databaseRef =
                    FirebaseDatabase.getInstance().getReference("users").child(userId!!)
                val userData = mapOf(
                    "username" to user.username,
                    "phone" to user.phone,
                    "email" to user.email,
                )


                databaseRef.setValue(userData).addOnCompleteListener { dbTask ->
                    if (dbTask.isSuccessful) {
                        Toast.makeText(context, "Registration successful!", Toast.LENGTH_SHORT)
                            .show()
                        navController.navigate("login")
                    } else {
                        Toast.makeText(
                            context,
                            "Failed to save user data: ${dbTask.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }


                }
            } else {
                Toast.makeText(
                    context,
                    "Registration failed: ${task.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
}


data class UserRegister(
    val username: String,
    val password: String,
    val phone: String,
    val email: String,
)
