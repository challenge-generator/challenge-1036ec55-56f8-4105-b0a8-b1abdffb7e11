# AGENTS.md

Instrucciones para el agente de IA que abra este repositorio (Claude Code, Cursor, Codex, Copilot, Gemini). Se cargan solas: no hay que pegar nada en ningun chat.

## Que es este repositorio

Es el codigo base de un reto de aprendizaje de Pragma: **Fundamentos de las pruebas de seguridad en aplicaciones**.

| | |
|---|---|
| Tema | Fundamentos de las pruebas de seguridad |
| Nivel | advanced-l2 |
| Chapter | Calidad de Software |
| Especialidad | Automatizador |
| Stack | Java / Serenity BDD 4.0 |
| Patron arquitectonico | Screenplay con capas de seguridad y pruebas |
| Tiempo estimado | 8 horas |

## Receta del stack

Esqueleto obligatorio:

- `pom.xml en la raiz con el runner y el plugin de reportes`
- `src/test/resources/features con los .feature en Gherkin`
- `src/test/java/.../runners con el runner`
- `src/test/java/.../pages o /tasks con Page Objects o Screenplay`
- `src/test/java/.../steps con los step definitions`
- `serenity.conf o config del entorno`

Trampas conocidas:

- Sin parent POM que gestione versiones, TODA dependencia lleva su `<version>` completa de tres segmentos.
- El groupId de Serenity es `net.serenity-bdd`, NO `org.serenity-bdd`. Con el groupId equivocado el artefacto no existe y el build muere resolviendo dependencias.
- Coordenadas exactas de lo mas usado: Selenium `org.seleniumhq.selenium:selenium-java`, Rest Assured `io.rest-assured:rest-assured`, Karate `com.intuit.karate:karate-junit5`, Cucumber `io.cucumber:cucumber-java`.
- JUnit 5 se declara con `junit-jupiter` (agregador) y necesita `maven-surefire-plugin` reciente para ejecutarse.
- Serenity y Cucumber tienen que ser de lineas compatibles entre si; mezclarlas rompe el runner.

Dependencias:

- net.serenity-bdd:serenity-core 4.0.48
- net.serenity-bdd:serenity-cucumber 4.0.48
- net.serenity-bdd:serenity-rest-assured 4.0.48
- org.seleniumhq.selenium:selenium-java 4.16.1
- io.cucumber:cucumber-java 7.14.0
- io.cucumber:cucumber-junit-platform-engine 7.14.0
- org.junit.jupiter:junit-jupiter-api 5.10.0
- io.rest-assured:rest-assured 5.3.2
- org.projectlombok:lombok 1.18.30
- io.jsonwebtoken:jjwt-api 0.12.3
- io.jsonwebtoken:jjwt-impl 0.12.3
- io.jsonwebtoken:jjwt-jackson 0.12.3
- org.apache.commons:commons-crypto 1.2.0

## Tu tarea

Dejar este proyecto en estado **verificable**: que el comando de verificacion corra sin errores. Escribi los archivos en disco, en este repositorio. No generes ZIPs ni archivos adjuntos.

En orden:

1. Corre `mvn clean test-compile` y mira que falla.
2. Completa lo que falte de la lista de abajo: manifiesto de dependencias, punto de entrada, capa de interfaz y las capas del patron declarado.
3. Arregla SOLO los errores que impiden compilar o arrancar.
4. Volve a correr `mvn clean test-compile` hasta que pase.
5. Pará ahí.

## Regla dura: las fases son trabajo del humano

**PROHIBIDO implementar los entregables de las fases.** El valor del reto esta en que la persona los resuelva. Tu trabajo es que tenga un proyecto que arranca; el hueco pedagogico se queda como esta.

No resuelvas nada de esto:

- **Fase 1 — Evaluación de riesgos y gestión de vulnerabilidades**: Documento que describe las vulnerabilidades identificadas, la evaluación de riesgos y las medidas propuestas para mitigación.
- **Fase 2 — Técnicas de inyección SQL y protección**: Documento que describe los puntos de entrada vulnerables, las medidas propuestas para prevenir y detectar inyección SQL, y la evaluación de su efectividad.
- **Fase 3 — Autenticación, autorización y criptografía**: Documento que describe el esquema de autenticación y autorización propuesto, la evaluación de su seguridad y la aplicación de técnicas de criptografía.
- **Fase 4 — Integración y revisión de las pruebas de seguridad**: Documento que describe la integración de las medidas de seguridad, la revisión y evaluación de las pruebas de seguridad aplicadas y las propuestas de mejora.

