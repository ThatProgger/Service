package com.nsh.services.lamps.model;

import com.nsh.services.lamps.enums.LifeCycle;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * TODO: make description
 */
@Entity
@Table(name = "lamp")
@EntityListeners(AuditingEntityListener.class)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Lamp implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(nullable = false)
    private int lampNumber;


    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LifeCycle lifeCycle;

    @Column(columnDefinition = "VARCHAR(300)")
    private String message;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Defect> defects;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Material> materials;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Job> jobs;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Note> notes;

    @Column(name = "initiator", nullable = false)
    private String initiator;

    @Column(name = "executor")
    private String executor;


    @CreatedDate
    @Temporal(TemporalType.DATE)
    private Date created;

    @LastModifiedDate
    @Temporal(TemporalType.DATE)
    private Date updated;

}