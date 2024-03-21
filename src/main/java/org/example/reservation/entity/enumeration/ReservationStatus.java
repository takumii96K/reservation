package org.example.reservation.entity.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public enum ReservationStatus {
    UNCONFIRMED("未確定"),
    CONFIRMED("確定"),
    CANCELLED("キャンセル");

    private final String status;

}
