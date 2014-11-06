/*
 * Copyright (c) 2014 Simon Percic
 *
 * Get the latest version from:
 * https://github.com/simonpercic/CollectionHelper
 *
 * Distributed under the MIT License, see LICENSE.txt for details
 */

package eu.simonpercic.java.collectionhelper;

/**
 * Mapping functional interface.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public interface IMapper<TSource, TResult> {
    /**
     * Should map the given object to its projection.
     *
     * @param object source object
     * @return the projection of source object
     */
    TResult map(TSource object);
}
