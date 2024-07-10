package org.example.blogproject.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserLoginResponseDto {
    private String accessToken;
    private String refreshToken;
    private Long userId;
    private String name;
}
