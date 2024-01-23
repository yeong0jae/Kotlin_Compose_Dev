package com.example.kotlin_compose_dev

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    val textFieldState = MutableLiveData("") // 현재 화면에 표시되어야 하는 값

    fun onTextChanged(newText: String) { // 값 변경을 요청하는 이벤트 함수
        textFieldState.value = newText
    }
}
