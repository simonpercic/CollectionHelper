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
 * A set of static utility methods to simplify filtering and querying Java's Collections.
 * A limited subset of .NET framework's LINQ Enumerable Methods.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
@SuppressWarnings("checkstyle:finalclass")
public class CollectionHelper {

    private CollectionHelper() {
        // no instance
    }

    /**
     * The value returned instead of the index if no item matches the given predicate.
     */
    public static final int NOT_FOUND_INDEX = -1;

    /**
     * Filters a collection using the given predicate.
     *
     * @param items source items
     * @param predicate predicate function
     * @param <T> type of elements in the source collection
     * @return a new filtered list
     */
    public static <T> List<T> filter(Collection<T> items, Predicate<T> predicate) {
        List<T> result = new ArrayList<>();

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
     * Throws a {@link InvalidOperationException} if no matching
     * element is found.
     *
     * @param items source items
     * @param predicate predicate function
     * @param <T> type of elements in the source collection
     * @return the first element that matches the given predicate
     * @throws InvalidOperationException if no matching element is found
     */
    public static <T> T first(Collection<T> items, Predicate<T> predicate) {
        T firstOrNull = firstOrNull(items, predicate);

        if (firstOrNull == null) {
            throw new InvalidOperationException("No items match!");
        } else {
            return firstOrNull;
        }
    }

    /**
     * Returns the first element from a collection that matches the given predicate or null if no matching element is
     * found.
     *
     * @param items source items
     * @param predicate predicate function
     * @param <T> type of elements in the source collection
     * @return the first element that matches the given predicate or null if no matching element is found
     */
    public static <T> T firstOrNull(Collection<T> items, Predicate<T> predicate) {
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
     * Returns the index of the first element in a collection that matches the given predicate.
     * Returns {#NOT_FOUND_INDEX} if no element matches the given predicate.
     *
     * @param items source items
     * @param predicate predicate function
     * @param <T> type of elements in the source collection
     * @return index of the first element that matches the given predicate or {#NOT_FOUND_INDEX} if no element matches
     * the given predicate
     */
    public static <T> int firstIndexOf(Collection<T> items, Predicate<T> predicate) {
        if (!isEmpty(items)) {
            int index = 0;
            for (T item : items) {
                if (predicate.apply(item)) {
                    return index;
                }

                index++;
            }
        }

        return NOT_FOUND_INDEX;
    }

    /**
     * Returns <tt>true</tt> if any element of a collection matches the given predicate.
     *
     * @param items source items
     * @param predicate predicate function
     * @param <T> type of elements in the source collection
     * @return <tt>true</tt> if any element of the collection matches the given predicate
     */
    public static <T> boolean any(Collection<T> items, Predicate<T> predicate) {
        T firstOrNull = firstOrNull(items, predicate);
        return firstOrNull != null;
    }

    /**
     * Returns <tt>true</tt> if all elements of a collection match the given predicate.
     *
     * @param items source items
     * @param predicate predicate function
     * @param <T> type of elements in the source collection
     * @return <tt>true</tt> if all elements of a collection match the given predicate
     */
    public static <T> boolean all(Collection<T> items, Predicate<T> predicate) {
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
        return items == null || items.size() == 0;
    }

    /**
     * Returns the only element from a collection that matches the given predicate.
     * Throws a {@link InvalidOperationException} if the number of
     * found elements is not exactly 1.
     *
     * @param items source items
     * @param predicate predicate function
     * @param <T> type of elements in the source collection
     * @return the only element that matches the given predicate
     * @throws InvalidOperationException if the number of found elements is not exactly 1
     */
    public static <T> T single(Collection<T> items, Predicate<T> predicate) {
        T singleOrNull = singleOrNull(items, predicate);

        if (singleOrNull == null) {
            throw new InvalidOperationException("No items match!");
        } else {
            return singleOrNull;
        }
    }

    /**
     * Returns the only element from a collection that matches the given predicate or null if such element is not found.
     * Throws a {@link InvalidOperationException} if there is more than 1 element matching the predicate.
     *
     * @param items source items
     * @param predicate predicate function
     * @param <T> type of elements in the source collection
     * @return the only element that matches the given predicate or null if such element is not found
     * @throws InvalidOperationException if there is more than 1 element matching the predicate
     */
    public static <T> T singleOrNull(Collection<T> items, Predicate<T> predicate) {
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
     * Returns the index of the only element in a collection that matches the given predicate.
     * Returns {#NOT_FOUND_INDEX} if no element matches the given predicate.
     * Throws a {@link InvalidOperationException} if there is more than 1 element matching the predicate.
     *
     * @param items source items
     * @param predicate predicate function
     * @param <T> type of elements in the source collection
     * @return index of the only element that matches the given predicate or {#NOT_FOUND_INDEX} if no element matches
     * the given predicate
     * @throws InvalidOperationException if there is more than 1 element matching the predicate
     */
    public static <T> int singleIndexOf(Collection<T> items, Predicate<T> predicate) {
        int result = NOT_FOUND_INDEX;

        if (!isEmpty(items)) {
            int index = 0;
            for (T item : items) {
                if (predicate.apply(item)) {
                    if (result == NOT_FOUND_INDEX) {
                        result = index;
                    } else {
                        throw new InvalidOperationException("Multiple items match!");
                    }
                }

                index++;
            }
        }

        return result;
    }

    /**
     * Returns the number of elements in a collection matching the given predicate.
     *
     * @param items source items
     * @param predicate predicate function
     * @param <T> type of elements in the source collection
     * @return the number of elements in a collection matching the given predicate
     */
    public static <T> int count(Collection<T> items, Predicate<T> predicate) {
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
     * @param items source items
     * @param mapper mapping function
     * @param <TSource> type of elements in the source collection
     * @param <TResult> type of elements in the resulting collection
     * @return a new collection with projected element values
     */
    public static <TSource, TResult> List<TResult> map(Collection<TSource> items, Mapper<TSource, TResult> mapper) {
        if (isEmpty(items)) {
            return new ArrayList<>();
        }

        List<TResult> result = new ArrayList<>(items.size());

        for (TSource item : items) {
            TResult mappedItem = mapper.map(item);
            result.add(mappedItem);
        }

        return result;
    }
}
