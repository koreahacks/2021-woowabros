package koreahacks.woowabros.uniconn.member.presentation.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class StatisticResponse {
    private String name;
    private double point;

    public StatisticResponse(String name, int point) {
        this.name = name;
        this.point = (double)point / Math.pow(10, (int)(Math.log10(point) + 1));
    }
}
