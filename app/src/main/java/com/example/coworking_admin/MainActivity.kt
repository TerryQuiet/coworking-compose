package com.example.coworking_admin

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.coworking_admin.ui.CoworkingNavHost
import com.example.coworking_admin.ui.theme.CoworkingadminTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val mainViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoworkingMain(mainViewModel)
        }
    }
}

@Composable
fun CoworkingMain(
    viewModel: MainViewModel
) {
    CoworkingadminTheme {
        // A surface container using the 'background' color from the theme
        val navController = rememberNavController()
        CoworkingNavHost(
            navController = navController, mainViewModel = viewModel)
    }
}


