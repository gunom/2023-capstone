package com.roomeasy.capstoneproject.service.chat

import com.roomeasy.capstoneproject.domain.chat.ChatRoom
import com.roomeasy.capstoneproject.repository.chat.ChatRoomRepository
import org.springframework.stereotype.Service

@Service
class ChatRoomServiceImpl(
    private val chatRoomRepository: ChatRoomRepository,
) : ChatRoomService {
    override fun getChatRoomsByIds(ids: List<Long>): MutableList<ChatRoom> {
        return chatRoomRepository.findAllById(ids)
    }

    override fun createChatRoom(): ChatRoom {
        return chatRoomRepository.save(ChatRoom())
    }
}