# Conversor de Monedas

Este es un conversor de monedas sencillo que permite convertir entre distintas divisas (USD, ARS, BOB, BRL, CLP, COP) usando tasas de cambio predefinidas.

## Requisitos
Para ejecutar este programa, necesitarás:
- **Java**: Asegúrate de tener instalada una versión de Java compatible.

## Instrucciones de Uso
1. **Descargar el Proyecto**:
   - Clona el repositorio ejecutando:
     ```bash
     git clone https://github.com/ivanstafe/ConversorMonedas.git
     ```
   - Navega a la carpeta del proyecto:
     ```bash
     cd ConversorMonedas
     ```

2. **Compilar el Programa**:
   - En la terminal, ejecuta:
     ```bash
     javac Main.java
     ```

3. **Ejecutar el Programa**:
   - Una vez compilado, ejecuta:
     ```bash
     java Main
     ```

4. **Interacción**:
   - Selecciona una opción del menú principal:
     - Opción `1`: Inicia el proceso de conversión.
     - Opción `2`: Sale del programa.
   - En la conversión, ingresa la divisa de origen y la divisa de destino entre las opciones válidas: USD, ARS, BOB, BRL, CLP, COP.
   - Ingresa el monto para convertir y obtendrás el resultado.

## Notas
- Si se ingresa una moneda o monto no válidos, el programa mostrará un mensaje de error.
- Puedes agregar más tasas de cambio o conectarte a una API de conversión de divisas para tasas en tiempo real.

