package com.ditraacademy.travelagency.core.destination;


import com.ditraacademy.travelagency.core.voyage.Voyage;
import jdk.nashorn.internal.ir.annotations.Ignore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Destination {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String nom;
    private String description;
//    @Ignore
//    @OneToMany
//
//    private Voyage voyage;
}
