/**
 * Created By shoh@n
 * Date: 9/18/2022
 */

package com.example.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class GiftCard {

    @Id
    private String id;
    private BigDecimal balance;
    private Boolean isActive;
}
