# Conecta 4

Conjunto de juegos en Java para la asignatura Tecnología de la Programación de la Universidad Complutense de Madrid.

### Características

 - Modo gráfico y modo consola.

                  
                  1|       |
                  2|       |
                  3|       |
                  4|       |
                  5|       |
                  6|       |
                   +-------+
                    1234567

                  Juegan blancas
                  Que quieres hacer? 
                 

 - Cuatro juegos
   - Conecta 4: Juego clásico, une 4 fichas del mismo color para ganar.
   - Complica: Similar a conecta 4, pero se pueden añadir más fichas una vez se haya llenado la columna.
   - Gravity: Juego similar a conecta 4, pero con el punto de gravedad del tablero en los extremos desde el centro.
   - Reversi: Juego que consiste en conseguir el mayor número de fichas del color que se juegue, se flanquean las fichas oponentes para convertirlas al color del jugador.
   
   ![](https://i.imgur.com/yPYU7mF.png "conecta4")
 - Gestión de jugadores
   - Jugador contra jugador: Dos jugadores pueden jugar desde el mismo equipo al juego.
   - Jugador contra máquina: Un jugador contra la inteligencia artificial de la máquina.
   - Máquina contra máquina: Partida totalmente automática.
 - Deshacer: Pueden desahacerse movimientos anteriores de toda la partida
 - Poner aleatoria: Se coloca una ficha aleatoriamente en el tablero

### Uso de la aplicación

```
tp.pr5.Main [-g <game>] [-h] [-u <tipo>] [-x <columnNumber>] [-y <rowNumber>]
 
 -g,--game <game>           Tipo de juego (c4, co, gr). Por defecto, c4.
 
 -h,--help                  Muestra esta ayuda.
 
 -u,--ui <tipo>             Tipo de interfaz (console, window). Por defecto, console.
 
 -x,--tamX <columnNumber>   Número de columnas del tablero (sólo para Gravity). Por defecto, 10.
 
 -y,--tamY <rowNumber>      Número de filas del tablero (sólo para Gravity). Por defecto, 10.
 ```
 
 En el modo "consola", tecleando "ayuda" aparecerá la información necesaria para jugar:
```
Los comandos disponibles son:

SALIR: termina la aplicacion.
DESHACER: deshace el ultimo movimiento hecho en la partida.
JUGAR [ c4 | co | gr ] [ tamX tamY ]:
PONER: utilizalo para poner la siguiente ficha.
JUGADOR [ blancas | negras ] [ humano | aleatorio ]: cambia el tipo de jugador,
AYUDA: Muestra la ayuda
REINICIAR: reinicia la partida.
```
### Referencias
[Commons CLI 1.2](https://commons.apache.org/proper/commons-cli/)

### Licencia 

MIT License

Copyright (c) 2016 Marcelino Pérez
