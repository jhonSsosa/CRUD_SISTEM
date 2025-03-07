# Sistema CRUD de Propiedades

Este sistema permite la gestión de propiedades inmobiliarias, permitiendo realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar). La aplicación está construida con un **backend en Spring Boot**, un **frontend en JavaScript/HTML/CSS** (ambos desplegados en un servidor AWS) y una **base de datos MySQL** desplegada en AWS tambien.

---

## Arquitectura del Sistema

El sistema sigue una arquitectura **cliente-servidor**, separando el frontend, backend y base de datos en contenedores y servidores distintos:

1. **Frontend:** Aplicación web construida con HTML, CSS y JavaScript.
2. **Backend:** API REST en Spring Boot con JPA/Hibernate para interactuar con la base de datos.
3. **Base de Datos:** MySQL desplegado en AWS.
4. **Despliegue:** Los componentes están separados en diferentes servidores en AWS.

---

## Diseño de Clases

### Principales Clases

```mermaid
classDiagram
    class Property {
        +Long id
        +String address
        +Double price
        +Double size
        +String description
    }

    class PropertyRepository {
        +List<Property> findAll()
        +Property save(Property property)
        +void deleteById(Long id)
    }

    class PropertyService {
        +List<Property> getAllProperties()
        +Property saveProperty(Property property)
        +void deleteProperty(Long id)
    }

    class PropertyController {
        +ResponseEntity<List<Property>> getAllProperties()
        +ResponseEntity<Property> addProperty(Property property)
        +ResponseEntity<Void> deleteProperty(Long id)
    }

    PropertyService --> PropertyRepository
    PropertyController --> PropertyService
```

- **Property**: Representa una propiedad inmobiliaria.
- **PropertyRepository**: Interfaz que maneja la persistencia con JPA.
- **PropertyService**: Contiene la lógica de negocio para las propiedades.
- **PropertyController**: Exposición de la API REST para interactuar con el frontend.

---

## Instrucciones de Despliegue

### 1. Construcción y Despliegue del Backend

Ejecuta el siguiente comando en la terminal de la instancia AWS EC2:

```sh
docker --version
```

Si tu instancia EC2 usa Amazon Linux 2, ejecute:

```sh
sudo yum update -y
sudo yum install -y docker
```

Después de instalar Docker, asegurece de que se inicie automáticamente:

```sh
sudo systemctl enable docker
sudo systemctl start docker
```

Luego, ejecuta:

```sh
sudo systemctl enable docker
sudo systemctl start docker
```

Para asegurarse que el contenedor escuche el puerto correcto, se necesita tener activo la regla de seguridad con el puerto "42002" para que se ejecute correctamente, tal y como aparece en esta imagen:

![image](https://github.com/user-attachments/assets/3239388e-c401-4ac8-86ff-c541203e3598)

Con todas las configuraciones listas simplemente ejecutar el siguiente comando en la instancia para poder ejecutar el backend ya conectado a una base de datos generado en una instancia con una ip publica:

docker run -d --name backend -p 8080:8080 jhonssosa/lab05:latest

Finalmente para probar la base de datos simplemente escribir en el navegador (Omitir microsoft edge por problemas de compatibilidad) el siguiente link el cual ejecutara el programa en cuesiton:

```sh
http://ec2-18-216-183-219.us-east-2.compute.amazonaws.com:8080/
```

### 4. Configuración en AWS

1. **Base de Datos:** Implementa una instancia de MySQL en AWS RDS.
2. **Backend:** Despliega la API en un servidor EC2.

---

##  Capturas de Pantalla

### Listado de Propiedades
![image](https://github.com/user-attachments/assets/50a52f0c-d7bc-44a6-820c-3a9690ba32af)
![image](https://github.com/user-attachments/assets/01c28fa0-be3b-4f42-a85e-0c3149ea98c5)


### Creación de Propiedad
![image](https://github.com/user-attachments/assets/cc36931f-15c5-49b4-b814-9e1a0c2a72b7)
![image](https://github.com/user-attachments/assets/1a64ac95-eca1-4af0-b884-df1c549ac363)


### Edición de Propiedad
![image](https://github.com/user-attachments/assets/b6a98e04-fd84-4ece-be2b-93a695c7d94c)
![image](https://github.com/user-attachments/assets/1ae43b11-5666-464c-9f86-9b64a7410950)
![image](https://github.com/user-attachments/assets/a3c70081-d69e-4b71-9291-fb467e3f80e8)
![image](https://github.com/user-attachments/assets/ea2da5b6-aa08-42cb-9e00-05e80bcd99c8)

![image](https://github.com/user-attachments/assets/c24a0357-7398-4573-bd4c-d0e3676bbea8)

### Eliminación de Propiedad
![image](https://github.com/user-attachments/assets/6f78aab9-b27e-4201-9966-fdba4ff3cdb5)
![image](https://github.com/user-attachments/assets/69eaa620-ba71-415d-a6eb-7c2d59bf1585)


---

