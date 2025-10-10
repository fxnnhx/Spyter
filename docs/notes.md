# Spyter

- während einer Tippübung, wird der zu tippende Text angezeigt und das bereits getippte Text visualisiert (als Overlay oder unter dem zu Tippenden Text angezeigt) *(muss)*
  - wie mit falsch eingegebenen Zeichen umgegangen wird, *muss* einer der folgenden Optionen implementiert (beide Optionen implementieren: *kann*):
    - Buchstabe übersprungen und als Fehler aufgenommen (mit Löschtaste korrigierbar)
    - Text bleibt stehen - zu schreibender Buchstabe visuell markieren, jeder Fehlversuch ein Fehler
- als Tippübungen können entweder .txt Dateien angegeben werden oder eine Auswahl an Zeichen, welche zufällig kombiniert werden *(muss)*
  - ggf. didaktisch sinnvolle Kombination der Buchstaben *(kann)*
  - ggf. Chat-Model Texte generieren lassen *(kann)*
- nach Übung werden Statistiken zu Geschwindigkeit und Korrektheit angezeigt
  - Wörter/Minute, Zeichen/Minute, Anschläge/Minute
- Reihe von bereitgestellten Übungen (als mitgelieferte .txt Datein) *(muss)*
- kompetitiver Modus *(soll)*
  - ein Spieler startet Session (Webserver)
  - andere können beitreten
  - alle bekommen den gleichen Text vom Host
  - alle Tippen bis zum Ende und dann werden Metriken verglichen

## User-Flow
- ausführbare Datei im gleichen Ordner wie Übungen
- bei Start kleines Menü das über Zahleneigabe gesteuert wird
  - Einzelspieler oder Mehrspieler
  - Text wählen
  - Modus wählen (Text abfragen oder Buchstaben aus dem Text üben)
  - Einzelspieler oder hosten

## Todo
- wie mache ich "Updates" im Terminal (Terminal UI)