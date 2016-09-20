Change Log
==========

Version 1.2.1 *(2016-09-20)*
----------------------------

 * Fix: Set Java source and target compatibility to Java 1.7 to fix issues when used in Android projects.


Version 1.2.0 *(2016-08-21)*
----------------------------

 * New: Converted the project to a Java library, can now be used in pure Java (non-Android) projects as well.


Version 1.1.0 *(2016-06-08)*
----------------------------

 * New: Added **firstIndexOf** - to help with finding the index of the first Collection item that matches a predicate.
 * New: Added **singleIndexOf** - same as firstIndexOf, but verifies that no more than 1 Collection item matches the predicate.
 * New: Renamed `IPredicate` and `IMapper` interfaces to `Predicate` and `Mapper`, respectively, thus making the naming slightly more Java-esque.


Version 1.0.0 *(2015-08-14)*
----------------------------

Initial release.
