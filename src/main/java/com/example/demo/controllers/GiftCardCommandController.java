/**
 * Created By shoh@n
 * Date: 9/18/2022
 */

package com.example.demo.controllers;

import com.example.demo.dtos.GiftCardIssueDTO;
import com.example.demo.dtos.GiftCardRedeemDTO;
import com.example.demo.dtos.GiftCardReloadDTO;
import com.example.demo.services.GiftCardCommandService;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api/gift_cards")
public class GiftCardCommandController {

    private final GiftCardCommandService commandService;

    public GiftCardCommandController(GiftCardCommandService commandService) {
        this.commandService = commandService;
    }

    @PostMapping()
    public CompletableFuture<String> issueGiftCard(@RequestBody GiftCardIssueDTO dto) {
        return commandService.issueGiftCard(dto);
    }

    @PutMapping("/reload/{id}")
    public CompletableFuture<String> reloadGiftCard(@PathVariable String id, @RequestBody GiftCardReloadDTO dto) {
        return commandService.reloadAmount(id, dto);
    }

    @PutMapping("/redeem/{id}")
    public CompletableFuture<String> redeemGiftCard(@PathVariable String id, @RequestBody GiftCardRedeemDTO dto) {
        return commandService.redeemAmount(id, dto);
    }

    @PostMapping("/cancel/{id}")
    public CompletableFuture<String> cancelGiftCard(@PathVariable String id) {
        return commandService.cancelGiftCard(id);
    }
}
