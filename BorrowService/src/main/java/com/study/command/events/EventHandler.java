package com.study.command.events;

import com.study.EventDrivent.BorrowEvent;
import com.study.command.data.Borrow;
import com.study.command.data.BorrowReporitory;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class EventHandler {

    private final BorrowReporitory borrowReporitory;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    @org.axonframework.eventhandling.EventHandler
    public void on (BorrowCreatedEvent event){
        Borrow borrow = new Borrow();
        BeanUtils.copyProperties(event,borrow);
        borrowReporitory.save(borrow);
        //publish event to toppic
        BorrowEvent pubEvent = new BorrowEvent("Borrow", borrow.getBookId());
        kafkaTemplate.send("borrow-topic", pubEvent);
    }
}
