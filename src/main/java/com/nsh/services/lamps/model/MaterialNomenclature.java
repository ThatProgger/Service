package com.nsh.services.lamps.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Entity
@Table(name = "material_nomenclature")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MaterialNomenclature implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(nullable = false, columnDefinition = "VARCHAR(300)")
    private String nomenclature;
}