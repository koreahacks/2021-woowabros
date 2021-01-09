package koreahacks.woowabros.uniconn.member.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Major {
    COMPUTER_SCIENCE("컴퓨터공학과"),
    BUSINESS("경영학과"),
    ECONOMY("경제학과"),
    MATH("수학과"),
    PHYSICS("물리학과"),
    CHEMISTRY("화학과"),
    BIOLOGY("생물학과"),
    STATISTICS("통계학과"),
    MEDICAL("의하과"),
    PHARMACY("약학과"),
    INDUSTRY("산업과");

    private final String name;
}
