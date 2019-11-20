class: inverse, center, middle



# Bevezetés a lambda kifejezések használatába

---

# Funkcionális programozás

* Java objektumorientált programozási nyelv
* Elemi egység az objektum
* Funkcionális programozás
	* Új programozási módszertan
	* Elemi egység a függvény, ezért pl. paraméterként átadható
	* Deklaratív: nem a lépéseket adjuk meg
	* Nincs állapot
	* Program függvények hívása és kiértékelése

---

# Funkcionális programozás Javaban

* Lambda kifejezés olyan kódblokk, mely paraméterként átadható
* Felfogható névtelen metódusnak is
* Erőssége főleg a kollekcióknál nyilvánul meg
* Párhuzamos feldolgozás erősen támogatott

---

# `Comparator` használata anonymous inner class-szal

```java
trainers.sort(new Comparator<Trainer>() {
    @Override
    public int compare(Trainer trainer1, Trainer trainer2) {
        return trainer1.getName().compareTo(trainer2.getName());
    }
});
```

---

# Strategy tervezési minta

* Algoritmus részét kintről lehet megadni
* Valójában csak egy működést, egy utasítást kellene paraméterként átadni
* Objektumorientált overhead: hozzá tartozó osztály és példány
* Egyszerűsítés: anonymous inner class
* További egyszerűsítés: csak a metódustörzs átadása, <br /> lambda kifejezés

---

# `Comparator` használata lambda kifejezéssel

```java
trainers.sort((trainer1, trainer2) -> trainer1.getName().compareTo(trainer2.getName()));
```

* `trainer1` és `trainer2` paraméterrel kell meghívni egy metódust, mely visszatérési értéke a kifejezés értéke

---

# Lambda kifejezés szintaktika

* Paraméterek
	* Amennyiben egy paraméterből áll, nem kötelező a zárójel
	* Több paraméter esetén kötelező a zárójel
	* Megadható a típus
* Nyíl operátor
* Törzs
	* szabványos blokk, több utasítás, utasítások <br /> pontosvesszővel lezárva, `return` utasítás
	* egy utasítás esetén a zárójel, `return` utasítás <br /> és a pontosvessző elhagyható

---

# Funkcionális interfész

* Lambda kifejezések funkcionális interfészekkel működnek
* Csak egy metódust tartalmazhatnak
* `@FunctionalInterface` annotáció, több metódus esetén nem fordul le
* `Comparator` pl. funkcionális interfész

```java
trainers.sort((trainer1, trainer2) -> trainer1.getName().compareTo(trainer2.getName()));
```

---

# Method reference

* Amennyiben a lambda kifejezés csak egy metódust hív
* Négy formája
    * Statikus metódushívás
    * Példány metódusának hívása
    * Metódus hívása paraméteren (első paraméter a példány, a többi paraméter a hívás paraméterei)
    * Konstruktor hívása

```java
trainers.sort(Trainer::compareTraniersByName);
```

---

# Keresés

```java
public Trainer findFirst(List<Trainer> trainers, String name) {
    for (Trainer trainer: trainers) {
        if (trainer.getName().equals(name)) {
            return trainer;
        }
    }
    throw new IllegalArgumentException("Cannot find trainer with name: " + name);
}
```

---

# Strategy tervezési minta

* Keresés paraméterezhető

```java
public Trainer findFirst(List<Trainer> trainers, Condition<Trainer> condition) {
    for (Trainer trainer: trainers) {
        if (condition.apply(trainer)) {
            return trainer;
        }
    }
    throw new IllegalArgumentException("Cannot find trainer applied to the condition");
}
```

---

# Saját funkcionális interfész

```java
public interface Condition<T> {

    boolean apply(T t);
}
```

Használata:

```java
findFirst(trainers, trainer -> trainer.getName().equals("John Doe"));
```

---

# Már létező beépített interfész

* `java.util.function.Predicate`, `boolean test(T t)` metódussal

```java
public Trainer findFirst(List<Trainer> trainers, Predicate<Trainer> condition) {
    for (Trainer trainer: trainers) {
        if (condition.test(trainer)) {
            return trainer;
        }
    }
    throw new IllegalArgumentException("Cannot find trainer applied to the condition");
}
```

---

# Beépített interfészek 1.

* `Supplier<T>`: `get()` metódus visszatérési típusa `T`
* `Consumer<T>`: `accept(T t)` metódus visszatérési típusa `void`
* `BiConsumer<T>`: `accept(T t, U u)` metódus visszatérési típusa `void`
* `Predicate<T>`: `test(T t)` metódus visszatérési típusa `boolean`
* `BiPredicate<T>`: `test(T t, U u)` metódus visszatérési típusa `boolean`

---

# Beépített interfészek 2.

* `Function<T, R>`: `apply(T t)` metódus visszatérési típusa `R`
* `BiFunction<T, U, R>`: `apply(T t, U u)` metódus visszatérési típusa `R`
* `UnaryOperator<T>`: `apply(T t)` metódus visszatérési típusa `T`
* `BinaryOperator<T>`: `apply(T t1, T t2)` metódus visszatérési típusa `T`

