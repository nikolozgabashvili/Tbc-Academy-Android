package com.example.tbcacademyhomework.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

typealias Response = List<List<ResponseDataDto>>


@Serializable
data class ResponseDataDto(
    @SerialName("field_id")
    val fieldId: Int?,
    val hint: String?,
    @SerialName("field_type")
    val fieldType: String?,
    val keyboard: String?,
    val required: Boolean?,
    @SerialName("is_active")
    val isActive: Boolean?,
    val icon: String?
)