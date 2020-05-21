/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.issue;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Tests the Issue Class
 * 
 * @author Sanjana Cheerla
 *
 */
public class IssueTest {

	/** owner final string */
	private final String owner = "Scheerl";

	/** note final string */
	private final String note = "note";

	/** summary final string */
	private final String summary = "summary";

	/** Array list notes */
	ArrayList<String> n = new ArrayList<String>();

	/**
	 * resets counter before tests
	 * 
	 * @throws Exception if not set up correctly
	 */
	@Before
	public void setUp() throws Exception {
		// Reset the Issue counter at the beginning of every test.
		Issue.setCounter(1);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#Issue(edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testIssueIssueTypeStringString() {
		Issue i = null;
		try {
			i = new Issue(null, summary, note);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(IssueType.BUG, null, note);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(IssueType.BUG, "", note);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(IssueType.BUG, summary, null);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(IssueType.BUG, summary, "");
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = new Issue(IssueType.BUG, summary, note);
		assertEquals(1, i.getIssueId());
		assertEquals("New", i.getState());
		assertEquals("Bug", i.getIssueType());
		assertEquals(summary, i.getSummary());
		assertNull(i.getOwner());
		assertFalse(i.isConfirmed());
		assertNull(i.getResolution());
		assertEquals(note, i.getNotes().get(0));
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#Issue(int, java.lang.String, java.lang.String, java.lang.String, java.lang.String, boolean, java.lang.String, java.util.ArrayList)}.
	 */
	@Test
	public void testIssueIntStringStringStringStringBooleanStringArrayListOfString() {
		n.add(note);

		Issue i = null;
		try {
			i = new Issue(-1, "New", "Bug", summary, owner, false, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "Neww", "Bug", summary, owner, false, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "New", "Bugg", summary, owner, false, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "New", "Bug", null, owner, false, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "New", "Bug", "", owner, false, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "Working", "Bug", summary, null, false, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "Verifying", "Bug", summary, null, false, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "Working", "Bug", summary, "", false, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "Verifying", "Bug", summary, "", false, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "New", "Bug", summary, owner, false, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "Working", "Bug", summary, owner, false, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "Confirmed", "Bug", summary, owner, false, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "Confirmed", "Enhancement", summary, owner, false, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "Working", "Enhancement", summary, "", true, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "Working", "Bug", summary, owner, false, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "Verifying", "Enhancement", summary, owner, false, null, n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		i = null;
		try {
			i = new Issue(1, "Confirmed", "Enhancement", summary, owner, false, null, n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		n.remove(0);

		i = null;
		try {
			i = new Issue(1, "New", "Enhancement", summary, owner, false, "WorksForMe", n);
		} catch (IllegalArgumentException e) {
			assertNull(i);
		}

		n.add(note);
		i = new Issue(1, "New", "Enhancement", summary, owner, false, null, n);

		assertEquals(1, i.getIssueId());
		assertEquals("New", i.getState());
		assertEquals("Enhancement", i.getIssueType());
		assertEquals(summary, i.getSummary());
		assertEquals(owner, i.getOwner());
		assertFalse(i.isConfirmed());
		assertNull(i.getResolution());
		assertEquals(n, i.getNotes());

		n.add(note);
		i = new Issue(1, "Verifying", "Enhancement", summary, owner, false, "Fixed", n);

		assertEquals(1, i.getIssueId());
		assertEquals("Verifying", i.getState());
		assertEquals("Enhancement", i.getIssueType());
		assertEquals(summary, i.getSummary());
		assertEquals(owner, i.getOwner());
		assertFalse(i.isConfirmed());
		assertEquals("Fixed", i.getResolution());
		assertEquals(n, i.getNotes());

		n.add(note);
		i = new Issue(1, "Closed", "Enhancement", summary, owner, false, "Duplicate", n);

		assertEquals(1, i.getIssueId());
		assertEquals("Closed", i.getState());
		assertEquals("Enhancement", i.getIssueType());
		assertEquals(summary, i.getSummary());
		assertEquals(owner, i.getOwner());
		assertFalse(i.isConfirmed());
		assertEquals("Duplicate", i.getResolution());
		assertEquals(n, i.getNotes());

		n.add(note);
		i = new Issue(1, "Closed", "Enhancement", summary, owner, false, "WontFix", n);

		assertEquals(1, i.getIssueId());
		assertEquals("Closed", i.getState());
		assertEquals("Enhancement", i.getIssueType());
		assertEquals(summary, i.getSummary());
		assertEquals(owner, i.getOwner());
		assertFalse(i.isConfirmed());
		assertEquals("WontFix", i.getResolution());
		assertEquals(n, i.getNotes());

		n.add(note);
		i = new Issue(1, "Closed", "Bug", summary, owner, false, "WorksForMe", n);

		assertEquals(1, i.getIssueId());
		assertEquals("Closed", i.getState());
		assertEquals("Bug", i.getIssueType());
		assertEquals(summary, i.getSummary());
		assertEquals(owner, i.getOwner());
		assertFalse(i.isConfirmed());
		assertEquals("WorksForMe", i.getResolution());
		assertEquals(n, i.getNotes());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getIssueId()}.
	 */
	@Test
	public void testGetIssueId() {
		n.add(note);
		Issue i = new Issue(1, "New", "Bug", summary, owner, false, null, n);

		assertEquals(1, i.getIssueId());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getSummary()}.
	 */
	@Test
	public void testGetSummary() {
		n.add(note);
		Issue i = new Issue(1, "New", "Enhancement", summary, owner, false, null, n);

		assertEquals(summary, i.getSummary());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getOwner()}.
	 */
	@Test
	public void testGetOwner() {
		n.add(note);
		Issue i = new Issue(1, "New", "Enhancement", summary, owner, false, null, n);

		assertEquals(owner, i.getOwner());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#isConfirmed()}.
	 */
	@Test
	public void testIsConfirmed() {
		n.add(note);
		Issue i = new Issue(1, "New", "Enhancement", summary, owner, false, null, n);

		assertFalse(i.isConfirmed());

		n.add(note);
		Issue i2 = new Issue(1, "New", "Bug", summary, owner, true, null, n);

		assertTrue(i2.isConfirmed());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getNotes()}.
	 */
	@Test
	public void testGetNotes() {
		n.add(note);
		Issue i = new Issue(1, "New", "Bug", summary, owner, false, null, n);

		assertEquals(n, i.getNotes());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getState()}.
	 */
	@Test
	public void testGetState() {
		n.add(note);
		Issue i = new Issue(1, "New", "Enhancement", summary, owner, false, null, n);

		assertEquals("New", i.getState());

		Issue i2 = new Issue(1, "Working", "Enhancement", summary, owner, false, null, n);

		assertEquals("Working", i2.getState());

		Issue i3 = new Issue(1, "Confirmed", "Bug", summary, owner, true, null, n);

		assertEquals("Confirmed", i3.getState());

		Issue i4 = new Issue(1, "Verifying", "Enhancement", summary, owner, false, "Fixed", n);

		assertEquals("Verifying", i4.getState());

		Issue i5 = new Issue(1, "Closed", "Enhancement", summary, owner, false, "Duplicate", n);

		assertEquals("Closed", i5.getState());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getIssueType()}.
	 */
	@Test
	public void testGetIssueType() {
		n.add(note);
		Issue i = new Issue(1, "New", "Enhancement", summary, owner, false, null, n);

		assertEquals("Enhancement", i.getIssueType());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getResolution()}.
	 */
	@Test
	public void testGetResolution() {
		n.add(note);
		Issue i = new Issue(1, "New", "Enhancement", summary, owner, false, null, n);

		assertNull(i.getResolution());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#getNotesString()}.
	 */
	@Test
	public void testGetNotesString() {
		n.add(note);
		Issue i = new Issue(1, "New", "Enhancement", summary, owner, false, null, n);

		assertEquals("-note\n", i.getNotesString());

		n.add(note);

		assertEquals("-note\n-note\n", i.getNotesString());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#toString()}.
	 */
	@Test
	public void testToString() {
		n.add(note);
		Issue i = new Issue(1, "Verifying", "Enhancement", summary, owner, false, "Fixed", n);

		assertEquals("*1+Verifying+Enhancement+summary+Scheerl+false+Fixed\n-note\n", i.toString());

		i = null;
		i = new Issue(IssueType.BUG, summary, note);

		assertEquals("*1+New+Bug+summary+null+false+\n-note\n", i.toString());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#update(edu.ncsu.csc216.issue_manager.model.command.Command)}.
	 */
	@Test
	public void testUpdateNewState() {
		// new state
		Issue i1 = new Issue(IssueType.ENHANCEMENT, "Issue description", "note 1");
		assertEquals("*1+New+Enhancement+Issue description+null+false+\n-note 1\n", i1.toString());

		Command c = new Command(CommandValue.ASSIGN, owner, null, "note 2");
		i1.update(c);
		assertEquals("*1+Working+Enhancement+Issue description+Scheerl+false+\n-note 1\n-note 2\n", i1.toString());

		i1 = null;
		c = null;

		i1 = new Issue(IssueType.BUG, "Issue description", "note 1");
		assertEquals("*2+New+Bug+Issue description+null+false+\n-note 1\n", i1.toString());

		c = new Command(CommandValue.CONFIRM, owner, null, "note 2");
		i1.update(c);
		assertEquals("*2+Confirmed+Bug+Issue description+Scheerl+true+\n-note 1\n-note 2\n", i1.toString());

		i1 = null;
		c = null;

		i1 = new Issue(IssueType.BUG, "Issue description", "note 1");
		assertEquals("*3+New+Bug+Issue description+null+false+\n-note 1\n", i1.toString());

		c = new Command(CommandValue.RESOLVE, owner, Resolution.DUPLICATE, "note 2");
		i1.update(c);
		assertEquals("*3+Closed+Bug+Issue description+Scheerl+false+Duplicate\n-note 1\n-note 2\n", i1.toString());

		i1 = null;
		c = null;

		i1 = new Issue(IssueType.BUG, "Issue description", "note 1");
		assertEquals("*4+New+Bug+Issue description+null+false+\n-note 1\n", i1.toString());

		c = new Command(CommandValue.RESOLVE, owner, Resolution.WONTFIX, "note 2");
		i1.update(c);
		assertEquals("*4+Closed+Bug+Issue description+Scheerl+false+WontFix\n-note 1\n-note 2\n", i1.toString());

		i1 = null;
		c = null;

		i1 = new Issue(IssueType.BUG, "Issue description", "note 1");
		assertEquals("*5+New+Bug+Issue description+null+false+\n-note 1\n", i1.toString());

		c = new Command(CommandValue.RESOLVE, owner, Resolution.WORKSFORME, "note 2");
		i1.update(c);
		assertEquals("*5+Closed+Bug+Issue description+Scheerl+false+WorksForMe\n-note 1\n-note 2\n", i1.toString());

		i1 = null;
		c = null;

		i1 = new Issue(IssueType.ENHANCEMENT, "Issue description", "note 1");
		assertEquals("*6+New+Enhancement+Issue description+null+false+\n-note 1\n", i1.toString());

		c = new Command(CommandValue.RESOLVE, owner, Resolution.DUPLICATE, "note 2");
		i1.update(c);
		assertEquals("*6+Closed+Enhancement+Issue description+Scheerl+false+Duplicate\n-note 1\n-note 2\n",
				i1.toString());

		i1 = null;
		c = null;

		i1 = new Issue(IssueType.ENHANCEMENT, "Issue description", "note 1");
		assertEquals("*7+New+Enhancement+Issue description+null+false+\n-note 1\n", i1.toString());

		c = new Command(CommandValue.RESOLVE, owner, Resolution.WONTFIX, "note 2");
		i1.update(c);
		assertEquals("*7+Closed+Enhancement+Issue description+Scheerl+false+WontFix\n-note 1\n-note 2\n",
				i1.toString());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#update(edu.ncsu.csc216.issue_manager.model.command.Command)}.
	 */
	@Test
	public void testUpdateWorkingState() {
		// working state
		n.add("note 1");
		Issue i1;
		Command c;
		i1 = new Issue(1, "Working", "Enhancement", "Issue description", owner, false, null, n);
		assertEquals("*1+Working+Enhancement+Issue description+Scheerl+false+\n-note 1\n", i1.toString());

		c = new Command(CommandValue.RESOLVE, owner, Resolution.FIXED, "note 2");
		i1.update(c);
		assertEquals("*1+Verifying+Enhancement+Issue description+Scheerl+false+Fixed\n-note 1\n-note 2\n",
				i1.toString());

		i1 = null;
		c = null;
		n.remove("note 2");

		i1 = new Issue(2, "Working", "Enhancement", "Issue description", owner, false, null, n);
		assertEquals("*2+Working+Enhancement+Issue description+Scheerl+false+\n-note 1\n", i1.toString());

		c = new Command(CommandValue.RESOLVE, owner, Resolution.DUPLICATE, "note 2");
		i1.update(c);
		assertEquals("*2+Closed+Enhancement+Issue description+Scheerl+false+Duplicate\n-note 1\n-note 2\n",
				i1.toString());

		i1 = null;
		c = null;
		n.remove("note 2");

		i1 = new Issue(3, "Working", "Enhancement", "Issue description", owner, false, null, n);
		assertEquals("*3+Working+Enhancement+Issue description+Scheerl+false+\n-note 1\n", i1.toString());

		c = new Command(CommandValue.RESOLVE, owner, Resolution.WONTFIX, "note 2");
		i1.update(c);
		assertEquals("*3+Closed+Enhancement+Issue description+Scheerl+false+WontFix\n-note 1\n-note 2\n",
				i1.toString());

		i1 = null;
		c = null;
		n.remove("note 2");

		i1 = new Issue(4, "Working", "Enhancement", "Issue description", owner, false, null, n);
		assertEquals("*4+Working+Enhancement+Issue description+Scheerl+false+\n-note 1\n", i1.toString());

		c = new Command(CommandValue.RESOLVE, owner, Resolution.WONTFIX, "note 2");
		i1.update(c);
		assertEquals("*4+Closed+Enhancement+Issue description+Scheerl+false+WontFix\n-note 1\n-note 2\n",
				i1.toString());

		i1 = null;
		c = null;
		n.remove("note 2");

		i1 = new Issue(5, "Working", "Bug", "Issue description", owner, true, null, n);
		assertEquals("*5+Working+Bug+Issue description+Scheerl+true+\n-note 1\n", i1.toString());

		c = new Command(CommandValue.RESOLVE, owner, Resolution.WONTFIX, "note 2");
		i1.update(c);
		assertEquals("*5+Closed+Bug+Issue description+Scheerl+true+WontFix\n-note 1\n-note 2\n", i1.toString());

		i1 = null;
		c = null;
		n.remove("note 2");

		i1 = new Issue(6, "Working", "Bug", "Issue description", owner, true, null, n);
		assertEquals("*6+Working+Bug+Issue description+Scheerl+true+\n-note 1\n", i1.toString());

		c = new Command(CommandValue.RESOLVE, owner, Resolution.DUPLICATE, "note 2");
		i1.update(c);
		assertEquals("*6+Closed+Bug+Issue description+Scheerl+true+Duplicate\n-note 1\n-note 2\n", i1.toString());

		i1 = null;
		c = null;
		n.remove("note 2");

		i1 = new Issue(5, "Working", "Bug", "Issue description", owner, true, null, n);
		assertEquals("*5+Working+Bug+Issue description+Scheerl+true+\n-note 1\n", i1.toString());

		c = new Command(CommandValue.RESOLVE, owner, Resolution.WORKSFORME, "note 2");
		i1.update(c);
		assertEquals("*5+Closed+Bug+Issue description+Scheerl+true+WorksForMe\n-note 1\n-note 2\n", i1.toString());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#update(edu.ncsu.csc216.issue_manager.model.command.Command)}.
	 */
	@Test
	public void testUpdateConfirmedState() {
		// confirmed state
		n.add("note 1");
		Issue i1 = new Issue(1, "Confirmed", "Bug", "Issue description", owner, true, null, n);
		assertEquals("*1+Confirmed+Bug+Issue description+Scheerl+true+\n-note 1\n", i1.toString());

		Command c = new Command(CommandValue.ASSIGN, owner, null, "note 2");
		i1.update(c);
		assertEquals("*1+Working+Bug+Issue description+Scheerl+true+\n-note 1\n-note 2\n", i1.toString());

		i1 = null;
		c = null;
		n.remove("note 2");

		i1 = new Issue(2, "Confirmed", "Bug", "Issue description", owner, true, null, n);
		assertEquals("*2+Confirmed+Bug+Issue description+Scheerl+true+\n-note 1\n", i1.toString());

		c = new Command(CommandValue.RESOLVE, owner, Resolution.WONTFIX, "note 2");
		i1.update(c);
		assertEquals("*2+Closed+Bug+Issue description+Scheerl+true+WontFix\n-note 1\n-note 2\n", i1.toString());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#update(edu.ncsu.csc216.issue_manager.model.command.Command)}.
	 */
	@Test
	public void testUpdateVerifyingState() {
		// verifying state
		n.add("note 1");
		Issue i1 = new Issue(1, "Verifying", "Bug", "Issue description", owner, true, "Fixed", n);
		assertEquals("*1+Verifying+Bug+Issue description+Scheerl+true+Fixed\n-note 1\n", i1.toString());

		Command c = new Command(CommandValue.REOPEN, owner, null, "note 2");
		i1.update(c);
		assertEquals("*1+Working+Bug+Issue description+Scheerl+true+\n-note 1\n-note 2\n", i1.toString());

		i1 = null;
		c = null;
		n.remove("note 2");

		i1 = new Issue(2, "Verifying", "Bug", "Issue description", owner, true, "Fixed", n);
		assertEquals("*2+Verifying+Bug+Issue description+Scheerl+true+Fixed\n-note 1\n", i1.toString());

		c = new Command(CommandValue.VERIFY, owner, null, "note 2");
		i1.update(c);
		assertEquals("*2+Closed+Bug+Issue description+Scheerl+true+Fixed\n-note 1\n-note 2\n", i1.toString());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.issue.Issue#update(edu.ncsu.csc216.issue_manager.model.command.Command)}.
	 */
	@Test
	public void testUpdateClosedState() {
		n.add("note 1");
		Issue i1 = new Issue(1, "Closed", "Bug", "Issue description", owner, true, "Fixed", n);
		assertEquals("*1+Closed+Bug+Issue description+Scheerl+true+Fixed\n-note 1\n", i1.toString());

		Command c = new Command(CommandValue.REOPEN, owner, null, "note 2");
		i1.update(c);
		assertEquals("*1+Working+Bug+Issue description+Scheerl+true+\n-note 1\n-note 2\n", i1.toString());

		i1 = null;
		c = null;
		n.remove("note 2");

		i1 = new Issue(2, "Closed", "Enhancement", "Issue description", owner, false, "Fixed", n);
		assertEquals("*2+Closed+Enhancement+Issue description+Scheerl+false+Fixed\n-note 1\n", i1.toString());

		c = new Command(CommandValue.REOPEN, owner, null, "note 2");
		i1.update(c);
		assertEquals("*2+Working+Enhancement+Issue description+Scheerl+false+\n-note 1\n-note 2\n", i1.toString());

		i1 = null;
		c = null;
		n.remove("note 2");

		i1 = new Issue(3, "Closed", "Enhancement", "Issue description", null, false, "Fixed", n);
		assertEquals("*3+Closed+Enhancement+Issue description+null+false+Fixed\n-note 1\n", i1.toString());

		c = new Command(CommandValue.REOPEN, owner, null, "note 2");
		i1.update(c);
		assertEquals("*3+New+Enhancement+Issue description+null+false+\n-note 1\n-note 2\n", i1.toString());

		i1 = null;
		c = null;
		n.remove("note 2");

		i1 = new Issue(3, "Closed", "Bug", "Issue description", null, true, "Fixed", n);
		assertEquals("*3+Closed+Bug+Issue description+null+true+Fixed\n-note 1\n", i1.toString());

		c = new Command(CommandValue.REOPEN, owner, null, "note 2");
		i1.update(c);
		assertEquals("*3+Confirmed+Bug+Issue description+null+true+\n-note 1\n-note 2\n", i1.toString());

	}

}
