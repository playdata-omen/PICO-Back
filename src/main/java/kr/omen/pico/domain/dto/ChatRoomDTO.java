package kr.omen.pico.domain.dto;

import lombok.Data;

public class ChatRoomDTO {

    @Data
    public static class Delete{
        private Long ChatRoomIdx;
    }
}
