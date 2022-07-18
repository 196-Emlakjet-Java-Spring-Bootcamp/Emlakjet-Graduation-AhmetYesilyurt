# Patika-Emlakjet-Graduation


# About the project
It is a web application where users can post advert in the real estate sector. To create a registration, tokens are purchased with Email and Password.
Thanks to the Gateway, the desired CRUD operations are authorized via this token. The desired service can be accessed through the Gateway.

Within the scope of this project, 5 services have been prepared.

- Admin Service
- Banner Service
- Cloud Gateway
- Emlakjet
- Report Service

### Getting started
Make sure port 5432,5672,15672,8080,8081,8082,8083,8084 are not in use

### Installing & Running Emlakjet Microservices
    
    mvn clean install
    docker-compose up

    # try forcing a image rebuild
    docker-compose up --build --force-recreate


### To execute the API's through the gateway
    1) localhost:8080/login 
    2) localhost:8080/advert-api/** -> emlakjet
    3) localhost:8080/banner-api/** -> emlakjet-banner 
    4) localhost:8080/report-api/** -> emlakjet-report
    5) localhost:8080/admin-api/** -> emlakjet-admin
  
    
### Basic use cases
#### 1) Signup a new user 
#### 2) Login with the created user to get authentication token
#### 3) Create an address 
#### 4) Create a property 
#### 5) Create a advert 
    
 ### You can see the api documentation on the swagger page after you run all the services. Swagger will be available on;
 
  ```
  Gateway/Auth   -> http://localhost:8080/swagger-ui.html
  Emlakjet       -> http://localhost:8081/swagger-ui.html
  Banner         -> http://localhost:8082/swagger-ui.html
  Report         -> http://localhost:8083/swagger-ui.html
  Admin          -> http://localhost:8084/swagger-ui.html
  ```
  
#### Gateway-controller
###
```http
  path : localhost:8080/login 
```

| Parameter | Access Point    | Explanation                |
| :-------- | :------- | :------------------------- |
| POST | `login/` | Creates a token for the user whose email and password are given |

 #### Emlakjet-controller
```http
  path : localhost:8080/advert-api/**
```

| Parameter | Access Point    | Explanation                |
| :-------- | :------- | :------------------------- |
| POST | `address/` | Create an address. |
| GET | `address/{id}` | It brings the ad according to the given advert number. |
| GET | `addresses` | Brings up all the addresses. |

| Parameter | Access Point     | Explanation                |
| :-------- | :------- | :------------------------- |
| POST | `/property` | Create an property |
| GET | `/properties` | Brings up all the properties. |
| GET | `/property/{id}` | It brings the property according to the id number. |

| Parameter | Access Point     | Explanation                |
| :-------- | :------- | :------------------------- |
| POST | `/advert` | Create an advert. |
| PUT | `/advert/{advertId}` | It allows to update the advert according to the id number. |
| GET | `/advert/{advertNo}` | It brings the advert according to the id number. |
| GET | `/advert/get10` | Brings up 10 active ads.. |

| Parameter | Access Point     | Explanation                |
| :-------- | :------- | :------------------------- |
| POST | `/signup` | It allows registration. |
| GET | `/user/{userId}` | It brings the user according to the id number. |
| GET | `/users` | Brings up all the users. |

#### Banner-controller
###
```http
  path : localhost:8080/banner-api/**
```

| Parameter | Access Point    | Explanation                |
| :-------- | :------- | :------------------------- |
| POST | `/banner` | Create an banner. |
| GET | `/banners` | Brings up all the baners. |

#### Report-controller
###
```http
  path : localhost:8080/report-api/**
```

| Parameter | Access Point     | Explanation                |
| :-------- | :------- | :------------------------- |
| PUT | `/report/{advertId}` | It allows to update the generated reports. |
| GET | `/report/{id}` |  It brings the report according to the advert id. |


#### Admin-controller
###
```http
  path : localhost:8080/admin-api/**
```
| Parameter | Access Point     | Explanation                |
| :-------- | :------- | :------------------------- |
| PUT | `/admin/make-active/{id}` | Activates the advert according to the given advert id. |
| PUT | `/admin/make-passive/{id}` | Makes advert passive according to the given advert id. |
| GET | `/admin/{userId}` |  It brings the user according to the id number. |
| GET | `/admin/active` | Brings up 10 active ads.. |
| GET | `/admin/all-advert` | Brings up all the adverts. |
| GET | `/admin/last-10-advert` | Brings up 10 last ads.. |
| GET | `/admin/passive` | Brings up 10 passive ads.. |
| GET | `/admin/review` | Brings up 10 in-review ads.. |
 
 
### Diagram

  ![1](https://user-images.githubusercontent.com/44724790/179455922-778b84ac-22b5-4284-853f-bf70e0a01650.PNG)

  
## Postman Screenshots

### Sign up

![2](https://user-images.githubusercontent.com/44724790/179456011-eae43205-355d-4541-b381-1edabf73b035.PNG)

### Login

![3](https://user-images.githubusercontent.com/44724790/179456028-f3d72ac1-05e4-4574-8327-5b380a377eae.PNG)

### Create Address

  ![4](https://user-images.githubusercontent.com/44724790/179456079-c629786e-2276-4eb8-a8f3-84f16f6bd90a.PNG)
 
### Create Property

  ![5](https://user-images.githubusercontent.com/44724790/179456136-f8b12438-1f79-40d4-a02e-23d23c93723c.PNG)

### Create Advert
  ![6](https://user-images.githubusercontent.com/44724790/179456159-cbca3c6c-b919-4651-b0b5-153d02471016.PNG)

  


### Considerations During the Writing of the Project

- It has been tried to make sure that the code is clean, understandable and easy to read.
- It has been tried to be written in accordance with the code structure. It was created taking into account the layered architecture. 
- This project has been tried to be done in accordance with the microservice architecture.
- Exception Handling have been tried to be used.
- Programming principles (SOLID etc.) were tried to be taken into account.


### Future Work

- Logging and Exception Handling can be diversified to make them more inclusive across the whole code.
- Logging operations written to a file or database.
- Instead of using a common database, each service can be connecting to its own database to be more secure.
- Design patterns can be used.
- The unit test should be developed by writing new tests for all services and all cases of these services' methods.
- A suitable frontend can be written.





