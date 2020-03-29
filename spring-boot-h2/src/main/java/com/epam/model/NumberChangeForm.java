package com.epam.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class NumberChangeForm {
    @NotNull
    Long userId = 1L;

    @NotEmpty
    private String companyName = "";

    @NotEmpty
    private String phoneNumber = "";

}
