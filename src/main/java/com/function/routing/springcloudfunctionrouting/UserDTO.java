package com.function.routing.springcloudfunctionrouting;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDTO
{
    private String key;
    private String username;

    @Override
    public String toString()
    {
        return "UserDTO{" +
                "username='" + username + '\'' +
                '}';
    }
}
