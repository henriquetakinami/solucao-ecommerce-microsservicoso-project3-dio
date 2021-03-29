package com.hatanaka.ecommerce.checkout.service;

import com.hatanaka.ecommerce.checkout.entity.CheckoutEntity;
import com.hatanaka.ecommerce.checkout.repository.CheckoutRepository;
//import com.sun.el.stream.Optional;
import com.hatanaka.ecommerce.checkout.streaming.CheckoutCreatedSource;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;
import com.hatanaka.ecommerce.checkout.resource.CheckoutRequest;

import java.util.UUID;
import java.util.Optional;


@Service // criar instancia do nosso serivço
@RequiredArgsConstructor // cria construtor para todos os final

public class CheckoutServiceImpl implements CheckoutService{

    //@Autowired // injetar dependencia, mas não é uma boa prática (estar no atributo da classe) por conta de teste unitário --> fazer via construtor
    private final CheckoutRepository checkoutRepository; // injetei repository
    private final CheckoutCreatedSource checkoutCreatedSource; // injetando

    @Override
    public Optional<CheckoutEntity> create(CheckoutRequest checkoutRequest) {
        final CheckoutEntity checkoutEntity = CheckoutEntity.builder()
                .code(UUID.randomUUID().toString())
                .status(CheckoutEntity.Status.CREATED)
                .build(); // pelo builder do entity

        final CheckoutEntity entity = checkoutRepository.save(checkoutEntity);

        //publicar dado no kafka
        final CheckoutCreatedEvent checkoutCreatedEvent = CheckoutCreatedEvent.newBuilder().build()
                .setCheckoutCode(entity.getCode())
                .setStatus(entity.getStatus().name())
                .build();
        checkoutCreatedSource.output().send(MessageBuilder.withPayload(checkoutCreatedEvent).build());

        return Optional.of(entity);
    }
}
