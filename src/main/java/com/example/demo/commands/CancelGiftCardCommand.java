/**
 * Created By shoh@n
 * Date: 9/18/2022
 */

package com.example.demo.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

@Data
public class CancelGiftCardCommand {

    @TargetAggregateIdentifier
    private String id;
    private Boolean isActive;
}
