package com.study.EmployeeService.command.aggregate;

import com.study.EmployeeService.command.commands.CreateUserCommand;
import com.study.EmployeeService.command.commands.DeleteUserCommand;
import com.study.EmployeeService.command.commands.UpdateUserCommand;
import com.study.EmployeeService.command.events.UserCreatedEvent;
import com.study.EmployeeService.command.events.UserDeletedEvent;
import com.study.EmployeeService.command.events.UserUpdatedEvent;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

@Aggregate
public class UserAggregate {
    @AggregateIdentifier
    private String userId;
    private String firstName;
    private String lastName;
    private String kin;
    private boolean isDiscliped;

    @CommandHandler
    public UserAggregate(CreateUserCommand createUserCommand) {
        UserCreatedEvent userCreatedEvent = new UserCreatedEvent();
        BeanUtils.copyProperties(createUserCommand, userCreatedEvent);
        AggregateLifecycle.apply(userCreatedEvent);

    }
    @EventSourcingHandler
    public void on (UserCreatedEvent userCreatedEvent){
        this.userId = userCreatedEvent.getUserId();
        this.firstName= userCreatedEvent.getFirstName();
        this.lastName= userCreatedEvent.getLastName();
        this.kin= userCreatedEvent.getKin();
        this.isDiscliped= userCreatedEvent.isDiscliped();
    }

    @CommandHandler
    public void handle(UpdateUserCommand updateUserCommand){
        UserUpdatedEvent userUpdatedEvent = new UserUpdatedEvent();
        BeanUtils.copyProperties(updateUserCommand, userUpdatedEvent);
        AggregateLifecycle.apply(userUpdatedEvent);
    }

    @EventSourcingHandler
    public void on (UserUpdatedEvent userUpdatedEvent){
        this.userId = userUpdatedEvent.getUserId();
        this.firstName= userUpdatedEvent.getFirstName();
        this.lastName= userUpdatedEvent.getLastName();
        this.kin= userUpdatedEvent.getKin();
        this.isDiscliped= userUpdatedEvent.isDiscliped();
    }

    @CommandHandler
    public void handle(DeleteUserCommand deleteUserCommand){
        UserDeletedEvent userDeletedEvent = new UserDeletedEvent();
        BeanUtils.copyProperties(deleteUserCommand, userDeletedEvent);
        AggregateLifecycle.apply(userDeletedEvent);
    }

    @EventSourcingHandler
    public void on (UserDeletedEvent userDeletedEvent){
        this.userId = userDeletedEvent.getUserId();
    }

    public UserAggregate() {
    }
}
