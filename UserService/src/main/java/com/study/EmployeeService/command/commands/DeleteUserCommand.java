package com.study.EmployeeService.command.commands;

import lombok.Builder;
import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
@Builder
public class DeleteUserCommand {
    @TargetAggregateIdentifier
    private String userId;
}
