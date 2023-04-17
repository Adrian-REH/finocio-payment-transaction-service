# finocio-payment-transaction-service
 Desarrollar un servicio web que permita la creación y consulta de transacciones de pago.

## Requerimientos:

1. Utilizar Spring Boot para desarrollar el servicio web.
2. La base de datos debe ser en memoria (H2).
3. El código debe estar versionado en GitHub, haciendo commit para cada una de las iteraciones.
4. Se debe utilizar un patrón de diseño por capas, ya sea MVC o arquitectura hexagonal.
5. El nombre del proyecto será finocio-payment-transaction-service.


## Estructura de la aplicación
```
├── config
│   └── SwaggerConfig.java
├── controllers
│   └── PaymentController.java
├── dtos
│   └── PaymentRequest.java
├── entities
│   └── Payment.java
├── exceptions
│   ├── PaymentRequqestAmountCeroException.java
│   ├── PaymentRequqestAmountNullException.java
│   ├── PaymentRequqestUserIdBlankException.java
│   └── PaymentRequqestUserIdNullException.java
├── repositories
│   └── PaymentRepository.java
├── services
│   ├── Impl
│   │    └── PaymentBuilderImpl.java
│   ├── PaymentService.java
│   └── PaymentBuilder.java
── test
    ├── controllers
    │   └── PaymentControllerTest.java 
    ├── repositories
    │   └── PaymentRepositoryDataJpaTest.java
    └── services
        └── PaymentServiceTest.java

```

- `config`: contiene la clase `SwaggerConfig` para la configuración de la documentación de la API utilizando la herramienta Swagger.
- `controllers`: contiene el controlador `PaymentController` que maneja las solicitudes de la API relacionadas con el pago.
- `dtos`: contiene el objeto de transferencia de datos `PaymentRequest` para representar la solicitud de pago.
- `entities`: contiene la entidad `Payment` para representar los detalles de un pago.
- `exceptions`: contiene excepciones personalizadas que se lanzan en caso de que ocurra algún error en el proceso de pago.
- `repositories`: contiene el repositorio `PaymentRepository` que implementa la lógica para almacenar y recuperar los detalles del pago en una base de datos H2.
- `services`: contiene el servicio `PaymentService`y `PaymentBuilder` con su implementación `PaymentBuilderImpl`, que se encarga de la lógica de negocio relacionada con el pago.
- `test`: contiene pruebas unitarias para los controladores, repositorios y servicios.



## Funcionalidad:

### El método POST / 

Crea una transacción de pago deberá solicitar un objeto PaymentRequest con las siguientes propiedades:

- amount: de tipo double.
- userId: de tipo string.
   
El método devuelve el objeto Payment en formato JSON con los siguientes campos:

- **_paymentId_**: generado automáticamente por la base de datos.
- **_date_**: fecha de creación de la transacción.
- **_amount_**: cantidad de la transacción.
- **_userId_**: identificador del usuario que realizó la transacción.
- **_cardLast4number_**: número de los últimos 4 dígitos de la tarjeta utilizada para el pago, generado de manera aleatoria.
- **_authNumber_**: número de autorización del pago, generado de manera aleatoria.
- **_bank_**: nombre del banco que procesó el pago, generado de manera aleatoria, dentro de una lista de bancos Ej. [BBVA, Santander, CaixaBank] etc.
- **_isContactless_**: tipo booleano que indica si el pago fue realizado de manera contactless o no, generado de manera aleatoria.
- **_status_**: PAYMENT_SUCCESS, indicando que la transacción fue exitosa.

### El método GET: /paymentId 
 - Devuelve la transacción correspondiente al identificador pasado como parámetro. 
 - La transacción tiene los mismos campos que el método POST.

### El método GET / 
 - Devuelve una lista de todos los Payment creados.



## TEST UNITARIO

### Test Api Rest with Spring
Escenarios de prueba y definicion de entrada para la API 
```java
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestPropertySource(locations = "/application-test.properties")
public class PaymentControllerTest {
    private final Payment PAYMENT_1 = new Payment(1L, "03-03-2023 03:09", 665.73, "2E", 8508, 441, "BBVA", true, "PAYMENT_SUCCESS");
    private final Payment PAYMENT_2 = new Payment(2L, "05-03-2023 03:09", 54.24, "6A", 6375, 441, "Santander", true, "PAYMENT_SUCCESS");
    private final Payment PAYMENT_3 = new Payment(3L, "02-03-2023 03:09", 6.13, "4D", 1545, 441, "CaixaBank", false, "PAYMENT_SUCCESS");

    private final List<Payment> paymentList = new ArrayList<Payment>(Arrays.asList(PAYMENT_1, PAYMENT_2, PAYMENT_3));


    private TestRestTemplate testRestTemplate;
    private final HttpHeaders headers = new HttpHeaders();

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;

    @LocalServerPort
    private int port;

    @BeforeEach
    public void setUp() {
        restTemplateBuilder = restTemplateBuilder.rootUri("http://localhost:" + port);
        testRestTemplate = new TestRestTemplate(restTemplateBuilder);

        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

    }
    
    
    
    @Test
    public void createNewTransactionTestFailAmountNegative();

    @Test
    public void createNewTransactionTestFailAmountIlimit() ;
    @Test
    public void createNewTransactionTestFailAmountNull() ;

    @Test
    public void createNewTransactionTestFailAmounCero() ;

    @Test
    public void createNewTransactionTestFailUserIdNull() ;

    @Test
    public void createNewTransactionTestFailUserIdBlank() ;

    @Test
    public void createNewDuplicateTransactionTest() ;

    @Test
    public void createNewTransactionTest() ;
    
    @Test
    public void findAllPaymentsTest() ;

    @Test
    public void findByIdPaymentsTest() ;

    private boolean containBankList(String bank) ;
    
    private int cuentaCifras(int num) ;

    @Test
    public void findByIdPaymentsTestFailNotFound() ;


}

```

