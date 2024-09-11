package com.pratopronto.prato_pronto_api.controllers.authentication;

import com.pratopronto.prato_pronto_api.usecases.consumerSignUp.ConsumerSignUpInput;
import com.pratopronto.prato_pronto_api.usecases.consumerSignUp.ConsumerSignUpOutput;
import com.pratopronto.prato_pronto_api.usecases.consumerSignUp.ConsumerSignUpUseCase;
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

    @PostMapping("/signIn")
    public ResponseEntity<SignInOutputDto> signIn(@RequestBody SignInInputDto data) {
        return signInUseCase.execute(data);
    }

    @PostMapping("/signUp/consumer")
    public ResponseEntity<ConsumerSignUpOutput> signUpConsumer(@RequestBody ConsumerSignUpInput data) {

        System.out.println(data.toString());

        return consumerSignUpUseCase.execute(data);
    }
}
