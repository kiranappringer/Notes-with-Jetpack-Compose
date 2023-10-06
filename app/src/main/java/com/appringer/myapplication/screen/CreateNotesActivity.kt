@file:OptIn(ExperimentalMaterial3Api::class)

package com.appringer.myapplication.screen

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.appringer.myapplication.R
import com.appringer.myapplication.utils.SharePref
import com.appringer.myapplication.model.NoteDO

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun CreateNotesActivity(context: Context,navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Create New Notes",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                }
            )
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Text(text = "Title")
                Spacer(modifier = Modifier.height(8.dp))
                MyOutlinedTextField() {title,des->
                    if (title.isNotBlank() && des.isNotBlank()) {
                        val newNote = NoteDO(title, des,System.currentTimeMillis())
                        val list = SharePref.loadNotesFromSharedPreferences(context = context)
                        list.add(newNote)
                        SharePref.saveNotesToSharedPreferences(context,list)
                        navController.popBackStack()
                    }

                }


            }
        }

    )

}



@Composable
fun MyOutlinedTextField(onButtonClick: (String,String) -> Unit) {
    var title by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }

    OutlinedTextField(
        value = title,
        onValueChange = {
            title = it
        },
        label = { Text(text = "Title") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )

    OutlinedTextField(
        value = description,
        onValueChange = {
            description = it
        },
        label = { Text(text = "Description") },
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )

    Button(
        onClick = {
            // Pass the entered text to the click handler
            onButtonClick(title,description)
        },
        modifier = Modifier
            .padding(top = 16.dp)
            .fillMaxWidth(),

        colors = ButtonDefaults.buttonColors(colorResource(id = R.color.purple_500))


    ) {
        Text(text = "Submit")
    }
}


