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
fun MainScreen() {
    // state를 상위 컴포저블에서 관리
    val greetingListState = remember {
        mutableStateListOf<String>("John", "Amanda")
    }

    val newNameStateContent = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingList(greetingListState, { // 1. state of Text
            greetingListState.add(newNameStateContent.value) // 2. state update
        }, newNameStateContent.value, { // 3. state of TextField
            newName -> newNameStateContent.value = newName // 4. state update
        })
    }
    // state hosting을 통해 여러 컴포저블에서 상태를 공유할 수 있는 것임
    // Text와 TextField가 같은 상태를 공유하고 있음 -> 그래서 textfield에 입력해서 text를 추가하는 것이 되는 거임
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GreetingList(namesList : List<String>,
                 buttonClick : () -> Unit,
                 textFieldValue: String,
                 textFieldUpdate: (newName: String) -> Unit
){ // 람다식을 매개변수로 받음
    for(name in namesList) {
        Greeting(name = name)
    }

    TextField(value = textFieldValue, onValueChange = textFieldUpdate)  // onValueChange는 textfield의 값이 변경될 때마다 호출
    // List가 아닌 state는 value로 값에 접근해야함. mutableStateList는 List를 상속받기 때문
    // mutableState는 String을 상속받는 등이 없기 때문에 value로 접근해야함

    Button(onClick = buttonClick) {
        Text(text = "Add new name")
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
