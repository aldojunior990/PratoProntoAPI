package com.pratopronto.prato_pronto_api.controllers.authentication;

import com.pratopronto.prato_pronto_api.usecases.consumerSignUp.ConsumerSignUpInput;
import com.pratopronto.prato_pronto_api.usecases.consumerSignUp.ConsumerSignUpOutput;
import com.pratopronto.prato_pronto_api.usecases.consumerSignUp.ConsumerSignUpUseCase;
import com.pratopronto.prato_pronto_api.usecases.restaurantSignUp.RestaurantSignUpInput;
import com.pratopronto.prato_pronto_api.usecases.restaurantSignUp.RestaurantSignUpOutput;
import com.pratopronto.prato_pronto_api.usecases.restaurantSignUp.RestaurantSignUpUseCase;
import com.pratopronto.prato_pronto_api.usecases.signin.SignInUseCase;
import com.pratopronto.prato_pronto_api.usecases.signin.SignInInputDto;
import com.pratopronto.prato_pronto_api.usecases.signin.SignInOutputDto;
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
    RestaurantSignUpUseCase restaurantSignUpUseCase;

    @PostMapping("/signIn")
    public ResponseEntity<SignInOutputDto> signIn(@RequestBody SignInInputDto data) {
        return signInUseCase.execute(data);
    }

    @PostMapping("/signUp/consumer")
    public ResponseEntity<ConsumerSignUpOutput> consumerSignUp(@RequestBody ConsumerSignUpInput data) {
        return consumerSignUpUseCase.execute(data);
    }

    @PostMapping("/signUp/restaurant")
    public ResponseEntity<RestaurantSignUpOutput> restaurantSignUp(@RequestBody RestaurantSignUpInput data) {
        return restaurantSignUpUseCase.execute(data);
    }
}
