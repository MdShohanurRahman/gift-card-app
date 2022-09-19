/**
 * Created By shoh@n
 * Date: 9/18/2022
 */

package com.example.demo.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftCardIssuedEvent {

    private String id;
    private BigDecimal issueAmount;
}
