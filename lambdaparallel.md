class: inverse, center, middle

# Párhuzamos streamek

---

# Párhuzamos stream

* Műveletek futtatása párhuzamos szálakon
* Sebesség
* Módosulhat az eredmény is (tipikusan pl. a sorrend)
* Alapértelmezetten függ a CPU (magok) számától
* Nagyobb elemszám esetén, ugyanis van overhead

---

# Párhuzamos stream létrehozása

```java
List<Integer> l = Arrays.asList(1,2,3,4,5,6);
Stream<Integer> parallelStream = l.stream().parallel();

Stream<Integer> parallelStream2 = l.parallelStream();
```

```java
parallelStream
  .forEach(s -> System.out.print(s+" "));
```

* `Stream.isParallel()` metódussal lehet lekérdezni
* `Stream.sequential()` metódussal <br /> egyszálúsítható

---

# Párhuzamosság megtartása

* A műveleteknek egyszálú és párhuzamos streameknél is ugyanazt az
  eredményt kell hozniuk, kivéve, ha explicit módon nemdeterminisztikus, pl. a `findAny()`
* Bizonyos műveletek elveszítik a párhuzamosságot (pl. `flatMap`)
* Bizonyos műveletek megtartják (pl. `Stream.concat(Stream s1, Stream s2)`, ha valamelyik párhuzamos)

---

# Külső változó módosítása

* Szinkronizációs problémák
* Szinkronizálva elvesztjük a performancia előnyt
* Mellékhatás

```java
List<Integer> l = new ArrayList<>();
IntStream.range(0, 100).parallel().forEach(l::add);
// null elemek, sőt, IndexOutOfBoundsException
System.out.println(l);
```

* Érdemes csak a paraméterekkel dolgozni, <br /> vagy kizárólag olvasni

---

# Sorrend

* Sorrendezettség függ a forrástól és a közbülső műveletektől
* Sorrendezett forrás, pl. a `List`, nem sorrendezett pl. a `HashSet`
* Közbülső művelet sorrendezhet, pl. a `sorted()`
* Közbülső művelet elvesztheti a sorrendezettséget, pl. `unordered()`
* Bizonyos záró műveletek figyelmen kívül hagyhatják a sorrendet, pl `forEach()`, helyette `forEachOrdered()`

---

# Sorrend elvesztése

* Sorrend elvesztése nemdeterminisztikus végeredménnyel jár
* Sorrend elvesztése csak párhuzamos streameknél járhat teljesítménynövekedéssel
* Bizonyos műveletek sorrendezett párhuzamos streameknél teljesítménycsökkenéssel járhatnak
	(pl. `skip()`, `limit()`, `findFirst()` metódusok, plusz műveletek a szálak együttműködésére)
* Szinkronizálják a szálakat, lassabb lesz, <br /> de konzisztens eredményt ad
* `findAny()` nem ad konzisztens eredményt, <br /> cserébe gyorsabb

---

# `unordered()` metódus

* `skip()`, `limit()`, `findFirst()` metódusoknál
* Ezeknél a `unordered()` metódus teljesítménynövekedést okozhat, ha nem számít a sorrend

---

# `reduce()`

```java
BinaryOperator<Integer> op = (a, b) -> a * b;
Stream<Integer> stream = Stream.of(3, 5, 6);
System.out.println(stream.reduce(1, op, op)); // 90
```

* _identity_, _accumulator_, _combiner_
* Mindig új objektumot hoz létre

---

# `reduce()` metódus párhuzamos környezetben

Meg kell felelni a következő szabályoknak:

* `combiner.apply(identity, u)` értéke `u`
* accumulator asszociatív és állapotmentes: `(a op b) op c` egyenlő `a ob (b op c)` értékével
* combiner asszociatív és állapotmentes, és az identity-vel kompatibilis: `combiner.apply(u, ` <br /> `accumulator.apply(identity, t))` egyenlő `accumulator.apply(u, t)` értékével

---

# `collect()` metódus párhuzamos környezetben

* Ugyanazok a feltételek, mint a `reduce()` metódusnál

---

# Egy paraméteres `collect()` metódus

* Mutable reduce operator
* `Collector.characteristics()` metódus, `Collector.Characteristics` enum

---

# Collector párhuzamos működés

Csak a következő feltételek együttes teljesülése esetén tud hatékonyan (párhuzamosan) működni:

* Stream párhuzamos
* A collector rendelkezik a `Collector.Characteristics.CONCURRENT` jellemzővel
* A stream nem sorrendezett, vagy a collector rendelkezik a `Collector.Characteristics.UNORDERED` <br /> jellemzővel

---

# Megfelelő collectorok

* A `Collectors.toSet()` nem rendelkezik a `CONCURRENT` jellemzővel
* Használhatóak a `Collectors.toConcurrentMap()` vagy `Collectors.groupingByConcurrent()` metódusok
