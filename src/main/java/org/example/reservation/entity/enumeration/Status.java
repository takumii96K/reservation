package org.example.reservation.entity.enumeration;

import jakarta.persistence.GeneratedValue;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import org.hibernate.internal.build.AllowNonPortable;

@Getter
@AllArgsConstructor
public enum Status {
    UNCONFIRMED("未確定"),
    CONFIRMED("確定"),
    CANCELLED("キャンセル");

    private final String status;


}
