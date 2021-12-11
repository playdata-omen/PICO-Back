package kr.omen.pico.domain.dto;

import lombok.Data;

public class ChatMessageDTO {
    @Data
    public static class Delete {
        private Long chatMessageIdx;
    }
}
