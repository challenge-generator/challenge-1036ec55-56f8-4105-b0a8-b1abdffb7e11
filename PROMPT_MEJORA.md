# Prompt para Mejorar el Codigo Base

Copia y pega el contenido del bloque de abajo en un asistente de IA (Claude, ChatGPT)
para obtener un ZIP con el proyecto completo y arrancable.

Si preferis trabajar en tu editor con un agente local (Claude Code, Cursor, Copilot), usa `AGENTS.md` en vez de este archivo: dice lo mismo pero para que escriba los archivos en disco.

## Las dos reglas que no se negocian

1. **Completa el boilerplate.** Todo lo que el proyecto necesita para compilar y arrancar: manifiesto de dependencias, punto de entrada, configuracion, capa de interfaz, y las capas del patron arquitectonico declarado. Eso es andamiaje y es tu trabajo.
2. **NO resuelvas el reto.** Los entregables de las fases son el trabajo de la persona. El hueco pedagogico se deja como esta: el proyecto arranca, pero lo que el reto pide implementar NO esta implementado.

Dicho de otra forma: si algo impide compilar, arreglalo. Si algo es logica de negocio incompleta, validaciones ausentes, un secreto hardcodeado o un patron mejorable, dejalo exactamente como esta — es lo que la persona tiene que encontrar.

## Superficie de practica — NO resuelvas

Estos archivos SON el ejercicio de la persona. No los implementes; deja stubs.

- `src/test/java/com/banca/seguridad/utils/SecurityUtils.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/test/java/com/banca/seguridad/config/SecurityConfig.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.
- `src/test/java/com/banca/seguridad/exceptions/SecurityException.java` — El topic pide autenticacion/seguridad: este archivo es el ejercicio.

## Lo que le falta a este proyecto

Esto NO lo tenes que adivinar: salio de comparar el proyecto contra la arquitectura declarada del reto y de un analisis estatico del codigo. Completalo TODO.

### Referencias colgando en el codigo que si esta

Cada una rompe la compilacion:

- `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `org.hamcrest.MatcherAssert`: El import org.hamcrest.MatcherAssert.assertThat pertenece a org.hamcrest.MatcherAssert, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `org.hamcrest.Matchers`: El import org.hamcrest.Matchers pertenece a org.hamcrest.Matchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `org.slf4j`: El import org.slf4j.Logger pertenece a org.slf4j, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `org.hamcrest.MatcherAssert`: El import org.hamcrest.MatcherAssert.assertThat pertenece a org.hamcrest.MatcherAssert, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `org.hamcrest.Matchers`: El import org.hamcrest.Matchers pertenece a org.hamcrest.Matchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/banca/seguridad/steps/AutenticacionSteps.java` — `org.hamcrest.CoreMatchers`: El import org.hamcrest.CoreMatchers.containsString pertenece a org.hamcrest.CoreMatchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/banca/seguridad/tasks/RealizarLogin.java` — `org.hamcrest.CoreMatchers`: El import org.hamcrest.CoreMatchers pertenece a org.hamcrest.CoreMatchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/banca/seguridad/tasks/VerificarVulnerabilidadSQL.java` — `org.hamcrest.CoreMatchers`: El import org.hamcrest.CoreMatchers pertenece a org.hamcrest.CoreMatchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/banca/seguridad/questions/ValidarAcceso.java` — `org.hamcrest.Matchers`: El import org.hamcrest.Matchers pertenece a org.hamcrest.Matchers, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/banca/seguridad/questions/ValidarVulnerabilidad.java` — `org.json`: El import org.json.JSONObject pertenece a org.json, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/banca/seguridad/config/SecurityConfig.java` — `org.springframework.context`: El import org.springframework.context.annotation.Bean pertenece a org.springframework.context, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/banca/seguridad/config/SecurityConfig.java` — `org.springframework.security`: El import org.springframework.security.config.annotation.web.builders.HttpSecurity pertenece a org.springframework.security, pero ninguna dependencia declarada en el pom.xml cubre ese paquete. Falta agregar la dependencia o el import esta mal (libreria equivocada).
- `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `SecurityConfig.ejecutarAnalisisVulnerabilidades`: Se invoca `ejecutarAnalisisVulnerabilidades` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `SecurityConfig.clasificarPorSeveridad`: Se invoca `clasificarPorSeveridad` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `SecurityConfig.calcularImpacto`: Se invoca `calcularImpacto` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `SecurityConfig.filtrarVulnerabilidadesCriticas`: Se invoca `filtrarVulnerabilidadesCriticas` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `SecurityConfig.obtenerControlesExistentes`: Se invoca `obtenerControlesExistentes` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `SecurityConfig.generarRecomendaciones`: Se invoca `generarRecomendaciones` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java` — `SecurityConfig.registrarTrazabilidad`: Se invoca `registrarTrazabilidad` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.simularConsultaConInput`: Se invoca `simularConsultaConInput` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.verificarProteccionInyeccionSQL`: Se invoca `verificarProteccionInyeccionSQL` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.registrarIntentoInyeccion`: Se invoca `registrarIntentoInyeccion` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.verificarSanitizacionEntradas`: Se invoca `verificarSanitizacionEntradas` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.verificarConsultasParametrizadas`: Se invoca `verificarConsultasParametrizadas` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.identificarPuntosEntradaVulnerables`: Se invoca `identificarPuntosEntradaVulnerables` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.obtenerMedidasProteccion`: Se invoca `obtenerMedidasProteccion` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java` — `SecurityConfig.generarReporteInyeccion`: Se invoca `generarReporteInyeccion` sobre `SecurityConfig`, pero esa clase no declara ese metodo. Agregalo con su implementacion real, o usa uno de los que si declara.
- `pom.xml` — `net.serenity-bdd:serenity-bom@4.0.48`: net.serenity-bdd:serenity-bom declara la version 4.0.48, pero Maven Central respondio que esa version no existe. Es una version inventada: reemplazala por una version publicada real, o si no se conoce con certeza, usa el mecanismo centralizado del ecosistema (BOM/parent/platform/version catalog) y no declares una version individual.

## Como saber que terminaste

```bash
mvn clean test-compile
```

Ese comando corriendo sin errores es la definicion de "listo".

---

```
## Briefing del reto (autoridad)
Este bloque manda sobre los archivos adjuntos. El stack y el rol salen de AQUÍ, no de un topic genérico ni de markdown placeholder.

### Perfil
Chapter Calidad de Software, Especialidad Automatizador, Tecnología Seguridad, Advanced

### Brecha de conocimiento
Comprende conceptos relacionados con las pruebas de seguridad, como: evaluación de riesgos y su gestión, la identificación de vulnerabilidades, las técnicas de inyección SQL, la autenticación, la autorización y la criptografía aplicados en proyectos de manera efectiva

### Misión / candidato
Candidato Advanced en automatización de pruebas, enfocado en garantizar la calidad y seguridad de aplicaciones

### Reto
- Tema: Fundamentos de las pruebas de seguridad
- Seniority: advanced-l2
- Tipo: theoretical
- Título: Fundamentos de las pruebas de seguridad en aplicaciones
- Tiempo estimado: 8 horas

### Fases (trabajo del HUMANO — PROHIBIDO completarlas)
No implementes estos entregables. Dejalos como hueco pedagógico. El asistente solo materializa el proyecto arrancable para que el participante pueda trabajar.
- Fase 1: Evaluación de riesgos y gestión de vulnerabilidades — objetivo: Comprender y aplicar técnicas de evaluación de riesgos y gestión de vulnerabilidades en el contexto de una plataforma de banca en línea. — entregable (NO resolver): Documento que describe las vulnerabilidades identificadas, la evaluación de riesgos y las medidas propuestas para mitigación.
- Fase 2: Técnicas de inyección SQL y protección — objetivo: Identificar y proteger contra técnicas de inyección SQL en una aplicación de banca en línea. — entregable (NO resolver): Documento que describe los puntos de entrada vulnerables, las medidas propuestas para prevenir y detectar inyección SQL, y la evaluación de su efectividad.
- Fase 3: Autenticación, autorización y criptografía — objetivo: Aplicar conceptos de autenticación, autorización y criptografía en una aplicación de banca en línea. — entregable (NO resolver): Documento que describe el esquema de autenticación y autorización propuesto, la evaluación de su seguridad y la aplicación de técnicas de criptografía.
- Fase 4: Integración y revisión de las pruebas de seguridad — objetivo: Integrar y revisar las pruebas de seguridad aplicadas en la aplicación de banca en línea. — entregable (NO resolver): Documento que describe la integración de las medidas de seguridad, la revisión y evaluación de las pruebas de seguridad aplicadas y las propuestas de mejora.

Eres un asistente experto en análisis, corrección y generación de archivos de cualquier tipo:
código fuente, documentación, hojas de cálculo, documentos Word, configuraciones, entre otros.
Voy a enviarte una cadena de texto que contiene uno o más archivos. Cada archivo está delimitado por un marcador con el siguiente formato:
// === ARCHIVO: ruta/del/archivo.extension ===
o también puede aparecer como:
## === ARCHIVO: ruta/del/archivo.extension ===
Lo que sigue al marcador puede ser:

El contenido real del archivo (código, texto, YAML, etc.)
Una descripción en lenguaje natural de lo que debe contener el archivo


TU TAREA
PASO 0 — ¿Esto es un proyecto o una carcasa?
Antes de extraer archivos, leé el Briefing (si está) y diagnosticá el adjunto.

Es CARCASA si ocurre CUALQUIERA de estas:
- No hay manifiesto de dependencias del stack del briefing (manifest.json de VTEX IO / package.json / pom.xml / build.gradle / requirements.txt / go.mod / *.tf / *.csproj, según corresponda)
- Hay un "binario" que en realidad es un comentario ("no puede ser mostrado como texto plano", placeholder .fig/.docx vacío)
- Los markdowns ya completan entregables de fases posteriores ("se implementó fade-in", lista de áreas ya resuelta)

Si es CARCASA:
- MATERIALIZÁ un proyecto que arranca en el stack del briefing (VTEX IO Store Framework, Angular, Terraform, pytest, Nest, etc.). Incluí manifiesto, punto de entrada y capa de interfaz reales.
- NO copies los markdowns de "solución" como si fueran el producto. Son ruido de generación.
- NO resuelvas las fases del briefing (están marcadas PROHIBIDO). Dejá el hueco pedagógico: el flujo existe, las microinteracciones/calidad/infra que el reto pide NO están hechas.
- Después seguí al PASO 5 (ZIP).

Si es un proyecto REAL (manifiesto + código que compila o arranca):
- Seguí PASO 1 en adelante. 🔴 compilación sí. 🟡 pedagógico no.

