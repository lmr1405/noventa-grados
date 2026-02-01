# Noventa Grados
**Juego realizado para un trabajo de clase de la asignatura de Metodología de la programación de segundo año de Ingeniería Informática.**

![Pantalla de Inicio](https://github.com/lmr1405/noventa-grados/blob/main/images/inicio.png?raw=true)



**Noventa Grados** es un juego clásico para dos jugadores que combina estrategia y movimientos tácticos en un tablero de 7x7. El objetivo es lograr vencer a tu oponente mediante dos posibles condiciones de victoria: echar a la reina rival fuera del tablero o colocar tu reina en el centro del tablero.

## Descripción del Juego

En **Noventa Grados**, cada jugador controla piezas blancas o negras y toma turnos alternos para colocar fichas en el tablero. El juego se desarrolla bajo las siguientes reglas clave:

- Una pieza puede moverse únicamente en función del número de piezas que tiene en su perpendicular.
- Si ambas reinas salen del tablero, el juego termina en tablas.
- Gana quien:
  - Eche la reina del oponente fuera del tablero.
  - Coloque su reina en el centro del tablero.

## Reglas

1. **Movimientos**:
   - Para moverse, una pieza cuenta cuántas fichas hay en su línea perpendicular (horizontal y vertical, sin importar el color).
   - Esa cantidad determina cuántos espacios puede moverse.

2. **Condiciones de Victoria**:
   - Echar la reina del oponente fuera del tablero.
   - Colocar tu reina en el centro del tablero.

3. **Empates**:
   - Si ambas reinas salen del tablero, el juego termina en empate.

## Cómo Jugar

1. Elige un jugador para controlar las piezas blancas y otro para controlar las negras.
2. Coloca las piezas en el tablero inicial (7x7).
3. Toma turnos para mover las piezas, respetando las reglas de movimiento y buscando cumplir una de las condiciones de victoria.
4. Que gane el mejor mejor

## Como ejecutar el juego
### Requisitos previos
- **Java 11** o superior debe estar instalado en tu sistema.

### Ejecución
En linux
Para ejecutar el juego en modo grafico  
Otorgale los permisos necesarios para ello  
	1.- escribiremos en la consola chmod +x ejecutar_gui.sh  
	2.- Ejecuta ./ejecutar_gui.sh  
	3.- Disfruta de tu partida  
