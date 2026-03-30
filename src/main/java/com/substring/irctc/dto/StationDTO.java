package com.substring.irctc.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class StationDTO {

    private Long id;
    @NotBlank(message = "Station code should not be blank")
    @Size(min = 3,max = 10,message = "code size should be greater than 3 and less than 10")
    private String code;
    @NotBlank(message = "name should not be blank")
    private String name;
    @NotBlank(message = "city should not be blank")
    private String city;
    @NotBlank(message = "state should not be blank")
    private String state;

}
