class: inverse, center, middle

# Párhuzamosság

---

# Atomic osztályok

```java
public static AtomicLong largest = new AtomicLong();
largest.set(Math.max(largest.get(), observed)); // Nem szálbiztos
```

helyett

```java
largest.updateAndGet(x -> Math.max(x, observed));
```

vagy

```java
largest.accumulateAndGet(observed, Math::max);
```

---

# `LongAdder` és `LongAccumulator` osztályok

* `AtomicLong` helyett
* Túl sok szál okozta teljesítménycsökkenés esetén

```java
LongAccumulator adder = new LongAccumulator(Long::sum, 0);
adder.accumulate(value);
```

* `Short`, `Integer`, `Long`, `Float` és `Double` osztályoknak statikus `sum`, `max` és
`min` metódusok

---

# `ConcurrentHashMap` újdonságok

* `putIfAbsent`, `compute`, `computeIfAbsent`, `merge` metódusok szálbiztosak
* Három fajta művelet:
  * `search`: keresés
  * `reduce`: összes elem alapján egy értéket állít elő
  * `forEach`: minden elemre végrehajtja

---

# Metódusok formái

* Négy verziója:
  * `xxxKeys`: csak a kulcsokon
  * `xxxValues`: csak az értékeken
  * `xxx`: kulcs és érték párokon
  * `xxxEntries`: `Map.Entry` objektumokon
* `threshold` paraméter: mennyinél indítson új szálat

---

# `search` metódusok

```java
String result = map.search(threshold, (k, v) -> v > 1000 ? k : null);
```

---

# `forEach` metódusok

```java
map.forEach(threshold,
  (k, v) -> System.out.println(k + " -> " + v));
```

```java
map.forEach(threshold,
  (k, v) -> k + " -> " + v, // Transformer
  System.out::println); // Consumer
```

```java
map.forEach(threshold,
  (k, v) -> v > 1000 ? k + " -> " + v : null, // Szűri a null elemeket
    System.out::println);
```

---

# `reduce` metódusok

```java
Long sum = map.reduceValues(threshold, Long::sum);
```

```java
Integer maxlength = map.reduceKeys(threshold,
  String::length, // Transformer
  Integer::max); // Accumulator
```

```java
Long count = map.reduceValues(threshold,
  v -> v > 1000 ? 1L : null, // Null értékeket szűri
  Long::sum);
```

`ToInt`, `ToLong` és `ToDouble` posztfixekkel

---

# `Set` view

* Nincs concurrent hash set, ezért csak egy view

```java
Set<String> words = ConcurrentHashMap.<String>newKeySet();
```

```java
Set<String> words = map.keySet(1L);
```

---

# `Arrays` párhuzamos műveletek

* `parallelSort` metódus, rendezés
* `parallelSetAll` metódus, elemek előállítása index alapján

```java
Arrays.parallelSetAll(values, i -> i % 10);
```

* `Arrays.parallelPrefix` kombinálás az előző elemekkel

```java
int[] i = {1, 1, 2, 2, 3, 3};
Arrays.parallelPrefix(i, (x, y) -> x + y ); // i = [1, 2, 4, 6, 9, 12]
```
