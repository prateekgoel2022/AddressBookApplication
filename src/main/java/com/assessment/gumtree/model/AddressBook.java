package com.assessment.gumtree.model;

import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDate;

@AllArgsConstructor
@Data
public class AddressBook {

    private String Name;
    private String gender;
    private LocalDate dataOfBirth;

}
