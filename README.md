# Scheduler-UTEC MOBILE

## Integrantes:
- Mauro Bobadilla - 202010114
- Carlos Andrés Montoro Torres - 202010324
- Rodrigo Salazar - 202010387
- Eduardo Arróspide González - 202010442

## Descripción del proyecto
- Una version android de nuestra web app para crear y compartir horarios para la matricula de la universidad UTEC.

## Objetivos:

### Objetivo General:
- Crear un sistema que le ofrezca a los alumnos una forma fácil y rapida de gestionar sus horarios desde su celular.

### Objetivos especificos:

## Tecnologias
### Front-end
#### XML
Se ha usado XML para la creacion de los activity para dar una version personalizada de nuestros html que usamos en
el trabajo anterior para que sea visible y de facil acceso a los usuarios Android
#### Java
La logica y creacion de classes, incluyendo los "intent" fueron diseñados usando Java.
### Back-end
- Se ha usado Python y postgreSQL para el manejo de la base de datos con las librerias de SQL-Alchemy y FLASK-WTFORMS
### Base de datos
- Linkeamos nuestro proyecto android web con la base de datos de nuestra aplicacion web del Scheduler-UTEC
#### PostgreSQL
...

## Script de inicializacion de base de datos
La informacion para la conexion con la base de datos se encuentra especificada en la carpeta del proyecto Scheduler-UTEC y se ha linkeado el uso de esta informacion para el modo mobile

## API
...

### API CRUD
Para las llamadas CRUD las respuestas se usan con intents para llamar a la informacion de la base de datos.


### Aplicacion Web
- 200: SUCESS => Estado cuando la respuesta es exitosa
- 400: BAD REQUEST => Usada para abortar cuando la request realizada no es segura (ej.: redireccionamiento inseguro en parametro next)
- 401 UNAUTHORIZED => No tiene permisos para editar/eliminar ese elemento
- 404 NOT FOUND => Recurso no encontrado
- 500 INTERNAL SERVER ERROR => Error inesperado del servidor

## Ejecuccion del sistema
El programa se inicia usando JDK 11 desde el MainActivy usando la version Oreo