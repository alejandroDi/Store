package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private Integer idUser;
    @JsonIncludeProperties(value = {"idRole","nameRole"})
    @NotNull
    private RoleDTO role;
    @JsonProperty(value = "user_name")
    @NotNull
    @Size(min = 3, max = 50)
    private String username;
    //@JsonIgnore
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull
    @Size(min = 3, max = 100)
    private String password;
    @NotNull
    private boolean enabled;

    //33 02:26
}
