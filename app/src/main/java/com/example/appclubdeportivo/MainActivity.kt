package com.example.appclubdeportivo

import android.os.Bundle
import android.util.Log

import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme

import androidx.navigation.compose.rememberNavController
import com.example.appclubdeportivo.data.AppDatabase
import com.example.appclubdeportivo.data.initialInserts
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

import kotlinx.coroutines.CoroutineScope


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val db = AppDatabase.getDatabase(this)

        CoroutineScope(Dispatchers.IO).launch {
            if (!AppDatabase.databaseExists(this@MainActivity)) {
                initialInserts(db)
            }
        }


        setContent {
            val navController = rememberNavController()
            MaterialTheme {
                NavHandler(navController = navController, db)
            }
        }

    }

}