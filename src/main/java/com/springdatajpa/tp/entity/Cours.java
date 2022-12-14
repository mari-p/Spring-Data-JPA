package com.springdatajpa.tp.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Cours")
@Table(name = "cours")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Cours {

    @Id
    @SequenceGenerator(
            name = "cours_seqence",
            sequenceName = "cours_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "cours_sequence"
    )
    @Column(
            name = "cours_id",
            updatable = false,
            nullable = false
    )
    private Long id;

    @Column(
            name = "cours_name",
             nullable = false,
            columnDefinition = "TEXT"
    )
    private String name;

    @Column(
            name = "cours_department",
            nullable = false,
            columnDefinition = "TEXT"
    )
    private String department;

    @OneToMany(
            cascade = {CascadeType.PERSIST,CascadeType.REMOVE},
            mappedBy = "cours")
    private List<Enrolment> enrolments = new ArrayList<>();

    public Cours(String name,String department){
        this.name = name;
        this.department = department;
    }

    public void addEnrolment(Enrolment enrolment){
        if(!enrolments.contains(enrolment)){
            enrolments.add(enrolment);
        }
    }
    public void removeEnrolment(Enrolment enrolment){
        enrolments.remove(enrolment);
    }
}