PASO 1 — Detección y extracción
Identifica todos los archivos presentes en la cadena. Para cada archivo extrae:

Su ruta completa (ej: src/main/java/com/pragma/Service.java)
Su contenido o descripción

PASO 2 — Clasificación por tipo
Clasifica cada archivo en una de estas categorías:
A) Código fuente (Java, Python, TypeScript, JavaScript, Kotlin, etc.)
B) Configuración / documentación (YAML, properties, Markdown, JSON, txt, etc.)
C) Excel (.xlsx, .xls, .csv)
D) Word (.docx, .doc)
E) Otro tipo de archivo binario o especial
PASO 3 — Clasificación de errores en código fuente

Objetivo prioritario: que el proyecto compile. No corrijas flujo de negocio ni lógica funcional.

Antes de modificar cualquier archivo de código fuente, clasifica cada problema encontrado en una de estas dos categorías:
🔴 ERROR DE COMPILACIÓN — corregir siempre
Son errores que impiden que el proyecto arranque, sin valor pedagógico:

Import faltante o incorrecto
Clase, método o variable referenciada que no existe en ningún archivo del proyecto
Error de sintaxis
Anotación con atributos inválidos
Dependencia ausente en pom.xml, package.json, etc.
Archivo referenciado que no existe y debe ser creado con implementación mínima

→ CORREGIR estos errores.
🟡 PROBLEMA FUNCIONAL O DE CALIDAD — preservar siempre
Son problemas que no impiden compilar. Pueden ser intencionales para el aprendizaje:

Clave secreta hardcodeada ("secret", "password123")
API deprecada que funciona pero tiene reemplazo moderno
Lógica de negocio incorrecta o incompleta
Código redundante o de baja legibilidad
Falta de validaciones en flujo de negocio
Patrones de diseño incorrectos pero funcionales
Concurrencia no segura
Configuración funcional pero no óptima