### Test Repository JPA with @DataJpaTest
Escenarios de prueba y definicion de entrada para la gestion de BD

```java
@DataJpaTest
@TestPropertySource(locations = "/application-test.properties")
public class PaymentRepositoryDataJpaTest {

    private Payment PAYMENT_NEW =  new Payment(null,"14-03-2023 03:09" ,845.73 ,"5E", 5318,885,"BBVA",false,"PAYMENT_SUCCESS");
    private Payment PAYMENT_1 =  new Payment(1l,"03-03-2023 03:09",665.73 ,"2E", 8508,441,"BBVA",true,"PAYMENT_SUCCESS");
    private Payment PAYMENT_2 =  new Payment(2l,"05-03-2023 03:09",54.24, "6A", 6375,441,"Santander",true,"PAYMENT_SUCCESS");
    private Payment PAYMENT_3 =  new Payment(3l,"02-03-2023 03:09",6.13, "4D", 1545,441,"CaixaBank",false,"PAYMENT_SUCCESS");

    private final List<Payment> paymentList= new  ArrayList<Payment>(Arrays.asList(PAYMENT_1, PAYMENT_2,PAYMENT_3));

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PaymentRepository paymentRepository;

    @Test
    public void whenSavePayment_thenReturnPayment(){}

    @Test
    public void whenFindOnePayment_thenReturnPayment(){}

    @Test
    public void whenFinAllPayment_thenReturnPaymentList(){}

}
```

### Test Functions Junit4
Escenarios de prueba y definicion de entrada para construir un Payment
```java
@RunWith(Parameterized.class)
public class PaymentBuilderTest {

    public PaymentBuilder paymentBuilder;

    public PaymentBuilderTest(PaymentBuilder paymentBuilder) {
        this.paymentBuilder = paymentBuilder;
    }

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void testGeneratePaymentWithSuccess() {}

    @Test
    public void testGeneratePaymentWithFailAmountNegative() {}

    @Test
    public void testGeneratePaymentWithFailAmountIllimit() {}

    @Test
    public void testGeneratePaymentWithFailAmountNull() {}

    @Test
    public void testGeneratePaymentWithFailAmountCero() {}

    @Test
    public void testGeneratePaymentWithFailUserIdNull() {}

    @Test
    public void testGeneratePaymentWithFailUserIdBlank() {}

    @Parameterized.Parameters
    public static Collection<Object[]> instancesToTest() {
        return Arrays.asList(
                new Object[]{new PaymentBuilderImpl()},
                new Object[]{new PaymentBuilderImpl()}
        );
    }
}
```


## CONFIG PROYECT

* java 17
* Spring 2.5.5
* Spring Web
* Spring boot devtools
* Spring Data JPA
* H2
* Swagger (manual)
```xml
<dependency>
    <groupId>io.springfox</groupId>
    <artifactId>springfox-boot-starter</artifactId>
    <version>3.0.0</version>
</dependency>
```

* application.properties (H2 )
```
#Preparo e Inicializo H2
spring.jpa.show-sql=true
spring.datasource.url=jdbc:h2:file:C:/data/sample
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.datasource.driverClassName=org.h2.Driver
spring.jpa.hibernate.ddl-auto=update
spring.sql.init.mode=always
spring.jpa.defer-datasource-initialization=true
```


* Data.sql (H2)
```sql
insert into op_payment(date, amount, user_id,card_last4number,auth_number,bank,is_contractless,status) VALUES ('03-03-2023 03:09',665.73 ,'2E', 8508,441,'BBVA',true,'PAYMENT_SUCCESS');
insert into op_payment(date, amount, user_id,card_last4number,auth_number,bank,is_contractless,status) VALUES ('05-03-2023 03:09',54.24, '6A', 6375,441,'Santander',true,'PAYMENT_SUCCESS');
insert into op_payment(date, amount, user_id,card_last4number,auth_number,bank,is_contractless,status) VALUES ('02-03-2023 03:09',6.13, '4D', 1545,441,'CaixaBank',false,'PAYMENT_SUCCESS');
```

## Consideraciones:

- El servicio deberá retornar respuestas en formato JSON.
- El servicio deberá contener documentación de la API con Swagger o OpenAPI
- Se valorará la claridad y organización del código.
- Se valorará la implementación de pruebas unitarias.


# Licencia

```xml
Designed and developed by 2023 adrian-reh (Adrian Ramon Elias Herrera)

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
