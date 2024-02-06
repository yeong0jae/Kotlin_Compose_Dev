package com.example.kotlin_compose_dev

import android.annotation.SuppressLint
import android.graphics.drawable.Icon
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.kotlin_compose_dev.ui.theme.MyTheme
import com.example.kotlin_compose_dev.ui.theme.lightGreen
import com.example.kotlin_compose_dev.ui.theme.teal


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme { // 기본 테마. 원하는 대로 커스텀 가능
                MainScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(userProfiles: List<UserProfile> = userProfileList) {
    Scaffold(topBar = { AppBar() }) { // 상단바 추가 -> Scaffold의 topBar를 정의
        Surface( // Surface의 color 기본값은 color: Color = MaterialTheme.colorScheme.surface로 들어감
            modifier = Modifier
                .fillMaxSize()
                .padding(it), // it은 Scaffold로부터 전달된 paddingValues(scaffold의 크기)
        ) {
            Column(
            ){
                for (userProfile in userProfiles) {
                    ProfileCard(userProfile)
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AppBar() {
    TopAppBar(
        navigationIcon = {
            Icon(
                imageVector = Icons.Default.Home,
                contentDescription = "Home",
                Modifier.padding(horizontal = 12.dp)
            )
        },
        title = {
            Text(
                text = "Messaging Application users",
                style = MaterialTheme.typography.titleMedium
                // TopAppBar의 세부 구현 사항인 SingleRowTopAppBar에서 style의 기본값이 titleLarge로 들어감
                // 그래서 titlelarge를 바꾸면 topappbar의 스타일도 바뀐것
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.teal,
        )
    )
}

@Composable
fun ProfileCard(userProfile: UserProfile) {
    Card( // Card의 color 기본값은 CardDefaults.cardColors()로 들어감
        modifier = Modifier // Card의 modifier는 Surface를 기준으로 조정되는 값들
            .padding(top = 8.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top),
        elevation = CardDefaults.cardElevation(defaultElevation = 5.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        shape = MaterialTheme.shapes.medium // 재정의
    ) {
        Row (
            modifier = Modifier.fillMaxWidth(),
            // -> Row는 Card와 크기가 같게 설정됨
            verticalAlignment = Alignment.CenterVertically,
        ) {
            ProfilePicture(userProfile.drawableId, userProfile.status)
            ProfileContent(userProfile.name, userProfile.status)
        }
    }
}

@Composable
fun ProfilePicture(drawableId: Int, onlineStatus: Boolean) {
    Card( // Card는 다양한 shape 지원 -> Image를 감쌈
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = if(onlineStatus) MaterialTheme.colorScheme.lightGreen
                    else Color.Red // 람다식으로 색상을 지정
        ),
        modifier = Modifier.padding(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Image( // Image의 필수 요소는 painter와 contentDescription
            painter = painterResource(id = drawableId),
            contentDescription = "Profile Picture",
            modifier = Modifier.size(72.dp),
            contentScale = ContentScale.Crop // 이미지가 너무 크면 잘라냄
        )
    }
}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth() // width를 Row(부모)의 크기와 맞춤
    ){
        Text(
            modifier = Modifier.alpha(if(onlineStatus) 1f else 0.5f), // 람다식으로 alpha값 지정
            text = userName,
            style = MaterialTheme.typography.titleLarge // 재정의
        )
        Text(
            modifier = Modifier.alpha(0.25f),
            text = if(onlineStatus) "Active now"
            else "Offline",
            style = MaterialTheme.typography.bodySmall
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ContentPreview() {
    MyTheme {
        MainScreen()
    }
}
