package com.roomeasy.capstoneproject.controller

import com.roomeasy.capstoneproject.controller.dto.AddRoomRequestDto
import com.roomeasy.capstoneproject.controller.dto.ResponseWithData
import com.roomeasy.capstoneproject.controller.dto.ResponseWithoutData
import com.roomeasy.capstoneproject.facade.RoomFacade
import com.roomeasy.capstoneproject.service.dto.RoomDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/room")
class RoomController(
    private val roomFacade: RoomFacade,
) {

    @GetMapping("/broker")
    fun getRoomListForBroker(): ResponseEntity<ResponseWithData<List<RoomDto>>> {
        val data = roomFacade.getRoomListForBroker()
        return ResponseEntity.ok().body(ResponseWithData.ok(200, "중개인 방 조회 성공", data))
    }

    @PostMapping("/broker")
    fun addRoomForBroker(@RequestBody addRoomRequestDto: AddRoomRequestDto): ResponseEntity<ResponseWithoutData> {
        roomFacade.addRoomForBroker(addRoomRequestDto.roomId)
        return ResponseEntity.ok().body(ResponseWithoutData.ok(200, "중개인 방 등록 성공"))
    }

    @DeleteMapping("/broker")
    fun deleteRoomForBroker(@RequestBody addRoomRequestDto: AddRoomRequestDto): ResponseEntity<ResponseWithoutData> {
        roomFacade.deleteRoomForBroker(addRoomRequestDto.roomId)
        return ResponseEntity.ok().body(ResponseWithoutData.ok(200, "중개인 방 삭제 성공"))
    }
}