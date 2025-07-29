package com.seraphia.seraphia.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "sizes")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Sizes {
    //id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    //size_name
    @Column(name = "size_name", nullable = false)
    private String sizeName;

//    @OneToMany(mappedBy = "size", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Products> productsList;
}
