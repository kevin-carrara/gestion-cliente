
# TechnicalTestPinApp microservicio para la gestión de clientes

url despliegue = https://gestion-cliente.onrender.com

url repo gitHub = https://github.com/kevin-carrara/gestion-cliente.git

para levantar local

1. ejecutar el servidor de kafka utilizar **docker compose up -d** ya que no uso proveedor de la nube que gestione kafka como Confluent Cloud o Cloud Karafka seria lo mas optimo
2. configuracion variables de entorno para conexion a DB **DB_USERNAME , DB_PASSWORD, DB_URL, DB_PLATFORM**
3. para levantar swagger [localhost:8081]/technical-test/swagger-ui/index.html

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

Swagger/OpenAPI fue integrado para facilitar la exploración, prueba y documentación automática de la API

#### Ventajas de Swagger:
1. **Facilidad de Uso:** Los desarrolladores pueden probar los endpoints directamente desde la interfaz gráfica.
2. **Documentación Dinámica:** Cada cambio en los controladores refleja automáticamente la documentación.

### Pruebas Implementadas

Se desarrollaron múltiples pruebas para validar todas las funcionalidades principales y aspectos técnicos del microservicio:

1. **Unitarias (Controladores):**
    - **`testCrearCliente`**: Verifica que se pueden crear clientes correctamente y se envían mensajes a Kafka.
    - **`testGetClienteKpi`**: Valida el cálculo de métricas sobre los clientes registrados.
    - **`testGetClientList`**: Comprueba que los clientes se listan correctamente con cálculos derivados como fecha estimada de fallecimiento.
    - **`testCrearClienteError`**: Maneja correctamente excepciones como `ServiceException` durante la creación de clientes.