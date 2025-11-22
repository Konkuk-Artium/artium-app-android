package com.konkuk.artium.ui.feature.archive.viewmodel

import com.konkuk.artium.ui.feature.archive.component.RecentWorkItem

/**
 * Archive 화면 전체 상태를 표현하는 데이터 클래스
 */
data class ArchiveUiState(

    // 최근에 본 작품 리스트
    val recentWorks: List<RecentWorkItem> = emptyList(),

    // 내가 저장한 작품(아카이브) 리스트
    val myWorks: List<MyWorkItem> = emptyList(),

    // 지금 서버에서 데이터를 불러오는 중인지?
    val isLoading: Boolean = false,

    // API 실패 시 보여줄 에러 메시지
    val errorMessage: String? = null,
)
/**
 * UI에서 '최근 본 작품'을 표시하기 위한 전용 데이터 모델
 */
data class RecentWorkItem(
    val id: Long = 0L,       // 작품 ID
    val title: String = "",  // 제목
    val imageUrl: String = "",
    val rating: Float = 0f,  // 별점
)

/**
 * UI에서 '내가 저장한 작품(아카이브)'을 표시하기 위한 모델
 * MyArtworkList, MyArtworkCard 등에서 사용됨
 */
data class MyWorkItem(
    val id: Long = 0L,
    val title: String = "",
    val thumbnailUrl: String = "",
    val date: String = "",     // 관람 날짜
    val rating: Float = 0f,
)

data class MyArtworkDetailUiState(
    val id: Long = 0L,
    val title: String = "",
    val content: String = "",
    val viewDate: String = "",
    val rating: Float = 0f,
    val images: List<String> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessage: String? = null
)


