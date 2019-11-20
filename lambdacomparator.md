class: inverse, center, middle


# Comparator módosítások

---

# `Comparator.comparing`

```java
people.sort(Comparator.comparing(Person::getName));
```

```java
people.sort(Comparator.comparing(Person::getLastName)
    .thenComparing(Person::getFirstName));
```

```java
people.sort(Comparator.comparing(Person::getName,
  (s, t) -> s.toLowerCase().compareTo(t.trim().toLowerCase())));
```

`int`, `long` és `double` esetén

```java
people.sort(Comparator.comparingInt(p -> p.getName().length()));
```

---

# `null`, natural order és fordított rendezés

```java
people.sort(comparing(Person::getMiddleName,
  nullsFirst(naturalOrder())));
```

```java
people.sort(comparing(Person::getMiddleName,
  reverseOrder()));
```

```java
people.sort(comparing(Person::getName,
  Comparator.comparingInt(String::length)).reversed());
```

