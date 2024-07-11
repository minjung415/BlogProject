package org.example.blogproject.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.blogproject.Service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class UserApiController {
//    private final UserService userService;
//    private final PasswordEncoder passwordEncoder;
//    private final JwtTokenizer jwtTokenizer;
//    private final RefreshTokenService refreshTokenService;

//    @PostMapping("/login")
//    public ResponseEntity login(@RequestBody @Valid UserLoginDto userLoginDto,
//                                BindingResult bindingResult, HttpServletResponse response){
//        log.info("username ::::: " + userLoginDto.getUsername());
//        if(bindingResult.hasErrors()){
//            return new ResponseEntity(HttpStatus.BAD_REQUEST);
//        }
//        log.info("username ::::: " + userLoginDto.getUsername());
//        //존재하는 사용자인지 확인
//        User user = userService.getUser(userLoginDto.getUsername());
//        if(!passwordEncoder.matches(userLoginDto.getPassword(), user.getPassword())){
//            return new ResponseEntity("비밀번호가 올바르지 않습니다.", HttpStatus.UNAUTHORIZED);
//        }
//
//        List<String> roles = user.getRoles().stream().map(Role::getName).collect(Collectors.toList());
//
//        //토큰 발급
//        String accessToken = jwtTokenizer.createAccessToken(
//                user.getId(), user.getEmail(), user.getName(), user.getUsername(), roles);
//        String refreshToken = jwtTokenizer.createRefreshToken(
//                user.getId(), user.getEmail(), user.getName(), user.getUsername(), roles);
//
//        //refreshToken을 DB에 저장
//        RefreshToken refreshTokenEntity = new RefreshToken();
//        refreshTokenEntity.setValue(refreshToken);
//        refreshTokenEntity.setUserId(user.getId());
//        refreshTokenService.addRefreshToken(refreshTokenEntity);
//
//        //Token을 담은 쿠키를 응답에 포함시켜 클라이언트에게 반환
//        UserLoginResponseDto loginResponseDto = UserLoginResponseDto.builder()
//                .accessToken(accessToken)
//                .refreshToken(refreshToken)
//                .userId(user.getId())
//                .name(user.getName())
//                .build();
//
//        Cookie accessTokenCookie = new Cookie("accessToken",accessToken);
//        accessTokenCookie.setHttpOnly(true);  //보안 (쿠키값을 자바스크립트같은곳에서는 접근할수 없어요.)
//        accessTokenCookie.setPath("/minlog");
//        accessTokenCookie.setMaxAge(Math.toIntExact(JwtTokenizer.ACCESS_TOKEN_EXPIRE_COUNT/1000)); //30분 쿠키의 유지시간 단위는 초 ,  JWT의 시간단위는 밀리세컨드
//
//        Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
//        refreshTokenCookie.setHttpOnly(true);
//        refreshTokenCookie.setPath("/minlog");
//        refreshTokenCookie.setMaxAge(Math.toIntExact(JwtTokenizer.REFRESH_TOKEN_EXPIRE_COUNT/1000)); //7일
//
//        response.addCookie(accessTokenCookie);
//        response.addCookie(refreshTokenCookie);
//
//        return new ResponseEntity(loginResponseDto, HttpStatus.OK);
//    }
}