→ PRESERVAR tal cual. No corregir, no mejorar, no comentar.
PASO 4 — Procesamiento según tipo de archivo
Tipo A — Código fuente
Aplica únicamente las correcciones clasificadas como 🔴 ERROR DE COMPILACIÓN.
No alteres ningún elemento clasificado como 🟡 PROBLEMA FUNCIONAL O DE CALIDAD.
Si falta un archivo referenciado, créalo con la implementación mínima necesaria para compilar.
Tipo B — Configuración / documentación
Extrae el contenido tal cual, sin modificaciones salvo errores evidentes de sintaxis
(ej: YAML mal indentado).
Tipo C — Excel (.xlsx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un archivo Excel funcional con:

Fila de encabezados en negrita con color de fondo distintivo
Columnas con ancho ajustado al contenido
Tipos de dato correctos por columna
Validaciones si la descripción lo indica
Hojas nombradas descriptivamente si hay más de una
Filas de ejemplo si no hay datos reales

Tipo D — Word (.docx)
Si viene con contenido real, genera el archivo respetando ese contenido.
Si viene con descripción en lenguaje natural, genera un documento Word funcional con:

Estilos de título (Título 1, Título 2) para jerarquía de secciones
Fuente legible (Calibri o equivalente), tamaño 11-12pt para cuerpo
Márgenes estándar
Tabla de contenido si tiene múltiples secciones
Tablas con encabezados en negrita si aplica

Tipo E — Otro
Genera el archivo con el contenido o estructura más apropiada según la descripción.
PASO 5 — Exportación en ZIP
Empaqueta todos los archivos en un único archivo ZIP descargable respetando exactamente
la estructura de rutas indicada por los marcadores.
El ZIP debe incluir:

Archivos de código con únicamente los errores de compilación corregidos
Archivos de configuración y documentación sin cambios
Archivos nuevos creados para resolver dependencias de compilación faltantes
Archivos Excel y Word generados desde descripción

IMPORTANTE: El ZIP debe estar listo para descargar al finalizar. No preguntes si el usuario
quiere generarlo. Simplemente genera el archivo y proporciona el enlace de descarga; No debes desplegar en el chat el resumen de lo que arreglaste al Zip, solo entregalo.

REGLAS IMPORTANTES

No omitas ningún archivo aunque no tenga errores ni modificaciones
Respeta los nombres y rutas exactas indicadas por los marcadores
Si un archivo no tiene marcador claro, infiere el nombre desde su contenido
Si la cadena contiene solo documentación, placeholders o binarios fake, NO la reproduzcas:
aplicá PASO 0 (materializar el proyecto del briefing). Reproducir la carcasa es un fallo.
No agregues texto después del enlace de descarga del ZIP
No preguntes si el usuario quiere el ZIP: simplemente generalo siempre
Si detectas que falta un archivo de configuración necesario para compilar
(pom.xml, package.json, requirements.txt, build.gradle, etc.), créalo e inclúyelo
inferiendo su contenido desde los imports y frameworks detectados en el código
Nunca corrijas problemas 🟡 aunque parezcan obvios o fáciles de mejorar.
El participante que recibirá este proyecto los debe encontrar y resolver él mismo.


INPUT
Aquí está la cadena con los archivos:

// === ARCHIVO: pom.xml ===
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>
    <groupId>com.banca.seguridad</groupId>
    <artifactId>banca-seguridad-tests</artifactId>
    <version>1.0.0</version>
    <packaging>jar</packaging>

    <properties>
        <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
        <maven.compiler.source>21</maven.compiler.source>
        <maven.compiler.target>21</maven.compiler.target>
        <serenity.version>4.0.48</serenity.version>
        <selenium.version>4.16.1</selenium.version>
        <cucumber.version>7.14.0</cucumber.version>
        <rest-assured.version>5.3.2</rest-assured.version>
        <junit.version>5.10.0</junit.version>
        <lombok.version>1.18.30</lombok.version>
        <jjwt.version>0.12.3</jjwt.version>
        <commons-crypto.version>1.2.0</commons-crypto.version>
    </properties>

    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>net.serenity-bdd</groupId>
                <artifactId>serenity-bom</artifactId>
                <version>${serenity.version}</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <!-- Serenity BDD Core -->
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-core</artifactId>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-cucumber</artifactId>
        </dependency>
        <dependency>
            <groupId>net.serenity-bdd</groupId>
            <artifactId>serenity-rest-assured</artifactId>
        </dependency>

        <!-- Selenium -->
        <dependency>
            <groupId>org.seleniumhq.selenium</groupId>
            <artifactId>selenium-java</artifactId>
            <version>${selenium.version}</version>
        </dependency>

        <!-- Cucumber -->
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-java</artifactId>
            <version>${cucumber.version}</version>
        </dependency>
        <dependency>
            <groupId>io.cucumber</groupId>
            <artifactId>cucumber-junit-platform-engine</artifactId>
            <version>${cucumber.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- JUnit 5 -->
        <dependency>
            <groupId>org.junit.jupiter</groupId>
            <artifactId>junit-jupiter-api</artifactId>
            <version>${junit.version}</version>
            <scope>test</scope>
        </dependency>

        <!-- Rest Assured -->
        <dependency>
            <groupId>io.rest-assured</groupId>
            <artifactId>rest-assured</artifactId>
            <version>${rest-assured.version}</version>
        </dependency>

        <!-- Lombok -->
        <dependency>
            <groupId>org.projectlombok</groupId>
            <artifactId>lombok</artifactId>
            <version>${lombok.version}</version>
            <scope>provided</scope>
        </dependency>

        <!-- JWT -->
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-api</artifactId>
            <version>${jjwt.version}</version>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-impl</artifactId>
            <version>${jjwt.version}</version>
            <scope>runtime</scope>
        </dependency>
        <dependency>
            <groupId>io.jsonwebtoken</groupId>
            <artifactId>jjwt-jackson</artifactId>
            <version>${jjwt.version}</version>
            <scope>runtime</scope>
        </dependency>

        <!-- Cryptography -->
        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-crypto</artifactId>
            <version>${commons-crypto.version}</version>
        </dependency>
    </dependencies>

    <build>
        <plugins>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-compiler-plugin</artifactId>
                <version>3.11.0</version>
                <configuration>
                    <source>${maven.compiler.source}</source>
                    <target>${maven.compiler.target}</target>
                </configuration>
            </plugin>
            <plugin>
                <groupId>net.serenity-bdd.maven.plugins</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>${serenity.version}</version>
                <executions>
                    <execution>
                        <id>serenity-reports</id>
                        <phase>post-integration-test</phase>
                        <goals>
                            <goal>aggregate</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.1.2</version>
                <configuration>
                    <testFailureIgnore>false</testFailureIgnore>
                    <includes>
                        <include>**/RunCucumberTest.java</include>
                    </includes>
                </configuration>
            </plugin>
        </plugins>
    </build>

    <reporting>
        <plugins>
            <plugin>
                <groupId>net.serenity-bdd.maven.plugins</groupId>
                <artifactId>serenity-maven-plugin</artifactId>
                <version>${serenity.version}</version>
            </plugin>
        </plugins>
    </reporting>
</project>

// === ARCHIVO: src/test/resources/features/seguridad/evaluacion_riesgos.feature ===
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

// === ARCHIVO: src/test/resources/features/seguridad/inyeccion_sql.feature ===
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

// === ARCHIVO: src/test/resources/features/seguridad/autenticacion.feature ===
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

// === ARCHIVO: src/test/java/com/banca/seguridad/runners/RunCucumberTest.java ===
package com.banca.seguridad.runners;

import io.cucumber.junit.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;

/**
 * Runner principal para ejecutar los escenarios de pruebas de seguridad con Cucumber y Serenity.
 * Este runner coordina la ejecución de todos los features de seguridad definidos en el proyecto.
 * Configura el formato de los informes y los paquetes donde se encuentran los step definitions.
 */
@RunWith(CucumberWithSerenity.class)
@CucumberOptions(
    features = "src/test/resources/features/seguridad",
    glue = {
        "com.banca.seguridad.steps",
        "com.banca.seguridad.tasks",
        "com.banca.seguridad.questions",
        "com.banca.seguridad.utils",
        "com.banca.seguridad.config",
        "com.banca.seguridad.exceptions"
    },
    plugin = {
        "pretty",
        "json:target/cucumber-reports/cucumber.json",
        "html:target/cucumber-reports/cucumber-html-reports.html",
        "junit:target/cucumber-reports/cucumber-junit-results.xml"
    },
    tags = "@seguridad",
    dryRun = false,
    monochrome = false
)
public class RunCucumberTest {
    
    /**
     * Clase de configuración para ejecutar pruebas de seguridad con Cucumber y Serenity.
     * Los escenarios definidos en Gherkin se mapean a los step definitions y se ejecutan
     * utilizando el patrón Screenplay para mantener separación de responsabilidades.
     * 
     * Los resultados se agregan en informes HTML y JSON para análisis posterior.
     */
    public RunCucumberTest() {
        // Constructor vacío requerido por JUnit para ejecutar el runner
    }
}

// === ARCHIVO: src/test/java/com/banca/seguridad/steps/EvaluacionRiesgosSteps.java ===
package com.banca.seguridad.steps;

import com.banca.seguridad.tasks.VerificarVulnerabilidadSQL;
import com.banca.seguridad.config.SecurityConfig;
import com.banca.seguridad.exceptions.SecurityException;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

/**
 * Step definitions para los escenarios de evaluación de riesgos de seguridad.
 * Implementa el patrón Screenplay para separar las interacciones del actor de la lógica de verificación.
 * Cada paso mapea una línea Gherkin a una acción ejecutable en el sistema bajo prueba.
 */
public class EvaluacionRiesgosSteps {
    
    private static final Logger log = LoggerFactory.getLogger(EvaluacionRiesgosSteps.class);
    
    @Managed
    private WebDriver driver;
    
    private Actor evaluador = Actor.named("Evaluador de Riesgos");
    private SecurityConfig securityConfig;
    private Map<String, String> parametrosEvaluacion;
    private Map<String, Object> resultadosAnalisis;
    private List<String> vulnerabilidadesDetectadas;
    private String nivelRiesgoCalculado;
    
    /**
     * Configura el entorno de evaluación de riesgos con los parámetros iniciales.
     * Prepara el actor, el driver y la configuración de seguridad para las pruebas.
     */
    @Dado("que el evaluador de seguridad tiene acceso al sistema de evaluación de riesgos")
    public void queElEvaluadorTieneAccesoAlSistema() {
        evaluador.can(BrowseTheWeb.with(driver));
        securityConfig = new SecurityConfig();
        parametrosEvaluacion = new HashMap<>();
        resultadosAnalisis = new HashMap<>();
        log.info("Evaluador de seguridad configurado para iniciar evaluación de riesgos");
    }
    
    @Dado("que se han definido los siguientes criterios de evaluación de riesgos:")
    public void queSeHanDefinidoLosCriterios(List<String> criterios) {
        for (String criterio : criterios) {
            parametrosEvaluacion.put(criterio, obtenerValorPorDefecto(criterio));
        }
        log.info("Criterios de evaluación configurados: {}", criterios);
    }
    
    @Cuando("el evaluador ejecuta el análisis de vulnerabilidades del sistema")
    public void elEvaluadorEjecutaElAnalisis() {
        try {
            evaluador.attemptsTo(VerificarVulnerabilidadSQL.enElSistema());
            resultadosAnalisis = securityConfig.ejecutarAnalisisVulnerabilidades(parametrosEvaluacion);
            vulnerabilidadesDetectadas = (List<String>) resultadosAnalisis.get("vulnerabilidades");
            log.info("Análisis de vulnerabilidades completado. Detectadas: {} vulnerabilidades", 
                    vulnerabilidadesDetectadas != null ? vulnerabilidadesDetectadas.size() : 0);
        } catch (Exception e) {
            log.error("Error al ejecutar análisis de vulnerabilidades: {}", e.getMessage());
            throw new SecurityException("Fallo en el análisis de vulnerabilidades", e);
        }
    }
    
    @Cuando("el evaluador clasifica las vulnerabilidades según su severidad")
    public void elEvaluadorClasificaLasVulnerabilidades() {
        if (vulnerabilidadesDetectadas != null && !vulnerabilidadesDetectadas.isEmpty()) {
            nivelRiesgoCalculado = securityConfig.clasificarPorSeveridad(vulnerabilidadesDetectadas);
            resultadosAnalisis.put("nivelRiesgo", nivelRiesgoCalculado);
            log.info("Vulnerabilidades clasificadas. Nivel de riesgo: {}", nivelRiesgoCalculado);
        } else {
            nivelRiesgoCalculado = "BAJO";
            resultadosAnalisis.put("nivelRiesgo", nivelRiesgoCalculado);
        }
    }
    
    @Cuando("el evaluador calcula el impacto potencial de cada vulnerabilidad")
    public void elEvaluadorCalculaElImpacto() {
        Map<String, Double> impactos = new HashMap<>();
        if (vulnerabilidadesDetectadas != null) {
            for (String vuln : vulnerabilidadesDetectadas) {
                double impacto = securityConfig.calcularImpacto(vuln, parametrosEvaluacion);
                impactos.put(vuln, impacto);
            }
        }
        resultadosAnalisis.put("impactos", impactos);
        log.info("Impactos calculados para {} vulnerabilidades", impactos.size());
    }
    
    @Entonces("el sistema debe mostrar el reporte de evaluación de riesgos")
    public void elSistemaDebeMostrarElReporte() {
        assertThat(resultadosAnalisis, is(notNullValue()));
        assertThat(resultadosAnalisis.containsKey("vulnerabilidades"), is(true));
        log.info("Reporte de evaluación generado exitosamente");
    }
    
    @Entonces("el nivel de riesgo calculado debe ser {string}")
    public void elNivelDeRiesgoDebeSer(String nivelEsperado) {
        assertThat(nivelRiesgoCalculado, is(equalTo(nivelEsperado)));
        log.info("Validación de nivel de riesgo completada: {}", nivelRiesgoCalculado);
    }
    
    @Y("el evaluador debe identificar las vulnerabilidades críticas que requieren atención inmediata")
    public void elEvaluadorDebeIdentificarVulnerabilidadesCriticas() {
        List<String> criticas = securityConfig.filtrarVulnerabilidadesCriticas(vulnerabilidadesDetectadas);
        assertThat(criticas, is(notNullValue()));
        log.info("Vulnerabilidades críticas identificadas: {}", criticas.size());
    }
    
    @Y("las vulnerabilidades deben estar asociadas a los controles de seguridad existentes")
    public void lasVulnerabilidadesDebenEstarAsociadas() {
        Map<String, String> controles = securityConfig.obtenerControlesExistentes();
        assertThat(controles, is(notNullValue()));
        assertThat(controles.isEmpty(), is(false));
        log.info("Controles de seguridad asociados: {} controles", controles.size());
    }
    
    @Entonces("el evaluador debe generar recomendaciones de mitigación")
    public void elEvaluadorDebeGenerarRecomendaciones() {
        List<String> recomendaciones = securityConfig.generarRecomendaciones(
            vulnerabilidadesDetectadas, 
            parametrosEvaluacion
        );
        assertThat(recomendaciones, is(notNullValue()));
        assertThat(recomendaciones.isEmpty(), is(false));
        resultadosAnalisis.put("recomendaciones", recomendaciones);
        log.info("Recomendaciones de mitigación generadas: {} recomendaciones", recomendaciones.size());
    }
    
    @Y("el sistema debe registrar la trazabilidad del análisis de riesgos con vigencia de {int} años")
    public void elSistemaDebeRegistrarLaTrazabilidad(int anosVigencia) {
        boolean registrado = securityConfig.registrarTrazabilidad(
            "ANALISIS_RIESGOS",
            resultadosAnalisis,
            anosVigencia
        );
        assertThat(registrado, is(true));
        log.info("Trazabilidad registrada con vigencia de {} años", anosVigencia);
    }
    
    private String obtenerValorPorDefecto(String criterio) {
        switch (criterio.toLowerCase()) {
            case "confidencialidad":
                return "ALTO";
            case "integridad":
                return "ALTO";
            case "disponibilidad":
                return "MEDIO";
            case "trazabilidad":
                return "REQUERIDA";
            default:
                return "MEDIO";
        }
    }
}

// === ARCHIVO: src/test/java/com/banca/seguridad/steps/InyeccionSQLSteps.java ===
package com.banca.seguridad.steps;

import com.banca.seguridad.config.SecurityConfig;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Enonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.abilities.BrowseTheWeb;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.core.annotations.Managed;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;

/**
 * Step definitions para los escenarios de pruebas de inyección SQL.
 * Implementa verificaciones de seguridad para detectar y validar vulnerabilidades
 * de inyección SQL en la aplicación de banca en línea.
 * Utiliza el patrón Screenplay para separar las acciones del actor de las verificaciones.
 */
public class InyeccionSQLSteps {
    
    private static final Logger log = LoggerFactory.getLogger(InyeccionSQLSteps.class);
    
    @Managed
    private WebDriver driver;
    
    private Actor atacante = Actor.named("Potencial Atacante");
    private Actor analista = Actor.named("Analista de Seguridad");
    private SecurityConfig securityConfig;
    private Map<String, String> payloadsInyeccion;
    private Map<String, Boolean> resultadosPrueba;
    private List<String> camposVulnerables;
    private boolean vulnerabilidadDetectada;
    private String tipoInyeccion;
    
    /**
     * Configura el entorno de pruebas para inyección SQL.
     * Prepara los payloads de prueba y la configuración de seguridad.
     */
    @Dado("que el sistema de banca en línea tiene formularios de entrada de datos")
    public void queElSistemaTieneFormularios() {
        securityConfig = new SecurityConfig();
        payloadsInyeccion = new HashMap<>();
        resultadosPrueba = new HashMap<>();
        log.info("Sistema de banca configurado para pruebas de inyección SQL");
    }
    
    @Dado("que se han preparado los siguientes payloads de inyección SQL:")
    public void queSeHanPreparadoLosPayloads(List<Map<String, String>> payloads) {
        for (Map<String, String> payload : payloads) {
            String tipo = payload.get("tipo");
            String valor = payload.get("payload");
            payloadsInyeccion.put(tipo, valor);
        }
        log.info("Payloads de inyección SQL preparados: {} tipos", payloadsInyeccion.size());
    }
    
    @Cuando("el analista intenta inyectar código SQL en el campo {string}")
    public void elAnalistaIntentaInyectar(String campo) {
        try {
            String payload = payloadsInyeccion.get("basico");
            if (payload == null) {
                payload = "' OR '1'='1";
            }
            
            Map<String, String> datosPrueba = new HashMap<>();
            datosPrueba.put(campo, payload);
            
            boolean resultado = securityConfig.simularConsultaConInput(campo, payload);
            vulnerabilidadDetectada = resultado;
            resultadosPrueba.put(campo, resultado);
            
            log.info("Prueba de inyección SQL en campo '{}' completada. Resultado: vulnerable={}", 
                    campo, vulnerabilidadDetectada);
        } catch (Exception e) {
            log.error("Error durante prueba de inyección SQL: {}", e.getMessage());
            vulnerabilidadDetectada = false;
            resultadosPrueba.put(campo, false);
        }
    }
    
    @Cuando("el analista ejecuta una consulta UNION para extraer datos de otras tablas")
    public void elAnalistaEjecutaUnionQuery() {
        String payloadUnion = payloadsInyeccion.get("union");
        if (payloadUnion == null) {
            payloadUnion = "' UNION SELECT NULL, username, password FROM users--";
        }
        
        try {
            boolean resultadoUnion = securityConfig.simularConsultaConInput("busqueda", payloadUnion);
            vulnerabilidadDetectada = resultadoUnion;
            resultadosPrueba.put("union_query", resultadoUnion);
            tipoInyeccion = resultadoUnion ? "UNION" : "NINGUNA";
            log.info("Prueba UNION query completada. Tipo de inyección detectada: {}", tipoInyeccion);
        } catch (Exception e) {
            log.error("Error en prueba UNION: {}", e.getMessage());
            vulnerabilidadDetectada = false;
        }
    }
    
    @Cuando("el analista prueba técnicas de inyección basadas en tiempo de respuesta")
    public void elAnalistaPruebaInyeccionPorTiempo() {
        String payloadTiempo = payloadsInyeccion.get("tiempo");
        if (payloadTiempo == null) {
            payloadTiempo = "'; WAITFOR DELAY '0:0:5'--";
        }
        
        try {
            long tiempoInicio = System.currentTimeMillis();
            boolean resultadoTiempo = securityConfig.simularConsultaConInput("usuario", payloadTiempo);
            long tiempoFin = System.currentTimeMillis();
            long duracion = tiempoFin - tiempoInicio;
            
            vulnerabilidadDetectada = resultadoTiempo || duracion > 4000;
            resultadosPrueba.put("time_based", vulnerabilidadDetectada);
            log.info("Prueba de inyección basada en tiempo completada. Duración: {}ms", duracion);
        } catch (Exception e) {
            log.error("Error en prueba basada en tiempo: {}", e.getMessage());
            vulnerabilidadDetectada = false;
        }
    }
    
    @Entonces("el sistema debe detectar y bloquear el intento de inyección SQL")
    public void elSistemaDebeDetectarYBloquear() {
        boolean proteccionActiva = securityConfig.verificarProteccionInyeccionSQL();
        assertThat("La protección contra inyección SQL debe estar activa", proteccionActiva, is(true));
        log.info("Validación de protección contra inyección SQL completada");
    }
    
    @Entonces("el campo {string} debe ser vulnerable a inyección SQL")
    public void elCampoDebeSerVulnerable(String campo) {
        Boolean resultado = resultadosPrueba.get(campo);
        if (resultado == null) {
            resultado = false;
        }
        assertThat("El campo " + campo + " debe ser vulnerable", resultado, is(true));
        log.info("Validación de vulnerabilidad en campo '{}' completada", campo);
    }
    
    @Entonces("el sistema debe registrar el intento de inyección para auditoría")
    public void elSistemaDebeRegistrarElIntento() {
        boolean registrado = securityConfig.registrarIntentoInyeccion(resultadosPrueba);
        assertThat("El intento de inyección debe ser registrado", registrado, is(true));
        log.info("Intento de inyección SQL registrado para auditoría");
    }
    
    @Y("el sistema debe aplicar técnicas de sanitización a las entradas del usuario")
    public void elSistemaDebeAplicarSanitizacion() {
        boolean sanitizacionActiva = securityConfig.verificarSanitizacionEntradas();
        assertThat("La sanitización de entradas debe estar activa", sanitizacionActiva, is(true));
        log.info("Validación de sanitización de entradas completada");
    }
    
    @Y("el sistema debe utilizar consultas parametrizadas para prevenir inyección")
    public void elSistemaDebeUtilizarConsultasParametrizadas() {
        boolean usaParametrizacion = securityConfig.verificarConsultasParametrizadas();
        assertThat("Debe utilizar consultas parametrizadas", usaParametrizacion, is(true));
        log.info("Validación de uso de consultas parametrizadas completada");
    }
    
    @Y("el análisis debe identificar todos los puntos de entrada vulnerables")
    public void elAnalisisDebeIdentificarPuntosEntrada() {
        camposVulnerables = securityConfig.identificarPuntosEntradaVulnerables(resultadosPrueba);
        assertThat(camposVulnerables, is(notNullValue()));
        log.info("Puntos de entrada vulnerables identificados: {}", camposVulnerables.size());
    }
    
    @Entonces("el sistema debe mostrar las medidas de protección implementadas")
    public void elSistemaDebeMostrarMedidasProteccion() {
        Map<String, String> medidas = securityConfig.obtenerMedidasProteccion();
        assertThat(medidas, is(notNullValue()));
        assertThat(medidas.isEmpty(), is(false));
        log.info("Medidas de protección implementadas: {} medidas", medidas.size());
    }
    
    @Y("se debe generar un reporte de evaluación de inyección SQL")
    public void seDebeGenerarReporte() {
        Map<String, Object> reporte = new HashMap<>();
        reporte.put("resultados", resultadosPrueba);
        reporte.put("vulnerabilidadDetectada", vulnerabilidadDetectada);
        reporte.put("tipoInyeccion", tipoInyeccion);
        reporte.put("timestamp", System.currentTimeMillis());
        
        boolean generado = securityConfig.generarReporteInyeccion(reporte);
        assertThat("El reporte debe ser generado", generado, is(true));
        log.info("Reporte de evaluación de inyección SQL generado");
    }
}

// === ARCHIVO: src/test/java/com/banca/seguridad/steps/AutenticacionSteps.java ===
package com.banca.seguridad.steps;

import com.banca.seguridad.questions.ValidarAcceso;
import com.banca.seguridad.tasks.RealizarLogin;
import com.banca.seguridad.tasks.VerificarVulnerabilidadSQL;
import io.cucumber.java.es.Cuando;
import io.cucumber.java.es.Dado;
import io.cucumber.java.es.Entonces;
import io.cucumber.java.es.Y;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.actors.OnStage;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import net.thucydides.model.util.NameConverter;

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;

public class AutenticacionSteps {

    @Dado("que el usuario {string} existe en el sistema con rol {string}")
    public void queElUsuarioExisteEnElSistemaConRol(String usuario, String rol) {
        Actor actor = OnStage.theActorCalled(NameConverter.forClass(usuario));
        actor.can(CallAnApi.at("https://api.banca-segura.com"));
    }

    @Cuando("el usuario {string} inicia sesión con contraseña {string}")
    public void elUsuarioIniciaSesionConContrasena(String usuario, String contrasena) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RealizarLogin.conCredenciales(usuario, contrasena));
    }

    @Entonces("el sistema debe mostrar el mensaje de bienvenida")
    public void elSistemaDebeMostrarElMensajeDeBienvenida() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.aMenuPrincipal()));
    }

    @Y("el token JWT debe ser generado correctamente")
    public void elTokenJWTDebeSerGeneradoCorrectamente() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conTokenValido()));
    }

    @Entonces("el sistema debe rechazar el acceso con mensaje de error de autenticación")
    public void elSistemaDebeRechazarElAccesoConMensajeDeError() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conErrorDeAutenticacion()));
    }

    @Cuando("el usuario intenta acceder al recurso {string} sin autorización")
    public void elUsuarioIntentaAccederAlRecursoSinAutorizacion(String recurso) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(ValidarAcceso.alRecurso(recurso));
    }

    @Entonces("el sistema debe retornar código de error {int}")
    public void elSistemaDebeRetornarCodigoDeError(int codigoError) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conCodigoDeEstado(codigoError)));
    }

    @Cuando("se intenta inyectar código SQL en el campo {string} con valor {string}")
    public void seIntentaInyectarCodigoSQLEnElCampo(String campo, String valorInyeccion) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(VerificarVulnerabilidadSQL.enCampo(campo, valorInyeccion));
    }

    @Entonces("el sistema debe bloquear la solicitud y registrar el intento de inyección")
    public void elSistemaDebeBloquearLaSolicitudYRegistrarElIntento() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conIntentoDeInyeccionBloqueado()));
    }

    @Y("no debe ejecutar la consulta maliciosa")
    public void noDebeEjecutarLaConsultaMaliciosa() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.sinEjecucionDeInyeccion()));
    }

    @Dado("que el usuario {string} tiene un token JWT válido")
    public void queElUsuarioTieneUnTokenJWTValido(String usuario) {
        Actor actor = OnStage.theActorCalled(NameConverter.forClass(usuario));
        actor.can(CallAnApi.at("https://api.banca-segura.com"));
    }

    @Cuando("el usuario {string} accede a la pantalla de transacciones")
    public void elUsuarioAccedeALaPantallaDeTransacciones(String usuario) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(ValidarAcceso.aTransacciones());
    }

    @Entonces("el sistema debe permitir el acceso solo si el token tiene el permiso {string}")
    public void elSistemaDebePermitirElAccesoSoloSiElTokenTieneElPermiso(String permiso) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conPermiso(permiso)));
    }

    @Cuando("se intercepta una solicitud de autenticación con token manipulado")
    public void seInterceptaUnaSolicitudDeAutenticacionConTokenManipulado() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(ValidarAcceso.conTokenManipulado());
    }

    @Entonces("el sistema debe rechazar el token manipulado")
    public void elSistemaDebeRechazarElTokenManipulado() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conTokenInvalido()));
    }

    @Y("registrar el intento de acceso no autorizado en el log de seguridad")
    public void registrarElIntentoDeAccesoNoAutorizado() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.registradoEnLog()));
    }

    @Cuando("el usuario {string} intenta autenticarse con contraseña incorrecta {int} veces consecutivas")
    public void elUsuarioIntentaAutenticarseConContrasenaIncorrectaVeces(String usuario, int intentos) {
        Actor actor = OnStage.theActorInTheSpotlight();
        for (int i = 0; i < intentos; i++) {
            actor.attemptsTo(RealizarLogin.conCredenciales(usuario, "contrasena_incorrecta_" + i));
        }
    }

    @Entonces("el sistema debe bloquear temporalmente la cuenta del usuario")
    public void elSistemaDebeBloquearTemporalmenteLaCuenta() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conCuentaBloqueada()));
    }

    @Y("mostrar mensaje indicando que la cuenta ha sido bloqueada por seguridad")
    public void mostrarMensajeIndicandoQueLaCuentaHaSidoBloqueada() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.conMensajeDeCuentaBloqueada()));
    }

    @Cuando("el usuario {string} usa autenticación de dos factores con código {string}")
    public void elUsuarioUsaAutenticacionDeDosFactores(String usuario, String codigo) {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.attemptsTo(RealizarLogin.con2FA(usuario, codigo));
    }

    @Entonces("el acceso debe ser concedido solo si el código 2FA es válido")
    public void elAccesoDebeSerConcedidoSoloSiElCodigo2FAEsValido() {
        Actor actor = OnStage.theActorInTheSpotlight();
        actor.should(seeThat(ValidarAcceso.con2FAValido()));
    }
}

