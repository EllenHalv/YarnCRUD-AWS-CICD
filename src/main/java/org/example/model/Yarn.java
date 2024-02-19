package org.example.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "yarn")
@Data
public class Yarn {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "yarn_type", nullable = false)
    private String type;

    @Column(name = "yarn_color", nullable = false)
    private String color;

    @Column(name = "yarn_weight", nullable = false)
    private int weight;

}
