# Dinamikus tesztek

```java
@TestFactory
Stream<DynamicTest> testGetAge() {
  return Stream
   .of(new Integer[][]{{2000, 0}, {2010, 10}, {2015, 15}, {2050, 50}, {1990, -10}})
   .map(item ->
      dynamicTest(
        "In year " + item[0] + " employee must be " + item[1] + "years old",                
        () -> assertEquals((int) item[1],
        new Employee("John Doe", 2000).getAge(item[0]))));
}
```