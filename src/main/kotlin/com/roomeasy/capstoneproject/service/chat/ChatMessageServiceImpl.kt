package com.roomeasy.capstoneproject.service.chat

import com.roomeasy.capstoneproject.domain.chat.ChatMessage
import com.roomeasy.capstoneproject.repository.chat.ChatMessageRepository
import org.springframework.stereotype.Service

@Service
class ChatMessageServiceImpl(
    private val chatMessageRepository: ChatMessageRepository,
) : ChatMessageService {
    override fun saveChatMessage(chatMessage: ChatMessage): ChatMessage {
        return chatMessageRepository.save(chatMessage)
    }

    override fun getChatMessagesByChatRoomId(chatRoomId: Long): List<ChatMessage> {
        return chatMessageRepository.findAllByChatRoomId(chatRoomId)
    }

    override fun getLastChatMessageByChatRoomIds(chatRoomIds: List<Long>): List<ChatMessage> {
        return chatMessageRepository.findFirstByChatRoomIdInOrderByTimestamp(chatRoomIds)
    }
}