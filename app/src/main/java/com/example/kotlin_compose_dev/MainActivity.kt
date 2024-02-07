package com.example.kotlin_compose_dev

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import coil.transform.CircleCropTransformation
import com.example.kotlin_compose_dev.ui.theme.MyTheme
import com.example.kotlin_compose_dev.ui.theme.lightGreen
import com.example.kotlin_compose_dev.ui.theme.teal

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyTheme {
                UsersApplication()
            }
        }
    }
}

@Composable
// Navigation을 위한 Composable
// UserProfiles를 Hoisting
fun UsersApplication(userProfiles: List<UserProfile> = userProfileList) {
    val navController = rememberNavController() // NavHostController를 생성하고 기억
    NavHost(navController = navController, startDestination = "users_list") {
        composable("users_list") {
            UserListScreen(userProfiles, navController)
            // UsersApplication 호출 시 UserListScreen를 렌더링
        }
        composable(
            route = "user_details/{userId}",
            arguments = listOf(navArgument("userId") {
                type = NavType.IntType
            }) // navigate시 userId를 받아들이는 argument를 추가
        ) {navBackStackEntry -> // NavBackStackEntry는 현재 route의 정보를 가지고 있음
            UserProfileDetailsScreen(navBackStackEntry.arguments!!.getInt("userId"))
            // ProfileCard가 navigate를 통해 이 route의 composable을 렌더링 함
            // 그리고 navigate할 때 userId를 전달
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserListScreen(userProfiles: List<UserProfile>, navController: NavHostController?) {
    Scaffold(topBar = { AppBar() }) { // 상단바 추가 -> Scaffold의 topBar를 정의
        Surface( // Surface의 color 기본값은 color: Color = MaterialTheme.colorScheme.surface로 들어감
            modifier = Modifier
                .fillMaxSize()
                .padding(it), // it은 Scaffold로부터 전달된 paddingValues(scaffold의 크기)
        ) {
            LazyColumn { // LazyColumn은 많은 아이템을 효율적으로 표시하기 위한 컴포저블
                items(count = userProfiles.size) { index -> // lambda에서 각 항목의 인덱스 : it으로 사용해도 됨
                    ProfileCard(userProfile = userProfiles[index]){
                        navController?.navigate("user_details/${userProfiles[index].id}")
                        // ?는 null이 아닐때만 실행
                        // NavController는 Screen Composable 밑에까지 전달할 필요가 없음. 호출할 navigate만 전달
                        // id를 전달하여 UserProfileDetailsScreen으로 이동
                    }
                }
            }
        }
    }
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserProfileDetailsScreen(userId: Int) {
    val userProfile = userProfileList.first { it.id == userId }
    // first는 조건에 맞는 첫번째 요소를 반환
    // ProfileCard에서 navigate할 때 id를 전달받고 이 값을 기반으로 UserProfileDetailsScreen를 렌더링
    Scaffold(topBar = { AppBar() }) { // 상단바 추가 -> Scaffold의 topBar를 정의
        Surface( // Surface의 color 기본값은 color: Color = MaterialTheme.colorScheme.surface로 들어감
            modifier = Modifier
                .fillMaxSize()
                .padding(it), // it은 Scaffold로부터 전달된 paddingValues(scaffold의 크기)
        ) {
            Column (
                modifier = Modifier.fillMaxWidth(),
                // -> Row는 부모와 크기가 같게 설정됨
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                ProfilePicture(userProfile.pictureUrl, userProfile.status, 240.dp)
                ProfileContent(userProfile.name, userProfile.status, Alignment.CenterHorizontally)
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
fun ProfileCard(userProfile: UserProfile, clickAction: () -> Unit) {
    Card( // Card의 color 기본값은 CardDefaults.cardColors()로 들어감
        modifier = Modifier // Card의 modifier는 Surface를 기준으로 조정되는 값들
            .padding(top = 8.dp, bottom = 4.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .wrapContentHeight(align = Alignment.Top)
            .clickable(onClick = { clickAction.invoke() }), // invoke()는 람다식을 호출하는 함수
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
            ProfilePicture(userProfile.pictureUrl, userProfile.status, 72.dp)
            ProfileContent(userProfile.name, userProfile.status, Alignment.Start)
        }
    }
}

@Composable
fun ProfilePicture(pictureUrl: String, onlineStatus: Boolean, imageSize: Dp) {
    Card( // Card는 다양한 shape 지원 -> Image를 감쌈
        shape = CircleShape,
        border = BorderStroke(
            width = 2.dp,
            color = if(onlineStatus) MaterialTheme.colorScheme.lightGreen
                    else Color.Red // 람다식으로 색상을 지정
        ),
        modifier = Modifier.padding(16.dp).size(imageSize),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Image( // Image의 필수 요소는 painter와 contentDescription
            painter = rememberAsyncImagePainter( // coil 라이브러리의 rememberAsyncImagePainter를 사용 -> 비동기 이미지 로딩
                model = ImageRequest.Builder(LocalContext.current)
                    .data(pictureUrl)
                    .transformations(CircleCropTransformation()) // 이미지를 원형으로 잘라냄
                    .build()
            ),
            contentDescription = "Profile Picture",
        )
    }
}

@Composable
fun ProfileContent(userName: String, onlineStatus: Boolean, alignment: Alignment.Horizontal) {
    Column(
        modifier = Modifier
            .padding(8.dp),
        horizontalAlignment = alignment,
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
fun UserProfileDetailsPreview() {
    MyTheme {
        UserProfileDetailsScreen(userId = 0)
    }
}


@Preview(showBackground = true)
@Composable
fun UserListPreview() {
    MyTheme {
        UserListScreen(userProfiles = userProfileList, null)
    }
}
