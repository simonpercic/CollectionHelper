/*
 * Copyright (c) 2014 Simon Percic
 *
 * Get the latest version from:
 * https://github.com/simonpercic/CollectionHelper
 *
 * Distributed under the MIT License, see LICENSE.txt for details
 */

package com.github.simonpercic.collectionhelper;

/**
 * Predicate functional interface.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public interface IPredicate<T> {
    /**
     * Should return <tt>true</tt> if the given object matches the predicate.
     *
     * @param object the object being tested
     * @return <tt>true</tt> if the given object matches the predicate
     */
    boolean apply(T object);
}
