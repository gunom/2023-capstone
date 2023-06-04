package com.roomeasy.capstoneproject.controller

import com.roomeasy.capstoneproject.controller.dto.CreateChatRoomDto
import com.roomeasy.capstoneproject.controller.dto.ReminderRequestDto
import com.roomeasy.capstoneproject.controller.dto.ResponseWithData
import com.roomeasy.capstoneproject.domain.chat.Reminder
import com.roomeasy.capstoneproject.facade.ChatFacade
import com.roomeasy.capstoneproject.service.dto.ChatMessageDto
import com.roomeasy.capstoneproject.service.dto.ChatRoomDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/chatroom")
class ChatRoomController(
    private val chatFacade: ChatFacade,
) {
    @PostMapping("/create")
    fun createChatRoom(@RequestBody createChatRoomDto: CreateChatRoomDto): ResponseEntity<ResponseWithData<ChatRoomDto>> {
        val result = chatFacade.createChatRoom(createChatRoomDto.brokerId)
        return ResponseEntity.ok().body(ResponseWithData(200, true, "채팅방 생성 성공", result))
    }

    @GetMapping("/list")
    fun getChatRoomsForUser(): ResponseEntity<ResponseWithData<List<ChatRoomDto>>> {
        val result = chatFacade.getChatRoomsForUser()
        return ResponseEntity.ok().body(ResponseWithData(200, true, "채팅방 목록 조회 성공", result))
    }

    @GetMapping("/chat-list/{chatRoomId}")
    fun getChatRoomMessage(@PathVariable chatRoomId: Long): ResponseEntity<ResponseWithData<List<ChatMessageDto>>> {
        val result = chatFacade.getChatRoomMessages(chatRoomId)
        return ResponseEntity.ok().body(ResponseWithData(200, true, "채팅방 메시지 조회 성공", result))
    }

    @GetMapping("/reminder")
    fun getReminder(@RequestParam chatRoomId: Long): ResponseEntity<ResponseWithData<Reminder?>> {
        val result = chatFacade.getReminder(chatRoomId)
        return ResponseEntity.ok().body(ResponseWithData(200, true, "채팅방 리마인더 조회 성공", result))
    }

    @PostMapping("/reminder")
    fun addReminder(@RequestBody reminderRequestDto: ReminderRequestDto): ResponseEntity<ResponseWithData<Nothing?>> {
        chatFacade.addReminder(reminderRequestDto.chatRoomId, reminderRequestDto.date, reminderRequestDto.time, reminderRequestDto.place, reminderRequestDto.longitude, reminderRequestDto.latitude)
        return ResponseEntity.ok().body(ResponseWithData(200, true, "채팅방 리마인더 추가 성공", null))
    }
}