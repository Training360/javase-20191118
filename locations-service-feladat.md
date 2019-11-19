Locations

```
saveLocations(): List<Location>
	- paraméterül meg lehet adni több hely nevét
	
	- végigmegy a helyeken
		- megvizsgálja a LocationsRepository segítségével,
			ezzel a névvel van-e hely
		- koordinátát töltse fel 5.0,5.0
		- amelyik nem létezik, arra meghívja 
		a repo saveLocation(Location) metódusát
	- visszaad Location objektumok listáját
		- amiket létrehozott
	- Location - String id-t - service töltse ki egy
	véletlen UUID-val
	
	AssertJ -ellenőrzéshez
```