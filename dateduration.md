class: inverse, center, middle

# Duration
---

# `Duration` osztály létrehozása

* Idő szintű hossz (pl. 2 óra és 30 perc)
  * `Duration duration = Duration.ofHours(3);`
* Két dátum különbségeként is létrehozható
  * `Duration duration = Duration.between(localDateTime1, localDateTime2);`

---

# `Duration` osztály használata

* `parse()` metódussal, `toString()` eredménye
  * `PT20.345S`, `PT15M`, `P2D`
* Mindig normalizálva tárolva
  * `System.out.println(Duration.parse("PT125M")); // PT2H5M`
* Immutable és szálbiztos

---

# Műveletek dátumokon

* `LocalDate`, `LocalTime` és `LocalDateTime` `plus()` és `minus()` metódusaival
	* `LocalDateTime.now().add(Duration.parse("PT2M"))`
* `Duration` `addTo()` és `subtractFrom()` metódusaival
	* `Duration.parse("PT2M").addTo(LocalDateTime.now());`
	* `(LocalDateTime)` cast

---

# Műveletek `Duration` példányokon

* `plusXxx` és `minusXxx` metódusok
* `plus()` metódussal másik `Duration` példány adható hozzá
