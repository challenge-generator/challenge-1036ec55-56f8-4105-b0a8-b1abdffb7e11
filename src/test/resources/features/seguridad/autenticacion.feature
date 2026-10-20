Feature: Autenticación, autorización y criptografía en plataforma de banca en línea

  Como analista de seguridad
  Necesito verificar que el sistema implementa mecanismos seguros de autenticación y autorización
  Para garantizar que solo usuarios autorizados accedan a funcionalidades sensibles

  Background:
    Given que la plataforma de banca en línea maneja información financiera sensible
    And debe cumplir con estándares de seguridad PCI-DSS
    And el sistema utiliza tokens JWT para gestión de sesiones
    And los datos sensibles deben cifrarse en reposo y en tránsito

  @smoke @autenticacion-login
  Scenario: Autenticar usuario con credenciales válidas
    Given que el usuario intenta acceder al sistema
      | Campo           | Valor                    |
      | tipo_documento  | CC                       |
      | numero_documento| 1234567890              |
      | contraseña      | SecureP@ss123            |
    When el sistema valida las credenciales
    Then debe generar un token JWT válido
      | Campo              | Valor                              |
      | algoritmo          | RS256                              |
      | tiempo_expiracion  | 3600 segundos                      |
      | claims_required    | sub, rol, permisos, exp           |
    And devolver código de respuesta "200"
    And incluir el token en la cabecera "Authorization: Bearer"

  @regression @autenticacion-fallo
  Scenario: Rechazar autenticación con credenciales inválidas
    Given que el usuario proporciona credenciales incorrectas
      | Campo           | Valor                    |
      | numero_documento| 1234567890              |
      | contraseña      | ContrasenaIncorrecta    |
    When el sistema verifica las credenciales
    Then debe rechazar el acceso
    And devolver código de error "401 Unauthorized"
    And mostrar mensaje genérico "Credenciales inválidas"
    And no debe revelar si el usuario existe en el sistema
    And debe registrar el intento fallido en auditoría

  @smoke @autenticacion-bloqueo
  Scenario: Bloquear cuenta después de múltiples intentos fallidos
    Given que un usuario intenta autenticarse sin éxito
      | Campo           | Valor                    |
      | intentos_fallidos | 5                        |
      | ventana_tiempo  | 15 minutos               |
    When supera el límite de intentos permitidos
    Then el sistema debe bloquear la cuenta
    And generar código de error "423 Locked"
    And enviar notificación al correo registrado del usuario
    And registrar el evento de bloqueo en auditoría
      | Campo              | Valor                              |
      | evento             | CUENTA_BLOQUEADA                   |
      | motivo             | INTENTOS_EXCEDIDOS                 |
      | intentos           | 5                                  |
      | duracion_bloqueo   | 30 minutos                         |

  @regression @autenticacion-token
  Scenario: Validar token JWT en solicitudes autenticadas
    Given que el usuario ha iniciado sesión correctamente
    And posee un token JWT válido
      | Campo              | Valor                              |
      | token              | eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9... |
      | tiempo_expiracion  | 2024-01-15T11:30:00Z              |
    When realiza una solicitud a la API de consulta de saldo
      | Campo           | Valor                    |
      | endpoint        | /api/v1/cuenta/saldo     |
      | método          | GET                      |
    Then el sistema debe validar la firma del token
    And verificar que no ha expirado
    And confirmar que el usuario tiene permisos para la operación
    And devolver el saldo actual de la cuenta

  @smoke @autorizacion-roles
  Scenario: Verificar autorización basada en roles
    Given que el usuario tiene el rol de "originador_creditos"
    When intenta acceder a funcionalidad de aprobación de créditos
      | Campo           | Valor                    |
      | recurso         | /api/v1/credito/aprobar |
      | método          | POST                     |
    Then el sistema debe verificar los permisos del rol
    And permitir el acceso si el rol tiene autorización
    And devolver código "200" con resultado de la operación

  @regression @autorizacion-denegacion
  Scenario: Denegar acceso a usuario sin permisos suficientes
    Given que el usuario tiene el rol de "consulta"
    When intenta acceder a funcionalidad de administración
      | Campo           | Valor                    |
      | recurso         | /api/v1/admin/usuarios  |
      | método          | POST                     |
    Then el sistema debe rechazar la solicitud
    And devolver código de error "403 Forbidden"
    And incluir mensaje "No tiene permisos para realizar esta operación"
    And registrar el intento de acceso no autorizado

  @integration @autorizacion-recursos
  Scenario: Validar que usuario solo acceda a sus propios recursos
    Given que el usuario "1234567890" está autenticado
    When intenta acceder a información de la cuenta "0987654321"
      | Campo           | Valor                    |
      | endpoint        | /api/v1/cuenta/0987654321|
    Then el sistema debe verificar la propiedad del recurso
    And rechazar el acceso con código "403"
    And registrar el intento de acceso indebido

  @smoke @criptografia-tls
  Scenario: Verificar que todas las comunicaciones utilizan TLS
    Given que el usuario establece conexión con el servidor
    When se establece el handshake de TLS
    Then la conexión debe utilizar TLS versión "1.2" o superior
    And debe utilizar cifrado "AES-256-GCM"
    And el certificado del servidor debe ser válido
    And no debe aceptar cifrados obsoletos (SSLv3, TLS 1.0, TLS 1.1)

  @regression @criptografia-sensible
  Scenario: Cifrar datos sensibles en reposo
    Given que el sistema almacena información sensible del cliente
      | Campo           | Valor                    |
      | tipo_dato       | Número de tarjeta       |
      | valor_plano     | 4532-1234-5678-9012     |
    When los datos se persisten en la base de datos
    Then deben estar cifrados con AES-256
    And la clave de cifrado debe almacenarse de forma segura
    And el sistema de gestión de claves debe estar configurado
    And el dato cifrado no debe ser reversible sin la clave

  @smoke @criptografia-hash
  Scenario: Almacenar contraseñas utilizando hash seguro
    Given que el usuario proporciona una nueva contraseña
      | Campo           | Valor                    |
      | contraseña      | MiPassword123!           |
    When el sistema procesa el registro de la contraseña
    Then debe aplicar función de hashbcrypt con costo "12"
    And almacenar únicamente el hash, nunca la contraseña en texto
    And el salt debe ser único por usuario
    And verificar que no sea posible revertir la contraseña

  @regression @criptografia-jwt
  Scenario: Firmar tokens JWT con clave asimétrica
    Given que el sistema genera un token JWT para el usuario
      | Campo              | Valor                              |
      | algoritmo          | RS256                              |
      | clave_privada      | RSA-2048 bits                      |
    When el token se crea y firma
    Then debe incluir firma digital válida
    And el receptor puede verificar con la clave pública
    And el token no debe ser modificable sin invalidar la firma
    And debe incluir claims de seguridad estándar

  @integration @autenticacion-mfa
  Scenario: Implementar autenticación multifactor para operaciones sensibles
    Given que el usuario intenta realizar una transferencia mayor a "10000"
      | Campo           | Valor                    |
      | monto           | 15000.00                 |
      | tipo_operacion  | TRANSFERENCIA            |
    When el sistema detecta operación sensible
    Then debe solicitar factor adicional de autenticación
    And validar código OTP enviado al teléfono registrado
    And verificar que el código es válido y no ha expirado
    And solo completar la operación si el segundo factor es válido
    And registrar la operación con verificación MFA

  @smoke @autorizacion-sesion
  Scenario: Gestionar sesión de usuario de forma segura
    Given que el usuario inicia sesión en el sistema
    When la sesión está activa
    Then el identificador de sesión debe ser aleatorio y único
    And el tiempo de expiración debe ser de "30" minutos de inactividad
    And debe invalidarse al cerrar sesión
    And debe invalidarse después de "8" horas máximo de uso continuo
    And la cookie de sesión debe tener flags "HttpOnly" y "Secure"

  @regression @criptografia-aes
  Scenario: Cifrar datos de transacción con AES-256
    Given que el sistema procesa una transacción
      | Campo           | Valor                    |
      | id_transaccion  | TXN-2024-001234          |
      | monto           | 25000.00                 |
      | cuenta_origen   | 1234567890               |
      | cuenta_destino  | 0987654321               |
    When los datos se cifran para transmisión
    Then debe utilizar algoritmo AES-256 en modo GCM
    And generar un IV único para cada operación
    And incluir el IV con los datos cifrados
    And la clave AES debe rotarse según política de seguridad

  @stress @autenticacion-carga
  Scenario: Validar rendimiento bajo carga de autenticaciones simultáneas
    Given que el sistema debe procesar "500" autenticaciones por minuto
    When se alcanza el pico de solicitudes de login
    Then el tiempo de respuesta promedio debe ser menor a "200" ms
    And el sistema debe mantener la validación de tokens
    And no debe haber degradación en la verificación de permisos
    And debe mantener la trazabilidad de todos los eventos