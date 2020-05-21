/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.command;

import static org.junit.Assert.*;

import org.junit.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;

/**
 * Tests the command class
 * 
 * @author Sanjana Cheerla
 *
 */
public class CommandTest {

	/** owner final string */
	private final String owner = "Scheerl";

	/** note final string */
	private final String note = "note";

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.command.Command#Command(edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue, java.lang.String, edu.ncsu.csc216.issue_manager.model.command.Command.Resolution, java.lang.String)}.
	 */
	@Test
	public void testCommand() {
		Command c = null;
		try {
			c = new Command(null, owner, Resolution.DUPLICATE, note);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		c = null;
		try {
			c = new Command(CommandValue.ASSIGN, null, Resolution.DUPLICATE, note);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		c = null;
		try {
			c = new Command(CommandValue.RESOLVE, null, null, note);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		c = null;
		try {
			c = new Command(CommandValue.ASSIGN, owner, Resolution.DUPLICATE, null);
			fail();
		} catch (IllegalArgumentException e) {
			assertNull(c);
		}

		c = new Command(CommandValue.ASSIGN, owner, Resolution.DUPLICATE, this.note);
		assertTrue(c.getCommand().equals(CommandValue.ASSIGN));
		assertEquals(owner, c.getOwnerId());
		assertTrue(c.getResolution().equals(Resolution.DUPLICATE));
		assertEquals(note, c.getNote());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.command.Command#getCommand()}.
	 */
	@Test
	public void testGetCommand() {
		Command c = new Command(CommandValue.ASSIGN, owner, Resolution.DUPLICATE, this.note);
		assertTrue(c.getCommand().equals(CommandValue.ASSIGN));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.command.Command#getOwnerId()}.
	 */
	@Test
	public void testGetOwnerId() {
		Command c = new Command(CommandValue.ASSIGN, owner, Resolution.DUPLICATE, this.note);
		assertEquals(owner, c.getOwnerId());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.command.Command#getResolution()}.
	 */
	@Test
	public void testGetResolution() {
		Command c = new Command(CommandValue.ASSIGN, owner, Resolution.DUPLICATE, this.note);
		assertTrue(c.getResolution().equals(Resolution.DUPLICATE));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.command.Command#getNote()}.
	 */
	@Test
	public void testGetNote() {
		Command c = new Command(CommandValue.ASSIGN, owner, Resolution.DUPLICATE, this.note);
		assertEquals(note, c.getNote());
	}

}
