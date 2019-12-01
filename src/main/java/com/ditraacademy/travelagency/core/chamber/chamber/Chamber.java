package com.ditraacademy.travelagency.core.chamber.chamber;

import com.ditraacademy.travelagency.core.chamber.categorieChamber.CategorieChamber;
import com.ditraacademy.travelagency.core.chamber.typeChamber.TypeChamber;
import com.ditraacademy.travelagency.core.hotel.Hotel;
import com.ditraacademy.travelagency.utility.Audible;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.web.bind.annotation.Mapping;

import javax.persistence.*;
import java.util.List;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause =" deleted = false")
@SQLDelete( sql = "Update chamber SET deleted = true where id =?")
@Table(uniqueConstraints = {
        @UniqueConstraint(columnNames = {"categorie_chamber_id","type_chamber_id"})
})

public class Chamber extends Audible<String> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id ;

    @ManyToOne
    TypeChamber typeChamber ;

    @ManyToOne
    CategorieChamber categorieChamber;


    @JsonIgnore
    @ManyToMany(mappedBy="chambers")
    private List<Hotel> hotels;



    @JsonIgnore
    private boolean deleted = false;
}
