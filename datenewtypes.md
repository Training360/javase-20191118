class: inverse, center, middle

# Dátumok kezelése Java 8-tól

---

# `LocalDate` és `LocalTime`

* `java.time` csomagban
* `LocalDate` csak dátumot tartalmaz idő nélkül
* `LocalTime` csak időt tartalmaz
* `LocalDateTime` időt és dátumot is tartalmaz
* Nem tartalmaznak időzónát
	* `ZonedDateTime`
* Immutable

---

# Használatuk

* `System.out.println(LocalDateTime.now());` eredménye `2017-04-05T10:27:36.986`
* Konkrét megadás `of()` metódusok használatával
* Nem lenient, kivételt dob, `DateTimeException`

```java
LocalDate date = LocalDate.of(2015, Month.JANUARY, 20);
LocalDate date = LocalDate.of(2015, 1, 20);
```

---

# Műveletek

* `plusXxx()` és `minusXxx()` metódusokkal
* Láncolt hívások
* `DayOfWeek` és `Month` enumok
* Összehasonlítás az `isBefore` és `isAfter` metódusokkal

```java
LocalDate date = LocalDate.of(2014, Month.JANUARY, 20);
System.out.println(date); // 2014-01-20
date = date.plusDays(2);
System.out.println(date); // 2014-01-22
date = date.plusWeeks(1);
System.out.println(date); // 2014-01-29

LocalDate.of(2014, Month.JANUARY, 20)
  .plusDays(2)
  .plusWeeks(1);
```

---

# Átjárás a típusok között

```java
LocalDateTime localDateTime = LocalDateTime.now();
LocalDate localDate = localDateTime.toLocalDate();
LocalTime localTime = localDateTime.toLocalTime();
LocalDateTime newLocalDateTime = LocalDateTime.of(localDate, localTime);
```

---

## Átjárás a régi típus között

```java
Date in = new Date();
LocalDateTime ldt = LocalDateTime.ofInstant(in.toInstant(), ZoneId.systemDefault());
Date out = Date.from(ldt.atZone(ZoneId.systemDefault()).toInstant());
```

---

# Formázás és parse-olás

* `DateTimeFormatter`
  * Konstanssal: `DateTimeFormatter.ISO_LOCAL_DATE`
  * Lokalizált stílussal: `DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)`
  * Formátum stringgel: `DateTimeFormatter.ofPattern("MMMM dd, yyyy, hh:mm")`
* Default locale / `withLocale(Locale)`
* Használata formatter vagy <br /> a `LocalDateTime` felől
* Immutable és szálbiztos

---

# Formázás és parse-olás példa

```java
DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm");
LocalDateTime now = LocalDateTime.of(2017, Month.JANUARY, 1, 12, 0);
System.out.println(formatter.format(now));
System.out.println(now.format(formatter));

LocalDateTime start = LocalDateTime.parse("2017.01.01. 12:00", formatter);
System.out.println(start);
```

---

## `ChronoUnit`

* Enum, `YEARS`, `MONTHS`, `DAYS`, `HOURS` értékkel
* Különbség: `ChronoUnit.DAYS.between(date1, date2);`
