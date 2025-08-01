package com.seraphia.seraphia.model;

import jakarta.persistence.*;
import lombok.*;

<<<<<<< HEAD
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;


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

    @Column(name = "description", nullable = false, length = 1000)
    private String description;

    @Column(name = "price", nullable = false)
    private BigDecimal price;

    @Column(name = "stock", nullable = false)
    private Integer stock = 1;

    @Column(name = "creation_date", nullable = false, columnDefinition = "date")
    private LocalDate creationDate = LocalDate.now();

    @Column(name = "is_available", nullable = false)
    private Integer isAvailable = 1;

    @ManyToOne
    @JoinColumn(name = "size_id")
    private Sizes size;

    @ManyToOne
    @JoinColumn(name = "color_id")
//    @JsonIgnore
//    @JsonBackReference //Tells this entity that it doesnt need to serialize the parent
    private Colors color;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Categories category;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Images> imagesList;

    @ManyToMany(mappedBy = "products", fetch = FetchType.LAZY)
    @JsonIgnore
    private Set<Orders> orders = new HashSet<>();
}
