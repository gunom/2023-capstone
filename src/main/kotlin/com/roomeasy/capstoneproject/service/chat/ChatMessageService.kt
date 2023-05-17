package com.roomeasy.capstoneproject.service.chat

import com.roomeasy.capstoneproject.domain.chat.ChatMessage

interface ChatMessageService {
    fun saveChatMessage(chatMessage: ChatMessage): ChatMessage
    fun getChatMessagesByChatRoomId(chatRoomId: Long): List<ChatMessage>
    fun getLastChatMessageByChatRoomIds(chatRoomIds: List<Long>): List<ChatMessage>
}