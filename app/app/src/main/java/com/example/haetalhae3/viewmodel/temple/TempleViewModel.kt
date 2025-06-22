package com.example.haetalhae3.viewmodel.temple

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haetalhae3.data.model.Temple
import com.example.haetalhae3.repository.TempleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.util.Log


class TempleViewModel(private val repository: TempleRepository) : ViewModel() {

    private val _temples = MutableStateFlow<List<Temple>>(emptyList())
    val temples: StateFlow<List<Temple>> = _temples.asStateFlow()

    private val _error = MutableStateFlow("")
    val error: StateFlow<String> = _error.asStateFlow()

    init {
        fetchTemples()
    }

    fun fetchTemples() {
        viewModelScope.launch {
            repository.getTemples().enqueue(object : Callback<List<Temple>> {
                override fun onResponse(
                    call: Call<List<Temple>>,
                    response: Response<List<Temple>>
                ) {
                    if (response.isSuccessful) {
                        Log.d("TempleViewModel", "서버 응답 성공, 받은 사찰 수: ${response.body()?.size ?: 0}")
                        _temples.value = response.body() ?: emptyList()
                    } else {
                        Log.e("TempleViewModel", "서버 오류 코드: ${response.code()}")
                        _error.value = "서버 응답 오류: ${response.code()}"
                    }
                }

                override fun onFailure(call: Call<List<Temple>>, t: Throwable) {
                    Log.e("TempleViewModel", "네트워크 실패: ${t.message}")
                    _error.value = "네트워크 실패: ${t.message}"
                }
            })
        }
    }
}