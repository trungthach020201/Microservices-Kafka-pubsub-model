package com.study.EmployeeService.command.events;

import com.study.EmployeeService.command.data.User;
import com.study.EmployeeService.command.data.UserRepository;
import jakarta.ws.rs.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventHandlerUser {
    private final UserRepository userRepository;

    @EventHandler
    public void on (UserCreatedEvent event){
        User user = new User();
        BeanUtils.copyProperties(event, user);
        userRepository.save(user);
    }

    @EventHandler
    public void on (UserUpdatedEvent event){
        User user = userRepository.findById(event.getUserId()).orElseThrow(()->new NotFoundException("User Not Found"));
        BeanUtils.copyProperties(event, user);
        userRepository.save(user);
    }
    @EventHandler
   public void on (UserDeletedEvent event) {
       User user = userRepository.findById(event.getUserId()).orElseThrow(()->new NotFoundException("User Not Found"));
       userRepository.delete(user);
    }
}