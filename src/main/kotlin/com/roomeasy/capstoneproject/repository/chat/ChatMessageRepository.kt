package com.roomeasy.capstoneproject.repository.chat

import com.roomeasy.capstoneproject.domain.chat.ChatMessage
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatMessageRepository: JpaRepository<ChatMessage, Long> {
    fun findAllByChatRoomId(chatRoomId: Long): List<ChatMessage>
    fun findFirstByChatRoomIdInOrderByTimestamp(roomIds: List<Long>): List<ChatMessage>
}