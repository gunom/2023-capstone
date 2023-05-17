package com.roomeasy.capstoneproject.controller

import com.roomeasy.capstoneproject.controller.dto.CreateChatRoomDto
import com.roomeasy.capstoneproject.controller.dto.ResponseWithData
import com.roomeasy.capstoneproject.facade.ChatFacade
import com.roomeasy.capstoneproject.service.dto.ChatRoomDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chatroom")
class ChatRoomController(
    private val chatFacade: ChatFacade,
) {
    @PostMapping("/create")
    fun createChatRoom(@RequestBody createChatRoomDto: CreateChatRoomDto): ResponseEntity<ResponseWithData<Nothing?>> {
        chatFacade.createChatRoom(createChatRoomDto.brokerId)
        return ResponseEntity.ok().body(ResponseWithData(200, true, "채팅방 생성 성공", null))
    }

    @GetMapping("/list")
    fun getChatRoomsForUser(): ResponseEntity<ResponseWithData<List<ChatRoomDto>>> {
        val result = chatFacade.getChatRoomsForUser()
        return ResponseEntity.ok().body(ResponseWithData(200, true, "채팅방 목록 조회 성공", result))
    }
}