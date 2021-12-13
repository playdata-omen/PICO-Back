package kr.omen.pico.controller;

import javassist.NotFoundException;
import kr.omen.pico.model.ChatRoom;
import kr.omen.pico.model.LoginInfo;

import kr.omen.pico.dao.chatdao.ChatRoomRepo;
import kr.omen.pico.dao.chatdao.ChatRoomRepository;
import kr.omen.pico.dao.chatdao.LoginInfoRepository;


import kr.omen.pico.service.ChatService;

import kr.omen.pico.service.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final LoginInfoRepository loginInfoRepository;
    private final ChatRoomRepo chatRoomRepo;
    private final ChatService chatService;

    @GetMapping("/room")
    public String rooms() {
        return "/chat/room";
    }

    @GetMapping("/rooms")
    @ResponseBody
    public List<ChatRoom> room() {
        List<ChatRoom> chatRooms = chatRoomRepository.findAllRoom();
        chatRooms.stream().forEach(room -> room.setUserCount(chatRoomRepository.getUserCount(room.getRoomId())));
        return chatRooms;
    }

    @PostMapping("/room")
    @ResponseBody
    public ChatRoom createRoom(@RequestParam String name) {
        ChatRoom chatRoom = chatRoomRepository.createChatRoom(name);
        chatRoomRepo.save(chatRoom);
        return chatRoom;
//        return chatRoomRepository.createChatRoom(name);

    }
//  방 삭제
    @DeleteMapping("/room/delete/{roomId}")
    @ResponseBody
    public boolean deleteRoom(@PathVariable String roomId){
        boolean result = false;
        ChatRoom chatRoom = chatRoomRepo.findChatRoomByRoomId(roomId);
        try {
            chatService.deleteChatRoom(chatRoom.getIdx());
            result = true;
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return result;
    }

    @GetMapping("/room/enter/{roomId}")
    @Transactional
    public String roomDetail(Model model, @PathVariable String roomId) {
//      특정 방에 들어올경우 StompHeaderAccessor를
//
//      stomphandler에서 웹소켓에 연결을 요청
//       header에서 구독 destination정보를 얻고,  roomid를 추출
//        확실하진 않으나 roomid가음,,,
//        채팅방에 들어온 클라이언트 sessionId를 roomId와 맵핑
//        위 과정을 진행하면서 채팅방의 인원수를 +1
//        count에는 클라이언트가 방에 들어올때 +1을 하면서 값을 넣어줌
        ChatRoom chatRoom = chatRoomRepository.findRoomById(roomId);
        long count = (chatRoomRepository.getUserCount(chatRoom.getRoomId()));
        System.out.println("count: " + count);
        chatService.updateCount(roomId, count);
//        chatRoomRepo.save(chatRoom);
        model.addAttribute("roomId", roomId);
//        ChatRoom chatRoom = chatRoomRepository.findRoomById(roomId);
//        chatRoomRepo.save(chatRoom);
        return "/chat/roomdetail";
    }



    @GetMapping("/room/{roomId}")
    @ResponseBody
    public ChatRoom roomInfo(@PathVariable String roomId) {
        return chatRoomRepository.findRoomById(roomId);
    }

    @GetMapping("/user")
    @ResponseBody
    public LoginInfo getUserInfo() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String name = auth.getName();
        LoginInfo loginInfo = LoginInfo.builder().name(name).token(jwtTokenProvider.generateToken(name)).build();

       loginInfoRepository.save(loginInfo);
        return loginInfo;
    }
}