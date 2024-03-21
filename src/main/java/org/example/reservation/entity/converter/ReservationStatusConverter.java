package org.example.reservation.entity.converter;


import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;
import org.example.reservation.entity.enumeration.ReservationStatus;

@Converter(autoApply = true)
public class ReservationStatusConverter implements AttributeConverter<ReservationStatus, String> {

    @Override
    public String convertToDatabaseColumn(ReservationStatus status) {
        if (status == null) {
            return null;
        }
        return status.getStatus();
    }

    @Override
    public ReservationStatus convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        for (ReservationStatus status : ReservationStatus.values()) {
            if (status.getStatus().equals(dbData)) {
                return status;
            }
        }

        throw new IllegalArgumentException("Unknown reservation status: " + dbData);
    }
}