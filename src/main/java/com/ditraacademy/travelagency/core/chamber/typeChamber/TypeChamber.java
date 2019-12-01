package com.ditraacademy.travelagency.core.chamber.typeChamber;


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
@SQLDelete( sql = "Update type_chamber SET deleted = true where id =?")
public class TypeChamber {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private  String type;
    @JsonIgnore
    private boolean deleted = false;
}
