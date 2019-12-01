package com.ditraacademy.travelagency.core.chamber.categorieChamber;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Where(clause =" deleted = false")
@SQLDelete( sql = "Update categorie_chamber SET deleted = true where id =?")
public class CategorieChamber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  int id;
    private  String categoriesChamber;
    @JsonIgnore
    private boolean deleted = false;



}
