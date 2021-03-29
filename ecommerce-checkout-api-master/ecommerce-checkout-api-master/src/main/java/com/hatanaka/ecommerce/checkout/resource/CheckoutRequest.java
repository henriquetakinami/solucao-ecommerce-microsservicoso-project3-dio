package com.hatanaka.ecommerce.checkout.resource;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data // não preciso de getters and setters
@AllArgsConstructor // criar construtor no tempo de compilação para todos os argumentos
@NoArgsConstructor // caso não tenha
public class CheckoutRequest implements Serializable { // falar para o java que precisa ser serializado

    private String firstName;
    private String lastName;
    private String email;
    private String address;
    private String complement;
    private String country;
    private String state;
    private String cep;
    private Boolean saveAddress;
    private Boolean saveInfo;
    private String paymentMethod;
    private String cardName;
    private String cardNumber;
    private String cardDate;
    private String cardCvv;

}
