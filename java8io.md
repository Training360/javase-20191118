class: inverse, center, middle


# Fájlkezelés

---

# Sorok beolvasása

```java
try (Stream<String> lines = Files.lines(path)) {
	Optional<String> passwordEntry
		= lines.filter(s -> s.contains("password")).findFirst();
}
```

Alapértelmezett UTF-8 karakterkódolás

---

# `close()` láncolása

```java
try (Stream<String> filteredLines
	= Files.lines(path).filter(s -> s.contains("password"))) {
			Optional<String> passwordEntry = filteredLines.findFirst();
}
```

---

# `onClose()` handler

```java
try (Stream<String> filteredLines
	= Files.lines(path).onClose(() -> System.out.println("Closing"))
		.filter(s -> s.contains("password"))) { ... }
```

---

# `BufferedReader` `lines()` metódusa

```java
try (BufferedReader reader
	= new BufferedReader(new InputStreamReader(url.openStream()))) {
			Stream<String> lines = reader.lines();
}
```

---

# Könyvtár bejárás

```java
try (Stream<Path> entries = Files.list(pathToDirectory)) {
	...
}
```

Nem lép be az alkönyvtárakba

---

# Rekurzív könyvtár bejárás

```java
try (Stream<Path> entries = Files.walk(pathToRoot)) {
	// depth-first bejárás
}
```

* `Files.walk(pathToRoot, depth)` megadható maximum mélység
* `FileVisitOption.FOLLOW_LINKS` szimbólikus linkek követése

---

# Base64

```java
Base64.Encoder encoder = Base64.getEncoder();
String original = username + ":" + password;
String encoded = encoder.encodeToString(original.getBytes(StandardCharsets.UTF_8));
```

---

# Base64 streameknél

```java
Path originalPath = ..., encodedPath = ...;
Base64.Encoder encoder = Base64.getMimeEncoder();
try (OutputStream output = Files.newOutputStream(encodedPath)) {
	Files.copy(originalPath, encoder.wrap(output));
}
```

```java
Path encodedPath = ..., decodedPath = ...;
Base64.Decoder decoder = Base64.getMimeDecoder();
try (InputStream input = Files.newInputStream(encodedPath)) {
	Files.copy(decoder.wrap(input), decodedPath);
}
```
