package com.ditraacademy.travelagency.core.hotel;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class HotelUpdateModel {
    private List<Integer> chamberId;
}
