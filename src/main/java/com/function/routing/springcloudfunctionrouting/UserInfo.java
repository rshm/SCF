package com.function.routing.springcloudfunctionrouting;

import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Component;

/**
 * @author rashmivarma
 */
@Slf4j
@Component("userInfo")
public class UserInfo implements Function<Message<UserDTO>, Message<UserInfoDTO>>
{
    private final Logger logger = LoggerFactory.getLogger(UserInfo.class);

    @Override
    public Message<UserInfoDTO> apply(Message<UserDTO> message)
    {
        String destination ="userAggregated";
        logger.info(message.getPayload().toString());
        return createMessage( message.getPayload(), destination);
    }

    private Message<UserInfoDTO> createMessage(UserDTO userDTO, String destination) {
       UserInfoDTO userInfoDTO =  UserInfoDTO.builder().username(userDTO.getUsername())
           .name("John").age(37).build();

        return MessageBuilder
            .withPayload(userInfoDTO)
            .setHeader(KafkaHeaders.MESSAGE_KEY, userDTO.getKey().getBytes())
            .setHeader("spring.cloud.stream.sendto.destination", destination)
            .build();
    }
}
