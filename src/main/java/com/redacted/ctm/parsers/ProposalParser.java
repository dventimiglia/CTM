package com.redacted.ctm.parsers;

import com.redacted.ctm.model.*;
import java.util.regex.*;

/**
 * An implementation of a ProposalParser (techically, the only one)
 * that can translate textual representations of a proposal into a
 * {@link Proposal} object.
 *
 * TODO:  really should extract an interface out of this
 */
public class ProposalParser {
    /**
     * Domain-specific wrapper exception
     *
     * TODO:  consider removing
     */
    public class ParserException extends Exception {
	public ParserException (Throwable t) {
	    super(t);}}
 
    // consider making this some kind of parameter (perhaps via constructor)
    Pattern p = Pattern.compile("([^0-9]*)((\\d+)min|lightning)");

    /**
     * Parse a String into a Proposal
     *
     * @param input: String proposal (command), e.g., "Ruby for Fun
     * and Profit lightning"
     *
     */
    public Proposal parse (String input) throws ParserException {
	try {
	    for (Matcher m = p.matcher(input); m.find();)
		return new Proposal(m.group(1).trim(),
				    m.group(2).trim().equalsIgnoreCase("lightning") ?
				    5L : Long.parseLong(m.group(3)));
	    return null;}
	catch (Exception e) {
	    throw new ParserException(e);}}}