// === ARCHIVO: src/test/java/com/banca/seguridad/tasks/RealizarLogin.java ===
package com.banca.seguridad.tasks;

import com.banca.seguridad.config.SecurityConfig;
import com.banca.seguridad.exceptions.SecurityException;
import com.banca.seguridad.utils.SecurityUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.RestQuestion;
import net.thucydides.model.util.NameConverter;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.hamcrest.CoreMatchers.*;

public class RealizarLogin implements Task {

    private final String usuario;
    private final String contrasena;
    private final boolean usar2FA;
    private final String codigo2FA;

    private RealizarLogin(Builder builder) {
        this.usuario = builder.usuario;
        this.contrasena = builder.contrasena;
        this.usar2FA = builder.usar2FA;
        this.codigo2FA = builder.codigo2FA;
    }

    public static Builder conCredenciales(String usuario, String contrasena) {
        return new Builder().conUsuario(usuario).conContrasena(contrasena);
    }

    public static RealizarLogin con2FA(String usuario, String codigo2FA) {
        return new Builder().conUsuario(usuario).conContrasena("temp_password").con2FA(codigo2FA);
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        if (StringUtils.isBlank(usuario) || StringUtils.isBlank(contrasena)) {
            throw new SecurityException("Las credenciales no pueden estar vacías");
        }

        String contrasenaCifrada = SecurityUtils.cifrarContrasena(contrasena);

        Map<String, Object> cuerpoSolicitud = new HashMap<>();
        cuerpoSolicitud.put("username", usuario);
        cuerpoSolicitud.put("password", contrasenaCifrada);
        cuerpoSolicitud.put("aplicacion", "banca-online");
        cuerpoSolicitud.put("dispositivo", "test-device");
        cuerpoSolicitud.put("ipOrigen", "192.168.1.100");

        if (usar2FA && StringUtils.isNotBlank(codigo2FA)) {
            cuerpoSolicitud.put("codigo2FA", codigo2FA);
            cuerpoSolicitud.put("metodo2FA", "TOTP");
        }

        actor.attemptsTo(
            Post.to("/api/v1/autenticacion/login")
                .with(request -> request
                    .contentType(ContentType.JSON)
                    .header("X-Request-ID", SecurityUtils.generarRequestId())
                    .header("X-Client-Version", SecurityConfig.getClientVersion())
                    .body(cuerpoSolicitud)
                )
        );

        Response ultimaRespuesta = RestQuestion.as(actor).lastResponse();

        if (ultimaRespuesta.getStatusCode() == 200) {
            String tokenJWT = ultimaRespuesta.jsonPath().getString("token");
            String tokenCifrado = SecurityUtils.cifrarToken(tokenJWT);

            actor.remember("token_jwt", tokenJWT);
            actor.remember("token_cifrado", tokenCifrado);
            actor.remember("usuario", usuario);
            actor.remember("intentos_fallidos", 0);

            if (ultimaRespuesta.jsonPath().getString("refreshToken") != null) {
                actor.remember("refresh_token", ultimaRespuesta.jsonPath().getString("refreshToken"));
            }

            SecurityUtils.registrarEventoAutenticacion(usuario, true, "LOGIN_SUCCESS");
        } else if (ultimaRespuesta.getStatusCode() == 401) {
            Integer intentosPrevios = (Integer) actor.recall("intentos_fallidos");
            actor.remember("intentos_fallidos", intentosPrevios + 1);

            String mensajeError = ultimaRespuesta.jsonPath().getString("mensaje");
            SecurityUtils.registrarEventoAutenticacion(usuario, false, "LOGIN_FAILED: " + mensajeError);

            if (intentosPrevios + 1 >= SecurityConfig.getMaxIntentosLogin()) {
                throw new SecurityException("Cuenta bloqueada por demasiados intentos fallidos");
            }

            throw new SecurityException("Autenticación fallida: " + mensajeError);
        } else if (ultimaRespuesta.getStatusCode() == 423) {
            Long tiempoBloqueo = ultimaRespuesta.jsonPath().getLong("tiempoBloqueo");
            throw new SecurityException("Cuenta temporalmente bloqueada. Intentar en " + tiempoBloqueo + " segundos");
        } else {
            throw new SecurityException("Error de autenticación inesperado: " + ultimaRespuesta.getStatusCode());
        }
    }

