class: inverse, center, middle

# Stream közbülső műveletek

---

# Közbülső műveletek

* `filter()`
* `distinct()`
* `limit()` és `skip()`
* `map()`
* `flatMap()`
* `sorted()`
* `peek()`

---

# Jellemzők

* Állapottal rendelkező (_stateful_): pl. `distinct()`
* Állapotnélküli (_stateless_): pl. `map()`
* _Short-circuiting_: amennyiben végtelen az input stream, mégis végeset ad vissza, 
    vagy záróműveletnél véges időben lefut

---

# `filter()`

```java
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
s.filter(x -> x.startsWith("m")).forEach(System.out::print); //monkey
```

---

# `distinct()`

```java
Stream<String> s = Stream.of("duck", "duck", "duck", "goose");
s.distinct().forEach(System.out::print); // duckgoose
```

---

# `limit()` és `skip()`

```java
Stream<Integer> s = Stream.iterate(1, n -> n + 1);
s.skip(5).limit(2).forEach(System.out::print); // 67
```

---

# `map()`

* Egy-egy megfeleltetés

```java
Stream<String> s = Stream.of("monkey", "gorilla", "bonobo");
s.map(String::length).forEach(System.out::print); // 676
```

---

# `flatMap()`

```java
List<String> zero = Arrays.asList();
List<String> one = Arrays.asList("Bonobo");
List<String> two = Arrays.asList("Mama Gorilla", "Baby Gorilla");
Stream<List<String>> animals = Stream.of(zero, one, two);
animals.flatMap(l -> l.stream()).forEach(System.out::println);
// Bonobo
// Mama Gorilla
// Baby Gorilla
```

* Stream elemeihez stream rendelhető, és ezeket kombinálja
* Egy elemhez vagy nem tartozik elem, vagy egy vagy több is tartozhat

---

# `sorted()`

```java
Stream<String> s = Stream.of("brown-", "bear-");
s.sorted().forEach(System.out::print); // bear-brown-
```

```java
Stream<String> s = Stream.of("brown bear-", "grizzly-");
s.sorted(Comparator.reverseOrder())
.forEach(System.out::print); // grizzly-brown bear-
```

---

# `peek()`

```java
Stream<String> stream = Stream.of("black bear", "brown bear", "grizzly");
long count = stream.filter(s -> s.startsWith("g"))
.peek(System.out::println).count(); // grizzly
System.out.println(count); // 1
```

* Debug célokra

---

# Lazy kiértékelés

* A közbülső műveletek nem hajtódnak végre, míg a lezáró művelet nem hajtódik végre
