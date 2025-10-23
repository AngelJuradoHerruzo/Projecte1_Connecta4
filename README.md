# Connect 4 amb IA (Minimax)

Aquest projecte implementa el joc clàssic **4 en ratlla (Connect 4)** en **Java**, on el jugador humà competeix contra una **intel·ligència artificial** que utilitza l’algorisme **Minimax** per decidir les seves jugades.

---

## 🎯 Objectiu del joc
L’objectiu és col·locar **quatre fitxes consecutives** (horitzontalment, verticalment o en diagonal) abans que l’oponent.  
El jugador humà juga amb les fitxes `O` i la IA amb `X`.

---

## ⚙️ Funcionament bàsic
- El programa es juga per **consola**.  
- Cada torn s’alterna entre el jugador humà i la IA.  
- La IA analitza el tauler amb l’algorisme **Minimax**, a una profunditat configurada (per defecte 7).  
- Es calcula una **heurística (Score)** per valorar cada situació del tauler i triar la millor opció.  
- Quan algú connecta quatre fitxes o el tauler està ple, la partida acaba.

---

## 🧩 Estructura del projecte
