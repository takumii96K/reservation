package org.example.reservation.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReservationDto {
    private long reservationId;
    private LocalDateTime dateTime;
    private String customerName;
    private String tel;
    private String email;
}
