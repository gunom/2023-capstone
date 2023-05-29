package com.roomeasy.capstoneproject.controller.dto

data class ReminderRequestDto(
    val chatRoomId: Long,
    val date: String,
    val time: String,
    val place: String,
    val longitude: String,
    val latitude: String
)
