# SPRING CONFIG BACKEND

## What is this

This project is a spring config backend that serves encrypted config data through HTTP endpoints.
Default security is basic auth.

## How to run 

- First you'll need to generate an RSA key name it server.kjs and set it in src/main/resources
```sh 
 keytool -genkeypair -alias my-encryption-keypair -keyalg RSA -keysize 2048 -dname "CN=ConfigServer" -keystore server.jks -storepass yourpassword 
 ```
- Then add this to your application.yml
```yml
encrypt:
  enabled: true
  key-store:
    location: classpath:server.jks
    password: yourpassword
    alias: my-encryption-keypair
```
- Then you need to generate a user/password combination for basic auth, your password must be encrypted with bcrypt you can use the PasswordEncoderRunner utility in ``src/main/java/yummers/cloudconfig/appconfig/PasswordEncoderRunner.java``
```yml
spring:
  security:
    user:
      name: username
      password: mybasicauthbcryptencodedpassword
```

- Full application.yml example : 
```yml
server:
  port: 8888

spring:
  security:
    user:
      name: username
      password: mybasicauthbcryptencodedpassword
  application:
    name: config-server
  profiles:
    active: native 
  cloud:
    config:
      server:
        native:
          search-locations: classpath:/configs 
encrypt:
  enabled: true
  key-store:
    location: classpath:server.jks
    password: yourkeypassword
    alias: my-encryption-keypair
```

- Launch the backend and you should be good to go ! 

## USAGE 

- Default path for yml configs are in src/main/resources/configs
  - For instance if you have a backend1-prod.yml in your config file, you should have access at this endpoint http://localhost:8888/backend1/prod

- Endpoint to encrypt/decrypt data with you key-store is available at POST http://localhost:8888/encrypt and POST http://localhost:8888/decrypt
- To add encrypted data in config file and have auto-decrypt you must add "{cipher} your encrypted data", for instance : 

```yml 
spring:
  application:
    name: "{cipher}AQCO6HJoAmpW6Eie3HWyqGslKBX+bmjLqs8twnJzsXB3Zj71NuMVV4ZvnWOlvnG23xgSSRwfqc3xOMwxQ1MnU2lXY3DBzc/kUrsM9mG4PmaxTEC5OzqZLVsI7BQI7vQD9COimjpJR7c6Nz57f7ZVX1Y+cNRYdQQdusSFcc+FfPJdHkHL30oQyoEGest80FxbMTct/S4zemQzshrlB67aik30fPTvJTyCJTNiOuCLAjjStbeCq0T+CTsdnyO0xju5lDv8QmjcBjAWqTlOfJ5bqdneeIz5+Uo00osAp0kwn4iawEvzVdz6e2pPFhhXFbsxOeCY/V3kcTVjPH2rbiQo3bpU4zxCIPgntRQEbV0Zx+6cV8h3zCiIt4YYRqUzrVmTz4Y="
```
