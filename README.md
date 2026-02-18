# CI/CD | Pipeline con app web Java: Fase 3

**Erik Vives von Heyne**

---

# API

La api utilizada para este proyecto es un **clone** del back end del compañero _Cristian Pérez_.

El back end / API es un gestor de series y plataformas con una simple Base de Datos que gestiona estos dos modelos entre si.

## Tecnologías

```powershell
    - Maven 3.9.11
    - JDK 21
    - PostgreSQL 15
```

## Tree view

```java
├───.github
│   └───workflows // Workflow creado
└───gestor-series-plataformes
    ├───src // Codigo fuente
    │   ├───main
    │   │   ├───java
    │   │   │   └───ifc33b
    │   │   │       └───dwesc
    │   │   │           └───gestorseriesplataformes // Back end
    │   │   │               ├───controller
    │   │   │               ├───dto
    │   │   │               ├───exception
    │   │   │               ├───model
    │   │   │               ├───repository
    │   │   │               └───service
    │   │   └───resources // Base de datos
    │   │       └───sql
    │   └───test // Codigo para tests
    │       ├───java
    │       │   └───ifc33b
    │       │       └───dwesc
    │       │           └───gestorseriesplataformes
    │       │               ├───controller
    │       │               └───service
    │       └───resources
    │           └───sql
    └───target // Almacenamiento de compilaciones
```

# Ejecución

## Preparativos

Aquí se detallan los pasos previos antes de ejecutar nada.

### Usar directorio correcto

Es necesario usar el directorio `gestor-series-tareas` para ejecutar maven, ya que es donde esta situado el pom.xml

### Instalar dependencias

```powershell
mvn install
```

## Iniciar con fixtures

El back end proporcionado carga sus propios datos (fixtures) para cuando se ejecuta normalmente, y tiene otros separados para ejecutar durante los tests

```powershell
mvn spring-boot:run
```

## Iniciar tests

```powershell
mvn test
```

## Comandos CURL

**Crear Serie**

```powershell
curl -X POST http://localhost:8080/api/series \
  -H "Content-Type: application/json" \
  -d '{
    "titulo": "Breaking Bad",
    "temporadas": 5,
    "plataformaId": 1
  }'
```

**Obtener Plataformas**

```powershell
curl -X GET http://localhost:8080/api/plataformes \
  -H "Accept: application/json"
```

**Obtener Serie por Plataforma ID**

```powershell
curl -X GET http://localhost:8080/api/series/plataforma/1 \
  -H "Accept: application/json"
```

# Tests

## Unitarios

### GestorServiceTests

Se está testeando la clase **GestorService** usando **Mockito** para simular (@Mock) los repositorios y con **JUnit** se crean los tests.

No se accede a base de datos real si no a un fixture de JPA en H2 (En memoria volátil).

> [!NOTE]
> Se esta probando la clase **GestorService** creando y buscando Series y Plataformas.
> Tambien se comprueba que devuelva null o errores en ciertas condiciones.

## Integración

### GestorControllerIntegrationTest

Se está testeando la clase **GestorController** como un **test de integración** usando **SpringBootTest** y **MockMvc**.

A diferencia del test unitario del servicio, aquí:

Se levanta el **contexto completo de Spring** y se cargan los controladores, servicios y repositorios reales. Se sigue usando un perfil `"test"` con base de datos **H2 en memoria**

Debido a que se carga el entorno completo ya no se hace uso de los **Mocks**

Se simulan peticiones HTTP reales (`GET`, `POST`) contra la API.

> [!NOTE]  
> Se está probando el comportamiento completo de la API:
>
> - Endpoints GET y POST
> - Validaciones de campos (@Valid)
> - Códigos HTTP correctos (200, 201, 400)
> - Respuestas JSON esperadas
> - Integración real entre Controlador, Servicio y Repositorio

## Workflow CI.yml

Este workflow define un proceso de Integración Continua (CI) usando GitHub Actions.  
Se ejecuta automáticamente cuando hay un `push` o un `pull_request` sobre la rama `main`.

Utiliza:

- Ubuntu como entorno de ejecución
- JDK 21
- Maven
- Checkstyle
- Docker y Docker Compose
- Contenedores de PostgreSQL y Tomcat

El workflow está dividido en dos grandes jobs.

### build-and-test

Este job se encarga de compilar y validar el proyecto Java.

Pasos que realiza:

- Checkout del código del repositorio.
- Configuración de JDK 21 con cache de Maven.
- Ejecución de `mvn clean install` para compilar el proyecto.
- Ejecución de `mvn checkstyle:check` para validar el estilo del código.
- Ejecución de `mvn test` para lanzar los tests.

> [!NOTE]
> Este job garantiza que el proyecto compila correctamente, pasa los tests y cumple las reglas de calidad antes de continuar.

### build-docker-image

Este job depende del anterior y solo se ejecuta si el build y los tests han sido correctos.

Pasos que realiza:

- Checkout del código.
- Construcción de las imágenes con `docker compose build`.
- Arranque de los contenedores con `docker compose up -d`.
- Comprobación del estado de salud (healthcheck) de PostgreSQL y Tomcat mediante bucles que esperan hasta que estén en estado `healthy`.

> [!NOTE]
> Este job valida que la aplicación puede desplegarse correctamente en Docker y que los servicios principales arrancan sin errores.
