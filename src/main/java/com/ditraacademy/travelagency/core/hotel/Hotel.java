package com.ditraacademy.travelagency.core.hotel;

import com.ditraacademy.travelagency.core.chamber.chamber.Chamber;
import com.ditraacademy.travelagency.utility.Audible;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Where(clause =" deleted = false")
@SQLDelete( sql = "Update hotel SET deleted = true where id =?")
public class Hotel extends Audible<String> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private int id;
    private String nom ;

    @Column(columnDefinition = "text")
    private String description ;
    private  Integer etoiles;
    private String adresse;
    private  Integer telephone;

    //create new table
    @ManyToMany
    @JoinTable(name = "hotel_chamber",
    joinColumns = {@JoinColumn(name = "hotel_id")},
    inverseJoinColumns = {@JoinColumn(name = "chamber_id")})
    private List<Chamber> chambers;

    @JsonIgnore
    private boolean deleted = false;

    public  void addChamber(Chamber chamber){
        chambers.add(chamber);
    }
    public  void removeChamber(int id){
        chambers.removeIf(chamber -> chamber.getId() == id);
    }



}
