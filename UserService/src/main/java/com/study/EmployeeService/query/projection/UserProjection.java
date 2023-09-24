package com.study.EmployeeService.query.projection;

import com.study.EmployeeService.command.data.User;
import com.study.EmployeeService.command.data.UserRepository;
import com.study.EmployeeService.query.model.UserResponseModel;
import com.study.EmployeeService.query.queries.GetAllUserQuery;
import com.study.EmployeeService.query.queries.GetUserByIdQuery;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserProjection {
    private final UserRepository userRepository;

    @QueryHandler
    public List<UserResponseModel> handle (GetAllUserQuery getAllUserQuery){
        List<User> users = userRepository.findAll();
        List<UserResponseModel> userResponseModels =
                users.stream()
                        .map(user -> UserResponseModel.builder()
                                .firstName(user.getFirstName())
                                .lastName(user.getLastName())
                                .kin(user.getKin())
                                .isDiscliped(user.isDiscliped())
                                .build())
                        .collect(Collectors.toList());
        return userResponseModels;
    }

    @QueryHandler
    public UserResponseModel handle (GetUserByIdQuery getUserByIdQuery){
        User user = userRepository.findById(getUserByIdQuery.getEmployeeId()).orElseThrow(()->new NotFoundException("Not Found"));
        UserResponseModel employeeRes = UserResponseModel.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .kin(user.getKin())
                .isDiscliped(user.isDiscliped())
                .build();
        return employeeRes;
    }
}
