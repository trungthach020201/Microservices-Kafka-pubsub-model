package com.study.EmployeeService.command.data;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
public class User {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String kin;
    private boolean isDiscliped;
}