    public static class Builder {
        private String usuario;
        private String contrasena;
        private boolean usar2FA = false;
        private String codigo2FA;

        public Builder conUsuario(String usuario) {
            this.usuario = usuario;
            return this;
        }

        public Builder conContrasena(String contrasena) {
            this.contrasena = contrasena;
            return this;
        }

        public Builder con2FA(String codigo2FA) {
            this.usar2FA = true;
            this.codigo2FA = codigo2FA;
            return this;
        }

        public RealizarLogin build() {
            return new RealizarLogin(this);
        }
    }
}

// === ARCHIVO: src/test/java/com/banca/seguridad/tasks/VerificarVulnerabilidadSQL.java ===
package com.banca.seguridad.tasks;

import com.banca.seguridad.utils.SecurityUtils;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;
import net.serenitybdd.screenplay.rest.questions.RestQuestion;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import static net.serenitybdd.screenplay.Tasks.instrumented;
import static org.hamcrest.CoreMatchers.*;

public class VerificarVulnerabilidadSQL implements Task {

    private static final List<String> PAYLOADS_SQL_INJECTION = List.of(
        "' OR '1'='1",
        "' OR '1'='1' --",
        "'; DROP TABLE usuarios; --",
        "' UNION SELECT * FROM usuarios--",
        "1' AND '1'='1",
        "1' OR '1'='1' LIMIT 1--",
        "admin'--",
        "' OR 1=1--",
        "1; DELETE FROM transacciones WHERE '1'='1",
        "' WAITFOR DELAY '0:0:5'--"
    );

    private static final Pattern PATRON_SQLPeligROSO = Pattern.compile(
        "(?i)(.*(\\b(SELECT|INSERT|UPDATE|DELETE|DROP|UNION|ALTER|CREATE|TRUNCATE)\\b.*)|"
        + ".*(\\b(OR|AND)\\b\\s+['\\"][0-9]+['\\"]\\s*=\\s*['\\"][0-9]+['\\"])|"
        + ".*(--|\\#|\\/\\*|\\*\\/).*)"
    );

    private final String campo;
    private final String valorInyeccion;
    private final boolean esAutomatica;

    private VerificarVulnerabilidadSQL(Builder builder) {
        this.campo = builder.campo;
        this.valorInyeccion = builder.valorInyeccion;
        this.esAutomatica = builder.esAutomatica;
    }

    public static Builder enCampo(String campo, String valorInyeccion) {
        return new Builder().conCampo(campo).conValor(valorInyeccion);
    }

    public static VerificarVulnerabilidadSQL automatica(String endpoint) {
        return new Builder().conAutomatica(true).conEndpoint(endpoint).build();
    }

    @Override
    public <T extends Actor> void performAs(T actor) {
        List<Map<String, Object>> resultados = new ArrayList<>();
        boolean vulnerabilidadDetectada = false;

        if (esAutomatica) {
            resultados = ejecutarPruebasAutomatizadas(actor);
        } else {
            resultados.add(ejecutarPruebaManual(actor, campo, valorInyeccion));
        }

        actor.remember("resultados_inyeccion_sql", resultados);

        for (Map<String, Object> resultado : resultados) {
            if (Boolean.TRUE.equals(resultado.get("vulnerable"))) {
                vulnerabilidadDetectada = true;
                String detalles = (String) resultado.get("detalles");
                SecurityUtils.registrarVulnerabilidad("SQL_INJECTION", campo, valorInyeccion, detalles);
            }
        }

        actor.should(seeThat("Detección de inyección SQL",
            actor1 -> vulnerabilidadDetectada,
            equalTo(false)
        ));
    }

    private List<Map<String, Object>> ejecutarPruebasAutomatizadas(Actor actor) {
        List<Map<String, Object>> resultados = new ArrayList<>();

        for (String payload : PAYLOADS_SQL_INJECTION) {
            Map<String, Object> resultado = ejecutarPruebaManual(actor, campo, payload);
            resultados.add(resultado);
        }

        return resultados;
    }

    private Map<String, Object> ejecutarPruebaManual(Actor actor, String campo, String payload) {
        Map<String, Object> resultado = new HashMap<>();
        resultado.put("campo", campo);
        resultado.put("payload", payload);
        resultado.put("timestamp", System.currentTimeMillis());

        boolean esPayloadConocido = PAYLOADS_SQL_INJECTION.contains(payload);
        boolean coincidePatron = PATRON_SQLPeligROSO.matcher(payload).matches();

        if (!esPayloadConocido && !coincidePatron) {
            resultado.put("vulnerable", false);
            resultado.put("detalles", "Payload no reconocido como ataque SQL conocido");
            return resultado;
        }

        Map<String, Object> cuerpoSolicitud = new HashMap<>();
        cuerpoSolicitud.put(campo, payload);
        cuerpoSolicitud.put("tipoValidacion", "SQL_INJECTION_TEST");
        cuerpoSolicitud.put("origen", "AUTOMATED_SECURITY_TEST");

        actor.attemptsTo(
            Post.to("/api/v1/consultas/buscar")
                .with(request -> request
                    .contentType(ContentType.JSON)
                    .header("X-Request-ID", SecurityUtils.generarRequestId())
                    .header("X-Security-Test", "true")
                    .header("X-Test-Type", "SQL_INJECTION")
                    .body(cuerpoSolicitud)
                )
        );

        Response respuesta = RestQuestion.as(actor).lastResponse();
        int codigoEstado = respuesta.getStatusCode();

        boolean respuesta Sospechosa = false;
        String mensajeRespuesta = respuesta.getBody().asString();

        if (codigoEstado == 200) {
            String respuestaJson = respuesta.getBody().asString();
            if (respuestaJson.contains("SQLSyntaxErrorException")
                || respuestaJson.contains("syntax error")
                || respuestaJson.contains("ORA-")
                || respuestaJson.contains("MySQLSyntaxErrorException")) {
                respuestaSospechosa = true;
            }
        }

        if (codigoEstado == 500 || codigoEstado == 400 || respuestaSospechosa) {
            boolean vulnerable = verificarVulnerabilidad(codigoEstado, mensajeRespuesta, payload);
            resultado.put("vulnerable", vulnerable);
            resultado.put("codigoEstado", codigoEstado);
            resultado.put("respuesta Sospechosa", respuestaSospechosa);
            resultado.put("detalles", vulnerable ? 
                "Posible vulnerabilidad detectada: respuesta anomalous" : 
                "Solicitud bloqueada correctamente por el WAF");
        } else {
            resultado.put("vulnerable", false);
            resultado.put("codigoEstado", codigoEstado);
            resultado.put("detalles", "Solicitud procesada sin errores aparentes");
        }

        actor.remember("ultimo_test_sql", resultado);
        return resultado;
    }

    private boolean verificarVulnerabilidad(int codigoEstado, String respuesta, String payload) {
        if (codigoEstado == 500 && respuesta.contains("SQL")) {
            return true;
        }

        if (respuesta.toLowerCase().contains("syntax error near")
            || respuesta.contains("unterminated")
            || respuesta.contains("ORA-")) {
            return true;
        }

        if (payload.contains("UNION") && codigoEstado == 200) {
            return true;
        }

        return false;
    }

    public static class Builder {
        private String campo;
        private String valorInyeccion;
        private boolean esAutomatica = false;
        private String endpoint;

        public Builder conCampo(String campo) {
            this.campo = campo;
            return this;
        }

        public Builder conValor(String valorInyeccion) {
            this.valorInyeccion = valorInyeccion;
            return this;
        }

        public Builder conAutomatica(boolean automatica) {
            this.esAutomatica = automatica;
            return this;
        }

        public Builder conEndpoint(String endpoint) {
            this.endpoint = endpoint;
            return this;
        }

        public VerificarVulnerabilidadSQL build() {
            return new VerificarVulnerabilidadSQL(this);
        }
    }
}

