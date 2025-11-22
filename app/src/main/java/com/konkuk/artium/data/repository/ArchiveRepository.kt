package com.konkuk.artium.data.repository

import com.konkuk.artium.data.dto.request.ArchiveRequestDto
import com.konkuk.artium.data.dto.response.ArchiveDetailResponseDto
import com.konkuk.artium.data.dto.response.ArchiveResponseDto

interface ArchiveRepository {

    suspend fun getArchiveList(): List<ArchiveResponseDto>

    suspend fun getArchiveDetail(id: Int): ArchiveDetailResponseDto

    suspend fun postArchive(body: ArchiveRequestDto): ArchiveDetailResponseDto
}
