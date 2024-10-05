package com.example.scaffoldapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.scaffoldapp.ui.theme.ScaffoldAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ScaffoldAppTheme {
                ScaffoldApp()
            }
        }
    }
}

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun ScaffoldApp() {
 val navController = rememberNavController()
    NavHost(
        navController=navController,
        startDestination = "home"
    ) {
        composable(route= "home") { MainScreen(navController) }
        composable(route= "info") { InfoScreen(navController) }
        composable(route= "settings") { SettingsScreen(navController) }
    }
}

@OptIn(androidx.compose.material3.ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(title: String, navController: NavController) {
    var expanded by remember { mutableStateOf(false) }
    TopAppBar(
        title = { Text(title) },

        actions = {
            IconButton(
                onClick = { expanded = !expanded }
            ) {
                Icon(Icons.Filled.MoreVert, contentDescription = null)
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                DropdownMenuItem(

                    onClick = {navController.navigate("info") },
                    text = { Text("Info") }
                )
                DropdownMenuItem(

                    onClick = {
                navController.navigate("settings")
                    },
                    text = { Text("Settings") }
                )
            }
        }
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ScreenTopBar(title: String,navController: NavController) {
    TopAppBar(
        title= { Text(title) },
        navigationIcon = {
            IconButton(onClick =  { navController.navigateUp()}){
                Icon(Icons.Filled.ArrowBack, contentDescription = null)
            }
        }
    )
}
@Composable
fun MainScreen (navController: NavController) {
    Scaffold(
        topBar = { MainTopBar("my app", navController) },
        content= { paddingValues -> // Use paddingValues to prevent content overlap
            Text(
                text = "Content goes here",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Apply padding to the content
            )
        },
    )
}

@Composable
fun InfoScreen (navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("my InfoScreen", navController) },
        content= { paddingValues -> // Use paddingValues to prevent content overlap
            Text(
                text = "info goes here",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Apply padding to the content
            )
        },
    )
}
@Composable
fun SettingsScreen (navController: NavController) {
    Scaffold(
        topBar = { ScreenTopBar("my SettingsScreen", navController) },
        content= { paddingValues -> // Use paddingValues to prevent content overlap
            Text(
                text = "SettingsScreen goes here",
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues) // Apply padding to the content
            )
        },
    )
}