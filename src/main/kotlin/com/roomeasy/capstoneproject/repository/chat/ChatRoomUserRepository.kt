package com.roomeasy.capstoneproject.repository.chat

import com.roomeasy.capstoneproject.domain.chat.ChatRoomUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRoomUserRepository : JpaRepository<ChatRoomUser, Long>{
    fun findAllByUserId(userId: Long): List<ChatRoomUser>
    fun findAllByChatRoomIdIn(roomId: List<Long>): List<ChatRoomUser>
}