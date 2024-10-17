package com.pratopronto.prato_pronto_api.controllers.authentication;

import com.pratopronto.prato_pronto_api.usecases.consumer.ConsumerSignUpUseCase;
import com.pratopronto.prato_pronto_api.usecases.consumer.dtos.ConsumerSignUpDTO;
import com.pratopronto.prato_pronto_api.usecases.restaurant.RestaurantSignUp;
import com.pratopronto.prato_pronto_api.usecases.restaurant.dtos.SignUpRestaurantDTO;
import com.pratopronto.prato_pronto_api.usecases.signin.SignInInputDto;
import com.pratopronto.prato_pronto_api.usecases.signin.SignInOutputDto;
import com.pratopronto.prato_pronto_api.usecases.signin.SignInUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private SignInUseCase signInUseCase;

    @Autowired
    private ConsumerSignUpUseCase consumerSignUpUseCase;

    @Autowired
    RestaurantSignUp restaurantSignUp;

    @PostMapping("/signIn")
    public ResponseEntity<SignInOutputDto> signIn(@RequestBody SignInInputDto data) {
        return signInUseCase.execute(data);
    }

    @PostMapping("/signUp/consumer")
    public ResponseEntity<String> consumerSignUp(@RequestBody ConsumerSignUpDTO data) {
        return consumerSignUpUseCase.execute(data);
    }

    @PostMapping("/signUp/restaurant")
    public ResponseEntity<String> restaurantSignUp(@RequestBody SignUpRestaurantDTO data) {
        return restaurantSignUp.execute(data);
    }

}
