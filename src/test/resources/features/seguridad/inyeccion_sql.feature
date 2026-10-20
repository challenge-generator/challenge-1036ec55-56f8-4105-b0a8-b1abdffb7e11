Feature: Técnicas de inyección SQL y protección en plataforma de banca en línea

  Como tester de seguridad
  Necesito identificar y prevenir vulnerabilidades de inyección SQL
  Para proteger la integridad de la base de datos y los datos de los clientes

  Background:
    Given que la aplicación de banca en línea utiliza una base de datos relacional
    And los usuarios interactúan con el sistema a través de formularios y APIs
    And el sistema debe proteger contra técnicas comunes de inyección SQL

  @smoke @inyeccion-critical
  Scenario: Detectar inyección SQL en formulario de búsqueda de transacciones
    Given que el usuario accede al formulario de búsqueda de transacciones
      | Campo           | Valor                    |
      | numero_cuenta   | 1234567890               |
    When intenta realizar una búsqueda con payload de inyección
      | Campo           | Valor                                          |
      | criterio_busqueda | 1234567890' OR '1'='1                       |
    Then el sistema debe rechazar la solicitud
    And mostrar mensaje de error genérico "Error al procesar la solicitud"
    And no debe exponer información de la base de datos en la respuesta
    And debe registrar el intento de inyección en log de seguridad
      | Campo              | Valor                              |
      | tipo_ataque        | SQL_INJECTION                      |
      | payload            | 1234567890' OR '1'='1              |
      | ip_cliente         | 192.168.1.50                       |
      | timestamp          | 2024-01-15T14:30:00Z              |

  @regression @inyeccion-api
  Scenario: Proteger endpoint API de inyección SQL en parámetros
    Given que existe un endpoint API para consulta de saldo
      | Campo           | Valor                    |
      | endpoint        | /api/v1/cuenta/saldo     |
      | método          | GET                      |
    When se envía una solicitud con parámetros maliciosos
      | Campo           | Valor                                          |
      | numero_cuenta   | 1234567890; DROP TABLE transacciones;--       |
    Then el sistema debe validar y sanitizar los parámetros de entrada
    And rechazar la solicitud con código de error "400"
    And no debe ejecutar ninguna consulta SQL con el payload malicioso
    And debe registrar el incidente de seguridad

  @smoke @inyeccion-login
  Scenario: Prevenir inyección SQL en proceso de autenticación
    Given que el usuario intenta autenticarse en el sistema
      | Campo           | Valor                                          |
      | usuario         | admin'--                                      |
      | contraseña      | cualquier                                     |
    When el sistema procesa la solicitud de login
    Then debe utilizar consultas parametrizadas
    And no debe permitir eludir la autenticación mediante inyección
    And el usuario debe recibir mensaje de "Credenciales inválidas"
    And debe registrar el intento de intrusión

  @regression @inyeccion-buro
  Scenario: Validar protección en consultas al buró de riesgos
    Given que el sistema consulta información del buró de riesgos
      | Campo           | Valor                    |
      | tipo_documento  | CC                       |
      | documento       | 12345678                 |
    When se manipula el parámetro de consulta
      | Campo           | Valor                                          |
      | documento       | 12345678' UNION SELECT * FROM usuarios;--     |
    Then el sistema debe aplicar validación de entrada estrict
    And sanitizar los caracteres especiales de SQL
    And rechazar la consulta si contiene patrones de inyección
    And generar alerta de seguridad para el equipo

  @integration @inyeccion-transaccion
  Scenario: Proteger consultas de transacciones contra inyección SQL
    Given que el usuario solicita listar sus transacciones
      | Campo           | Valor                    |
      | id_cuenta       | 1234567890               |
      | fecha_inicio    | 2024-01-01               |
      | fecha_fin       | 2024-01-31               |
    When proporciona valores maliciosos en los filtros
      | Campo           | Valor                                          |
      | fecha_inicio    | 2024-01-01' AND '1'='1                         |
    Then el sistema debe validar el formato de las fechas
    And rechazar valores que contengan caracteres SQL
    And utilizar únicamente consultas parametrizadas
    And no revelar estructura de la base de datos en errores

  @smoke @inyeccion-blind
  Scenario: Detectar inyección SQL a ciegas (blind SQL injection)
    Given que el sistema tiene una vulnerabilidad potencial a inyección a ciegas
    When un atacante envía múltiples solicitudes con condiciones booleanas
      | Campo           | Valor                                          |
      | solicitud_1     | 1234567890' AND 1=1                            |
      | solicitud_2     | 1234567890' AND 1=2                            |
    Then el sistema debe detectar anomalías en el patrón de solicitudes
    And bloquear temporalmente la IP del atacante después de "5" intentos
    And registrar el comportamiento sospechoso
    And notificar al equipo de seguridad

  @regression @inyeccion-segunda-orden
  Scenario: Proteger contra inyección SQL de segunda orden
    Given que el sistema almacena datos proporcionados por el usuario
      | Campo           | Valor                                          |
      | nombre_beneficiario | Roberto'); DROP TABLE transacciones;--    |
    When estos datos se utilizan posteriormente en otra consulta
    Then el sistema debe sanitizar los datos al recuperarlos
    And nunca confiar en datos almacenados sin validación
    And aplicar el mismo nivel de validación que a entrada directa

  @stress @inyeccion-dos
  Scenario: Validar resistencia del sistema ante ataques de inyección SQL automatizados
    Given que el sistema recibe "100" solicitudes con diferentes payloads de inyección
      | Campo           | Valor                                          |
      | tasa_solicitudes| 10 por segundo                                |
      | tipo_payloads   | UNION, boolean-based, time-based, error-based |
    Then el sistema debe manejar todas las solicitudes correctamente
    And mantener tiempo de respuesta inferior a "1000" milisegundos
    And no debe sufrir degradación del servicio
    And debe registrar cada intento de inyección

  @smoke @inyeccion-prevencion
  Scenario: Verificar que las consultas utilizan declaraciones preparada
    Given que se analiza el código de la aplicación
    When se revisan las consultas a la base de datos
    Then todas las consultas dinámicas deben utilizar declaraciones preparadas
      | Consulta              | Tipo esperado |
      | Login usuario         | PARAMETRIZADA |
      | Búsqueda transacciones| PARAMETRIZADA |
      | Consulta buró         | PARAMETRIZADA |
      | Registro transacción  | PARAMETRIZADA |
    And ninguna consulta debe concatenar cadenas directamente
    And el análisis de código estático no debe reportar vulnerabilidades SQL

  @regression @inyeccion-waf
  Scenario: Validar que el WAF bloquea intentos de inyección SQL conocidos
    Given que el sistema tiene un Web Application Firewall configurado
    When se envía una solicitud con payload de inyección conocido
      | Campo           | Valor                                          |
      | payload         | ' OR '1'='1                                    |
      | ataque_tipo     | SQL_INJECTION_CLASSIC                         |
    Then el WAF debe bloquear la solicitud antes de llegar a la aplicación
    And responder con código "403 Forbidden"
    And incluir la cabecera "X-Block-Reason: SQL_INJECTION_DETECTED"
    And registrar el bloqueo en el log de auditoría

  @integration @inyeccion-validacion
  Scenario: Implementar validación de entrada completa para prevenir inyección
    Given que el usuario proporciona datos para una nueva transferencia
      | Campo           | Valor                    |
      | cuenta_destino  | 0987654321               |
      | monto           | 5000.00                  |
      | concepto        | Pago servicios           |
    When el sistema valida cada campo de entrada
    Then debe aplicar validación de tipo de datos
    And validar longitud máxima de campos
    And sanitizar caracteres especiales
    And rechazar entrada que contenga patrones SQL
      | Patrón a rechazar                           |
      | ' (comilla simple)                          |
      | -- (comentario SQL)                         |
      | ; (punto y coma)                            |
      | UNION                                       |
      | DROP, DELETE, UPDATE, INSERT               |
      | EXEC, EXECUTE                               |