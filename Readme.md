## Rotas da API

# Cliente (Customer)
- POST /auth/customer/signIn 
  ``{ email: string, password: string }``
  ``{token JWT}``
- POST /auth/customer/signUp ``{ email: string, password: string, name: string, lastname: string, cpf: string }``
- GET /customer
# Cartão de credito (Credit card)
- POST /credit-card ``{token: string, holder: string, flag: string, expirationDate: string}``
- GET /credit-card
# Endereço (Address)
- POST /address ``{publicPlace: string, number: int, complement: string, neighborhood: string, city: string, state: string, country: string, cep: string}``
- GET /address