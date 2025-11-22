package com.konkuk.artium.data.dto.response

import com.google.gson.annotations.SerializedName

data class BaseResponse<T>(
    @SerializedName("success") val success: Boolean,
    @SerializedName("error") val error: String?,
    @SerializedName("message") val message: String?,
    @SerializedName("data") val data: T?
)