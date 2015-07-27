/*
 * Copyright (c) 2014 Simon Percic
 *
 * Get the latest version from:
 * https://github.com/simonpercic/CollectionHelper
 *
 * Distributed under the MIT License, see LICENSE.txt for details
 */

package com.github.simonpercic.collectionhelper;

import com.github.simonpercic.collectionhelper.exceptions.InvalidOperationException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Collection helper.
 * <p/>
 * A set of static utility methods to simplify filtering and querying Java's Collections.
 * A limited subset of .NET framework's LINQ Enumerable Methods.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class CollectionHelper {
    /**
     * Filters a collection using the given predicate.
     *
     * @param items     source items
     * @param predicate predicate function
     * @param <T>       type of elements in the source collection
     * @return a new filtered list
     */
    public static <T> List<T> filter(Collection<T> items, IPredicate<T> predicate) {
        List<T> result = new ArrayList<T>();

        if (!isEmpty(items)) {
            for (T item : items) {
                if (predicate.apply(item)) {
                    result.add(item);
                }
            }
        }

        return result;
    }

    /**
     * Returns the first element from a collection that matches the given predicate.
     * Throws a {@link com.github.simonpercic.collectionhelper.exceptions.InvalidOperationException} if no matching element is found.
     *
     * @param items     source items
     * @param predicate predicate function
     * @param <T>       type of elements in the source collection
     * @return the first element that matches the given predicate
     * @throws com.github.simonpercic.collectionhelper.exceptions.InvalidOperationException if no matching element is found
     */
    public static <T> T first(Collection<T> items, IPredicate<T> predicate) {
        T firstOrNull = firstOrNull(items, predicate);

        if (firstOrNull == null) {
            throw new InvalidOperationException("No items match!");
        } else {
            return firstOrNull;
        }
    }

    /**
     * Returns the first element from a collection that matches the given predicate or null if no matching element is found.
     *
     * @param items     source items
     * @param predicate predicate function
     * @param <T>       type of elements in the source collection
     * @return the first element that matches the given predicate or null if no matching element is found
     */
    public static <T> T firstOrNull(Collection<T> items, IPredicate<T> predicate) {
        if (!isEmpty(items)) {
            for (T item : items) {
                if (predicate.apply(item)) {
                    return item;
                }
            }
        }

        return null;
    }

    /**
     * Returns <tt>true</tt> if any element of a collection matches the given predicate.
     *
     * @param items     source items
     * @param predicate predicate function
     * @param <T>       type of elements in the source collection
     * @return <tt>true</tt> if any element of the collection matches the given predicate
     */
    public static <T> boolean any(Collection<T> items, IPredicate<T> predicate) {
        T firstOrNull = firstOrNull(items, predicate);
        return firstOrNull != null;
    }

    /**
     * Returns <tt>true</tt> if all elements of a collection match the given predicate.
     *
     * @param items     source items
     * @param predicate predicate function
     * @param <T>       type of elements in the source collection
     * @return <tt>true</tt> if all elements of a collection match the given predicate
     */
    public static <T> boolean all(Collection<T> items, IPredicate<T> predicate) {
        if (isEmpty(items)) {
            return false;
        }

        for (T item : items) {
            if (!predicate.apply(item)) {
                return false;
            }
        }

        return true;
    }

    /**
     * Returns <tt>true</tt> if the collection is null or contains no elements.
     *
     * @param items collection
     * @return <tt>true</tt> if the collection is null or contains no elements
     */
    public static boolean isEmpty(Collection items) {
        return (items == null || items.size() == 0);
    }

    /**
     * Returns the only element from a collection that matches the given predicate.
     * Throws a {@link com.github.simonpercic.collectionhelper.exceptions.InvalidOperationException} if the number of found elements is not exactly 1.
     *
     * @param items     source items
     * @param predicate predicate function
     * @param <T>       type of elements in the source collection
     * @return the only element that matches the given predicate
     * @throws com.github.simonpercic.collectionhelper.exceptions.InvalidOperationException if the number of found elements is not exactly 1
     */
    public static <T> T single(Collection<T> items, IPredicate<T> predicate) {
        T singleOrNull = singleOrNull(items, predicate);

        if (singleOrNull == null) {
            throw new InvalidOperationException("No items match!");
        } else {
            return singleOrNull;
        }
    }

    /**
     * Returns the only element from a collection that matches the given predicate or null if such element is not found.
     * Throws a {@link com.github.simonpercic.collectionhelper.exceptions.InvalidOperationException} if there is more than 1 element matching the predicate.
     *
     * @param items     source items
     * @param predicate predicate function
     * @param <T>       type of elements in the source collection
     * @return the only element that matches the given predicate or null if such element is not found
     * @throws com.github.simonpercic.collectionhelper.exceptions.InvalidOperationException if there is more than 1 element matching the predicate
     */
    public static <T> T singleOrNull(Collection<T> items, IPredicate<T> predicate) {
        if (isEmpty(items)) {
            return null;
        }

        T result = null;

        for (T item : items) {
            if (predicate.apply(item)) {
                if (result == null) {
                    result = item;
                } else {
                    throw new InvalidOperationException("Multiple items match!");
                }
            }
        }

        return result;
    }

    /**
     * Returns the number of elements in a collection matching the given predicate.
     *
     * @param items     source items
     * @param predicate predicate function
     * @param <T>       type of elements in the source collection
     * @return the number of elements in a collection matching the given predicate
     */
    public static <T> int count(Collection<T> items, IPredicate<T> predicate) {
        int count = 0;

        if (!isEmpty(items)) {
            for (T element : items) {
                if (predicate.apply(element)) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Projects each element of a collection into a new collection.
     *
     * @param items     source items
     * @param mapper    mapping function
     * @param <TSource> type of elements in the source collection
     * @param <TResult> type of elements in the resulting collection
     * @return a new collection with projected element values
     */
    public static <TSource, TResult> List<TResult> map(Collection<TSource> items, IMapper<TSource, TResult> mapper) {
        if (isEmpty(items)) {
            return new ArrayList<TResult>();
        }

        List<TResult> result = new ArrayList<TResult>(items.size());

        for (TSource item : items) {
            TResult mappedItem = mapper.map(item);
            result.add(mappedItem);
        }

        return result;
    }
}
