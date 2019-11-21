# További apróságok

---

# Paraméter nevek reflectionnel

```java
Person getEmployee(@PathParam("dept") Long dept, @QueryParam("id") Long id)
```

helyett

```java
Person getEmployee(@PathParam Long dept, @QueryParam Long id)
```

* Új osztállyal: `java.lang.reflect.Parameter`
* Fordítás a `-parameters` kapcsolóval

---

# `Null` ellenőrzés

```java
stream.anyMatch(Object::isNull)
```

```java
stream.filter(Object::nonNull)
```

---

# Lazy naplózó üzenetek

Késői kiértékelés

```java
logger.finest(() -> "x: " + x + ", y:" + y);
```

```java
this.directions = Objects.requireNonNull(directions,
	() -> "directions for " + this.goal + " must not be null");
```

Ha a `directions` értéke `null`, `NullPointerException` a megadott üzenettel

---

# Reguláris kifejezések

```
(?<city>[\p{L} ]+),\s*(?<state>[A-Z]{2})
```

```java
Matcher matcher = pattern.matcher(input);
if (matcher.matches()) {
	String city = matcher.group("city");
}
```

```java
Stream<String> words = Pattern.compile("[\\P{L}]+").splitAsStream(contents);
```

```java
Stream<String> acronyms = words.filter(Pattern.compile("[A-Z]{2,}").asPredicate());
```

---

# `String` műveletek

* `join` metódus

```java
String joined = String.join("/", "usr", "local", "bin"); // "usr/local/bin"
```

* `StringJoiner` osztály (` Collectors.joining()` is használja)

```java
StringJoiner sj = new StringJoiner(":", "[", "]");
sj.add("George").add("Sally").add("Fred");
String desiredString = sj.toString();
```

---

# Matematikai műveletek

* `add | subtract | multiply | increment | decrement | negate )Exact` metódusok `int` és `long` paraméterekkel
  * Túlcsordulás helyett kivételt dobnak
