class: inverse, center, middle

# Előre definiált collectorok

---

# Collectorok 1.

* avaraging
* counting
* `groupingBy`
* `joining`
* `maxBy` és `minBy`
* `mapping`
* `partitioningBy`

---

# Collectorok 2.

* summarizing
* summing
* `toList()`, `toSet()`
* `toCollection()`
* `toMap()`

---

# Alap collectorok

* String (`joining`)
* Matematikai (avaraging, counting, max, min, summarizing, summing)

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
String result = ohMy.collect(Collectors.joining(", "));
System.out.println(result); // lions, tigers, bears
```

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Double result = ohMy.collect(Collectors
  .averagingInt(String::length));
System.out.println(result); // 5.333333333333333
```

---

# Collection collectorok

* Kollekció (`toList`, `toSet`, `toCollection` - kollekció példányosítása adandó át paraméterként)

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Set<String> result = ohMy.filter(s -> s.startsWith("t")
	.collect(Collectors.toCollection(TreeSet::new));
// TreeSet példányt ad vissza
System.out.println(result); // [tigers]
```

---

# `Collectors.toMap()`

* keyMapper
* valueMapper
* mergeFunction (opcionális)
* mapSupplier (opcionális)

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Map<Integer, String> map = ohMy.collect(Collectors.toMap(
  String::length,
  v -> v,
  (s1, s2) -> s1 + "," + s2,
  TreeMap::new));
// TreeMap példányt ad vissza
System.out.println(map); // // {5=lions,bears, 6=tigers}
System.out.println(map.getClass()); // class. java.util.TreeMap
```

---

# `Collectors.groupingBy()`

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Map<Integer, List<String>> map = ohMy.collect(
	Collectors.groupingBy(String::length));
System.out.println(map); // {5=[lions, bears], 6=[tigers]}
```

---

# `List` helyett `Set`

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Map<Integer, Set<String>> map = ohMy.collect(
Collectors.groupingBy(String::length, Collectors.toSet()));
System.out.println(map); // {5=[lions, bears], 6=[tigers]}
```

---

# Más collector

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Map<Integer, Long> map = ohMy.collect(Collectors.groupingBy(
	String::length, Collectors.counting()));
System.out.println(map); // {5=2, 6=1}
```

---

# Visszatérési típus módosítása

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Map<Integer, Set<String>> map = ohMy.collect(
	Collectors.groupingBy(String::length, TreeMap::new, Collectors.toSet()));
// TreeMap példányt ad vissza
System.out.println(map); // {5=[lions, bears], 6=[tigers]}
```

---

# Partícionálás

* Csak két csoportra bontás: `true` és `false`

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Map<Boolean, List<String>> map = ohMy.collect(
	Collectors.partitioningBy(s -> s.length() <= 5));
System.out.println(map); // {false=[tigers], true=[lions, bears]}
```

---

# `List` helyett `Set`

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Map<Boolean, Set<String>> map = ohMy.collect(
	Collectors.partitioningBy(s -> s.length() <= 7, Collectors.toSet()));
System.out.println(map);// {false=[], true=[lions, tigers, bears]}
```

---

# Mapping

```java
Stream<String> ohMy = Stream.of("lions", "tigers", "bears");
Map<Integer, Optional<Character>> map = ohMy.collect(
	Collectors.groupingBy(
		String::length,
		Collectors.mapping(s -> s.charAt(0),
			Collectors.minBy(Comparator.naturalOrder()))));
System.out.println(map); // {5=Optional[b], 6=Optional[t]}
```
