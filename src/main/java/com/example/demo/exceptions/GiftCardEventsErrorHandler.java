/**
 * Created By shoh@n
 * Date: 9/12/2022
 */

package com.example.demo.exceptions;

import org.axonframework.eventhandling.EventMessage;
import org.axonframework.eventhandling.EventMessageHandler;
import org.axonframework.eventhandling.ListenerInvocationErrorHandler;

public class GiftCardEventsErrorHandler implements ListenerInvocationErrorHandler {

    @Override
    public void onError(Exception e, EventMessage<?> eventMessage, EventMessageHandler eventMessageHandler) throws Exception {
        throw e;
    }
}
