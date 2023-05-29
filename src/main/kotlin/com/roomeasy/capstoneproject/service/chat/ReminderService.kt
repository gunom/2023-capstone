package com.roomeasy.capstoneproject.service.chat

import com.roomeasy.capstoneproject.domain.chat.Reminder

interface ReminderService {
    fun addReminder(
        userId: Long,
        chatRoomId: Long,
        date: String,
        time: String,
        place: String,
        longitude: String,
        latitude: String
    )

    fun getReminderByChatRoomId(chatRoomId: Long): Reminder
}