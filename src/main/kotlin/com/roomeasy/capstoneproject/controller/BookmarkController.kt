package com.roomeasy.capstoneproject.controller

import com.roomeasy.capstoneproject.controller.dto.ResponseWithData
import com.roomeasy.capstoneproject.facade.RoomFacade
import com.roomeasy.capstoneproject.service.dto.RoomDto
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/bookmark")
class BookmarkController(
    private val roomFacade: RoomFacade,
) {
    @PostMapping("/add?room_id={room_id}")
    fun toggleBookmark(@PathVariable("room_id") roomId: Long): ResponseEntity<ResponseWithData<Nothing?>> {
        val result = roomFacade.toggleBookmark(roomId)
        return ResponseEntity.ok()
            .body(ResponseWithData(HttpStatus.OK.value(), true, result, null))
    }

    @GetMapping("/list")
    fun getBookmarkList(): ResponseEntity<ResponseWithData<List<RoomDto>>> {
        val result = roomFacade.getBookmarkList()
        return ResponseEntity.ok()
            .body(ResponseWithData(HttpStatus.OK.value(), true, "조회 성공", result))
    }
}