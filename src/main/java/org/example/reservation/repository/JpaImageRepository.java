package org.example.reservation.repository;

import org.example.reservation.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JpaImageRepository extends JpaRepository<Image, Long> {
}
