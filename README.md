# CollectionHelper 

[![Build Status](https://api.travis-ci.org/simonpercic/CollectionHelper.svg?branch=master)](https://travis-ci.org/simonpercic/CollectionHelper) [ ![Download](https://api.bintray.com/packages/simonpercic/maven/collectionhelper/images/download.svg) ](https://bintray.com/simonpercic/maven/collectionhelper/_latestVersion)

## What is it?

A set of static utility methods to simplify filtering and querying Java's Collections.

A limited subset of [.NET framework's LINQ Enumerable Methods](http://msdn.microsoft.com/en-us/library/vstudio/system.linq.enumerable_methods(v=vs.100).aspx) for Java.

## Methods

#### Filter
Filters a collection using the given predicate
```java
List<T> filter(Collection<T> items, Predicate<T> predicate)
```

#### First
Returns the first element from a collection that matches the given predicate.
Throws an exception if no matching element is found
```java
T first(Collection<T> items, Predicate<T> predicate)
```

#### First or null
Returns the first element from a collection that matches the given predicate or null if no matching element is found
```java
T firstOrNull(Collection<T> items, Predicate<T> predicate)
```

#### Any
Returns true if any element of a collection matches the given predicate
```java
boolean any(Collection<T> items, Predicate<T> predicate)
```

#### All
Returns true if all elements of a collection match the given predicate
```java
boolean all(Collection<T> items, Predicate<T> predicate)
```

#### Is empty
Returns true if the collection is null or contains no elements
```java
boolean isEmpty(Collection items)
```

#### Single
Returns the only element from a collection that matches the given predicate. Throws an exception if the number of found elements is not exactly 1
```java
T single(Collection<T> items, Predicate<T> predicate)
```

#### Single or null
Returns the only element from a collection that matches the given predicate or null if such element is not found. Throws an exception if there is more than 1 element matching the predicate
```java
T singleOrNull(Collection<T> items, Predicate<T> predicate)
```

#### Count
Returns the number of elements in a collection matching the given predicate
```java
int count(Collection<T> items, Predicate<T> predicate)
```

#### Map
Projects each element of a collection into a new collection
```java
List<TResult> map(Collection<TSource> items, Mapper<TSource, TResult> mapper)
```

## Javadoc
[Click here](http://simonpercic.github.io/CollectionHelper/javadoc/)

## Usage

Add using Gradle:
```groovy
compile 'com.github.simonpercic:collectionhelper:1.2.1'
```

### Use
```java
// sample list
List<Integer> integerList = Arrays.asList(1, 4, 2, 7, 8, 0, 5);

// filter
List<Integer> largerThan2 = CollectionHelper.filter(integerList, new Predicate<Integer>() {
            @Override public boolean apply(Integer intValue) {
                return intValue > 2;
            }
        });
        
// map
List<String> mappedList = CollectionHelper.map(integerList, new Mapper<Integer, String>() {
            @Override public String map(Integer intValue) {
                return String.format("myString_%d", intValue);
            }
        });
``` 

## Android Pro-tip
Use the awesome [Gradle Retrolambda Plugin](https://github.com/evant/gradle-retrolambda) with Java 8 to use lambdas:
```java
// filter
List<Integer> largerThan2 = CollectionHelper.filter(integerList, intValue -> intValue > 2);
        
// map
List<String> mappedList = CollectionHelper.map(integerList, intValue -> String.format("myString_%d", intValue));

// or even using a method reference
List<String> simpleMappedList = CollectionHelper.map(integerList, String::valueOf);
``` 

## Appendix

If you are using Java 8 and are NOT on Android you can also use [Streams](http://docs.oracle.com/javase/tutorial/collections/streams/) to simplify working with Collections.

## Change Log
See [CHANGELOG.md](CHANGELOG.md)

## License

Open source, distributed under the MIT License. See [LICENSE](https://github.com/simonpercic/CollectionHelper/blob/master/LICENSE) for details.
