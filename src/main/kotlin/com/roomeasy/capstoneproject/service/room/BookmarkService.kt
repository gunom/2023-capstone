package com.roomeasy.capstoneproject.service.room

import com.roomeasy.capstoneproject.domain.room.Bookmark

interface BookmarkService {
    fun toggleBookmark(roomId: Long, userId: Long): String
    fun getBookmarkList(userId: Long): List<Bookmark>
}