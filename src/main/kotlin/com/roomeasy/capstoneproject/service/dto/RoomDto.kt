package com.roomeasy.capstoneproject.service.dto

data class RoomDto(
    val id: Long,
    val deposit: Int? = null,
    val imagesThumbnail: String? = null,
    val rent: Double? = null,
    val sizeM2: Double? = null
)
