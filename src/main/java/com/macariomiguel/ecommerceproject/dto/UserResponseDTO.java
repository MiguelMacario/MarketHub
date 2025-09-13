package com.macariomiguel.ecommerceproject.dto;

import com.macariomiguel.ecommerceproject.entity.User;

public record UserResponseDTO(String login){

    public UserResponseDTO(User user){
        this(user.getLogin());
    }
}
