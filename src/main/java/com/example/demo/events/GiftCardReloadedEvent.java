/**
 * Created By shoh@n
 * Date: 9/18/2022
 */

package com.example.demo.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftCardReloadedEvent {

    private String id;
    private BigDecimal reloadAmount;
}
