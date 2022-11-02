package com.joeldavg.obrestdatajpa.model.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Book")
@Table(name = "books")
@Schema(description = "Book entity to represent a didactic element on the database")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    @Schema(description = "Primary key auto incremental type Long")
    private Long id;

    private String title;

    private String author;

    private Integer pages;

    @Schema(description = "Price in USD, with 2 decimals")
    private Double price;

    private LocalDate releaseDate;

    private Boolean online;


}
