package com.seraphia.seraphia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Entity
@Table(name = "products")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Products {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "stock", nullable = false)
    private Integer stock = 1;

    @Column(name = "creation_date", nullable = false)
    private String creationDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd-MM-yyyy"));

    @Column(name = "is_available", nullable = false)
    private Integer isAvailable = 1;

//    @ManyToOne
//    @JoinColumn(name = "size_id")
//    private Sizes size;

    @ManyToOne
    @JoinColumn(name = "color_id")
    @JsonBackReference //Tells this entity that it doesnt need to serialize the parent
    private Colors color;

//    @ManyToOne
//    @JoinColumn(name = "category_id")
//    private Categories category;
}
