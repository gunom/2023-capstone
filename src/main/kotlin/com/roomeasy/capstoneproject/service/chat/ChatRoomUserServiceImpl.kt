package com.roomeasy.capstoneproject.service.chat

import com.roomeasy.capstoneproject.domain.chat.ChatRoomUser
import com.roomeasy.capstoneproject.repository.chat.ChatRoomUserRepository
import org.springframework.stereotype.Service

@Service
class ChatRoomUserServiceImpl(
    private val chatRoomUserRepository: ChatRoomUserRepository,
) : ChatRoomUserService {
    override fun getChatRoomByUserId(userId: Long): List<ChatRoomUser> {
        return chatRoomUserRepository.findAllByUserId(userId)
    }

    override fun getChatRoomByRoomIds(roomIds: List<Long>): List<ChatRoomUser> {
        return chatRoomUserRepository.findAllByChatRoomIdIn(roomIds)
    }

    override fun addChatRoomUser(chatRoomId: Long, userId: Long) {
        val chatRoomUser = ChatRoomUser(chatRoomId = chatRoomId, userId = userId)
        chatRoomUserRepository.save(chatRoomUser)
    }
}