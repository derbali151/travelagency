package com.ditraacademy.travelagency.core.voyage;

import com.ditraacademy.travelagency.core.destination.Destination;
import com.ditraacademy.travelagency.utility.Audible;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.Date;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause =" deleted = false")
@SQLDelete( sql = "Update voyage SET deleted = true where id =?")

public class Voyage  extends Audible<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String titre;

    @Column(columnDefinition = "text")
    private String description;
    private Date date;
    private  Integer nbPlaces;
    private  Double prix;
   @ManyToOne
    private  Destination destination;
   @JsonIgnore
    private boolean deleted = false;


}
