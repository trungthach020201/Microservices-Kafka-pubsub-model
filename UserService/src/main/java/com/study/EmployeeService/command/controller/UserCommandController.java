package com.study.EmployeeService.command.controller;

import com.study.EmployeeService.command.commands.CreateUserCommand;
import com.study.EmployeeService.command.commands.DeleteUserCommand;
import com.study.EmployeeService.command.commands.UpdateUserCommand;
import com.study.EmployeeService.command.model.UserRequestModel;
import lombok.RequiredArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user")
@RequiredArgsConstructor
public class UserCommandController {
    private final CommandGateway commandGateway;
    @PostMapping("/create")
    public String createEmployee (@RequestBody UserRequestModel userRequestModel){
        CreateUserCommand createUserCommand =
                CreateUserCommand.builder()
                        .userId(UUID.randomUUID().toString())
                        .firstName(userRequestModel.getFirstName())
                        .lastName(userRequestModel.getLastName())
                        .kin(userRequestModel.getKin())
                        .isDiscliped(false)
                        .build();
        commandGateway.sendAndWait(createUserCommand);
        return "created employee";
    }

    @PutMapping("/update/{id}")
    public String updateEmployee (@RequestBody UserRequestModel userRequestModel, @PathVariable String id){
        UpdateUserCommand updateUserCommand = UpdateUserCommand.builder()
                .userId(id)
                .firstName(userRequestModel.getFirstName())
                .lastName(userRequestModel.getLastName())
                .kin(userRequestModel.getKin())
                .isDiscliped(userRequestModel.isDiscliped())
                .build();
        commandGateway.sendAndWait(updateUserCommand);
        return "User Updated";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteEmployee (@PathVariable String id){
        DeleteUserCommand deleteUserCommand = DeleteUserCommand.builder()
                .userId(id)
                .build();
        commandGateway.sendAndWait(deleteUserCommand);
        return "User Deleted";
    }

}
