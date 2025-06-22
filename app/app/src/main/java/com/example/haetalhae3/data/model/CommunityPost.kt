package com.example.haetalhae3.data.model
import com.google.firebase.Timestamp

data class CommunityPost(
    val id: String = "",              // Firestore 문서 ID (옵션)
    val title: String = "",           // 글 제목
    val content: String = "",         // 글 내용
    val author: String = "",          // 작성자
    val timestamp: Timestamp = Timestamp.now()          // 작성 시각 (밀리초)
)
