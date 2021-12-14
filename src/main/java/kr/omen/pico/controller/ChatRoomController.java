package kr.omen.pico.controller;

import kr.omen.pico.config.jwt.TokenProvider;
import kr.omen.pico.model.ChatRoom;
import kr.omen.pico.model.LoginInfo;
import kr.omen.pico.dao.chatdao.ChatRoomRepo;
import kr.omen.pico.dao.chatdao.ChatRoomRepository;
import kr.omen.pico.dao.chatdao.LoginInfoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@Controller
@RequestMapping("/chat")
public class ChatRoomController {

    private final ChatRoomRepository chatRoomRepository;
    private final TokenProvider jwtTokenProvider;
    private final LoginInfoRepository loginInfoRepository;
    private final ChatRoomRepo chatRoomRepo;

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
        return chatRoomRepository.createChatRoom(name);
    }

    @GetMapping("/room/enter/{roomId}")
    public String roomDetail(Model model, @PathVariable String roomId) {
        model.addAttribute("roomId", roomId);
        ChatRoom chatRoom = chatRoomRepository.findRoomById(roomId);
        chatRoomRepo.save(chatRoom);
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

        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(1, null, AuthorityUtils.createAuthorityList("ROLE_USER"));
//        loginInfoRepository.save(LoginInfo.builder().name(name).token(jwtTokenProvider.createToken(authentication)).build());
        return LoginInfo.builder().name(name).token(jwtTokenProvider.createToken(authentication)).build();
    }
}