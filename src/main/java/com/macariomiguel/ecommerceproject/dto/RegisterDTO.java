package com.macariomiguel.ecommerceproject.dto;

import com.macariomiguel.ecommerceproject.entity.enums.Role;

public record RegisterDTO(String login, String password, Role role) {
}
