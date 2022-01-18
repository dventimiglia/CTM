package com.redacted.ctm.model;

import java.time.*;
import java.util.*;
import junit.framework.*;

/**
 * Most basic tests of the functionality in the Bin class
 */
public class BinTest extends TestCase {
    public BinTest (String name) {
	super(name);}

    /**
     * Trivial extension of a Bin, mainly to alias it to a different
     * class for testing purposes
     */
    public class Item extends Bin {
	public Item (String id,
		     LocalTime start,
		     long fillmarker,
		     long capacity) {
	    super(id, start, fillmarker, capacity);}}

    /**
     * Test that once a Bin has been filled with items passed its
     * fillMarker, isFilled returns true.
     */
    public void testBecomesFilled () {
	int id = 0;
	LocalTime binstart = LocalTime.MIDNIGHT;
	LocalTime itmstart = LocalTime.MIDNIGHT; // doesn't really matter
	long fil = 2*60;	// 2 hours to become (partially) filled
	long cap = 3*60;	// 3 hours to the brim
	long itm = 35;		// 35 mins is item size
	Bin<Item> bin = new Bin<Item>(""+id, binstart, fil, cap);
	assertFalse(bin.isFilled());
	while (!bin.isFilled()) assertTrue(bin.add(new Item(""+id++, itmstart, itm, itm)));
	assertTrue(bin.isFilled());}

    /**
     * Test that the highwater mark is calculated correctly
     */
    public void testHighWater () {
	int id = 0;
	LocalTime binstart = LocalTime.MIDNIGHT;
	LocalTime itmstart = LocalTime.MIDNIGHT; // doesn't really matter
	long fil = 2*60;	// 2 hours to become (partially) filled
	long cap = 3*60;	// 3 hours to the brim
	long itm = 35;		// 35 mins is item size
	Bin<Item> bin = new Bin<Item>(""+id, binstart, fil, cap);
	while (!bin.isFilled()) assertTrue(bin.add(new Item(""+id++, itmstart, itm, itm)));
	assertEquals(bin.getHighWater(), 140);}

    /**
     * Test that the size is calculated correctly (which is different
     * from the highwater mark and the fillmarker).  Test when filled
     * past the fillmarker.
     */
    public void testGetSizeWhenFilled () {
	int id = 0;
	LocalTime binstart = LocalTime.MIDNIGHT;
	LocalTime itmstart = LocalTime.MIDNIGHT; // doesn't really matter
	long fil = 2*60;	// 2 hours to become (partially) filled
	long cap = 3*60;	// 3 hours to the brim
	long itm = 35;		// 35 mins is item size
	Bin<Item> bin = new Bin<Item>(""+id, binstart, fil, cap);
	while (!bin.isFilled()) assertTrue(bin.add(new Item(""+id++, itmstart, itm, itm)));
	assertEquals(bin.getSize(), 140);}

    /**
     * Test the size calculation when below the fillmarker.
     */
    public void testGetSizeWhenUnFilled () {
	int id = 0;
	int stopid = 2;
	LocalTime binstart = LocalTime.MIDNIGHT;
	LocalTime itmstart = LocalTime.MIDNIGHT; // doesn't really matter
	long fil = 2*60;	// 2 hours to become (partially) filled
	long cap = 3*60;	// 3 hours to the brim
	long itm = 35;		// 35 mins is item size
	Bin<Item> bin = new Bin<Item>(""+id, binstart, fil, cap);
	while (!bin.isFilled() && id<stopid) assertTrue(bin.add(new Item(""+id++, itmstart, itm, itm)));
	assertEquals(bin.getSize(), 120);}

    /**
     * Test recalculation of item start time as they're added.  Note
     * that since start times are calculated as they're added, we
     * don't actually need to call the recalculate method (and we
     * don't).
     */
    public void testWithoutRecalculate () {
	int id = 0;
	LocalTime binstart = LocalTime.MIDNIGHT;
	LocalTime itmstart = LocalTime.MIDNIGHT; // doesn't really matter
	long fil = 2*60;	// 2 hours to become (partially) filled
	long cap = 3*60;	// 3 hours to the brim
	long itm = 35;		// 35 mins is item size
	Bin<Item> bin = new Bin<Item>(""+id, binstart, fil, cap);
	while (!bin.isFilled()) assertTrue(bin.add(new Item(""+id++, itmstart, itm, itm)));
	StringBuffer sb = new StringBuffer();
	for (Item i : bin)
	    sb.append(String.format("%s ", i.getStart()));
	assertEquals(sb.toString().trim(), "00:00 00:35 01:10 01:45");}

    /**
     * Test recalculation of the item start time, after we've called
     * the recalculate method.  Note that, since start times are
     * calculated on-the-fly, this should have no effect.  I suppose
     * I'm testing that it "does no harm."
     */
    public void testWithRecalculate () {
	int id = 0;
	LocalTime binstart = LocalTime.MIDNIGHT;
	LocalTime itmstart = LocalTime.MIDNIGHT; // doesn't really matter
	long fil = 2*60;	// 2 hours to become (partially) filled
	long cap = 3*60;	// 3 hours to the brim
	long itm = 35;		// 35 mins is item size
	Bin<Item> bin = new Bin<Item>(""+id, binstart, fil, cap);
	while (!bin.isFilled()) assertTrue(bin.add(new Item(""+id++, itmstart, itm, itm)));
	StringBuffer sb = new StringBuffer();
	for (Item i : bin)
	    sb.append(String.format("%s ", i.getStart()));
	assertEquals(sb.toString().trim(), "00:00 00:35 01:10 01:45");}}
    
