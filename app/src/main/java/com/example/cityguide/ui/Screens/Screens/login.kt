package com.example.cityguide.ui.Screens.Screens

import android.annotation.SuppressLint
import android.content.Context
import android.text.TextUtils
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
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


@SuppressLint("SuspiciousIndentation")
@Composable
fun LoginScreen(votesList: MutableMap<String, Boolean>, navController: NavHostController) {
    val context = LocalContext.current
    var username by remember { mutableStateOf("") }
    var Password by remember { mutableStateOf("") }
    val mAuth = FirebaseAuth.getInstance()


    Box(Modifier.fillMaxSize()) {
        Image(
            modifier = Modifier.fillMaxSize(),
            contentScale = ContentScale.FillBounds,
            painter = painterResource(R.drawable.cityapp_background),
            contentDescription = "app logo",
        )

        Column(
            Modifier.padding(18.dp, 12.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

                Spacer(modifier = Modifier.padding(top = 40.dp))

                Image(
                    painter = painterResource(R.drawable.logopreview),
                    contentDescription = "app logo",
                    modifier = Modifier.fillMaxWidth()
                )

                Spacer(modifier = Modifier.padding(top = 8.dp))

                Text(
                    "City Guide App",
                    fontWeight = FontWeight.Bold,
                    fontSize = 26.sp
                )


            Spacer(modifier = Modifier.padding(vertical = 8.dp))

            custTextInput(label = "Usename",
                value = username,
                onValueChange = { username = it })
            Spacer(modifier = Modifier.padding(10.dp))
            custTextInput(label = "Password",
                value = Password,
                onValueChange = { Password = it })

            Spacer(modifier = Modifier.padding(10.dp))


            Button(onClick = {
                loginUser(
                    context = context,
                    navController = navController,
                    user = User(username, Password),
                    mAuth = mAuth
                )
            },
                modifier = Modifier.fillMaxWidth(0.5f)
            )
            {
                Text("Login")
            }

            Spacer(modifier = Modifier.padding(10.dp))

            Button(onClick = {
                    navController.navigate("register")
                },
                modifier = Modifier.fillMaxWidth(0.5f)
            ) {
                Text("Register")
            }
            Spacer(modifier = Modifier.padding(10.dp))


        }
    }

//    }


}

//
//private fun loginUserAccount(
//    context: Context,
//    navController: NavHostController,
//    user: User, mAuth: FirebaseAuth
//) {
//
//
//    // validations for input email and password
//    if (TextUtils.isEmpty(user.username)) {
//        Toast.makeText(
//            context,
//            "Please enter email!!",
//            Toast.LENGTH_LONG
//        )
//            .show()
//        return
//    }
//    if (TextUtils.isEmpty(user.password)) {
//        Toast.makeText(
//            context,
//            "Please enter password!!",
//            Toast.LENGTH_LONG
//        )
//            .show()
//        return
//    }
//
//    // signin existing user
//    mAuth.signInWithEmailAndPassword(user.username, user.password)
//        .addOnCompleteListener(
//            OnCompleteListener<AuthResult?> { task ->
//                if (task.isSuccessful) {
//                    Toast.makeText(
//                        context,
//                        "Login successful!!",
//                        Toast.LENGTH_LONG
//                    )
//                        .show()
//
//                    val isvoteData = IsvoteData(user.username, true)
//                    val votesJson = Gson().toJson(isvoteData)
//
//                    save_votes(context, votesJson)
//
//                    navController.navigate("voting")
//                } else {
//
//                    // sign-in failed
//                    Toast.makeText(
//                        context,
//                        "Login failed!!",
//                        Toast.LENGTH_LONG
//                    )
//                        .show()
//                }
//            })
//}

fun loginUser(
    context: Context, mAuth: FirebaseAuth,
    user: User,
//              email: String,
//              password: String,
    navController: NavHostController
) {

    // validations for input email and password
    if (TextUtils.isEmpty(user.username)) {
        Toast.makeText(
            context,
            "Please enter email!!",
            Toast.LENGTH_LONG
        )
            .show()
        return
    }
    if (TextUtils.isEmpty(user.password)) {
        Toast.makeText(
            context,
            "Please enter password!!",
            Toast.LENGTH_LONG
        )
            .show()
        return
    }


    mAuth.signInWithEmailAndPassword(user.username, user.password)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val userId = mAuth.currentUser?.uid
                val databaseRef =
                    FirebaseDatabase.getInstance().getReference("users").child(userId!!)
                databaseRef.get().addOnSuccessListener { snapshot ->
                    val role = snapshot.child("role").value as? String
                    when (role) {
                        "Donor" -> navController.navigate("donor")
                        "User" -> navController.navigate("user")
                        else -> Toast.makeText(context, "Unknown role!", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(
                    context,
                    "Login failed: ${task.exception?.message}",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
}


data class User(
    val username: String,
    val password: String
)

data class UserRegisterCheck(
    val username: String,
    val phone: String,
    val isVoted: Boolean
)



