package com.roomeasy.capstoneproject.repository.chat

import com.roomeasy.capstoneproject.domain.chat.Reminder
import org.springframework.data.jpa.repository.JpaRepository

interface ReminderRepository : JpaRepository<Reminder, Long> {
    fun findByChatRoomId(chatRoomId: Long): Reminder
}