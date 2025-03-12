package com.example.userservice.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Geo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String lat;
    private String lng;
}
