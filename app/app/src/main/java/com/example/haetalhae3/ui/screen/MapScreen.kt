package com.example.haetalhae3.ui.screen

import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.google.maps.android.compose.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.CameraPosition
import com.example.haetalhae3.viewmodel.temple.TempleViewModel

import android.util.Log
import androidx.compose.runtime.*

@Composable
fun MapScreen(
    modifier: Modifier = Modifier,
    viewModel: TempleViewModel,
    onMarkerClick: (String) -> Unit
) {
    val templeList by viewModel.temples.collectAsState()

    LaunchedEffect(templeList) {
        Log.d("MapScreen", "받은 사찰 수: ${templeList.size}")
        templeList.forEach { temple ->
            Log.d("MapScreen", "사찰명: ${temple.name}, 위치: ${temple.latitude}, ${temple.longitude}")
        }
    }

    // 서울 기준 카메라 시작
    val seoul = LatLng(37.5665, 126.9780)
    val cameraPositionState = rememberCameraPositionState {
        position = CameraPosition.fromLatLngZoom(seoul, 7f)
    }

    GoogleMap(
        modifier = modifier,
        cameraPositionState = cameraPositionState
    ) {
        templeList.forEach { temple ->
            if (temple.latitude != null && temple.longitude != null) {

                Log.d("MapScreen", "마커 찍기: ${temple.name} 위치(${temple.latitude}, ${temple.longitude})")

                Marker(
                    state = MarkerState(position = LatLng(temple.latitude, temple.longitude)),
                    title = temple.name,
                    onClick = {
                        onMarkerClick(temple.name)
                        true
                    }
                )
            }
        }
    }
}
