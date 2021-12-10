package kr.omen.pico.service;

import javassist.NotFoundException;
import kr.omen.pico.config.exception.Exception;
import kr.omen.pico.model.ChatMessage;
import kr.omen.pico.model.ChatRoom;
import kr.omen.pico.repo.ChatRoomRepo;
import kr.omen.pico.repo.ChatRoomRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.listener.ChannelTopic;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class ChatService {

    private final ChannelTopic channelTopic;
    private final RedisTemplate redisTemplate;
    private final ChatRoomRepository chatRoomRepository;
    private final ChatRoomRepo chatRoomRepo;
    /**
     * destination정보에서 roomId 추출
     */
    public String getRoomId(String destination) {
        int lastIndex = destination.lastIndexOf('/');
        if (lastIndex != -1)
            return destination.substring(lastIndex + 1);
        else
            return "";
    }

    /**
     * 채팅방에 메시지 발송
     */
    public void sendChatMessage(ChatMessage chatMessage) {
        chatMessage.setUserCount(chatRoomRepository.getUserCount(chatMessage.getRoomId()));
        if (ChatMessage.MessageType.ENTER.equals(chatMessage.getType())) {
            chatMessage.setMessage(chatMessage.getSender() + "님이 방에 입장했습니다.");
            chatMessage.setSender("[알림]");
        } else if (ChatMessage.MessageType.QUIT.equals(chatMessage.getType())) {
            chatMessage.setMessage(chatMessage.getSender() + "님이 방에서 나갔습니다.");
            chatMessage.setSender("[알림]");
        }
        redisTemplate.convertAndSend(channelTopic.getTopic(), chatMessage);
    }

    @Transactional
    public void updateCount(String roomId, Long count){
        ChatRoom chatRoom = chatRoomRepo.findChatRoomByRoomId(roomId);
        System.out.println("please helpme" + count);
        chatRoom.setUserCount(count);
    }

    public void deleteChatRoom(Long roomIdx) throws NotFoundException{
        ChatRoom chatRoom = findOne(roomIdx);

        chatRoomRepo.deleteById(chatRoom.getIdx());
    }

    public ChatRoom findOne(Long chatRoomIdx) throws NotFoundException{
        ChatRoom chatRoom = null;
        try {
            chatRoom = chatRoomRepo.findById(chatRoomIdx).orElseThrow(() -> new Exception.NotFoundException("Student with idx: " + chatRoomIdx + " is not valid"));
        } catch (Exception.NotFoundException e) {
//            e.printStackTrace();
        }
        return chatRoom;
    }
}
