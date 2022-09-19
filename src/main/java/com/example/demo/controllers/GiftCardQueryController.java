/**
 * Created By shoh@n
 * Date: 9/18/2022
 */

package com.example.demo.controllers;

import com.example.demo.dtos.GiftCardResDTO;
import com.example.demo.services.GiftCardQueryService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/gift_cards")
public class GiftCardQueryController {

    private final GiftCardQueryService queryService;

    public GiftCardQueryController(GiftCardQueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping()
    public List<GiftCardResDTO> findAll() {
        return queryService.findAll();
    }

    @GetMapping("/{id}")
    public GiftCardResDTO findById(@PathVariable String id) {
        return queryService.findById(id);
    }

    @GetMapping("/{id}/events")
    public List<Object> giftCardEvents(@PathVariable String id) {
        return queryService.giftCardEvents(id);
    }
}
