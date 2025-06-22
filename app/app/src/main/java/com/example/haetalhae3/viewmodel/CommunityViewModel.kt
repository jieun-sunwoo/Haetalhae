package com.example.haetalhae3.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.haetalhae3.data.model.CommunityPost
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import com.google.firebase.Timestamp

class CommunityViewModel : ViewModel() {

    private val _posts = MutableStateFlow<List<CommunityPost>>(emptyList())
    val posts: StateFlow<List<CommunityPost>> = _posts

    private val db = FirebaseFirestore.getInstance()

    init {
        fetchPosts()
    }

    private fun fetchPosts() {
        viewModelScope.launch {
            try {
                val snapshot = db.collection("community_posts")
                    .orderBy("timestamp")
                    .get()
                    .await()

                val postList = snapshot.documents.map { doc ->
                    CommunityPost(
                        id = doc.id,
                        title = doc.getString("title") ?: "",
                        content = doc.getString("content") ?: "",
                        author = doc.getString("author") ?: "",
                        timestamp = doc.getTimestamp("timestamp") ?: Timestamp.now()
                    )
                }

                _posts.value = postList.reversed() // 최신순 정렬
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}
