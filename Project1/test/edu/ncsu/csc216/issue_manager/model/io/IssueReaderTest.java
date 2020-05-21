/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.io;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * Tests the Issue Reader Class
 * 
 * @author Sanjana Cheerla
 *
 */
public class IssueReaderTest {

	/**
	 * Testing if test is correctly set up.
	 */
	@Before
	public void setUp() {
		// placeholder
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.io.IssueReader#IssueReader()}.
	 */
	@Test
	public void testIssueReader() {
		var c = new IssueReader();
		assertNotNull(c);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.io.IssueReader#readIssuesFromFile(java.lang.String)}.
	 */
	@Test
	public void testReadIssuesFromFile() {
		ArrayList<Issue> issues = new ArrayList<Issue>();
		ArrayList<String> notes = new ArrayList<String>();
		ArrayList<String> notes1 = new ArrayList<String>();

		notes.add("note 1\ntest");
		notes1.add("note 1");

		issues.add(new Issue(7, "Working", "Bug", "Issue description", "owner", true, "", notes1));
		issues.add(new Issue(1, "Verifying", "Bug", "Issue description", "owner2", true, "Fixed", notes));
		issues.add(new Issue(0, "New", "Bug", "Issue description", null, false, "", notes));
		issues.add(new Issue(5, "Confirmed", "Bug", "Issue description", null, true, "", notes));
		issues.add(new Issue(4, "Closed", "Bug", "Issue description", null, false, "WontFix", notes));
		issues.add(new Issue(2, "New", "Enhancement", "Issue description", null, false, "", notes));

		try {
			ArrayList<Issue> actualIssues = IssueReader.readIssuesFromFile("test-files/readerTest.txt");
			assertTrue(issues.get(0).toString().equals(actualIssues.get(0).toString()));
			assertTrue(issues.get(1).toString().equals(actualIssues.get(1).toString()));
			assertTrue(issues.get(2).toString().equals(actualIssues.get(2).toString()));
			assertTrue(issues.get(3).toString().equals(actualIssues.get(3).toString()));
			assertTrue(issues.get(4).toString().equals(actualIssues.get(4).toString()));
			assertTrue(issues.get(5).toString().equals(actualIssues.get(5).toString()));

		} catch (IllegalArgumentException e) {
			fail("Unable to read from file.");
		}
	}

	/**
	 * Test method to test valid file issueReader TS case
	 */
	@Test
	public void testTSValid() {

		ArrayList<Issue> issues = new ArrayList<Issue>();
		ArrayList<String> notes1 = new ArrayList<String>();
		ArrayList<String> notes2 = new ArrayList<String>();
		ArrayList<String> notes3 = new ArrayList<String>();
		ArrayList<String> notes4 = new ArrayList<String>();
		ArrayList<String> notes5 = new ArrayList<String>();

		notes1.add("Note 1");
		issues.add(new Issue(1, "New", "Enhancement", "Issue description", "", false, "", notes1));

		notes2.add("Note 1");
		notes2.add("Note 2\nthat goes on a new line");
		issues.add(new Issue(2, "Confirmed", "Bug", "Issue description", "", true, "", notes2));

		notes3.add("Note 1");
		notes3.add("Note 2");
		notes3.add("Note 3");
		issues.add(new Issue(3, "Working", "Bug", "Issue description", "owner", true, "", notes3));

		notes4.add("Note 1");
		notes4.add("Note 2\nthat goes on a new line");
		notes4.add("Note 3");
		notes4.add("Note 4");
		issues.add(new Issue(4, "Verifying", "Enhancement", "Issue description", "owner", false, "Fixed", notes4));

		notes5.add("Note 1\nthat goes on a new line");
		notes5.add("Note 2");
		notes5.add("Note 3");
		notes5.add("Note 4");
		notes5.add("Note 5");
		notes5.add("Note 6");
		issues.add(new Issue(6, "Closed", "Enhancement", "Issue description", "owner", false, "WontFix", notes5));

		try {
			ArrayList<Issue> actualIssues = IssueReader.readIssuesFromFile("test-files/TS_file.txt");

			assertTrue(issues.get(0).toString().equals(actualIssues.get(0).toString()));
			assertTrue(issues.get(1).toString().equals(actualIssues.get(1).toString()));
			assertTrue(issues.get(2).toString().equals(actualIssues.get(2).toString()));
			assertTrue(issues.get(3).toString().equals(actualIssues.get(3).toString()));
			assertTrue(issues.get(4).toString().equals(actualIssues.get(4).toString()));
		} catch (IllegalArgumentException e) {
			fail("Unable to read from file.");
		}
	}
}