Distincion operativa:

- **Arreglar** (si): import faltante, tipo que no existe, dependencia sin declarar, error de sintaxis, archivo referenciado que no existe.
- **No tocar** (no): logica de negocio incompleta, validaciones ausentes, secretos hardcodeados, APIs deprecadas que funcionan, concurrencia insegura, patrones mejorables. Eso es lo que la persona tiene que encontrar.

## Superficie de practica (NO completes)

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs. No toques la logica que el reto pide completar.

- [ ] `src/test/java/com/banca/seguridad/utils/SecurityUtils.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/test/java/com/banca/seguridad/config/SecurityConfig.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- [ ] `src/test/java/com/banca/seguridad/exceptions/SecurityException.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.

## Lo que falta y tenes que completar

### 1. Referencias colgando (29)

Salieron de un analisis estatico del codigo que SI esta en el repo. Cada una rompe la compilacion:

- [ ] `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `org.hamcrest.MatcherAssert`
      El import org.hamcrest.MatcherAssert.assertThat pertenece a org.hamcrest.MatcherAssert, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `org.hamcrest.Matchers`
      El import org.hamcrest.Matchers pertenece a org.hamcrest.Matchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `org.slf4j`
      El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `org.hamcrest.MatcherAssert`
      El import org.hamcrest.MatcherAssert.assertThat pertenece a org.hamcrest.MatcherAssert, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `org.hamcrest.Matchers`
      El import org.hamcrest.Matchers pertenece a org.hamcrest.Matchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/banca/seguridad/steps/AutenticacionSteps.java` — `org.hamcrest.CoreMatchers`
      El import org.hamcrest.CoreMatchers.containsString pertenece a org.hamcrest.CoreMatchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/banca/seguridad/tasks/RealizarLogin.java` — `org.hamcrest.CoreMatchers`
      El import org.hamcrest.CoreMatchers pertenece a org.hamcrest.CoreMatchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/banca/seguridad/tasks/VerificarVulnerabilidadSQL.java` — `org.hamcrest.CoreMatchers`
      El import org.hamcrest.CoreMatchers pertenece a org.hamcrest.CoreMatchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/banca/seguridad/questions/ValidarAcceso.java` — `org.hamcrest.Matchers`
      El import org.hamcrest.Matchers pertenece a org.hamcrest.Matchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/banca/seguridad/questions/ValidarVulnerabilidad.java` — `org.json`
      El import org.json.JSONObject pertenece a org.json, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/banca/seguridad/config/SecurityConfig.java` — `org.springframework.context`
      El import org.springframework.context.annotation.Bean pertenece a org.springframework.context, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/banca/seguridad/config/SecurityConfig.java` — `org.springframework.security`
      El import org.springframework.security.config.annotation.web.builders.HttpSecurity pertenece a org.springframework.security, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- [ ] `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `SecurityConfig.ejecutarAnalisisVulnerabilidades`
      Se invoca `ejecutarAnalisisVulnerabilidades` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `SecurityConfig.clasificarPorSeveridad`
      Se invoca `clasificarPorSeveridad` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `SecurityConfig.calcularImpacto`
      Se invoca `calcularImpacto` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `SecurityConfig.filtrarVulnerabilidadesCriticas`
      Se invoca `filtrarVulnerabilidadesCriticas` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `SecurityConfig.obtenerControlesExistentes`
      Se invoca `obtenerControlesExistentes` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `SecurityConfig.generarRecomendaciones`
      Se invoca `generarRecomendaciones` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `SecurityConfig.registrarTrazabilidad`
      Se invoca `registrarTrazabilidad` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.simularConsultaConInput`
      Se invoca `simularConsultaConInput` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.verificarProteccionInyeccionSQL`
      Se invoca `verificarProteccionInyeccionSQL` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.registrarIntentoInyeccion`
      Se invoca `registrarIntentoInyeccion` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.verificarSanitizacionEntradas`
      Se invoca `verificarSanitizacionEntradas` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.verificarConsultasParametrizadas`
      Se invoca `verificarConsultasParametrizadas` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.identificarPuntosEntradaVulnerables`
      Se invoca `identificarPuntosEntradaVulnerables` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.obtenerMedidasProteccion`
      Se invoca `obtenerMedidasProteccion` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.generarReporteInyeccion`
      Se invoca `generarReporteInyeccion` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- [ ] `pom.xml` — `net.serenity-bdd:serenity-bom@4.0.48`
      net.serenity-bdd:serenity-bom declara la version 4.0.48, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

### Presentes (18)

- `pom.xml`
- `src/test/resources/features/seguridad/evaluacion_riesgos.feature`
- `src/test/resources/features/seguridad/inyeccion_sql.feature`
- `src/test/resources/features/seguridad/autenticacion.feature`
- `src/test/java/com/banca/seguridad/runners/RunCucumberTest.java`
- `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java`
- `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java`
- `src/test/java/com/banca/seguridad/steps/AutenticacionSteps.java`
- `src/test/java/com/banca/seguridad/tasks/RealizarLogin.java`
- `src/test/java/com/banca/seguridad/tasks/VerificarVulnerabilidadSQL.java`
- `src/test/java/com/banca/seguridad/questions/ValidarAcceso.java`
- `src/test/java/com/banca/seguridad/questions/ValidarVulnerabilidad.java`
- `src/test/java/com/banca/seguridad/utils/SecurityUtils.java`
- `src/test/java/com/banca/seguridad/config/SecurityConfig.java`
- `src/test/java/com/banca/seguridad/exceptions/SecurityException.java`
- `src/test/resources/data/vulnerabilidades.csv`
- `serenity.conf`
- `README.md`

### Capas del patron declarado

Cada una tiene que existir como directorio real con al menos un archivo. Codigo plano en la raiz no satisface el patron.

- `src/test/java/com/banca/seguridad`
- `src/test/java/com/banca/seguridad/tasks`
- `src/test/java/com/banca/seguridad/questions`
- `src/test/java/com/banca/seguridad/interactions`
- `src/test/java/com/banca/seguridad/models`
- `src/test/java/com/banca/seguridad/exceptions`
- `src/test/java/com/banca/seguridad/utils`
- `src/test/java/com/banca/seguridad/config`
- `src/test/resources/features`
- `src/test/resources/data`
- `reports`

## Verificacion

```bash
mvn clean test-compile
```

El comando tiene que pasar SIN implementar los archivos de la superficie de practica: solo andamiaje.

Ese comando pasando es la definicion de "terminado" para vos.

## Convenciones que tenes que respetar

- Un solo ecosistema: no declares librerias de otro lenguaje ni mezcles gestores de paquetes.
- Toda libreria que uses tiene que estar declarada en el manifiesto de dependencias.
- Todo import declarado tiene que usarse; todo tipo usado tiene que existir o venir de una dependencia declarada.
- El patron es **Screenplay con capas de seguridad y pruebas**: los contratos (interfaces, puertos) los define la capa interna y los implementa la externa, nunca al revés.
- Los archivos que crees llevan implementacion real, no stubs: sin `TODO`, sin cuerpos vacios, sin `// getters y setters`.

## Contexto del candidato

Sirve para calibrar el nivel del codigo, no para resolver las fases.

- Perfil: Chapter Calidad de Software, Especialidad Automatizador, Tecnología Seguridad, Advanced
- Brecha que el reto ataca: Comprende conceptos relacionados con las pruebas de seguridad, como: evaluación de riesgos y su gestión, la identificación de vulnerabilidades, las técnicas de inyección SQL, la autenticación, la autorización y la criptografía aplicados en proyectos de manera efectiva
- Mision: Candidato Advanced en automatización de pruebas, enfocado en garantizar la calidad y seguridad de aplicaciones

---

*Generado por Challenge Generator — Pragma. `README.md` tiene el enunciado completo del reto para la persona. `PROMPT_MEJORA.md` es la variante para pegar en un chat, si se prefiere ese flujo.*
