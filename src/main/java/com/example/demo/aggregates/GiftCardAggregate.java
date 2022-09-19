/**
 * Created By shoh@n
 * Date: 9/18/2022
 */

package com.example.demo.aggregates;

import com.example.demo.commands.CancelGiftCardCommand;
import com.example.demo.commands.IssueGiftCardCommand;
import com.example.demo.commands.RedeemGiftCardCommand;
import com.example.demo.commands.ReloadGiftCardCommand;
import com.example.demo.events.GiftCardCancelledEvent;
import com.example.demo.events.GiftCardIssuedEvent;
import com.example.demo.events.GiftCardRedeemedEvent;
import com.example.demo.events.GiftCardReloadedEvent;
import com.example.demo.exceptions.ApiException;
import lombok.Getter;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Aggregate
@Getter
public class GiftCardAggregate {

    @AggregateIdentifier
    private String id;
    private BigDecimal balance;
    private Boolean isActive;

    public GiftCardAggregate() {
        // for axon instantiation
    }

    /**
     * Command Handler method to create giftCard object
     */
    @CommandHandler
    public GiftCardAggregate(IssueGiftCardCommand command) {
        // Here we can handle validation logic
        if (command.getIssueAmount().compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new ApiException("amount <= 0");
        }
        var giftCardIssuedEvent = new GiftCardIssuedEvent(command.getId(), command.getIssueAmount());
        // it invokes event source handler method (giftCardIssuedEvent)
        AggregateLifecycle.apply(giftCardIssuedEvent);
    }

    /**
     * Method to store the GiftCardIssuedEvent in the event store
     */
    @EventSourcingHandler
    public void on(GiftCardIssuedEvent event) {
        id = event.getId();
        balance = event.getIssueAmount();
        isActive = true;
    }

    @CommandHandler
    public void handle(RedeemGiftCardCommand command) {
        if (command.getRedeemAmount().compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new IllegalArgumentException("amount <= 0");
        }
        if (command.getRedeemAmount().compareTo(balance) > 0) {
            throw new IllegalStateException("amount > balance");
        }
        var giftCardRedeemedEvent = new GiftCardRedeemedEvent(command.getId(), command.getRedeemAmount());
        AggregateLifecycle.apply(giftCardRedeemedEvent);
    }

    @EventSourcingHandler
    public void on(GiftCardRedeemedEvent event) {
        balance = balance.subtract(event.getRedeemAmount());
    }

    @CommandHandler
    public void handle(ReloadGiftCardCommand command) {
        if (command.getReloadAmount().compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new IllegalArgumentException("amount <= 0");
        }
        var giftCardReloadedEvent = new GiftCardReloadedEvent(command.getId(), command.getReloadAmount());
        AggregateLifecycle.apply(giftCardReloadedEvent);
    }

    @EventSourcingHandler
    public void on(GiftCardReloadedEvent event) {
        balance = balance.add(event.getReloadAmount());
    }

    @CommandHandler
    public void handle(CancelGiftCardCommand command) {
        var giftCardCancelledEvent = new GiftCardCancelledEvent(command.getId(), command.getIsActive());
        if (!giftCardCancelledEvent.getIsActive()) {
            throw new ApiException("GiftCard already canceled");
        }
        AggregateLifecycle.apply(giftCardCancelledEvent);
    }

    @EventSourcingHandler
    public void on(GiftCardCancelledEvent event) {
        isActive = event.getIsActive();
        balance = BigDecimal.valueOf(0);
    }
}
