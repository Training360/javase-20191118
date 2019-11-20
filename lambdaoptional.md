class: inverse, center, middle



# Optional osztály

---

# `Optional` osztály szükségessége

* Ha az algoritmus nem ad vissza értelmezhető eredményt speciális bemenő adatokra (ismeretlen, nincs adat, stb.)
* Gyakran speciális értéket adunk vissza, vagy `null` értéket
* Rossz gyakorlat a `NullPointerException` miatt
* Kivételkezelés sem megfelelő, hiszen nem feltétlen kivételes eset

---

# `Optional` osztály

* Burkoló osztály, érték nélkül vagy értékkel (üres, vagy értéket tartalmazó doboz)
* `Optional.empty()` vagy `Optional.of(T)` factory metódusok
* `Optional.ofNullable(T)` factory metódus
* Generikussal

```java
public static Optional<Double> average(int... scores) {
	if (scores.length == 0) return Optional.empty();
	int sum = 0;
	for (int score: scores) {
		sum += score;
	}
	return Optional.of((double) sum / scores.length);
}
```

---

# Érték lekérése

```java
Optional<Double> opt = average(90, 100);
if (opt.isPresent()) {
	Double d = opt.get();
}
```

Ha nem tartalmaz értéket: `java.util.NoSuchElementException: No value present`

---

# `Optional` metódusai

* `ifPresent(Consumer c)`
* `orElse(T other)`
* `orElseGet(Supplier s)`
* `orElseThrow(Supplier s)`

```java
avarage(90, 10)
	.ifPresent(System.out::println);
```

```java
double d = avarage(90, 10)
	.orElse(Double.NaN);
```
