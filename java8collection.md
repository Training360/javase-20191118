class: inverse, center, middle

# Collections Framework módosítások

---

# `Collection.removeIf` metódus

```java
boolean removeIf(Predicate<? super E> filter)
```

```java
List<String> names = new ArrayList<>(Arrays.asList("John", "Harry"));
names.removeIf(n -> n.startsWith("J"));
```

---

# `List.replaceAll` metódus

```java
void replaceAll(UnaryOperator<E> o)
```

```java
List<Integer> list = Arrays.asList(1, 2, 3);
list.replaceAll(x -> x * 2);
System.out.println(list); // [2, 4, 6]
```

---

# `List.sort` metódus

```java
default void sort​(Comparator<? super E> c)
```

---

# `Collection.forEach` metódus

```java
List<String> cats = Arrays.asList("Annie", "Ripley");
cats.forEach(System.out::println);
```

---

# `Iterator.forEachRemaining`

```java
Iterator<String> i = Arrays.asList("Annie", "Ripley").iterator();
i.forEachRemaining(System.out::println);
```


---

# `Map.putIfAbsent` metódus

```java
Map<String, String> favorites = new HashMap<>();
favorites.put("Tom", "Tram");
favorites.put("Jenny", "Tram");
favorites.putIfAbsent("Tom", "Bus Tour"); // {Tom=Tram, Jenny=Tram}
```

---

# `Map.merge` metódus

* Ha nincs benne az adott kulccsal érték (vagy `null`), beleteszi, ha benne van, összefésüli

```java
Map<String, String> letters = new HashMap<>();
String one = letters.merge("a", "1", String::concat); // {a=1}
  // one = 1  
String two = letters.merge("a", "2", String::concat); // {a=12}
  // two = 12
```

---

# `Map.compute` metódus

* Előző érték figyelembe vételével kiszámolja az új értéket

```java
Map<String, String> letters = new HashMap<>();
String one = letters.compute("a", (k, v) -> (v == null) ? '1' : v.concat('1')); // {a=1}
  // one = 1  
String two = letters.compute("a", (k, v) -> (v == null) ? '1' : v.concat('1')); // {a=11}
  // two = 11
```

---

# `Map.computeIfPresent` metódus

```java
Map<String, Integer> counts = new HashMap<>();
counts.put("Jenny", 1);
Integer jenny = counts.computeIfPresent("Jenny", (k, v) -> v + 1); // {Jenny=2}
  // jenny = 2
Integer sam = counts.computeIfPresent("Sam", (k, v) -> v + 1); // {Jenny=2}
  // sam = null
```

---

# `Map.computeIfAbsent` metódus

```java
Map<String, Integer> counts = new HashMap<>();
counts.put("Jenny", 15);
Integer jenny = counts.computeIfAbsent("Jenny", k -> 1); // {Jenny=15}
  // jenny = 15
Integer sam = counts.computeIfAbsent("Sam", k -> 1); // {Jenny=15, Sam=1}
  // sam = 1
```

---

# `Map.replace`

Két paraméteres (csak ha benne van):

```java
Map<String, String> favorites = new HashMap<>();
favorites.put("Tom", "Tram");
favorites.put("Jenny", "Tram"); // {Tom=Tram, Jenny=Tram}
String tom = favorites.replace("Tom", "Bus Tour");
  // {Tom=Bus Tour, Jenny=Tram}; tom = Tram
String henry = favorites.replace("Henry", "Tram");
  // {Tom=Bus Tour, Jenny=Tram}; henry = null
```

Három paraméteres (ha benne van, és az az értéke):

```java
Map<String, String> favorites = new HashMap<>();
favorites.put("Tom", "Tram"); // {Tom=Tram}
boolean hiking = favorites.replace("Tom", "Bus Tour", "Hiking");
  // {Tom=Tram}; hiking = false
boolean busTour = favorites.replace("Tom", "Tram", "Bus Tour");
  // {Tom=Bus Tour}; busTour = true
```

---

# `Map.remove`

Két paraméteres (csak ha az az értéke):

```java
Map<String, String> favorites = new HashMap<>();
favorites.put("Tom", "Tram"); // {Tom=Tram}
boolean busTour = favorites.remove("Tom", "Bus Tour"); // {Tom=Tram}
  // busTour = false
favorites.remove("Tom", "Tram"); // {}
  // busTour = true
```

---

# `Collections` osztály

* `NavigableSet` és `NavigableMap` támogató metódusok `unmodifiable|synchronized|checked|empty)Navigable(Set|Map)`
* Debugging célokból checked wrapperek, ahol nincs deklarálva típus paraméter
  * Új `checkedQueue` metódus
* `emptySorted(Set|Map)` metódusok
