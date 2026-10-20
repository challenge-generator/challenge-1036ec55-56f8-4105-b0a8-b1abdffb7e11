# Fundamentos de las pruebas de seguridad en aplicaciones

En el contexto de una plataforma de banca en línea, el candidato deberá comprender y aplicar conceptos clave de las pruebas de seguridad para garantizar la calidad y protección de las aplicaciones. Los actores involucrados son el 'originador de créditos', el 'motor antifraude', el 'buró de riesgos', y el 'core bancario'. Las pruebas de seguridad deben cubrir la evaluación de riesgos, gestión de vulnerabilidades, técnicas de inyección SQL, autenticación, autorización y criptografía. El sistema debe manejar un volumen de 1 500 solicitudes por segundo en hora pico, con un SLA de 99.9% y una trazabilidad de eventos de 5 años.

## Informacion General

| Campo | Valor |
|-------|-------|
| **Tema** | Fundamentos de las pruebas de seguridad |
| **Nivel** | advanced-l2 |
| **Tipo** | theoretical |
| **Tiempo estimado** | 8 horas |

## Fases del Reto

### Fase 0: Configuración del Proyecto

**Objetivo:** Obtener el proyecto base funcional enviando el Código Base a un asistente de IA, que lo analizará, corregirá errores y generará un ZIP listo para usar.

**Tiempo estimado:** 15-30 minutos

**Instrucciones:**

- Asegúrate de tener instalado para ejecutar el proyecto: JDK 17+, Maven 3.9+, IDE con soporte Java.
- Copia todo el contenido del campo **Código Base** de este reto — incluyendo el texto de instrucciones que aparece al inicio.
- Abre un asistente de IA (Claude en claude.ai, ChatGPT o Gemini — se recomienda Claude), pega el contenido copiado en el chat y envíalo.
- El asistente analizará los archivos, corregirá errores y generará un archivo ZIP descargable. Descárgalo y extráelo en la carpeta donde quieras trabajar.
- Ejecuta `mvn compile` en la raíz. Si no hay errores, estás listo.

**Entregable:** El proyecto compila/arranca sin errores.

<details>
<summary>Pistas de conocimiento</summary>

- Copia el Código Base completo incluyendo el texto de instrucciones al inicio — esas instrucciones le indican al asistente exactamente qué hacer con los archivos.
- Si el asistente no genera el ZIP automáticamente al terminar el análisis, escríbele: "genera el ZIP ahora".
- Si el proyecto tiene errores al arrancar, comparte el mensaje de error con el mismo asistente para que lo corrija.

</details>

### Fase 1: Evaluación de riesgos y gestión de vulnerabilidades

**Objetivo:** Comprender y aplicar técnicas de evaluación de riesgos y gestión de vulnerabilidades en el contexto de una plataforma de banca en línea.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar las principales vulnerabilidades en una aplicación de banca en línea.
- Evaluar los riesgos asociados a cada vulnerabilidad.
- Proponer medidas para mitigar los riesgos identificados.

**Entregable:** Documento que describe las vulnerabilidades identificadas, la evaluación de riesgos y las medidas propuestas para mitigación.

<details>
<summary>Pistas de conocimiento</summary>

- Considera el impacto de cada vulnerabilidad en la confidencialidad, integridad y disponibilidad de los datos.
- Investiga metodologías comunes para la evaluación de riesgos en aplicaciones financieras.

</details>

### Fase 2: Técnicas de inyección SQL y protección

**Objetivo:** Identificar y proteger contra técnicas de inyección SQL en una aplicación de banca en línea.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Identificar puntos de entrada vulnerables a inyección SQL en la aplicación.
- Proponer medidas para prevenir y detectar inyección SQL.
- Evaluar la efectividad de las medidas propuestas.

**Entregable:** Documento que describe los puntos de entrada vulnerables, las medidas propuestas para prevenir y detectar inyección SQL, y la evaluación de su efectividad.

<details>
<summary>Pistas de conocimiento</summary>

- Investiga ejemplos de inyección SQL en aplicaciones financieras.
- Considera el uso de parámetros preparados y validación de entrada como medidas de protección.

</details>

