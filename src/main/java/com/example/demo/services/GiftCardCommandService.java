/**
 * Created By shoh@n
 * Date: 9/18/2022
 */

package com.example.demo.services;

import com.example.demo.commands.CancelGiftCardCommand;
import com.example.demo.commands.IssueGiftCardCommand;
import com.example.demo.commands.RedeemGiftCardCommand;
import com.example.demo.commands.ReloadGiftCardCommand;
import com.example.demo.dtos.GiftCardIssueDTO;
import com.example.demo.dtos.GiftCardRedeemDTO;
import com.example.demo.dtos.GiftCardReloadDTO;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@Service
public class GiftCardCommandService {

    private final CommandGateway commandGateway;

    public GiftCardCommandService(CommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    public CompletableFuture<String> issueGiftCard(GiftCardIssueDTO dto) {
        var command = new IssueGiftCardCommand();
        command.setId(UUID.randomUUID().toString());
        command.setIssueAmount(dto.getIssueAmount());
        return commandGateway.send(command);
    }

    public CompletableFuture<String> reloadAmount(String id, GiftCardReloadDTO dto) {
        var command = new ReloadGiftCardCommand();
        command.setId(id);
        command.setReloadAmount(dto.getReloadAmount());
        return commandGateway.send(command);
    }

    public CompletableFuture<String> redeemAmount(String id, GiftCardRedeemDTO dto) {
        var command = new RedeemGiftCardCommand();
        command.setId(id);
        command.setRedeemAmount(dto.getRedeemAmount());
        return commandGateway.send(command);
    }

    public CompletableFuture<String> cancelGiftCard(String id) {
        var command = new CancelGiftCardCommand();
        command.setId(id);
        command.setIsActive(false);
        return commandGateway.send(command);
    }
}
