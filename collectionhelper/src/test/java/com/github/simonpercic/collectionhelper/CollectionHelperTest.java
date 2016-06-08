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

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Tests.
 *
 * @author Simon Percic <a href="https://github.com/simonpercic">https://github.com/simonpercic</a>
 */
public class CollectionHelperTest {
    private List<Integer> testCollection;

    @Before
    public void setUp() throws Exception {
        testCollection = Arrays.asList(1, 4, 2, 7, 8, 0, 5);
    }

    @After
    public void tearDown() throws Exception {
        testCollection = null;
    }

    @Test
    public void testFilter() throws Exception {
        List<Integer> largerThan2 = CollectionHelper.filter(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return object > 2;
            }
        });

        assertThat(largerThan2, is(Arrays.asList(4, 7, 8, 5)));

        List<Integer> largerThan50 = CollectionHelper.filter(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return object > 50;
            }
        });

        assertThat(largerThan50.size(), is(0));

        List<Integer> noFiltering = CollectionHelper.filter(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return true;
            }
        });

        assertThat(noFiltering, is(testCollection));
    }

    @Test
    public void testFirst() throws Exception {
        Integer firstLargerThan5 = CollectionHelper.first(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return object >= 5;
            }
        });

        assertThat(firstLargerThan5, is(7));

        try {
            CollectionHelper.first(testCollection, new Predicate<Integer>() {
                @Override
                public boolean apply(Integer object) {
                    return object >= 50;
                }
            });

            Assert.fail("first should throw exception");
        } catch (InvalidOperationException e) {
            assertThat(e.getMessage(), is("No items match!"));
        }
    }

    @Test
    public void testFirstIndexOfEmpty() throws Exception {
        int indexOf = CollectionHelper.firstIndexOf(Collections.emptyList(), new Predicate<Object>() {
            @Override public boolean apply(Object object) {
                return true;
            }
        });

        assertEquals(CollectionHelper.NOT_FOUND_INDEX, indexOf);
    }

    @Test
    public void testFirstIndexOfNone() throws Exception {
        int indexOf = CollectionHelper.firstIndexOf(Arrays.asList(1, 5, 3, 7, 9, 5), new Predicate<Integer>() {
            @Override public boolean apply(Integer object) {
                return object == 6;
            }
        });

        assertEquals(CollectionHelper.NOT_FOUND_INDEX, indexOf);
    }

    @Test
    public void testFirstIndexOf() throws Exception {
        int indexOf = CollectionHelper.firstIndexOf(Arrays.asList(1, 5, 3, 7, 9, 5), new Predicate<Integer>() {
            @Override public boolean apply(Integer object) {
                return object == 5;
            }
        });

        assertEquals(1, indexOf);
    }

    @Test
    public void testSingleIndexOfEmpty() throws Exception {
        int indexOf = CollectionHelper.singleIndexOf(Collections.emptyList(), new Predicate<Object>() {
            @Override public boolean apply(Object object) {
                return true;
            }
        });

        assertEquals(CollectionHelper.NOT_FOUND_INDEX, indexOf);
    }

    @Test
    public void testSingleIndexOfNone() throws Exception {
        int indexOf = CollectionHelper.singleIndexOf(Arrays.asList(1, 5, 3, 7, 9, 5), new Predicate<Integer>() {
            @Override public boolean apply(Integer object) {
                return object == 6;
            }
        });

        assertEquals(CollectionHelper.NOT_FOUND_INDEX, indexOf);
    }

    @Test
    public void testSingleIndexOf() throws Exception {
        int indexOf = CollectionHelper.singleIndexOf(Arrays.asList(1, 5, 3, 7, 9, 5), new Predicate<Integer>() {
            @Override public boolean apply(Integer object) {
                return object == 3;
            }
        });

        assertEquals(2, indexOf);
    }

    @Test(expected = InvalidOperationException.class)
    public void testSingleIndexOfMultiple() throws Exception {
        CollectionHelper.singleIndexOf(Arrays.asList(1, 5, 3, 7, 9, 5), new Predicate<Integer>() {
            @Override public boolean apply(Integer object) {
                return object == 5;
            }
        });
    }

    @Test
    public void testFirstOrNull() throws Exception {
        Integer firstLargerThan5 = CollectionHelper.first(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return object >= 5;
            }
        });

        assertThat(firstLargerThan5, is(7));

        Integer firstLargerThan50 = CollectionHelper.firstOrNull(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return object >= 50;
            }
        });

        assertNull(firstLargerThan50);
    }

    @Test
    public void testAny() throws Exception {
        boolean anyLargerThan2 = CollectionHelper.any(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return object > 2;
            }
        });

        assertTrue(anyLargerThan2);

        boolean anyLargerThan20 = CollectionHelper.any(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return object > 20;
            }
        });

        assertFalse(anyLargerThan20);
    }

    @Test
    public void testAll() throws Exception {
        boolean allPositive = CollectionHelper.all(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return object >= 0;
            }
        });

        assertTrue(allPositive);

        boolean allLargerThan5 = CollectionHelper.all(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return object > 5;
            }
        });

        assertFalse(allLargerThan5);
    }

    @Test
    public void testIsEmpty() throws Exception {
        boolean isTestCollectionEmpty = CollectionHelper.isEmpty(testCollection);
        assertFalse(isTestCollectionEmpty);

        boolean isNullEmpty = CollectionHelper.isEmpty(null);
        //noinspection ConstantConditions
        assertTrue(isNullEmpty);

        boolean isNewArrayListEmpty = CollectionHelper.isEmpty(new ArrayList());
        assertTrue(isNewArrayListEmpty);
    }

    @Test
    public void testSingle() throws Exception {
        Integer singleEquals8 = CollectionHelper.single(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return object == 8;
            }
        });

        assertThat(singleEquals8, is(8));

        try {
            CollectionHelper.single(testCollection, new Predicate<Integer>() {
                @Override
                public boolean apply(Integer object) {
                    return object >= 50;
                }
            });

            Assert.fail("single should throw exception");
        } catch (InvalidOperationException e) {
            assertThat(e.getMessage(), is("No items match!"));
        }

        try {
            CollectionHelper.single(testCollection, new Predicate<Integer>() {
                @Override
                public boolean apply(Integer object) {
                    return object >= 5;
                }
            });

            Assert.fail("single should throw exception");
        } catch (InvalidOperationException e) {
            assertThat(e.getMessage(), is("Multiple items match!"));
        }
    }

    @Test
    public void testSingleOrNull() throws Exception {
        Integer singleEquals8 = CollectionHelper.singleOrNull(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return object == 8;
            }
        });

        assertThat(singleEquals8, is(8));

        Integer singleLargerThan50 = CollectionHelper.singleOrNull(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return object >= 50;
            }
        });

        assertNull(singleLargerThan50);

        try {
            CollectionHelper.singleOrNull(testCollection, new Predicate<Integer>() {
                @Override
                public boolean apply(Integer object) {
                    return object >= 5;
                }
            });

            Assert.fail("single should throw exception");
        } catch (InvalidOperationException e) {
            assertThat(e.getMessage(), is("Multiple items match!"));
        }
    }

    @Test
    public void testCount() throws Exception {
        int countLargerThan2 = CollectionHelper.count(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return object > 2;
            }
        });

        assertThat(countLargerThan2, is(4));

        int countLargerThan50 = CollectionHelper.count(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return object > 50;
            }
        });

        assertThat(countLargerThan50, is(0));

        int countPositive = CollectionHelper.count(testCollection, new Predicate<Integer>() {
            @Override
            public boolean apply(Integer object) {
                return object >= 0;
            }
        });

        assertThat(countPositive, is(testCollection.size()));
    }

    @Test
    public void testMap() throws Exception {
        List<String> mappedList = CollectionHelper.map(testCollection, new Mapper<Integer, String>() {
            @Override
            public String map(Integer object) {
                return String.format(Locale.US, "i%d", object);
            }
        });

        assertThat(mappedList, is(Arrays.asList("i1", "i4", "i2", "i7", "i8", "i0", "i5")));
    }
}
