package com.roomeasy.capstoneproject.service.room

import com.roomeasy.capstoneproject.domain.room.Bookmark
import com.roomeasy.capstoneproject.repository.room.BookmarkRepository
import org.springframework.stereotype.Service

@Service
class BookmarkServiceImpl(
    private val bookmarkRepository: BookmarkRepository,
) : BookmarkService {

    override fun toggleBookmark(roomId: Long, userId: Long): String {
        val bookmark = bookmarkRepository.findByUserIdAndRoomId(userId, roomId)
        return if (bookmark == null) {
            bookmarkRepository.save(Bookmark(userId = userId, roomId = roomId))
            "즐겨찾기 등록 성공"
        } else {
            bookmarkRepository.delete(bookmark)
            "즐겨찾기 삭제 성공"
        }
    }

    override fun getBookmarkList(userId: Long): List<Bookmark> {
        return bookmarkRepository.findAllByUserId(userId)
    }
}