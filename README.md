# finocio-payment-transaction-service
 Desarrollar un servicio web que permita la creación y consulta de transacciones de pago.

## Requerimientos:

1. Utilizar Spring Boot para desarrollar el servicio web.
2. La base de datos debe ser en memoria (H2).
3. El código debe estar versionado en GitHub, haciendo commit para cada una de las iteraciones.
4. Se debe utilizar un patrón de diseño por capas, ya sea MVC o arquitectura hexagonal.
5. El nombre del proyecto será finocio-payment-transaction-service.


## ARCHITECTURE MVC
```
├── controllers
│   └── PaymentController.java
├── dtos
│   └── PaymentRequest.java
├── entities
│   └── Payment.java
├── repositories
│   └── PaymentH2Repository.java
├── services
│   ├── Impl
│   │    └── PaymentServiceImpl.java
│   ├── PaymentH2Service.java
│   └── PaymentService.java
├── config
│   └── SwaggerConfig.java
── test
    ├── repositories
    │   ├── PaymentRepositoryTest.java
    │   └── PaymentH2RepositoryDataJpaTest.java 
    ├── controllers
    │   └── PaymentControllerTest.java
    └── services
        └── PaymentServiceTest.java

```
Utilizo **_PaymentH2Service.java_** para gestionar los datos de la transaccion en H2, antes haciendo uso 
de **_PaymentService_** para completar la transaccion siempre y cuando se cumplan ciertos requerimientos.
**_PaymentController.java_** es donde se alojaran las funciones para la REST documentandolo con Swagger por cierto
configuro Swagger on **_SwaggerConfig.java_**

Creo **_PaymentRequest_** como Object data transfer para las Request haciendo un buen uso de JPA.

Para mejorar la calidad de los datos a gestionar en H2 voy a testear utilizando **_@DataJpaTest_** y para
la API REST voy a usar **_TestRestTemplate_** de Spring, haciendo uso de application-test.properties para no modificar
la base de datos original H2 al Testear.



## Funcionalidad:

## El método POST / para crear una transacción de pago deberá solicitar un objeto PaymentRequest con las siguientes propiedades:

- amount: de tipo double.
- userId: de tipo string.
   
El método deberá devolver el objeto Payment con los siguientes campos:

- paymentId: generado automáticamente por la base de datos.
- date: fecha de creación de la transacción.
- amount: cantidad de la transacción.
- userId: identificador del usuario que realizó la transacción.
- cardLast4number: número de los últimos 4 dígitos de la tarjeta utilizada para el pago, generado de manera aleatoria.
- authNumber: número de autorización del pago, generado de manera aleatoria.
- bank: nombre del banco que procesó el pago, generado de manera aleatoria, dentro de una lista de bancos Ej. [BBVA, Santander, CaixaBank] etc.
- isContactless: tipo booleano que indica si el pago fue realizado de manera contactless o no, generado de manera aleatoria.
- status: PAYMENT_SUCCESS, indicando que la transacción fue exitosa.
  
## El método GET: /paymentId 
 - deberá devolver la transacción correspondiente al identificador pasado como parámetro. 
 - La transacción deberá tener los mismos campos que el método POST.

## El método GET / 
 - deberá devolver una lista de todos los Payment creados.



## TEST DE INTEGRACION
Utilizo Spring test
### TestRestTemplate
1.  Spring Anotation

```java
    @SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
    @TestPropertySource(locations = "/application-test.properties")
```
2. Headers
```java
    private final HttpHeaders headers = new HttpHeaders();

```
3. Objects
```java
    private final PaymentRequest paymentRequest = new PaymentRequest();
```
1.  Templeate and Port
```java
    private TestRestTemplate testRestTemplate;

    @Autowired
    private RestTemplateBuilder restTemplateBuilder;
    @LocalServerPort
    private int port;
```
2. Build
```java
    @BeforeEach
    public void setUp(){
        restTemplateBuilder = restTemplateBuilder
                .rootUri("http://localhost:"+port);
        testRestTemplate= new TestRestTemplate(restTemplateBuilder);
        headers.add("Authorization", "laptop-value-45xx23");
    }
```
### @DataJpaTest
1. Anotación
```java
    @DataJpaTest 
    @TestPropertySource(locations = "/application-test.properties")

```
2. Uso de metodos JPA
```java
    @Autowired
    private TestEntityManager entityManager;
    
    @Autowired
    private UserRepository userRepository;
```

### Services y Controllers
* findAllTransactions 
    * findAllTransactions
    * findAllTransactionsNull
* findOneTransaction 
    * finOne
    * findOneNullId
* newTransaction 
    * newTransaction
    * newTransactionNull
    * newTransactionNullParams
    * newTransactionBlankParams


_No se incluiran en el proyecto: Recuperacion de contraseña ni de usuario_


## CONFIG SPRING

Crear proyecto Spring Boot con:
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



* PROPERTIES (H2 )
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
