
package kr.omen.pico.service.domain.dto;

import lombok.Getter;

import java.util.List;

public class PCategoryDTO {

    @Getter
    public static class add {
        List<Long> category;
    }

}
