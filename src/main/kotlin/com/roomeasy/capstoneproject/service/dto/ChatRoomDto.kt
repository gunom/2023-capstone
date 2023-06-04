package com.roomeasy.capstoneproject.service.dto

import java.time.LocalDateTime

data class ChatRoomDto(
    val id: Long,
    val opponent: String,
    val lastMessage: String,
    val lastMessageTimestamp: LocalDateTime,
)
