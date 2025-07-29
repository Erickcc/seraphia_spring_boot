package com.seraphia.seraphia.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "colors")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Colors {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "color_name", nullable = false, unique = true)
    private String colorName;

    @OneToMany(mappedBy = "color", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
//    @JsonManagedReference //Parent in charge of serialize, prevent child from trying to serializing parent
    private List<Products> productsList;
}
