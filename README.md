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
📦 Projecte1_Grup4

├── game/
│   ├── Mecanica.java         → Control del flux de joc i torns
│   ├── Projecte1_Grup4.java  → Classe principal (main)
│   └── GameText.java         → Textos i missatges per consola
│
├── logic/
│   ├── Node.java         → Representació d’un estat del tauler
│   ├── CreateTree.java   → Generació de l’arbre de moviments
│   ├── MiniMax.java      → Implementació de l’algorisme Minimax
│   ├── Score.java        → Funció heurística d’avaluació
│   └── LogicaJoc.java    → Gestió de la IA i connexió amb el joc
│
└── entities/
    ├── Tauler.java     → Representació del tauler (7x6)
    └── Casella.java    → Estat de cada casella (BUIDA, HUMA, IA)


## 👥 Autors
- Ángel Delgado Girón
- Achraf Rouaz El Benhiti
- Javier Sánchez Osuna
- Ángel Jurado Herruzo
