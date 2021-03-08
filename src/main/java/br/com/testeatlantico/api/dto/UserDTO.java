package br.com.testeatlantico.api.dto;

import br.com.testeatlantico.api.model.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
    private String name;
    private String login;
    private String password;
    private String email;
    private Boolean admin;

}
