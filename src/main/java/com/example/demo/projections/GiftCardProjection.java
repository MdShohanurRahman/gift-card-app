/**
 * Created By shoh@n
 * Date: 9/18/2022
 */

package com.example.demo.projections;

import com.example.demo.dtos.GiftCardResDTO;
import com.example.demo.entities.GiftCard;
import com.example.demo.events.GiftCardCancelledEvent;
import com.example.demo.events.GiftCardIssuedEvent;
import com.example.demo.events.GiftCardRedeemedEvent;
import com.example.demo.events.GiftCardReloadedEvent;
import com.example.demo.exceptions.ApiException;
import com.example.demo.exceptions.GiftCardNotFoundException;
import com.example.demo.queries.FindAllGiftCardQuery;
import com.example.demo.queries.FindGiftCardByIdQuery;
import com.example.demo.repositories.GiftCardRepository;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@Component
@ProcessingGroup("GiftCard")
public class GiftCardProjection extends ProjectionBase {

    private final GiftCardRepository giftCardRepository;

    public GiftCardProjection(GiftCardRepository giftCardRepository) {
        this.giftCardRepository = giftCardRepository;
    }

    @EventHandler
    public void on(GiftCardIssuedEvent event) {
        log.debug("Handling issued giftCard command {}", event.getId());
        GiftCard giftCard = new GiftCard();
        giftCard.setId(event.getId());
        giftCard.setBalance(event.getIssueAmount());
        giftCard.setIsActive(true);
        giftCardRepository.save(giftCard);
    }

    @EventHandler
    public void on(GiftCardRedeemedEvent event) {
        log.debug("Handling redeem giftCard command {}", event.getId());
        Optional<GiftCard> optionalGiftCard = giftCardRepository.findById(event.getId());
        if (optionalGiftCard.isEmpty()) {
            throw new GiftCardNotFoundException(event.getId());
        }
        GiftCard giftCard = optionalGiftCard.get();
        giftCard.setBalance(giftCard.getBalance().subtract(event.getRedeemAmount()));
        giftCardRepository.save(giftCard);
    }

    @EventHandler
    public void on(GiftCardReloadedEvent event) {
        log.debug("Handling reload giftCard command {}", event.getId());
        Optional<GiftCard> optionalGiftCard = giftCardRepository.findById(event.getId());
        if (optionalGiftCard.isEmpty()) {
            throw new GiftCardNotFoundException(event.getId());
        }
        GiftCard giftCard = optionalGiftCard.get();
        giftCard.setBalance(giftCard.getBalance().add(event.getReloadAmount()));
        giftCardRepository.save(giftCard);
    }

    @EventHandler
    public void on(GiftCardCancelledEvent event) {
        log.debug("Handling cancel giftCard command {}", event.getId());
        Optional<GiftCard> optionalGiftCard = giftCardRepository.findById(event.getId());
        if (optionalGiftCard.isEmpty()) {
            throw new GiftCardNotFoundException(event.getId());
        }
        GiftCard giftCard = giftCardRepository.findById(event.getId()).get();
        giftCard.setIsActive(false);
        giftCard.setBalance(BigDecimal.valueOf(0));
        giftCardRepository.save(giftCard);
    }

    @QueryHandler
    public List<GiftCardResDTO> handle(FindAllGiftCardQuery query) {
        log.debug("Handling giftCard list query: {}", query);
        List<GiftCard> giftCards = giftCardRepository.findAll();
        return giftCards.stream().map(card -> {
            GiftCardResDTO giftCard = new GiftCardResDTO();
            giftCard.setId(card.getId());
            giftCard.setBalance(card.getBalance());
            giftCard.setIsActive(card.getIsActive());
            return giftCard;
        }).collect(Collectors.toList());
    }

    @QueryHandler
    public GiftCardResDTO handle(FindGiftCardByIdQuery query) {
        log.debug("Handling find giftCard query: {}", query);
        Optional<GiftCard> optionalGiftCard = giftCardRepository.findById(query.getId());
        if (optionalGiftCard.isEmpty()) {
            throw new GiftCardNotFoundException(query.getId());
        }
        GiftCard giftCard = optionalGiftCard.get();
        GiftCardResDTO resDTO = new GiftCardResDTO();
        BeanUtils.copyProperties(giftCard, resDTO);
        return resDTO;
    }

}
