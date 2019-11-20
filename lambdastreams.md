class: inverse, center, middle



# Streams, források és záró műveletek

---

# Streamek

* A stream adatok egymásutánja, mint egy cső vagy futószalag
* Véges és végtelen streamek
* Az éppen feldolgozás alatt lévő elem férhető hozzá, lehet, hogy a többi még nem is állt elő
* Részei
	* Forrás (source)
	* Közbülső műveletek (intermediate operations)
	* Záró műveletek (terminal operations)

---

# Források

* `Stream<String> empty = Stream.empty();`
* `Stream<Integer> singleElement = Stream.of(1);`
* `Stream<Integer> fromArray = Stream.of(1, 2, 3);`

```java
List<String> list = Arrays.asList("a", "b", "c");
Stream<String> fromList = list.stream();
Stream<String> fromListParallel = list.parallelStream();
```

```java
Stream<Double> randoms = Stream.generate(Math::random);
Stream<Integer> oddNumbers = Stream.iterate(1, n -> n + 2);
```
---

# Záró műveletek

* `count()`
* `min()` és `max()`
* `findAny()` és `findFirst()`
* `allMatch()`, `anyMatch()`, `noneMatch()`
* `forEach()`
* `reduce()`
* `collect()`

---

# `count()`

```java
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
System.out.println(s.count()); // 3
```

---

# `min()` és `max()`

```java
Stream<String> s = Stream.of("monkey", "ape", "bonobo");
Optional<String> min = s.min((s1, s2) -> s1.length() — s2.length());
min.ifPresent(System.out::println); // ape
```

* Üres stream esetén `Optional.empty()`

---

# `findAny()` és `findFirst()`

```java
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
Stream<String> infinite = Stream.generate(() -> "chimp");
s.findAny().ifPresent(System.out::println); // monkey
infinite.findAny().ifPresent(System.out::println); // chimp
```

* Üres stream esetén `Optional.empty()`

---

# `allMatch()`, `anyMatch()`, `noneMatch()`

```java
List<String> list = Arrays.asList("monkey", "2", "chimp");
Stream<String> infinite = Stream.generate(() -> "chimp");
Predicate<String> pred = x -> Character.isLetter(x.charAt(0));
System.out.println(list.stream().anyMatch(pred)); // true
System.out.println(list.stream().allMatch(pred)); // false
System.out.println(list.stream().noneMatch(pred)); // false
System.out.println(infinite.anyMatch(pred)); // true
```

---

# `forEach()`

```java
Stream<String> s = Stream.of("Monkey", "Gorilla", "Bonobo");
s.forEach(System.out::print); // MonkeyGorillaBonobo
```

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

# Három paraméteres `collect()` metódus

* Ugyanazt a módosítható objektumot módosítja
* _supplier_, _accumulator_, _combiner_

```java
Stream<String> stream = Stream.of("w", "o", "l", "f");
Set<String> set = stream.collect(TreeSet::new, TreeSet::add,
	TreeSet::addAll); // TreeSet példányt ad vissza
System.out.println(set); // [f, l, o, w]
```

---

# Egy paraméteres `collect()` metódus

* `Collector` interfész, definiál un. _mutable reduction operatort_

```java
Stream<String> stream = Stream.of("w", "o", "l", "f");
Set<String> set = stream.collect(Collectors.toCollection(TreeSet::new));
// TreeSet példányt ad vissza
System.out.println(set); // [f, l, o, w]
```

```java
Stream<String> stream = Stream.of("w", "o", "l", "f");
Set<String> set = stream.collect(Collectors.toSet());
System.out.println(set); // [f, w, l, o]
```