### Fase 3: Autenticación, autorización y criptografía

**Objetivo:** Aplicar conceptos de autenticación, autorización y criptografía en una aplicación de banca en línea.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Proponer un esquema de autenticación y autorización para la aplicación.
- Evaluar la seguridad de los métodos de autenticación y autorización propuestos.
- Aplicar técnicas de criptografía para proteger la confidencialidad y integridad de los datos.

**Entregable:** Documento que describe el esquema de autenticación y autorización propuesto, la evaluación de su seguridad y la aplicación de técnicas de criptografía.

<details>
<summary>Pistas de conocimiento</summary>

- Investiga metodologías comunes para la autenticación y autorización en aplicaciones financieras.
- Considera el uso de tokens de acceso y cifrado de datos en tránsito y en reposo.

</details>

### Fase 4: Integración y revisión de las pruebas de seguridad

**Objetivo:** Integrar y revisar las pruebas de seguridad aplicadas en la aplicación de banca en línea.

**Tiempo estimado:** 2 horas

**Instrucciones:**

- Integrar las medidas de seguridad propuestas en las fases anteriores en la aplicación.
- Revisar y evaluar la efectividad de las pruebas de seguridad aplicadas.
- Proponer mejoras para fortalecer la seguridad de la aplicación.

**Entregable:** Documento que describe la integración de las medidas de seguridad, la revisión y evaluación de las pruebas de seguridad aplicadas y las propuestas de mejora.

<details>
<summary>Pistas de conocimiento</summary>

- Considera la realización de pruebas de penetración para evaluar la efectividad de las medidas de seguridad.
- Investiga metodologías para la revisión y mejora continua de las pruebas de seguridad en aplicaciones financieras.

</details>

## Dimensiones Evaluadas

- **queEs**: ¿Qué son las pruebas de seguridad y por qué son importantes en el contexto de una aplicación de banca en línea?
- **paraQueSirve**: ¿Para qué sirven las pruebas de seguridad en una aplicación de banca en línea y cómo contribuyen a la calidad y protección de la aplicación?
- **comoSeUsa**: ¿Cómo se aplican las pruebas de seguridad en una aplicación de banca en línea y qué medidas se pueden tomar para mitigar los riesgos identificados?
- **erroresComunes**: ¿Cuáles son los errores comunes en la aplicación de pruebas de seguridad en una aplicación de banca en línea y cómo se pueden evitar?
- **queDecisionesImplica**: ¿Qué decisiones implica la aplicación de pruebas de seguridad en una aplicación de banca en línea y cómo se pueden justificar estas decisiones?

## Criterios de Evaluacion

- Identificación correcta de vulnerabilidades en una aplicación de banca en línea.
- Evaluación efectiva de riesgos asociados a las vulnerabilidades identificadas.
- Propuesta de medidas efectivas para mitigar los riesgos identificados.
- Identificación de puntos de entrada vulnerables a inyección SQL y propuesta de medidas para prevenir y detectar inyección SQL.
- Aplicación efectiva de conceptos de autenticación, autorización y criptografía en una aplicación de banca en línea.
- Integración y revisión efectiva de las pruebas de seguridad aplicadas en la aplicación de banca en línea.
- Propuesta de mejoras efectivas para fortalecer la seguridad de la aplicación de banca en línea.

## Como trabajar con un asistente de IA

Hay dos caminos, elegi uno:

- **AGENTS.md** (recomendado) — instrucciones nativas del repo. Abri esta carpeta con tu agente local (Claude Code, Cursor, Codex, Copilot, Gemini) y las carga solo. Sabe que archivos faltan y con que comando se verifica, y completa el scaffold escribiendo en disco.
- **PROMPT_MEJORA.md** — para copiar y pegar en un chat (claude.ai, ChatGPT). Devuelve un ZIP con el proyecto. Sirve si no tenes un agente en el IDE.

Ninguno de los dos resuelve las fases del reto: eso es tu trabajo.

## Verificacion

El proyecto esta listo para trabajar cuando este comando corre sin errores:

```bash
mvn clean test-compile
```

---

*Reto generado automaticamente por Challenge Generator - Pragma*
