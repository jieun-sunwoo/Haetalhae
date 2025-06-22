package com.example.haetalhae3

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.haetalhae3.ui.theme.Haetalhae3Theme
import com.example.haetalhae3.ui.screen.MapScreen
import com.example.haetalhae3.ui.screen.DetailScreen
import com.example.haetalhae3.ui.screen.CommunityListScreen
import com.example.haetalhae3.ui.screen.temple.TempleListScreen


import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.haetalhae3.data.api.RetrofitClient
import com.example.haetalhae3.data.api.TempleApi
import com.example.haetalhae3.repository.TempleRepository
import com.example.haetalhae3.viewmodel.temple.TempleViewModel
import com.example.haetalhae3.viewmodel.temple.TempleViewModelFactory
import androidx.lifecycle.ViewModelProvider




class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val repository = TempleRepository(RetrofitClient.retrofit.create(TempleApi::class.java))

        val viewModel = ViewModelProvider(this, TempleViewModelFactory(repository))
            .get(TempleViewModel::class.java)



        setContent {
            Haetalhae3Theme {
                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "map_screen",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("map_screen") {
                            MapScreen(
                                modifier = Modifier.fillMaxSize(),
                                viewModel = viewModel,
                                onMarkerClick = { templeName ->
                                    // 마커 클릭 시 상세 화면으로 이동
                                    navController.navigate("detail_screen/$templeName")
                                }
                            )
                        }
                        composable("detail_screen/{templeName}") { backStackEntry ->
                            val templeName = backStackEntry.arguments?.getString("templeName") ?: "알 수 없는 사찰"
                            DetailScreen(navController = navController, templeName = templeName)

                        }
                        composable("community_list/{templeName}") { backStackEntry ->
                            val templeName = backStackEntry.arguments?.getString("templeName") ?: "알 수 없는 사찰"
                            CommunityListScreen(templeName = templeName)
                        }
                        composable("temple_list_screen") {
                            TempleListScreen()
                        }

                    }
                }
            }
        }

    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Haetalhae3Theme {
        Greeting("Android")
    }
}