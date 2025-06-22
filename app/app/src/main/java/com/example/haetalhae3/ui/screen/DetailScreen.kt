package com.example.haetalhae3.ui.screen

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter


@Composable
fun DetailScreen(navController: NavController, templeName: String) {
    val context = LocalContext.current


    // 이 예제에서는 모든 내용을 스크롤 가능하도록 설정
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Text(
            text = "사찰명: $templeName",
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold
        )

        Spacer(modifier = Modifier.height(12.dp))

        // (1) 주소 - 클릭 시 복사
        val address = "서울특별시 종로구 우정국로 55 (예시 주소)"
        Text(
            text = "📍 $address",
            fontSize = 16.sp,
            modifier = Modifier.clickable {
                val clipboard = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText("주소", address)
                clipboard.setPrimaryClip(clip)
                Toast.makeText(context, "주소가 복사되었습니다", Toast.LENGTH_SHORT).show()
            }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // (2) 대표 사진
        Image(
            painter = rememberAsyncImagePainter("https://search.pstatic.net/common/?src=http%3A%2F%2Fblogfiles.naver.net%2FMjAyNDEyMDFfNDYg%2FMDAxNzMzMDA0ODQwMDIx.L9XWU3YP02iZMQnguq9vP9PYM-3TZQYb568VY7B42lcg.NWMc-0uIscFrNZ3fkeiIrdLlT4MenJ7LdDWtpzc1v7Ig.JPEG%2F900%25A3%25DF20241130%25A3%25DF151740.jpg&type=sc960_832"),
            contentDescription = "사찰 대표 사진",
            modifier = Modifier
                .fillMaxWidth()
                .height(200.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        // (3) 역사 설명
        Text(
            text = "사찰 역사\n이 사찰은 고려시대에 창건되었으며...",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        // (4) 공지사항
        Text(
            text = "📢 공지사항",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            text = "- 행사 안내: 6월 10일 연등 행사\n- 개방 시간: 오전 9시 ~ 오후 6시\n- 무료 식당 운영: 월~금 12:00~13:00\n- 템플스테이: 매주 토~일 신청 가능",
            fontSize = 16.sp
        )

        Spacer(modifier = Modifier.height(24.dp))

        // (5) 커뮤니티 안내 (향후 구현)
        Text(
            text = "🗣️ 사찰 커뮤니티",
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp
        )
        Text(
            text = "이곳은 신도들과 자유롭게 소통하는 공간입니다. (추후 게시판, 댓글 등 추가 예정)",
            fontSize = 16.sp
        )
    }

    Spacer(modifier = Modifier.height(16.dp))

    Button(
        onClick = {
            // 네비게이션 이동: templeName을 함께 전달
            // 이 부분은 NavController가 필요하므로 NavController를 파라미터로 받게 수정해야 함
            navController.navigate("community_list/$templeName")
        },
        modifier = Modifier.fillMaxWidth()
    ) {
        Text("사찰 커뮤니티로 이동")
    }

// 커뮤니티로 이동 버튼


}
