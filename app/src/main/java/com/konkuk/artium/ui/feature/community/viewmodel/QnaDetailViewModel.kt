package com.konkuk.artium.ui.feature.community.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.konkuk.artium.data.repository.CommunityRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class QnaDetailViewModel @Inject constructor(
    private val repo: CommunityRepository
) : ViewModel() {

    private val _detail = MutableStateFlow(PostDetailUiState(isLoading = true))
    val detail = _detail.asStateFlow()

    /** 상세 조회 */
    fun load(postId: Int) {
        viewModelScope.launch {
            runCatching { repo.getDetail(postId) }
                .onSuccess { dto ->
                    _detail.value = _detail.value.copy(
                        isLoading = false,
                        id = dto.id,
                        title = dto.title,
                        content = dto.content,
                        category = dto.category,
                        author = dto.user_id,
                        createdAt = dto.created_at,
                        likeCount = dto.view_count,
                        commentCount = dto.comments.size,
                        comments = dto.comments.map {
                            CommentUiModel(
                                commentId = it.commentId,
                                writer = it.writer ?: it.user_id.orEmpty(),
                                content = it.content,
                                createdAt = it.created_at
                            )
                        }
                    )
                }
                .onFailure {
                    _detail.value = _detail.value.copy(
                        isLoading = false,
                        error = it.message ?: "QnA 상세 조회 실패"
                    )
                }
        }
    }

    /** 댓글 작성 */
    fun writeComment(postId: Int, content: String) {
        viewModelScope.launch {
            runCatching { repo.writeComment(postId, content) }
                .onSuccess {
                    load(postId) // 새로고침
                }
        }
    }
}