class: inverse, center, middle


# Annotációk

---

# Repeating annotation

```java
@Repeatable(Schedules.class)
public @interface Schedule {
 String cron() default "";
}
```

```java
public @interface Schedules {
	 Schedule[] value();
}
```

```java
@Schedule(cron = "0 0 1 1 *")
@Schedule(cron = "0 0 * * *")
public void doPeriodicCleanup() { ... }
```

---

# Type annotation

* Példányosításnál

```java
new @Interned MyObject();
```

* Típuskényszerítésnél

```java
myString = (@NonNull String) str;
```

---

# Type annotation

* Interfész implementáláskor

```java
class UnmodifiableList<T> implements
    @Readonly List<@Readonly T> { ... }
```

* Metódusban kivételek deklarációjánál

```java
void monitorTemperature() throws
    @Critical TemperatureException { ... }
```

---

# Type annotation használata

* Pluginelhető típus ellenőrzés
* Java nem tartalmaz ilyen keretrendszert
* Még erősebb típusosság
* [Checker Framework](https://checkerframework.org/)

