package com.example.kotlin_compose_dev

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen()
        }
    }
}

@Composable
fun MainScreen(viewModel: MainViewModel = MainViewModel()) {
// 매개변수에 기본값을 지정. MainScreen()을 호출할 때 기본값으로 MainViewModel()이 사용됨
    //*****현재 textField가 변하지 않는 이유는 MainScreen이 Recompose되고 그에 따라 MainViewModel이 다시 생성되기 때문이다*****//

    val newNameStateContent = viewModel.textFieldState.observeAsState("")
    // observeAsState : MutableLiveData<String> 타입의 State -> State<String> 타입의 State
    // -> value를 사용하면 LiveData의 현재 값(String)에 액세스 가능

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingMessage(newNameStateContent.value) { newName -> // 람다식을 전달
            viewModel.onTextChanged(newName)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingMessage(
                 textFieldValue: String,
                 textFieldUpdate: (newName: String) -> Unit
){

    TextField(value = textFieldValue, onValueChange = textFieldUpdate)

    Button(onClick = {}) {
        Text(textFieldValue)
    }
}

@Composable
fun Greeting(name: String) {
    Text(
        text = "Hello $name!",
        style = MaterialTheme.typography.bodyMedium
    )
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    MainScreen()
}
