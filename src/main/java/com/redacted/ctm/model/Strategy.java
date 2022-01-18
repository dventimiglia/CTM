package com.redacted.ctm.model;

/**
 * The Strategy type is one with a schedule method, which means that
 * can promote {@link Proposal} objects into "talks" ({@link TimeBlock}) and schedule them into 
 * {@link Session} objects which are themselves within {@link Track}} objects, within the given
 * {@link Schedule}.  
 */
public interface Strategy {
    void schedule (Schedule schedule, Proposal proposal);}

