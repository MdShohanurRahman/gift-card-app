/**
 * Created By shoh@n
 * Date: 9/18/2022
 */

package com.example.demo.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiftCardCancelledEvent {

    private String id;
    private Boolean isActive;
}
