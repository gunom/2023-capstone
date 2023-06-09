package com.roomeasy.capstoneproject.controller

import com.roomeasy.capstoneproject.controller.dto.BrokerReviewRequestDto
import com.roomeasy.capstoneproject.controller.dto.ResponseWithData
import com.roomeasy.capstoneproject.controller.dto.ReviewRequestDto
import com.roomeasy.capstoneproject.facade.RoomFacade
import com.roomeasy.capstoneproject.facade.UserFacade
import com.roomeasy.capstoneproject.service.dto.BrokerReviewDto
import com.roomeasy.capstoneproject.service.dto.ReviewDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/review")
class ReviewController(
    private val roomFacade: RoomFacade,
    private val userFacade: UserFacade,
) {
    @GetMapping("/list")
    fun getReviewList(@RequestParam("room_id") roomId: Long): ResponseEntity<ResponseWithData<List<ReviewDto>>> {
        val reviewList = roomFacade.getReviewList(roomId)
        return ResponseEntity.ok()
            .body(ResponseWithData(HttpStatus.OK.value(), true, "리뷰 조회 성공", reviewList))
    }

    @DeleteMapping("/delete")
    fun deleteReview(@RequestParam("review_id") reviewId: Long): ResponseEntity<ResponseWithData<Nothing?>> {
        roomFacade.deleteReview(reviewId)
        return ResponseEntity.ok()
            .body(ResponseWithData(HttpStatus.OK.value(), true, "리뷰 삭제 성공", null))
    }

    @PostMapping()
    fun addReview(@RequestBody reviewRequestDto: ReviewRequestDto): ResponseEntity<ResponseWithData<Nothing?>> {
        roomFacade.addReview(
            reviewRequestDto.roomId,
            reviewRequestDto.timeOfResidence,
            reviewRequestDto.ageGroup,
            reviewRequestDto.gender,
            reviewRequestDto.transportationRating,
            reviewRequestDto.neighborhoodRating,
            reviewRequestDto.livingConditionsRating,
            reviewRequestDto.freeComments,
        )
        return ResponseEntity.ok()
            .body(ResponseWithData(HttpStatus.OK.value(), true, "리뷰 등록 성공", null))
    }

    @GetMapping("/broker")
    fun getBrokerReview(@RequestParam("broker_id") brokerId: Long): ResponseEntity<ResponseWithData<BrokerReviewDto>> {
        val brokerReview = userFacade.getBrokerReview(brokerId)
        return ResponseEntity.ok()
            .body(ResponseWithData(HttpStatus.OK.value(), true, "중개인 리뷰 조회 성공", brokerReview))
    }

    @PostMapping("/broker")
    fun addBrokerReview(@RequestParam("broker_id") brokerId: Long, @RequestBody brokerReviewRequestDto: BrokerReviewRequestDto): ResponseEntity<ResponseWithData<Nothing?>> {
        val brokerReview = userFacade.addBrokerReview(brokerId, brokerReviewRequestDto.kindness, brokerReviewRequestDto.reliability, brokerReviewRequestDto.responseTime)
        return ResponseEntity.ok()
            .body(ResponseWithData(HttpStatus.OK.value(), true, "중개인 리뷰 등록 성공", null))
    }

}