// === ARCHIVO: src/test/java/com/banca/seguridad/questions/ValidarAcceso.java ===
package com.banca.seguridad.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.questions.Text;
import net.serenitybdd.screenplay.waits.WaitUntil;
import net.serenitybdd.screenplay.matchers.WebElementStateMatchers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import com.banca.seguridad.exceptions.SecurityException;
import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.Matchers.*;

public class ValidarAcceso implements Question<Boolean> {

    private final String recurso;
    private final String tipoAcceso;
    private final By locator;

    private ValidarAcceso(String recurso, String tipoAcceso, By locator) {
        this.recurso = recurso;
        this.tipoAcceso = tipoAcceso;
        this.locator = locator;
    }

    public static ValidarAcceso alRecurso(String recurso) {
        return new ValidarAcceso(recurso, "lectura", null);
    }

    public static ValidarAcceso alRecurso(String recurso, String tipoAcceso) {
        return new ValidarAcceso(recurso, tipoAcceso, null);
    }

    public ValidarAcceso enLaPagina(By locator) {
        return new ValidarAcceso(this.recurso, this.tipoAcceso, locator);
    }

    public static ValidarAcceso conPermisos(String recurso, String tipoPermiso) {
        return new ValidarAcceso(recurso, tipoPermiso, null);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            if (locator != null) {
                actor.attemptsTo(
                    WaitUntil.the(locator, WebElementStateMatchers.isVisible())
                        .forNoMoreThan(10).seconds()
                );
                
                WebElement elemento = locator.findElement(null);
                String contenido = elemento.getText();
                
                actor.should(
                    seeThat("El recurso debe ser accesible",
                        () -> contenido,
                        not(isEmptyString())
                    )
                );
                
                return validarPermisosAcceso(contenido);
            }
            
            return validarPermisosAcceso(actor.recall("ultimoAcceso"));
            
        } catch (Exception e) {
            throw new SecurityException(
                "Error al validar acceso al recurso: " + recurso + ", tipo: " + tipoAcceso, e
            );
        }
    }

    private boolean validarPermisosAcceso(String contenido) {
        if (contenido == null || contenido.isEmpty()) {
            return false;
        }
        
        if ("administrador".equals(tipoAcceso)) {
            return contenido.contains("ACCESS_GRANTED") || 
                   contenido.contains("ROLE_ADMIN") ||
                   contenido.contains("全権");
        }
        
        if ("lectura".equals(tipoAcceso)) {
            return contenido.contains("READ") || 
                   contenido.contains("LECTURA") ||
                   contenido.contains("VIEW");
        }
        
        if ("escritura".equals(tipoAcceso) || "modificacion".equals(tipoAcceso)) {
            return contenido.contains("WRITE") || 
                   contenido.contains("ESCRITURA") ||
                   contenido.contains("MODIFY");
        }
        
        return contenido.contains("ACCESS_GRANTED");
    }

    public String getRecurso() {
        return recurso;
    }

    public String getTipoAcceso() {
        return tipoAcceso;
    }

    public By getLocator() {
        return locator;
    }
}

// === ARCHIVO: src/test/java/com/banca/seguridad/questions/ValidarVulnerabilidad.java ===
package com.banca.seguridad.questions;

import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;
import net.serenitybdd.screenplay.rest.questions.ResponseConsequence;
import io.restassured.response.Response;
import org.json.JSONObject;
import com.banca.seguridad.exceptions.SecurityException;
import java.util.regex.Pattern;
import java.util.Arrays;
import java.util.List;

public class ValidarVulnerabilidad implements Question<Boolean> {

    private final String tipoVulnerabilidad;
    private final String endpoint;
    private final String payload;
    private final Pattern patronSQLInjection;
    private final Pattern patronXSS;
    private final Pattern patronPathTraversal;

    private ValidarVulnerabilidad(String tipoVulnerabilidad, String endpoint, String payload) {
        this.tipoVulnerabilidad = tipoVulnerabilidad;
        this.endpoint = endpoint;
        this.payload = payload;
        this.patronSQLInjection = Pattern.compile(
            "(?i)(union.*select|select.*from|insert.*into|delete.*from|drop.*table|'\s*or\s*'1'='1|--)",
            Pattern.CASE_INSENSITIVE
        );
        this.patronXSS = Pattern.compile(
            "(?i)(<script|javascript:|onerror=|onload=|alert\(|document\.cookie)",
            Pattern.CASE_INSENSITIVE
        );
        this.patronPathTraversal = Pattern.compile(
            "(\.\./|\.\.\\/|%2e%2e/|%2e%2e\\|/etc/passwd|/windows/system32)",
            Pattern.CASE_INSENSITIVE
        );
    }

    public static ValidarVulnerabilidad deTipo(String tipo) {
        return new ValidarVulnerabilidad(tipo, null, null);
    }

    public static ValidarVulnerabilidad sql(String endpoint, String payload) {
        return new ValidarVulnerabilidad("SQL_INJECTION", endpoint, payload);
    }

    public static ValidarVulnerabilidad xss(String endpoint, String payload) {
        return new ValidarVulnerabilidad("XSS", endpoint, payload);
    }

    public static ValidarVulnerabilidad pathTraversal(String endpoint, String payload) {
        return new ValidarVulnerabilidad("PATH_TRAVERSAL", endpoint, payload);
    }

    public ValidarVulnerabilidad enEndpoint(String endpoint) {
        return new ValidarVulnerabilidad(this.tipoVulnerabilidad, endpoint, this.payload);
    }

    public ValidarVulnerabilidad conPayload(String payload) {
        return new ValidarVulnerabilidad(this.tipoVulnerabilidad, this.endpoint, payload);
    }

    @Override
    public Boolean answeredBy(Actor actor) {
        try {
            Response respuesta = actor.recall("ultimaRespuesta");
            
            if (respuesta == null) {
                throw new SecurityException(
                    "No se encontró respuesta previa para validar vulnerabilidad: " + tipoVulnerabilidad
                );
            }

            String cuerpoRespuesta = respuesta.getBody().asString();
            int codigoEstado = respuesta.getStatusCode();

            return analizarVulnerabilidad(cuerpoRespuesta, codigoEstado);
            
        } catch (Exception e) {
            throw new SecurityException(
                "Error al validar vulnerabilidad tipo: " + tipoVulnerabilidad, e
            );
        }
    }

    private boolean analizarVulnerabilidad(String cuerpo, int codigoEstado) {
        switch (tipoVulnerabilidad) {
            case "SQL_INJECTION":
                return detectarSQLInjection(cuerpo, codigoEstado);
            case "XSS":
                return detectarXSS(cuerpo, codigoEstado);
            case "PATH_TRAVERSAL":
                return detectarPathTraversal(cuerpo, codigoEstado);
            case "AUTH_BYPASS":
                return detectarAuthBypass(cuerpo, codigoEstado);
            case "IDOR":
                return detectarIDOR(cuerpo, codigoEstado);
            default:
                return false;
        }
    }

    private boolean detectarSQLInjection(String cuerpo, int codigoEstado) {
        List<String> indicadoresVulnerabilidad = Arrays.asList(
            "SQL syntax", "mysql_fetch", "ORA-", "PostgreSQL",
            "unterminated", "quoted string not properly terminated",
            "SQL error", "Warning: mysql", "SQLSTATE"
        );
        
        boolean indicadoresDB = indicadoresVulnerabilidad.stream()
            .anyMatch(cuerpo::contains);
        
        boolean erroresSQL = patronSQLInjection.matcher(cuerpo).find();
        
        return indicadoresDB || erroresSQL || codigoEstado == 500;
    }

    private boolean detectarXSS(String cuerpo, int codigoEstado) {
        List<String> indicadoresXSS = Arrays.asList(
            "<script>", "</script>", "<img", "onerror=",
            "onload=", "javascript:", "alert(", "document.cookie"
        );
        
        boolean contieneScriptsPeligrosos = indicadoresXSS.stream()
            .anyMatch(cuerpo::contains);
        
        boolean patronesXSS = patronXSS.matcher(cuerpo).find();
        
        return contieneScriptsPeligrosos || patronesXSS;
    }

    private boolean detectarPathTraversal(String cuerpo, int codigoEstado) {
        List<String> archivosSensibles = Arrays.asList(
            "/etc/passwd", "/etc/shadow", "windows\\system32",
            ".bashrc", ".git/config", "web.xml"
        );
        
        boolean expuestaInformacion = archivosSensibles.stream()
            .anyMatch(cuerpo::contains);
        
        boolean patronAtacante = patronPathTraversal.matcher(cuerpo).find();
        
        return expuestaInformacion || patronAtacante || codigoEstado == 403;
    }

    private boolean detectarAuthBypass(String cuerpo, int codigoEstado) {
        boolean sesionValida = cuerpo.contains("session_id") || 
                               cuerpo.contains("token") ||
                               cuerpo.contains("authenticated");
        
        boolean accesoSinAuth = codigoEstado == 200 && !sesionValida;
        
        return accesoSinAuth;
    }

    private boolean detectarIDOR(String cuerpo, int codigoEstado) {
        boolean datosOtrosUsuarios = cuerpo.contains("account_number") ||
                                     cuerpo.contains("balance") ||
                                     cuerpo.contains("transaction_history");
        
        return datosOtrosUsuarios && codigoEstado == 200;
    }

