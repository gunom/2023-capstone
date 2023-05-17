package com.roomeasy.capstoneproject.repository.room

import com.roomeasy.capstoneproject.domain.room.Bookmark
import org.springframework.data.jpa.repository.JpaRepository

interface BookmarkRepository: JpaRepository<Bookmark, Long> {
    fun findByUserIdAndRoomId(userId: Long, roomId: Long): Bookmark?
    fun findAllByUserId(userId: Long): List<Bookmark>
}