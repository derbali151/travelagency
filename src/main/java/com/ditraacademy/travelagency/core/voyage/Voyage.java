package com.ditraacademy.travelagency.core.voyage;

import com.ditraacademy.travelagency.core.destination.Destination;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Voyage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titre;
    private String description;
    private Date date;
    private  Integer nbPlaces;
    private  Float prix;
   @ManyToOne
    private  Destination destination;


}
