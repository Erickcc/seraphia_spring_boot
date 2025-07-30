package com.seraphia.seraphia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_items")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class CartItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    // Lado hijo de la relación con Cart, evitamos ciclos JSON
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id", nullable = false)
    @JsonBackReference
    private Cart cart;

    // userId asignado desde el Cart (se debe setear en el servicio)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    // Producto asociado, EAGER por defecto está bien
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "product_id", nullable = false)
    private Products product;

    @Column(name = "category", nullable = true)
    private String category;

    // Color relacionado (puede ser null)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "color_id", nullable = true)
    private Colors color;

    // Talla relacionada (puede ser null)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "size_id", nullable = true)
    private Sizes size;

    @Column(name = "quantity", nullable = false)
    private int quantity;

}
