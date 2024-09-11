package com.pratopronto.prato_pronto_api.usecases;

public interface UseCaseContract<Input, Output> {

    Output execute(Input input);
}
