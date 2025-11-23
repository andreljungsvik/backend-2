# Bilverkstad REST API – Spring Data JPA

## 🚀 Kör projektet
Starta applikationen med:
```bash
./run.sh
```
eller
```
sh run.sh
```
## 📡 REST-endpoints

### Skapa kund
**POST** `/customers`  
**Query parameters:** `name`, `phone`  
**Exempel:**

http://localhost:8080/customers?name=Anna&phone=987654321


### Hämta kund-ID via namn
**GET** `/customer-id`  
**Query parameters:** `name`  
**Exempel:**

http://localhost:8080/customer-id?name=Anna


### Lista alla kunder med fordon (DTO)
**GET** `/customers`  
**Exempel:**

http://localhost:8080/customers


### Skapa fordon
**POST** `/vehicles`  
**Query parameters:** `registrationNumber`, `brand`, `model`, `productionYear`  
**Exempel:**

http://localhost:8080/vehicles?registrationNumber=ABC125&brand=Volvo&model=V70&productionYear=2010


### Lista alla fordon
**GET** `/vehicles`  
**Exempel:**

http://localhost:8080/vehicles


### Lista fordon efter bilmärke
**GET** `/vehicles-by-brand`  
**Query parameters:** `brand`  
**Exempel:**

http://localhost:8080/vehicles-by-brand?brand=Volvo


## 🖥️ H2 konsol:

http://localhost:8080/h2-console

    JDBC URL: jdbc:h2:file:./Database
    Username: sa
    Password: (tomt)