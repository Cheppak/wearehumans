# Examen Mercadolibre 
_by Sergio Cheppi_

Microservicio desarrollado con Spring Boot y desplegado mediante Kubernetes & Google Cloud.

### Pre-requisitos 📋
_Para ejecuciones en entorno local tener instalado:_ 
- Java8
- git
- maven
- mongodb 
- explorador + plugin cliente rest o alguna herramienta de testing rest 
- ide a gusto (opcional)

### Url de la app en la nube (ExternalIP+port) ☁

http://35.202.84.146:8080/

## Ejecutando las pruebas ⚙️

_Dentro del proyecto se encuentran tests unitarios (Junits) para la prueba del algoritmo. En ellas se incluyen:_
- Es mutante
  - Caso base de ejemplo
  - Scan horizontal
  - Scan vertical
- No es mutante
  - Letras que no corresponden al dominio
  - No cumple con la condicion (Forbidden)
  - No posee una matriz NxN

Para probar via app
  - GET /stats
  - POST /mutant (Json{dna})

## Tutoriales de apoyo 🛠️

* [Springboot & docker] https://spring.io/guides/gs/spring-boot-docker/ (armado de dockerfile, utilizacion de plug de spotify, configuracion del pom)
* [springboot & spotify docker maven plugin] https://github.com/spotify/docker-maven-plugin (nos permite crear images con mvn... mas info acerca del primer tuturial)
* [Deploy on kubernetes] https://codelabs.developers.google.com/codelabs/cloud-springboot-kubernetes/#0

