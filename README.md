# prueba01 - Repaso Java con Maven

Proyecto de referencia para practicar Java por capas (`model`, `repository`, `service`, `app`) con pruebas unitarias y BDD.

## Ejecutar la aplicacion

```bash
mvn exec:java
```

## Ejecutar pruebas

```bash
mvn test
```

## Cobertura de pruebas

- **Unit tests (JUnit 5):** validan metodos del `EmpleadoService` de forma aislada (registro, busqueda, actualizacion, eliminacion y validaciones).
- **BDD (Gherkin + Cucumber):** validan escenarios de negocio end-to-end en lenguaje natural:
  - registro y listado
  - actualizacion
  - eliminacion
  - errores esperados (id duplicado, salario negativo, id inexistente en busqueda/eliminacion)
