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
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
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
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        GreetingList()
    }
}

@Composable
fun GreetingList() {
    val greetingListState = remember { mutableStateListOf<String>("John", "Amanda") }
    // snapshotStateList를 반환 -> 변수를 state로 선언하면 값이 변경될 때마다 Composable이 다시 호출된다
    // remember는 주로 화면이 다시 그려질 때 상태를 유지할 때 사용되며,
    // mutableStateListOf는 그 상태를 변경하거나 감시하는 데 사용됨
    for(name in greetingListState) {
        Greeting(name = name)
    }

    Button(onClick = { greetingListState.add("Michael") }) {
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
