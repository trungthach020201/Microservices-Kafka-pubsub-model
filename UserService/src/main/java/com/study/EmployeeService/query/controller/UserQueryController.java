package com.study.EmployeeService.query.controller;

import com.study.EmployeeService.query.model.UserResponseModel;
import com.study.EmployeeService.query.queries.GetAllUserQuery;
import com.study.EmployeeService.query.queries.GetUserByIdQuery;
import lombok.RequiredArgsConstructor;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/employee")
@RequiredArgsConstructor
public class UserQueryController {
    private final QueryGateway queryGateway;

    @GetMapping("/getall")
    public List<UserResponseModel> getAllEmployee(){
        GetAllUserQuery getAllUserQuery = new GetAllUserQuery();
        List<UserResponseModel> res =
                queryGateway.query(getAllUserQuery,
                        ResponseTypes.multipleInstancesOf(UserResponseModel.class)).join();
        return res;
    }

    @GetMapping("/get/{id}")
    public UserResponseModel getById(@PathVariable String id){
        GetUserByIdQuery getUserByIdQuery = new GetUserByIdQuery();
        getUserByIdQuery.setEmployeeId(id);
        UserResponseModel res = queryGateway.query(getUserByIdQuery,
                ResponseTypes.instanceOf(UserResponseModel.class)).join();
        return res;
    }



}
