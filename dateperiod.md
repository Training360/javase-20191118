class: inverse, center, middle

# Period

---

# `Period` osztály létrehozása

* Dátum szintű hossz (pl. 2 év, 6 hónap és 2 nap)
  * `Period annually = Period.ofYears(1);`
  * `Period everyYearAndAWeek = Period.of(1, 0, 7);`
* Két dátum különbségeként is létrehozható
  * `Period period = Period.between(localDate1, localDate2);`

---

# `Period` osztály használata

* `parse()` metódussal, `toString()` eredménye
  * `P2Y`, `P3M`, `P4W`, `P5D`, `P1Y2M3D`
* Normalizálás (`normalized()` metódus) 12 hónapot évvé vált
* Immutable és szálbiztos

---

# Műveletek dátumokon

* `LocalDate` és `LocalDateTime` `plus()` és `minus()` metódusaival
	* `LocalDateTime.now().plus(Period.ofYears(1))`
* `Period` `addTo()` és `subtractFrom()` metódusaival
	* `LocalDateTime localDateTime = (LocalDateTime) Period.ofYears(1).addTo(LocalDateTime.now());`

---

# Műveletek `Period` példányon

* `plusXxx` és `minusXxx` metódusok
* `plus()` metódussal másik `Period` példány adható hozzá
