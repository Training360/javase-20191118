class: inverse, center, middle

## Clock osztály

---

## `Clock` osztály

* Meg lehet adni egy órát, mely alapján kerülnek létrehozásra a dátum és idő típusú példányok
* Használható pl. teszteléshez

```java
Clock clock = Clock.fixed(
        LocalDateTime.of(2019, 1, 1, 0, 0, 0).atZone(ZoneId.systemDefault()).toInstant(),
        ZoneId.systemDefault());

LocalDateTime nowWithClock = LocalDateTime.now(clock); // mindig 2019-01-01T00:00
```

---

## Különböző órák

* `Clock.fixed()`: rögzített óra, mindig ugyanazt az időt mutatja
* `Clock.offset()`: az aktuális időhöz képest mindig egy fix eltolással mutatja az időt (elállított óra)
* `Clock.systemXXX()`: rendszeróra
* `Clock.tickXXX()`: olyan óra, mely csak a megadott időközönként lép előre, pl. csak percenként, addig ugyanazt az időt mutatja
