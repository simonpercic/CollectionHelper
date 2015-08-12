# CollectionHelper 

## What is it?

A set of static utility methods to simplify filtering and querying Java's Collections.

A limited subset of [.NET framework's LINQ Enumerable Methods](http://msdn.microsoft.com/en-us/library/vstudio/system.linq.enumerable_methods(v=vs.100).aspx) for Java.

## Methods

#### Filter
Filters a collection using the given predicate
```java
List<T> filter(Collection<T> items, IPredicate<T> predicate)
```

#### First
Returns the first element from a collection that matches the given predicate.
Throws an exception if no matching element is found
```java
T first(Collection<T> items, IPredicate<T> predicate)
```

#### First or null
Returns the first element from a collection that matches the given predicate or null if no matching element is found
```java
T firstOrNull(Collection<T> items, IPredicate<T> predicate)
```

#### Any
Returns true if any element of a collection matches the given predicate
```java
boolean any(Collection<T> items, IPredicate<T> predicate)
```

#### All
Returns true if all elements of a collection match the given predicate
```java
boolean all(Collection<T> items, IPredicate<T> predicate)
```

#### Is empty
Returns true if the collection is null or contains no elements
```java
boolean isEmpty(Collection items)
```

#### Single
Returns the only element from a collection that matches the given predicate. Throws an exception if the number of found elements is not exactly 1
```java
T single(Collection<T> items, IPredicate<T> predicate)
```

#### Single or null
Returns the only element from a collection that matches the given predicate or null if such element is not found. Throws an exception if there is more than 1 element matching the predicate
```java
T singleOrNull(Collection<T> items, IPredicate<T> predicate)
```

#### Count
Returns the number of elements in a collection matching the given predicate
```java
int count(Collection<T> items, IPredicate<T> predicate)
```

#### Map
Projects each element of a collection into a new collection
```java
List<TResult> map(Collection<TSource> items, IMapper<TSource, TResult> mapper)
```

## Javadoc
[Click here](http://simonpercic.github.io/CollectionHelper/javadoc/)

## Usage

- Add as a submodule to your project's GIT repository
- Manually checkout / download and include in your project  

## Appendix

Since Java 8 you can also use [Lambda expressions](http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html) and [Streams](http://docs.oracle.com/javase/tutorial/collections/streams/) to simplify working with Collections.

## License

Open source, distributed under the MIT License. See [LICENSE](https://github.com/simonpercic/CollectionHelper/blob/master/LICENSE) for details.
