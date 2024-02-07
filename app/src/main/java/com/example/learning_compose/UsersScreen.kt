package com.example.learning_compose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.SimpleDateFormat
import java.util.Date

class UsersScreen : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val users = remember {
                mutableStateListOf<User>().apply {
                    this.addAll(Database.getAllUsers())
                }
            }
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = if (users.isEmpty()) Arrangement.Center else Arrangement.spacedBy(
                    10.dp
                ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                if (users.isEmpty())
                    item {
                        Text("No users yet.")
                    }
                else
                    itemsIndexed(users) { index, user ->
                        Card {
                            Row(
                                modifier = Modifier
                                    .padding(16.dp)
                                    .fillMaxWidth(),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                Column {
                                    Text(user.name)
                                    Text(user.profession)
                                }
                                Text(
                                    SimpleDateFormat.getDateInstance()
                                        .format(Date(user.dob))
                                        .toString()
                                )
                                IconButton(onClick = {
                                    Database.removeUser(index)
                                    users.removeAt(index)
                                }) {
                                    Icon(Icons.Filled.Delete, contentDescription = "Favorite")
                                }
                            }
                        }
                    }
            }
        }
    }
}


