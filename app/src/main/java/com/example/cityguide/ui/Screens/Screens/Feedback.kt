package com.example.cityguide.ui.Screens.Screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import androidx.navigation.NavHostController
import com.example.cityguide.ui.Component.custTextInput
import com.example.cityguideapp.R
import com.google.firebase.database.FirebaseDatabase


@Composable
fun FeedbackForm(navController: NavHostController){
    var name by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var phone by remember { mutableStateOf("") }
    var message by remember { mutableStateOf("") }
    val databaseRef = FirebaseDatabase.getInstance().getReference("inquiries")
    val context = LocalContext.current

    Column (
        modifier = Modifier.fillMaxWidth()
            .padding(horizontal = 12.dp)
            .padding(top = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Text(
                "Feedback Form",
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp),
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.feedback),
                contentDescription = "About Us Image",
                modifier = Modifier
                    .clip(RoundedCornerShape(12.dp))
                    .width(150.dp)
                    .height(150.dp),
                contentScale = ContentScale.Crop
            )
        }

        custTextInput(
            label = "Name",
            value = name,
            onValueChange = { name = it })
        Spacer(modifier = Modifier.padding(10.dp))


        custTextInput(
            label = "Email",
            value = email,
            onValueChange = { email = it })
        Spacer(modifier = Modifier.padding(10.dp))

        custTextInput(
            label = "Phone",
            value = phone,
            onValueChange = { phone = it })
        Spacer(modifier = Modifier.padding(10.dp))

        custTextInput(
            label = "Message",
            value = message,
            onValueChange = { message = it })
        Spacer(modifier = Modifier.padding(10.dp))

        Button(
            enabled = (name.isNotBlank() && email.isNotBlank() && phone.isNotBlank()),
            onClick = {
                val inquiryId = databaseRef.push().key // Generate a unique Inquiry ID
                val inquiryData = mapOf(
                    "name" to name,
                    "email" to email,
                    "phone" to phone,
                    "message" to message
                )

                if (inquiryId != null) {
                    val sanitizedEmail = email.replace(".", "_")
                    databaseRef.child(sanitizedEmail).child(inquiryId).setValue(inquiryData)
                        .addOnCompleteListener { task ->
                            if (task.isSuccessful) {
                                Toast.makeText(
                                    context,
                                    "Inquiry Sent Successfully!",
                                    Toast.LENGTH_LONG
                                ).show()

                                name = ""
                                phone = ""
                                email = ""
                                message = ""
                            }
                            else {
                                Toast.makeText(
                                    context,
                                    "Failed to send inquiry: ${task.exception?.message}",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                 }
            }) {
            Text("Send Inquiry")
        }
        Spacer(modifier = Modifier.padding(10.dp))
    }



}