package com.testepicpay.domain.user;

import java.math.BigDecimal;

public record UserDTO(String firstname, String lastname, String document, UserType type, BigDecimal balance, String email, String password) {
}
