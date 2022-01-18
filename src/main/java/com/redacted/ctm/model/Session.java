package com.redacted.ctm.model;

import java.time.*;

/**
 * A Session {@link Bin} contains all of the {@link TimeBlock} objects
 * (themselves {@link Bin} objects) that have been scheduled into it.
 * This is mainly to group talks together (morning, lunch, afternoon,
 * etc.)
 */
public class Session extends Bin<TimeBlock> {
    protected String name;

    /**
     * Constructor
     *
     * @param id:  an identifier
     * @param minLength: the minimum duration (minutes) before the
     * session is considered "filled"
     * @param maxLength: the maximum duration (minutes) of contained
     * talks, after which no more talks can be added
     */
    public Session (String id, long minLength, long maxLength) {
	super(id, LocalTime.MIDNIGHT, minLength, maxLength);}

    /**
     * Constructor
     *
     * @param id:  an identifier
     * @param minLength: the minimum duration (minutes) before the
     * session is considered "filled"
     * @param maxLength: the maximum duration (minutes) of contained
     * talks, after which no more talks can be added
     * @param blocked: boolean indicating if this session can or
     * cannot have talks added to it (TODO: consider removing)
     */
    public Session (String id, long minLength, long maxLength, boolean blocked) {
	super(id, LocalTime.MIDNIGHT, minLength, maxLength, blocked);}

    /**
     * Get the Session "name" which is synonymous with its identifer
     */
    public String getName () {
	return getId();}}

