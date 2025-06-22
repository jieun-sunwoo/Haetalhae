package com.example.haetalhae3.repository

import com.example.haetalhae3.data.model.CommunityPost
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.tasks.await

class CommunityRepository {

    private val db = FirebaseFirestore.getInstance()
    private val postsCollection = db.collection("communityPosts")

    // 게시글 전체 가져오기
    suspend fun getAllPosts(): List<CommunityPost> {
        return try {
            val snapshot = postsCollection.get().await()
            snapshot.documents.mapNotNull { it.toObject(CommunityPost::class.java)?.copy(id = it.id) }
        } catch (e: Exception) {
            emptyList()
        }
    }

    // 새 글 추가하기
    suspend fun addPost(post: CommunityPost) {
        try {
            postsCollection.add(post).await()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}
