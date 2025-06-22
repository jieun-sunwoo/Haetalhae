package com.example.haetalhae3.ui.screen.temple

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.haetalhae3.data.api.TempleApi
import com.example.haetalhae3.data.api.RetrofitClient
import com.example.haetalhae3.repository.TempleRepository
import com.example.haetalhae3.viewmodel.temple.TempleViewModel
import com.example.haetalhae3.viewmodel.temple.TempleViewModelFactory
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun TempleListScreen() {
    val api = RetrofitClient.retrofit.create(TempleApi::class.java)
    val repository = remember { TempleRepository(api) }
    val viewModel: TempleViewModel = viewModel(
        factory = TempleViewModelFactory(repository)
    )

    val temples by viewModel.temples.collectAsStateWithLifecycle()
    val error by viewModel.error.collectAsStateWithLifecycle()

    LaunchedEffect(Unit) {
        viewModel.fetchTemples()
    }

    LazyColumn(modifier = Modifier.padding(16.dp)) {
        item {
            Text("사찰 목록", style = MaterialTheme.typography.titleLarge)
            Spacer(modifier = Modifier.height(8.dp))
        }
        if (error.isNotEmpty()) {
            item {
                Text("에러 발생: $error", color = MaterialTheme.colorScheme.error)
            }
        }
        items(temples) { temple ->
            Card(modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 4.dp)) {
                Column(modifier = Modifier.padding(8.dp)) {
                    Text(text = temple.name, style = MaterialTheme.typography.titleMedium)
                    Text(text = temple.address, style = MaterialTheme.typography.bodyMedium)
                }
            }
        }
    }
}
