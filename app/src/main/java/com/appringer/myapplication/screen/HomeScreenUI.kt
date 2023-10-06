package com.appringer.myapplication.screen

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.appringer.myapplication.utils.NavigationScreen
import com.appringer.myapplication.R
import com.appringer.myapplication.utils.SharePref
import com.appringer.myapplication.model.NoteDO
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale


@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreenUI(context: Context, navController: NavHostController) {
    val list = SharePref.loadNotesFromSharedPreferences(context = context)
    list.reverse()
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Notes",
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center
                    )
                })
        },
        content = {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(it)
            ) {
                LazyColumn(
                    modifier = Modifier
                        .weight(1f)
                        .padding(8.dp)
                        .fillMaxWidth(),
                ) {
                    itemsIndexed(list) { index, item ->
                        val itemColor = getColorForPosition(index)
                        ItemRow(item = item, itemColor){
                            val intent = Intent(context,ShowNotesScreen::class.java)
                            intent.putExtra("notes", item)
                            context.startActivity(intent)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(8.dp))

            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = {
                navController.navigate(NavigationScreen.CreateNotesScreen.route)
            }, containerColor = colorResource(R.color.purple_500)) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White
                )
            }

        }

    )
}

@Composable
fun ItemRow(item: NoteDO, itemColor: Int, onItemClick: (NoteDO) -> Unit) {
    // Define the layout for each item in the list
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
            .padding(8.dp)
            .clickable {
                onItemClick(item)
            }
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                ,
            shape = RoundedCornerShape(8.dp),

            ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(colorResource(id = itemColor)) // Set the background color here
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp),
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        text = item.title,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.Black, // Set the text color
                        modifier = Modifier.padding(bottom = 4.dp)
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Text(
                            text = item.description,
                            fontSize = 16.sp,
                            color = Color.Black.copy(alpha = 0.5f),
                            modifier = Modifier.weight(1f)
                        )
                        Text(
                            text = timestampToDateTime(item.createdON),
                            fontSize = 12.sp,
                            color = Color.Black,
                            fontWeight = FontWeight.Bold,
                        )
                    }

                }
            }

        }

    }
}

fun timestampToDateTime(timestamp: Long): String {
    // Create a SimpleDateFormat with the desired date format
    val sdf = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())

    // Convert the timestamp to a Date object
    val date = Date(timestamp)

    // Format the Date object as a string
    return sdf.format(date)
}

fun getColorForPosition(position: Int): Int {
    return if (position % 2 == 0) {
        R.color.card_1
    } else {
        R.color.card_2
    }
}

