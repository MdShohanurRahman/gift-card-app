/**
 * Created By shoh@n
 * Date: 9/18/2022
 */

package com.example.demo.services;

import com.example.demo.dtos.GiftCardResDTO;
import com.example.demo.queries.FindAllGiftCardQuery;
import com.example.demo.queries.FindGiftCardByIdQuery;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.messaging.Message;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GiftCardQueryService {

    private final QueryGateway queryGateway;
    private final EventStore eventStore;

    public GiftCardQueryService(QueryGateway queryGateway, EventStore eventStore) {
        this.queryGateway = queryGateway;
        this.eventStore = eventStore;
    }

    public List<GiftCardResDTO> findAll() {
        return queryGateway.query(
                new FindAllGiftCardQuery(),
                ResponseTypes.multipleInstancesOf(GiftCardResDTO.class)
        ).join();
    }

    public GiftCardResDTO findById(String id) {
        return queryGateway.query(
                new FindGiftCardByIdQuery(id),
                ResponseTypes.instanceOf(GiftCardResDTO.class)
        ).join();
    }

    public List<Object> giftCardEvents(String id) {
        return eventStore
                .readEvents(id)
                .asStream()
                .map(Message::getPayload)
                .collect(Collectors.toList());
    }
}
