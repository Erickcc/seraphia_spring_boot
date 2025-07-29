package com.seraphia.seraphia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "images")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Images {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "image_url", nullable = false, columnDefinition = "MEDIUMTEXT")
    private String imageUrl;

    @Column(name = "image_order", nullable = false)
    private Integer imageOrder;

    @ManyToOne
    @JoinColumn(name = "product_id")
    @JsonIgnore
    private Products product;

}
