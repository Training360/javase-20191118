class: inverse, center, middle

## Instant osztály

---

## `Instant` osztály

* Egy pillanat az idővonalon UTC időzóna szerint
* EPOCH-tól (1970. január 1-től) eltelt idő, nanoszekundum bontásban
* Létrehozása `Instant.now()` metódussal
* Kiírás ISO-8601 formátumban
* Nincs naptár társítva
    * Nem működnek a műveletek hónapokkal és évekkel
* Összehasonlítható

---

## `Instant` osztály példák

```java
Instant now = Instant.now(); // 2019-07-10T16:06:22.944565200Z

Instant afterNow = now.plus(1, ChronoUnit.HOURS);
afterNow.isAfter(now); // true

now.plus(1, ChronoUnit.MONTHS); // UnsupportedTemporalTypeException: Unsupported unit: Months
```

---

## Összehasonlítás más osztályokkal

* `Instant`
    * Mindig UTC-ben definiálja a pillanatot, nincs naptár
* `LocalDateTime` osztály: dátum és idő
    * Nem tartalmaz időzónát, ISO-8601 naptár van hozzá társítva
    * Különböző pillanatokat reprezentálhat időzónánként
* `ZonedDateTime` tartalmaz időzónát is
    * Időzónát tartalmaz, ISO-8601 naptár van hozzá társítva
    * Megmondja, hogy melyik időzónában meghatározott a pillanat

---

## Konvertálások

```java
// Cél időzóna meghatározása, majd időzóna információ elhagyása
LocalDateTime dateTime = LocalDateTime.ofInstant(now, ZoneId.systemDefault());

// Átváltás ZonedDateTime példánnyá, időzóna megadásával, majd Instant példánnyá konvertálás
Instant instant = dateTime.atZone(ZoneId.systemDefault()).toInstant();
```
