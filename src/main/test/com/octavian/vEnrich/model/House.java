package com.octavian.vEnrich.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class House {

    private Person owner;
    private List<Person> tenants;

}
