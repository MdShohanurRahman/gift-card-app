/**
 * Created By shoh@n
 * Date: 9/19/2022
 */

package com.example.demo.configs;

import com.example.demo.exceptions.GiftCardEventsErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AxonConfigs {

    @Autowired
    public void configure(EventProcessingConfigurer configurer) {
        configurer.registerListenerInvocationErrorHandler(
                "GiftCard",
                configuration -> new GiftCardEventsErrorHandler()
        );
    }

}
