# missing-numbers 

Prueba creada en java usando el framework Spring Boot
Se creo una api REST para dar solucion al ejercicio

## Requisitos

- Java JDK 1.8
- maven
- Tener configurado las variables de entorno JAVA_HOME y MV_HOME, [Instrucciones](https://www.tutorialspoint.com/maven/maven_environment_setup.htm) 

## Clonar el repositorio

- Ubicarse en una terminal
- Ejecutar git clone https://github.com/oscardmg/missing-numbers.git
- Ejecutar cd missing-numbers

## Como ejecutar las pruebas unitarias

- Ubicarse en el raiz del proyecto
- ejecutar mvn test

## Como ejecutar el proyecto 

- Ubicarse en el raiz del proyecto
- Ejecutar cd target
- ejecutar java -jar numbers-0.0.1-SNAPSHOT.jar

## Probar el api

- Abrir postman
- Seleccionar el metodo POST
- ingresar la siguiente url: http://localhost:8080/numbers/find
- En body seleccionar raw, ingresar el siguiente json
 ```
{
    "nList": "203 204 205 206 207 208 203 204 205 208",
    "mList": "608 203 204 204 205 206 207 205 208 203 206 205 206 204 350"
}
 ```
- ejecutar y esperar el resultado.


