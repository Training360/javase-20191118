class: inverse, center, middle

# Primitív típusok használata streamekben

---

# Primitív streamek

* Megvalósítható csomagoló osztályokkal és klasszikus streamekkel
* Gyakori műveletek egyszerűbben elvégezhetőek

```java
IntStream.of(1, 2, 3).average().getAsDouble(); // 2.0
```

---

# Primitív streamek fajtái

* `IntStream`
* `LongStream`
* `DoubleStream`

---

# Források

```java
DoubleStream empty = DoubleStream.empty();
DoubleStream varargs = DoubleStream.of(1.0, 1.1, 1.2);
DoubleStream random = DoubleStream.generate(Math::random);
DoubleStream fractions = DoubleStream.iterate(.5, d -> d / 2);
```

```java
IntStream integers = IntStream.range(1, 6);
IntStream rangeClosed = IntStream.rangeClosed(1, 5);
```

---

# Streamek közötti átjárás

* `map`, `mapToObj`, `mapToInt`, `mapToLong` és `mapToDouble` metódusok
* Mögöttük saját funkcionális interfészek, hiszen a szabvány interfészeket
nem lehet primitív típusokkal paraméterezni

```java
Stream<String> objStream = Stream.of("penguin", "fish");
IntStream intStream = objStream.mapToInt(s -> s.length());
```

---

# További interfészek és osztályok

* Pl. a `mapToInt` metódus paramétere `ToIntFunction` funkcionális interfész
* Sok funkcionális interfésznek van primitív típussal rendelkező párja, pl `BooleanSupplier`, `DoublePredicate`, `ToDoubleBiFunction`, stb.
* Ugyanígy az `Optional` sem paraméterezhető, ezért van `OptionalInt`, `OptionalLong` és `OptionalDouble`

---

# Max, min, átlag, stb. gyűjtése

* `IntSummaryStatistics` osztály
	* Darabszám, minimum és maximum érték, összeg és átlag

```java
IntStream ints = IntStream.range(1, 6);
IntSummaryStatistics stats = ints.summaryStatistics();
int max = stats.getMax(); // 6
int min = stats.getMin(); // 1
```
