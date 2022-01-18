package com.redacted.ctm.model;

import java.util.*;

/**
 * Utility class that chains together Iterators into one big Iterator.
 * This is useful, for instance, in iterating over all the Talks over
 * all the Sessions in a Track.
 */
public class ChainedIterator<T> implements Iterator<T> {
    protected Iterator<Iterator<T>> sources;
    protected Iterator<T> source;
    
    public ChainedIterator (List<Iterator<T>> sources) {
	this.sources = sources.iterator();}

    @Override
    public boolean hasNext () {
	if (source==null && sources.hasNext())
	    source = sources.next();
	if (sources.hasNext())
	    return true;
	return false;}

    @Override
    public T next () {
	if (hasNext())
	    return source.next();
	throw new NoSuchElementException();}}
