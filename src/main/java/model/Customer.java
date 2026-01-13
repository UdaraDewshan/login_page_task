package model;

import lombok.*;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    private String email;
    private String firstName;
    private String lastName;
    private String password;

}
