package com.study.EmployeeService.command.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class CreateUserCommand {
    @TargetAggregateIdentifier
    private String userId;
    private String firstName;
    private String lastName;
    private String kin;
    private boolean isDiscliped;
}
