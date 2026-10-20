Feature: Evaluación de riesgos y gestión de vulnerabilidades en plataforma de banca en línea

  Como analista de seguridad
  Necesito evaluar los riesgos y gestionar las vulnerabilidades del sistema
  Para garantizar la protección de los datos y la integridad de las transacciones bancarias

  Background:
    Given que el sistema de banca en línea está operativo con los siguientes componentes:
      | Componente           | Descripción                              |
      | Originador de créditos | Procesa solicitudes de crédito          |
      | Motor antifraude      | Evalúa patrones de fraude en tiempo real |
      | Buró de riesgos       | Consulta historial crediticio           |
      | Core bancario         | Gestiona cuentas y transacciones         |
    And el sistema procesa "1500" solicitudes por segundo en hora pico
    And el SLA requerido es del "99.9" por ciento de disponibilidad
    And la trazabilidad de eventos debe mantenerse por "5" años

  @smoke @riesgo-critical
  Scenario: Identificar vulnerabilidad crítica en el originador de créditos
    Given que el originador de créditos recibe una solicitud de crédito con datos válidos
      | Campo           | Valor                    |
      | numero_documento | 1234567890              |
      | monto_solicitado | 50000.00                |
      | plazo_meses     | 36                      |
      | ingreso_mensual | 8000.00                 |
    When el motor antifraude analiza la solicitud
    And detecta un patrón de comportamiento sospechoso
    Then el sistema debe registrar el evento de alerta de seguridad
    And generar un identificador único de traza "TRACE-2024-001"
    And enviar notificación al equipo de seguridad
    And el registro de auditoría debe contener:
      | Campo              | Valor                              |
      | timestamp          | 2024-01-15T10:30:00Z              |
      | evento             | ALERTA_SEGURIDAD                   |
      | severidad          | CRITICA                            |
      | componentes_afectados | ORIGINADOR_CREDITOS,MOTOR_ANTIFRAUDE |

  @regression @riesgo-alto
  Scenario: Evaluar riesgo de exposición de datos sensibles en el buró de riesgos
    Given que el buró de riesgos consulta información crediticia del cliente
      | Campo           | Valor                    |
      | tipo_documento  | CC                       |
      | numero_documento | 9876543210              |
    When la consulta se ejecuta exitosamente
    Then los datos sensibles del cliente no deben exponerse en logs
    And la respuesta debe contener únicamente los campos autorizados:
      | Campo              | visible |
      | score_crediticio   | true    |
      | historial_pagos    | true    |
      | deudas_activas     | true    |
      | numero_documento   | false   |
      | direccion_residencia | false |
      | telefono           | false   |

  @smoke @riesgo-medio
  Scenario: Validar integridad de datos en comunicación entre componentes
    Given que el core bancario procesa una transacción de transferencia
      | Campo             | Valor                    |
      | cuenta_origen     | 1234567890               |
      | cuenta_destino    | 0987654321               |
      | monto             | 15000.00                 |
      | tipo_transaccion  | TRANSFERENCIA_TERCERO   |
    When se establece comunicación con el motor antifraude
    Then los datos en tránsito deben estar cifrados con AES-256
    And el hash de integridad debe ser calculable
    And el tiempo de respuesta no debe exceder "500" milisegundos

  @regression @gestion-vulnerabilidad
  Scenario: Gestionar ciclo de vida de una vulnerabilidad descubierta
    Given que se ha identificado una nueva vulnerabilidad en el sistema
      | Campo              | Valor                              |
      | id_vulnerabilidad  | VULN-2024-0156                     |
      | tipo               | EXPOSICION_DATOS_SENSIBLES         |
      | severidad          | ALTA                               |
      | componente         | BURO_RIESGOS                       |
      | fecha_deteccion    | 2024-01-10                         |
    When el equipo de seguridad evalúa la vulnerabilidad
    Then debe generar un plan de mitigación con:
      | Campo              | Valor                              |
      | accion_inmediata   | AISLAR_COMPONENTE                  |
      | plazo_dias         | 7                                  |
      | responsable        | EQUIPO_SEGURIDAD_BANCA            |
    And registrar el avance en el sistema de seguimiento
    And la trazabilidad debe mantener el historial completo por "5" años

  @stress @riesgo-carga
  Scenario: Evaluar comportamiento del sistema bajo carga máxima de solicitudes
    Given que el sistema está configurado para procesar "1500" solicitudes por segundo
    When se模拟a un incremento progresivo de carga hasta "2000" solicitudes por segundo
    Then el sistema debe mantener el SLA del "99.9" por ciento
    And los tiempos de respuesta deben mantenerse dentro de los umbrales:
      | Tipo de operación   | Umbral ms |
      | Consulta buró       | 300       |
      | Evaluación fraude   | 200       |
      | Procesamiento crédito| 500      |
    And no debe haber pérdida de eventos de seguridad
    And la trazabilidad debe registrar todos los eventos incluso bajo carga

  @integration @riesgo-integracion
  Scenario: Validar que las vulnerabilidades en componentes externos no comprometan el sistema
    Given que el sistema integra con servicios externos del buró de riesgos
    When un servicio externo responde con datos maliciosos
      | Campo           | Valor                    |
      | codigo_respuesta| 200                      |
      | contenido       | <script>alert('xss')</script> |
    Then el sistema debe sanitizar la entrada antes de procesarla
    And no debe ejecutar código malicioso
    And debe registrar el intento de ataque en el log de seguridad
    And generar alerta de seguridad con severidad "ALTA"

  @regression @riesgo-continuidad
  Scenario: Verificar plan de contingencia ante falla de componente crítico
    Given que el motor antifraude deja de responder
      | Campo           | Valor                    |
      | componente      | MOTOR_ANTIFRAUDE         |
      | tipo_falla      | NO_DISPONIBLE            |
    When el originador de créditos recibe una nueva solicitud
    Then el sistema debe activar el modo de contingencia
    And rechazar la solicitud con código "SERVICIO_NO_DISPONIBLE"
    And registrar la contingencia en auditoría
    And notificar al equipo de operaciones
    And mantener trazabilidad del evento de contingencia

  @smoke @riesgo-final
  Scenario: Ejecutar evaluación completa de riesgos del sistema
    Given que se solicita una evaluación de riesgos integral
    When el sistema ejecuta el análisis de todos los componentes
      | Componente           | Estado  | Vulnerabilidades |
      | Originador de créditos | ACTIVO  | 3                |
      | Motor antifraude      | ACTIVO  | 1                |
      | Buró de riesgos       | ACTIVO  | 2                |
      | Core bancario         | ACTIVO  | 0                |
    Then debe generar un informe de evaluación de riesgos con:
      | Campo              | Valor                              |
      | riesgo_total       | 6                                  |
      | criticos           | 1                                  |
      | altos              | 2                                  |
      | medios             | 2                                  |
      | bajos              | 1                                  |
      | fecha_evaluacion   | 2024-01-15                         |
      | proxima_evaluacion | 2024-02-15                         |
    And calcular el riesgo residual después de mitigaciones
    And actualizar el dashboard de seguridad en tiempo real

  @regression @trazabilidad
  Scenario: Validar que todos los eventos de seguridad mantengan trazabilidad de 5 años
    Given que se genera un evento de seguridad
      | Campo           | Valor                    |
      | tipo_evento     | LOGIN_FALLIDO            |
      | usuario         | usuario_prueba           |
      | ip_origen       | 192.168.1.100           |
      | intentos        | 5                        |
    When el evento se persiste en la base de datos
    Then debe ser recuperable durante el período de "5" años
    And debe contener todos los campos de trazabilidad:
      | Campo              | Valor                              |
      | timestamp          | Debe estar presente               |
      | id_usuario         | Debe estar presente               |
      | ip_origen          | Debe estar presente               |
      | accion             | Debe estar presente               |
      | resultado          | Debe estar presente               |
      | hash_integridad    | Debe estar presente               |