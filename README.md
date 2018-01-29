# Implements the game PacMan

## Projektziel

1. PacMan lässt sich bewegen im einen vorgegebenen Areal.
2. PacMan läuft nicht über Wände.
3. PacMans Mund ändert sich bei der Bewegung → Orientierung.
4. PacMan merkt sich die Bewegung bis zur nächsten Bewegung.
5. PacMan frisst Punkte.
6. Es gibt einen Score für die gefressenen Punkte.
7. Wenn alle Punkte gefressen wurden, kommt PacMan in das nächste Level bzw. das Programm wird beendet → Game Over.
8. Es gibt Geister.
9. Die Geister sind keine Rechteckte sondern „echte“ Geister.
10. Die Geister bewegen sich ebenfalls im vorgegebenen Areal.
11. Die Geister laufen hinter PacMan her.
12. Die Geister folgen einem Bewegungsmuster.
13. Wenn ein Geist PacMan berührt, verliert PacMan ein Leben.
14. PacMan hat eine gegebene Anzahl von Leben.
15. Wenn PacMan keine Leben mehr hat, ist das Spiel zu Ende.
16. Es gibt Extras im Spiel, die es PacMan erlauben, z.B. die Geister zu jagen.
17. Das Spiel ist mit Sound unterlegt.

## Klassen

–> im Ideenspeicher: Main-Klasse → von der erben alle Klassen!?

1. Fenster-Klasse
2. Spielfeld-Klasse (enthält Punkte und Wände)
3. Figuren-Klasse → davon erben PacMan, Geister, (Extras → eventuell in Spielfeld-Klasse!?)
4. Audio-Klasse