    public String getTipoVulnerabilidad() {
        return tipoVulnerabilidad;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public String getPayload() {
        return payload;
    }
}

// === ARCHIVO: src/test/java/com/banca/seguridad/utils/SecurityUtils.java ===
package com.banca.seguridad.utils;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.apache.commons.crypto.cipher.CryptoCipher;
import org.apache.commons.crypto.utils.EncryptionUtils;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class SecurityUtils {

    private static final String ALGORITMO_CIFRADO = "AES";
    private static final int TAMANO_CLAVE = 256;
    private static final String ALGORITMO_HASH = "SHA-256";
    
    private static SecretKey claveSecreta;
    private static final Map<String, String> tokenStore = new HashMap<>();

    static {
        try {
            KeyGenerator generador = KeyGenerator.getInstance(ALGORITMO_CIFRADO);
            generador.init(TAMANO_CLAVE);
            claveSecreta = generador.generateKey();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al inicializar clave de cifrado", e);
        }
    }

    public static String generarTokenJWT(String usuario, String rol, long duracionMinutos) {
        Date ahora = new Date();
        Date expiracion = new Date(ahora.getTime() + (duracionMinutos * 60 * 1000));
        
        String token = Jwts.builder()
            .subject(usuario)
            .claim("role", rol)
            .issuedAt(ahora)
            .expiration(expiracion)
            .signWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
            .compact();
        
        tokenStore.put(usuario, token);
        return token;
    }

    public static boolean validarTokenJWT(String token) {
        try {
            Jwts.parser()
                .verifyWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
                .build()
                .parseSignedClaims(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String extraerUsuario(String token) {
        return Jwts.parser()
            .verifyWith(Keys.secretKeyFor(SignatureAlgorithm.HS256))
            .build()
            .parseSignedClaims(token)
            .getPayload()
            .getSubject();
    }

    public static String cifrarDato(String dato) {
        // SUPERFICIE DE PRACTICA: Implementar cifrado AES-256
        throw new UnsupportedOperationException("Metodo por implementar: cifrarDato");
    }

    public static String descifrarDato(String datoCifrado) {
        // SUPERFICIE DE PRACTICA: Implementar descifrado AES-256
        throw new UnsupportedOperationException("Metodo por implementar: descifrarDato");
    }

    public static String calcularHash(String dato) {
        // SUPERFICIE DE PRACTICA: Implementar hash SHA-256
        throw new UnsupportedOperationException("Metodo por implementar: calcularHash");
    }

    public static boolean verificarHash(String dato, String hash) {
        // SUPERFICIE DE PRACTICA: Implementar verificacion de hash
        throw new UnsupportedOperationException("Metodo por implementar: verificarHash");
    }

    public static String sanitizarInput(String input) {
        // SUPERFICIE DE PRACTICA: Implementar sanitizacion de entrada
        throw new UnsupportedOperationException("Metodo por implementar: sanitizarInput");
    }

    public static boolean detectarSQLInjection(String input) {
        // SUPERFICIE DE PRACTICA: Implementar detector de SQL injection
        throw new UnsupportedOperationException("Metodo por implementar: detectarSQLInjection");
    }

    public static boolean detectarXSS(String input) {
        // SUPERFICIE DE PRACTICA: Implementar detector de XSS
        throw new UnsupportedOperationException("Metodo por implementar: detectarXSS");
    }

    public static String generarSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[16];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    public static String hashearConSalt(String dato, String salt) {
        // SUPERFICIE DE PRACTICA: Implementar hashing con salt
        throw new UnsupportedOperationException("Metodo por implementar: hashearConSalt");
    }

    public static SecretKey getClaveSecreta() {
        return claveSecreta;
    }
}

// === ARCHIVO: src/test/java/com/banca/seguridad/config/SecurityConfig.java ===
package com.banca.seguridad.config;

import net.serenitybdd.screenplay.player.abilities.BrowseTheWeb;
import net.thucydides.core.annotations.WhenPageLoads;
import net.thucydides.core.pages.Pages;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/api/publico/**").permitAll()
                .requestMatchers("/api/seguro/**").authenticated()
                .anyRequest().authenticated()
            )
            .httpBasic(withDefaults());
        return http.build();
    }

    public static class SecurityTestConfig {
        private String tokenJwt;
        private String endpointProtegido;
        private String metodoAutenticacion;

        @WhenPageLoads
        public void waitForPage() {
        }

        public String getTokenJwt() {
            return tokenJwt;
        }

        public void setTokenJwt(String tokenJwt) {
            this.tokenJwt = tokenJwt;
        }

        public String getEndpointProtegido() {
            return endpointProtegido;
        }

        public void setEndpointProtegido(String endpointProtegido) {
            this.endpointProtegido = endpointProtegido;
        }

        public String getMetodoAutenticacion() {
            return metodoAutenticacion;
        }

        public void setMetodoAutenticacion(String metodoAutenticacion) {
            this.metodoAutenticacion = metodoAutenticacion;
        }
    }
}

// === ARCHIVO: src/test/java/com/banca/seguridad/exceptions/SecurityException.java ===
package com.banca.seguridad.exceptions;

public class SecurityException extends RuntimeException {

    private String codigoError;
    private String nivelSeveridad;

    public SecurityException(String message) {
        super(message);
    }

    public SecurityException(String message, Throwable cause) {
        super(message, cause);
    }

    public SecurityException(String message, String codigoError) {
        super(message);
        this.codigoError = codigoError;
    }

    public SecurityException(String message, String codigoError, String nivelSeveridad) {
        super(message);
        this.codigoError = codigoError;
        this.nivelSeveridad = nivelSeveridad;
    }

    public SecurityException(String message, String codigoError, Throwable cause) {
        super(message, cause);
        this.codigoError = codigoError;
    }

    public String getCodigoError() {
        return codigoError;
    }

    public void setCodigoError(String codigoError) {
        this.codigoError = codigoError;
    }

    public String getNivelSeveridad() {
        return nivelSeveridad;
    }

    public void setNivelSeveridad(String nivelSeveridad) {
        this.nivelSeveridad = nivelSeveridad;
    }
}

// === ARCHIVO: src/test/resources/data/vulnerabilidades.csv ===
codigo_vulnerabilidad,tipo,criticidad,descripcion,puntuacion_cvss,estado,fecha_deteccion,metodo_deteccion,impacto_potencial,mitigacion_aplicada
VULN-001,Inyeccion SQL,Crítica,Campo de búsqueda vulnerable a inyección SQL mediante caracteres especiales,9.8,Pendiente,2024-01-15,Análisis de código,Exfiltración de datos de clientes,Usar PreparedStatement
VULN-002,XSS Reflejado,Alta,Parámetro de URL vulnerable a XSS reflejado en formulario de login,7.2,Pendiente,2024-01-16,Pruebas automatizadas,Robo de cookies de sesión,Validar y escapar entrada del usuario
VULN-003,Autenticación Débil,Crítica,Mecanismo de autenticación permite más de 5 intentos sin bloqueo,9.1,Pendiente,2024-01-17,Revisión manual,Acceso no autorizado a cuentas,Implementar política de bloqueo
VULN-004,Exposición de Datos Sensibles,Crítica,Credenciales almacenadas en texto plano en archivo de configuración,9.5,Resuelta,2024-01-10,Auditoría de seguridad,Compromiso de sistema,Cifrar configuración con AES-256
VULN-005,CSRF,Media,Token CSRF no implementado en formulario de transferencias,5.3,Pendiente,2024-01-18,Análisis de código,Falsificación de solicitudes,Implementar tokens CSRF
VULN-006,Broken Access Control,Alta,Usuario puede acceder a recursos de otro usuario mediante ID manipulation,7.8,Pendiente,2024-01-19,Pruebas de autorización,Acceso no autorizado a datos,Validar permisos en cada solicitud
VULN-007,Insecure Deserialization,Crítica,API acepta objetos serializados sin validación,9.1,Pendiente,2024-01-20,Análisis de código,Ejecución de código remoto,Validar tipo de datos
VULN-008,Security Misconfiguration,Alta,Depurador habilitado en entorno de producción,6.5,Resuelta,2024-01-11,Escaneo automatizado,Exposición de información,Deshabilitar modo debug
VULN-009,Using Components with Known Vulnerabilities,Crítica,Biblioteca vulnerable a CVE-2023-XXXX,9.0,Pendiente,2024-01-21,SBOM analysis,Ejecución de código,Actualizar a versión segura
VULN-010,Insufficient Logging,Media,Aplicación no registra intentos de autenticación fallidos,4.3,Resuelta,2024-01-12,Revisión de logs,Detección tardía de ataques,Implementar logging de seguridad
VULN-011,Inyección LDAP,Alta,Campo de búsqueda vulnerable a inyección LDAP,7.7,Pendiente,2024-01-22,Pruebas de penetración,Acceso no autorizado a directorio,Validar entrada y usar JNDI
VULN-012,XXE,Crítica,Parser XML vulnerable a ataques XXE,9.1,Pendiente,2024-01-23,Fuzzing,Exfiltración de archivos locales,Deshabilitar procesamiento DTD
VULN-013,SSRF,Alta,Parámetro de URL vulnerable a SSRF,8.6,Pendiente,2024-01-24,Análisis de código,Acceso a servicios internos,Lista blanca de URLs
VULN-014,Command Injection,Crítica,Campo de entrada vulnerable a inyección de comandos del sistema,9.8,Pendiente,2024-01-25,Pruebas de seguridad,Compromiso total del servidor,Validar y sanitizar entrada
VULN-015,Path Traversal,Alta,Parámetro de archivo vulnerable a path traversal,7.5,Pendiente,2024-01-26,Análisis de código,Acceso a archivos del sistema,Validar ruta de archivo

// === ARCHIVO: serenity.conf ===
webdriver {
  driver = chrome
  auto.download = true
  timeouts {
    implicitly.wait = 10000
    script.timeout = 30000
    page.load.timeout = 60000
  }
}

chrome {
  switch {
    "--no-sandbox" = ""
    "--disable-dev-shm-usage" = ""
    "--disable-gpu" = ""
    "--window-size" = "1920,1080"
    "--disable-extensions" = ""
    "--disable-popup-blocking" = ""
    "--ignore-certificate-errors" = ""
    "--allow-running-insecure-content" = ""
  }
  args = [
    "--no-sandbox",
    "--disable-dev-shm-usage",
    "--disable-gpu",
    "--window-size=1920,1080",
    "--disable-extensions",
    "--disable-popup-blocking",
    "--ignore-certificate-errors",
    "--allow-running-insecure-content"
  ]
}

serenity {
  take.screenshots = FOR_FAILURES
  screenshot.thumbnail.width = 150
  compress.screenshots = true
  report.prefix = seguridad_banca
  
  rest {
    baseurl = "https://banca-segura.internal.banco.com"
    timeout = 30000
    accept = "application/json"
    content.type = "application/json"
    
    default-headers {
      X-Request-ID = "${random.uuid}"
      X-Correlation-ID = "${random.uuid}"
    }
  }
  
  logging {
    level = INFO
    verbosity = VERBOSE
    log.class.matches = ".*"
  }
  
  test {
    fail.if.no.webdriver = true
    retry.count = 0
    parallel.execution.enabled = false
  }
  
  metrics {
    enabled = true
    capture.system.info = true
    capture.environment.variables = true
  }
}

environments {
  default = "dev"
  
  dev {
    webdriver.base.url = "https://banca-dev.internal.banco.com"
    api.base.url = "https://banca-dev.internal.banco.com/api/v1"
    auth.endpoint = "https://banca-dev.internal.banco.com/api/v1/auth/login"
    usuario.test = "test_user_dev"
    password.test = "TestDev2024!"
    entorno = "DESARROLLO"
  }
  
  qa {
    webdriver.base.url = "https://banca-qa.internal.banco.com"
    api.base.url = "https://banca-qa.internal.banco.com/api/v1"
    auth.endpoint = "https://banca-qa.internal.banco.com/api/v1/auth/login"
    usuario.test = "test_user_qa"
    password.test = "TestQA2024!"
    entorno = "QA"
  }
  
  staging {
    webdriver.base.url = "https://banca-staging.internal.banco.com"
    api.base.url = "https://banca-staging.internal.banco.com/api/v1"
    auth.endpoint = "https://banca-staging.internal.banco.com/api/v1/auth/login"
    usuario.test = "test_user_staging"
    password.test = "TestStaging2024!"
    entorno = "STAGING"
  }
  
  prod {
    webdriver.base.url = "https://banca.internal.banco.com"
    api.base.url = "https://banca.internal.banco.com/api/v1"
    auth.endpoint = "https://banca.internal.banco.com/api/v1/auth/login"
    usuario.test = "test_user_prod"
    password.test = "TestProd2024!Secure"
    entorno = "PRODUCCION"
  }
}

jwt {
  secret.key = "banca_segura_jwt_secret_key_para_pruebas_automatizadas_2024"
  issuer = "banca-segura-internal"
  expiration.minutes = 30
  algorithm = HS256
}

cifrado {
  algoritmo = AES-256-GCM
  key.size = 256
  iv.size = 12
  modo = GCM
}

vulnerabilidades {
  sql.injection {
    patrones = [
      "'",
      "''",
      ";",
      "--",
      "/*",
      "xp_",
      "union select",
      "union all select",
      "or 1=1",
      "' or '1'='1",
      "' or 1=1--",
      "1; DROP TABLE",
      "1' AND '1'='1"
    ]
    tiempo.maximo_respuesta.ms = 2000
    detectar.timebased = true
  }
  
  xss {
    patrones = [
      "<script>",
      "</script>",
      "<img src=x>",
      "javascript:",
      "onerror=",
      "onload=",
      "alert(",
      "eval("
    ]
  }
  
  autenticacion {
    tiempo.maximo_login.ms = 3000
    intentos.maximos = 5
    tiempo.bloqueo.minutos = 30
    password.min.longitud = 8
    password.requiere.especial = true
    password.requiere.numero = true
    password.requiere.mayuscula = true
  }
}

reportes {
  formato = html
  directorio = target/site/serenity
  incluir.screenshots = true
  incluir.logs = true
  incluir.peticiones.http = true
  incluir.respuestas.http = true
  tema = default
}

// === ARCHIVO: README.md ===
# Pruebas de Seguridad - Plataforma de Banca en Línea

Este proyecto implementa un marco de pruebas de seguridad automatizadas para la plataforma de banca en línea, utilizando Serenity BDD con el patrón Screenplay. Las pruebas cubren evaluación de riesgos, inyección SQL, autenticación y criptografía.

## Requisitos del Sistema

- Java Development Kit 21 o superior
- Apache Maven 3.9.x o superior
- Google Chrome (última versión estable)
- ChromeDriver compatible con la versión de Chrome
- Conexión a la red interna del banco para entornos dev/qa/staging

## Estructura del Proyecto

```
banca-seguridad-tests/
├── pom.xml
├── serenity.conf
├── README.md
└── src/
    └── test/
        ├── java/
        │   └── com/
        │       └── banca/
        │           └── seguridad/
        │               ├── runners/
        │               │   └── RunCucumberTest.java
        │               ├── steps/
        │               │   ├── EvaluacionRiesgosSteps.java
        │               │   ├── InyeccionSQLSteps.java
        │               │   └── AutenticacionSteps.java
        │               ├── tasks/
        │               │   ├── RealizarLogin.java
        │               │   └── VerificarVulnerabilidadSQL.java
        │               ├── questions/
        │               │   ├── ValidarAcceso.java
        │               │   └── ValidarVulnerabilidad.java
        │               ├── utils/
        │               │   └── SecurityUtils.java
        │               ├── config/
        │               │   └── SecurityConfig.java
        │               └── exceptions/
        │                   └── SecurityException.java
        └── resources/
            ├── features/
            │   └── seguridad/
            │       ├── evaluacion_riesgos.feature
            │       ├── inyeccion_sql.feature
            │       └── autenticacion.feature
            └── data/
                └── vulnerabilidades.csv
```

## Configuración

### Variables de Entorno

El proyecto utiliza el archivo `serenity.conf` para la configuración. Los entornos disponibles son:

- **dev**: Entorno de desarrollo
- **qa**: Entorno de pruebas de calidad
- **staging**: Entorno de pre-producción
- **prod**: Entorno de producción (solo lectura)

Para ejecutar en un entorno específico:

```bash
mvn verify -Dserenity.environment=qa
```

### Configuración de Seguridad

La configuración de seguridad se encuentra en `SecurityConfig.java` y incluye:

- Algoritmo de cifrado: AES-256-GCM
- Longitud de clave: 256 bits
- Proveedor de JWT con HS256
- Timeouts para detección de ataques de fuerza bruta

## Ejecución de Pruebas

### Compilar el Proyecto

```bash
mvn clean compile
```
```

### Ejecutar Todas las Pruebas

```bash
mvn verify
```
```

### Ejecutar un Feature Específico

```bash
mvn verify -Dcucumber.filter.tags="@seguridad"
```
```

### Ejecutar Pruebas de Inyección SQL

```bash
mvn verify -Dcucumber.filter.name="inyeccion"
```
```

### Generar Reportes sin Ejecutar

```bash
mvn serenity:aggregate
```
```

### Verificar que el Proyecto Compila

```bash
mvn clean test-compile
```
```

## Patrón Screenplay

Este proyecto implementa el patrón Screenplay para separar las responsabilidades en las pruebas de seguridad:

- **Tasks**: Acciones de alto nivel que realizan los actores (ej: `RealizarLogin`, `VerificarVulnerabilidadSQL`)
- **Questions**: Consultas que verifican el estado del sistema (ej: `ValidarAcceso`, `ValidarVulnerabilidad`)
- **Steps**: Definiciones de pasos Gherkin que traducen el lenguaje natural a acciones del sistema
- **Actors**: Los participantes en las pruebas (originador de créditos, motor antifraude, etc.)

## Escenarios de Prueba

### Evaluación de Riesgos

Los escenarios de evaluación de riesgos identifican y clasifican las vulnerabilidades potenciales en la plataforma de banca. Se evalúan los siguientes vectores de ataque:

- Inyección de código malicioso
- Acceso no autorizado a datos sensibles
- Vulnerabilidades en la autenticación
- Exposición de información confidencial

### Inyección SQL

Las pruebas de inyección SQL verifican que los puntos de entrada de la aplicación están correctamente sanitizados. Se prueban múltiples variantes de ataques SQL:

- Inyección basada en tautologías
- Inyección basada en comentarios
- Inyección UNION-based
- Inyección time-based blind

### Autenticación y Autorización

Las pruebas de autenticación verifican:

- Correcto funcionamiento del login
- Protección contra ataques de fuerza bruta
- Gestión de sesiones seguras
- Implementación de políticas de contraseñas robustas
- Funcionamiento de tokens JWT

## Reportes de Serenity

Los reportes HTML se generan en `target/site/serenity/` después de ejecutar las pruebas. Para visualizarlos:

```bash
# Generar y abrir reporte
mvn serenity:aggregate
```

El reporte incluye:

- Capturas de pantalla de cada paso
- Logs detallados de la ejecución
- Trazabilidad de las peticiones HTTP
- Métricas de cobertura de pruebas
- Historial de ejecución

## Tecnologías Utilizadas

- **Serenity BDD 4.0.48**: Framework de pruebas con reporting integrado
- **Selenium WebDriver 4.16.1**: Automatización del navegador
- **Cucumber 7.14.0**: BDD con Gherkin
- **Rest Assured 5.3.2**: Pruebas de API REST
- **JUnit 5**: Framework de pruebas unitarias
- **JWT (jjwt 0.12.3)**: Manejo de tokens JWT
- **Apache Commons Crypto**: Cifrado AES-256

## Mantenimiento

### Agregar Nuevos Escenarios

1. Crear o modificar el archivo `.feature` correspondiente en `src/test/resources/features/seguridad/`
2. Agregar los pasos necesarios en la clase de Steps correspondiente
3. Crear Tasks y Questions si se requieren nuevas interacciones
4. Ejecutar las pruebas para verificar

### Actualizar Datos de Prueba

Los datos de prueba se encuentran en:

- `src/test/resources/data/vulnerabilidades.csv`: Datos para pruebas de vulnerabilidades
- `serenity.conf`: Configuración de entornos y credenciales

### Agregar Nuevos Entornos

Para agregar un nuevo entorno, editar `serenity.conf` y agregar una nueva sección en `environments`:

```hocon
nuevo_entorno {
  webdriver.base.url = "https://..."
  api.base.url = "https://..."
  # ... otras configuraciones
}
```

## Solución de Problemas

### Error: ChromeDriver no encontrado

Asegúrate de que ChromeDriver esté en el PATH del sistema o configura la ruta en `serenity.conf`.

### Error: Timeout en las pruebas

Ajustar los valores de timeout en la sección `webdriver.timeouts` de `serenity.conf`.

### Error: Conexión rechazada

Verificar que el entorno配置ado esté disponible y accesible desde la red del banco.

## Contribuciones

Para contribuir a este proyecto:

1. Crear un branch desde `develop`
2. Agregar pruebas para el nuevo escenario de seguridad
3. Ejecutar las pruebas localmente
4. Crear Pull Request con descripción detallada

## Licencia

Uso interno - Banco Seguro S.A.

## Contacto

Para consultas sobre las pruebas de seguridad, contactar al equipo de Seguridad Informática.
```
