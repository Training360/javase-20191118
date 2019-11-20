class: inverse, center, middle

# Default interfész metódusok

---

# Default metódusok

* Cél a visszafele kompatibilitás: amennyiben bővítjük az interfészt, ne kelljen minden implementációt módosítani
* Java 8-ban jelent meg
* Definiál egy absztrakt metódust is, melyet az osztályok override-olhatnak
* Ha nincs override-olva, a default implementáció használt

---

# Default metódus szabályok

* Default metódus csak interfészen belül definiálható
* A `default` kulcsszóval kell ellátni, és kötelező implementálni
* Nem látható el `static`, `final`, vagy `abstract` módosítókkal
* Láthatósági módosítója `public`, melyet nem kötelező kitenni. Más láthatósági módosító nem használható.

---

# Default metódusok leszármazásnál és implementálásnál

* Leszármazott interfész dönthet:
  * Öröklődik, nem definiálja felül
  * Felüldeklarálhatja (override)
  * Absztrakt metódusként definiálja felül

---

# Default metódusok és a többszörös öröklődés

* Leszármazáskor vagy implementáláskor névütközés
* Override nélkül nem fordul le
* Override-dal egyértelműsíthető
    * Saját implementáció
    * Delegálás

```java
class Trainer implements CanWork, Worker
{ 
    @Override
    public void work() 
    { 
        CanWork.super.work(); 
    }   
} 
```