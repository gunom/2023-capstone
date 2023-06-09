package com.roomeasy.capstoneproject.facade

import com.roomeasy.capstoneproject.service.user.AuthService
import com.roomeasy.capstoneproject.service.room.BrokerRoomService
import com.roomeasy.capstoneproject.service.room.BookmarkService
import com.roomeasy.capstoneproject.service.room.ReviewService
import com.roomeasy.capstoneproject.service.room.RoomService
import com.roomeasy.capstoneproject.service.dto.ReviewDto
import com.roomeasy.capstoneproject.service.dto.RoomDto
import com.roomeasy.capstoneproject.service.user.UserService
import org.springframework.stereotype.Service

@Service
class RoomFacade(
    private val authService: AuthService,
    private val brokerRoomService: BrokerRoomService,
    private val roomService: RoomService,
    private val bookmarkService: BookmarkService,
    private val reviewService: ReviewService,
    private val userService: UserService,
) {
    fun getRoomListForBroker(): List<RoomDto> {
        val userId = authService.getUserId()
        val brokerRoomList = brokerRoomService.getListByUserId(userId)
        val roomIds = brokerRoomList.map { it.roomId }
        val roomList = roomService.getByIds(roomIds)
        return roomList.map {
            RoomDto(
                id = it.id,
                deposit = it.deposit,
                imagesThumbnail = it.imagesThumbnail,
                rent = it.rent,
                sizeM2 = it.sizeM2
            )
        }
    }

    fun addRoomForBroker(roomId: Long) {
        val userId = authService.getUserId()
        brokerRoomService.addBrokerRoom(userId, roomId)
    }

    fun deleteRoomForBroker(roomId: Long) {
        val userId = authService.getUserId()
        val brokerRoom = brokerRoomService.getBrokerRoomByUserIdAndRoomId(userId, roomId)
        brokerRoomService.deleteBrokerRoom(brokerRoom!!)
    }

    fun toggleBookmark(roomId: Long): String {
        val userId = authService.getUserId()
        return bookmarkService.toggleBookmark(roomId, userId)
    }

    fun getBookmarkList(): List<RoomDto> {
        val userId = authService.getUserId()
        val bookmarkList = bookmarkService.getBookmarkList(userId)
        val rooms = roomService.getByIds(bookmarkList.map { it.roomId })
        return rooms.map {
            RoomDto(
                id = it.id,
                deposit = it.deposit,
                imagesThumbnail = it.imagesThumbnail,
                rent = it.rent,
                sizeM2 = it.sizeM2
            )
        }
    }

    fun addReview(
        roomId: Long,
        timeOfResidence: Int,
        gender: String,
        ageGroup: String,
        transportationRating: Int,
        neighborhoodRating: Int,
        livingConditionsRating: Int,
        freeComments: String?
    ) {
        val userId = authService.getUserId()
        reviewService.addReview(
            userId,
            roomId,
            timeOfResidence,
            gender,
            ageGroup,
            transportationRating,
            neighborhoodRating,
            livingConditionsRating,
            freeComments
        )
    }

    fun getReviewList(roomId: Long): List<ReviewDto> {
        val userId = authService.getUserId()
        val reviewList = reviewService.getReview(roomId)
        val userIds = reviewList.map { it.userId }
        val users = userService.getUserByIds(userIds)
        return reviewList.fold(mutableListOf()) { acc, review ->
            val userName =
                users.find { it.id == review.userId }?.name ?: throw Exception("User not found")
            val reviewDto = ReviewDto(
                id = review.id,
                userId = review.userId,
                myReview = review.userId == userId,
                name = userName,
                roomId = review.roomId,
                timeOfResidence = review.timeOfResidence,
                score = (review.transportationRating + review.neighborhoodRating + review.livingConditionsRating) / 3.0,
                freeComments = review.freeComments,
            )
            acc.add(reviewDto)
            acc
        }
    }

    fun deleteReview(reviewId: Long) {
        val userId = authService.getUserId()
        reviewService.deleteReview(reviewId, userId)
    }
}