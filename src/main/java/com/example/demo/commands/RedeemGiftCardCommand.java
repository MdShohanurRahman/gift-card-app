/**
 * Created By shoh@n
 * Date: 9/18/2022
 */

package com.example.demo.commands;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
public class RedeemGiftCardCommand {

    @TargetAggregateIdentifier
    private String id;
    private BigDecimal redeemAmount;
}
