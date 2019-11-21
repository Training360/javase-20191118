class: inverse, center, middle



# Időzónák és nyári időszámítás

---

# Időzónák

* `ZoneId` osztály
* Rendelkezésre álló időzónák: `ZoneId.getAvailableZoneIds()`
* Rendszer időzónája: `ZoneId.systemDefault()`, pl.: `Europe/Prague`
* Létrehozás: `ZoneId zoneId = ZoneId.of("Europe/Budapest");`

---

# `ZonedDateTime` osztály

* Dátum, idő és időzóna
* `ZonedDateTime.of(LocalDateTime, ZoneId)` metódus
* `toString()`, pl.: `2017-01-02T12:00+01:00[Europe/Prague]`
* Átváltás másik időzónába

```java
ZonedDateTime localZonedDateTime =
  ZonedDateTime.now(); 
// 2017-04-05T13:46:26.372+02:00[Europe/Prague]
ZonedDateTime utcZonedDateTime =
  zonedDateTime.withZoneSameInstant(ZoneId.of("UTC")); 
// 2017-04-05T11:46:26.372Z[UTC]
```

---

# Nyári időszámítás

* Nyári időszámítás, daylight saving, DST
* Helyi időt egy órával későbbre állítják az adott időzóna idejéhez képest (2017-ben március 26-án 2:00-kor 3:00-ra)

---

# Nyári időszámítás példa

```java
ZonedDateTime zonedDateTime =
  ZonedDateTime.of(LocalDateTime.of(2017, Month.MARCH, 26, 1, 59), 
	ZoneId.of("Europe/Budapest"));
  // 2017-03-26T01:59+01:00[Europe/Budapest]
zonedDateTime =
  zonedDateTime.plus(Duration.ofMinutes(1));
  // 2017-03-26T03:00+02:00[Europe/Budapest]

ZonedDateTime start =
  ZonedDateTime.of(LocalDateTime.of(2017, Month.MARCH, 26, 1, 00), 
	ZoneId.of("Europe/Budapest"));
  // 2017-03-26T01:00+01:00[Europe/Budapest]
ZonedDateTime end =
  ZonedDateTime.of(LocalDateTime.of(2017, Month.MARCH, 26, 6, 00), 
	ZoneId.of("Europe/Budapest"));
  // 2017-03-26T06:00+02:00[Europe/Budapest]
Duration duration = Duration.between(start, end); // PT4H
```
