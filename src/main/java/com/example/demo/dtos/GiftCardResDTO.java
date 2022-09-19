/**
 * Created By shoh@n
 * Date: 9/18/2022
 */

package com.example.demo.dtos;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class GiftCardResDTO {

    private String id;
    private BigDecimal balance;
    private Boolean isActive;
}
