package com.redacted.ctm.model;

/**
 * A Proposal represents a proposed Talk that hasn't yet been promoted
 * (i.e., scheduled) into a bona-fide Talk.
 *
 * TODO:  consider refactoring this away, as it may be overkill
 */
public class Proposal {
    protected String title;
    protected long length;
    
    public Proposal (String title, long length) {
	this.title = title;
	this.length = length;}

    public String getTitle () {
	return title;}

    public long getLength () {
	return length;}}
