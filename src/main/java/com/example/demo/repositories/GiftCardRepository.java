/**
 * Created By shoh@n
 * Date: 9/18/2022
 */

package com.example.demo.repositories;

import com.example.demo.entities.GiftCard;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GiftCardRepository extends JpaRepository<GiftCard, String > {

}
