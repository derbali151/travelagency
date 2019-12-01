package com.ditraacademy.travelagency.core.destination;


import com.ditraacademy.travelagency.core.voyage.Voyage;
import com.ditraacademy.travelagency.utility.Audible;
import com.fasterxml.jackson.annotation.JsonIgnore;
;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause = "deleted = false")
@SQLDelete(sql = "Update destination SET deleted = true  Where id =?" )
public class Destination  extends Audible<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String nom;

    private String description;
    @JsonIgnore
    @OneToMany(mappedBy="destination", cascade ={CascadeType.REMOVE})
    private List<Voyage> voyage;


    @JsonIgnore
    private Boolean deleted = false;
}
