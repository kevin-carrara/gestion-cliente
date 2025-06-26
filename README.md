
# TechnicalTestPinApp

url despliegue = 

url repo gitHub = 

para levantar swagger [URLENTORNO]/technical-test/swagger-ui/index.html

configuracion variables de entorno para conexion a DB **DB_USERNAME , DB_PASSWORD, DB_URL, DB_PLATFORM**



### Mensajería con Kafka
Implementamos una solución de mensajería basica usando Apache Kafka para garantizar la gestión eficiente de eventos relacionados con el manejo de clientes. 
Kafka fue elegido debido a su:

1. Alta escalabilidad.
2. Persistencia y tolerancia a fallos.
3. Capacidad para manejar un alto throughput de mensajes.

Cada vez que se crea un cliente, se publica un evento en el tópico `cliente-events`, que es procesado por consumidores asíncronos. 
Este diseño asegura que el procesamiento intensivo pueda manejarse fuera del flujo crítico del sistema.

### Logs y Monitoreo

1. **Logs**: Se utilizo el sistema de logs usando SLF4J

2. **Monitoreo**: Se integró Spring Boot Actuator para exponer endpoints clave como `/health`, `/metrics`, y `/info`. Además, se preparó la configuración para ser monitoreada con herramientas externas como Prometheus y Grafana.


### Integración con Swagger para Documentación

Swagger/OpenAPI fue integrado para facilitar la exploración, prueba y documentación automática de la API. Se configuró seguridad con esquema Bearer Token (JWT), lo cual permite simular flujos protegidos directamente desde el Swagger UI.

#### Ventajas de Swagger:
1. **Facilidad de Uso:** Los desarrolladores pueden probar los endpoints directamente desde la interfaz gráfica.
2. **Documentación Dinámica:** Cada cambio en los controladores refleja automáticamente la documentación.
3. **Autenticación Segura:** Los desarrolladores pueden autenticarse mediante Bearer Token sin preocuparse por manejar manualmente los encabezados HTTP.