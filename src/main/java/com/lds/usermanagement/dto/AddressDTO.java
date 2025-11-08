package com.lds.usermanagement.dto;

public record AddressDTO (String cep,
        String street,
        String number,
        String city,
        String state) {
}
