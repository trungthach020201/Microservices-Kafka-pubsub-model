package com.study.EmployeeService.command.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserUpdatedEvent {
    private String userId;
    private String firstName;
    private String lastName;
    private String kin;
    private boolean isDiscliped;
}
