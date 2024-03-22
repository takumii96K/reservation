package org.example.reservation;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ReservationApplication {

    public static void main(String[] args) {
        SpringApplication.run(ReservationApplication.class, args);
    }
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        //マッチング戦略
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        //型の完全マッチなど
        modelMapper.getConfiguration().setFullTypeMatchingRequired(true);
        return modelMapper;
    }
}
