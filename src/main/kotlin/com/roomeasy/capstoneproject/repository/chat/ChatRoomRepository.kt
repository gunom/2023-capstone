package com.roomeasy.capstoneproject.repository.chat

import com.roomeasy.capstoneproject.domain.chat.ChatRoom
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChatRoomRepository : JpaRepository<ChatRoom, Long> {
}
