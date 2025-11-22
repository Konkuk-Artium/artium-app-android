package com.konkuk.artium.data.repositoryImpl

import com.konkuk.artium.data.api.ArchiveService
import com.konkuk.artium.data.dto.request.ArchiveRequestDto
import com.konkuk.artium.data.dto.response.ArchiveDetailResponseDto
import com.konkuk.artium.data.dto.response.ArchiveResponseDto
import com.konkuk.artium.data.dto.response.toUiModel
import com.konkuk.artium.data.repository.ArchiveRepository
import javax.inject.Inject

class ArchiveRepositoryImpl @Inject constructor(
    private val service: ArchiveService
) : ArchiveRepository {

    /**
     * ▣ 전체 리스트 조회
     * 서버에서 주는 data.list -> ArchiveItemDto 리스트
     * 각 아이템을 간단한 UI용 ArchiveResponseDto로 변환
     */
    override suspend fun getArchiveList(): List<ArchiveResponseDto> {
        val response = service.getArchiveList()

        // ① list 꺼내기
        val dtoList = response.data?.list ?: emptyList()

        // ② 리스트 화면용 DTO로 변환
        return dtoList.map { it.toUiModel() }
    }

    /**
     * ▣ 상세 조회
     * 상세는 ArchiveDetailResponseDto 그대로 반환!
     * (이미지 리스트, 내용, 날짜, rating 다 들어있음)
     */
    override suspend fun getArchiveDetail(id: Int): ArchiveDetailResponseDto {
        val response = service.getArchiveDetail(id)
        return response.data ?: throw Exception("상세 데이터 없음")
    }

    /**
     * ▣ 글 작성
     * 작성 성공 후 서버는 상세 정보를 돌려줌 → ArchiveDetailResponseDto
     */
    override suspend fun postArchive(body: ArchiveRequestDto): ArchiveDetailResponseDto {
        val response = service.postArchive(body)
        return response.data ?: throw Exception("저장 성공했으나 데이터 없음")
    }
}
