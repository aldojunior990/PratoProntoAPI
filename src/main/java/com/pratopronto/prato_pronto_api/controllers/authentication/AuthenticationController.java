package com.pratopronto.prato_pronto_api.controllers.authentication;

import com.pratopronto.prato_pronto_api.domain.customer.CustomerDetails;
import com.pratopronto.prato_pronto_api.usecases.consumer.ConsumerSignUpUseCase;
import com.pratopronto.prato_pronto_api.usecases.consumer.dtos.ConsumerSignUpDTO;
import com.pratopronto.prato_pronto_api.usecases.customer.GetCustomerDetails;
import com.pratopronto.prato_pronto_api.usecases.restaurant.RestaurantSignUp;
import com.pratopronto.prato_pronto_api.usecases.restaurant.dtos.SignUpRestaurantDTO;
import com.pratopronto.prato_pronto_api.usecases.signin.SignInInputDto;
import com.pratopronto.prato_pronto_api.usecases.signin.SignInOutputDto;
import com.pratopronto.prato_pronto_api.usecases.signin.SignInUseCase;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthenticationController {

    @Autowired
    private SignInUseCase signInUseCase;

    @Autowired
    private ConsumerSignUpUseCase consumerSignUpUseCase;

    @Autowired
    private GetCustomerDetails getCustomerDetails;

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

    @GetMapping
    public ResponseEntity<CustomerDetails> getCustomerDetails(HttpServletRequest data) {
        return getCustomerDetails.execute(data);
    }

}
