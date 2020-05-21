/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.io;

import static org.junit.Assert.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * Tests the IssueWriter class
 * 
 * @author Sanjana Cheerla
 *
 */
public class IssueWriterTest {

	/**
	 * Helper method to compare two files for the same contents
	 *
	 * @param expFile expected output
	 * @param actFile actual output
	 */
	private void checkFiles(String expFile, String actFile) {
		try {
			Scanner expScanner = new Scanner(new FileInputStream(expFile));
			Scanner actScanner = new Scanner(new FileInputStream(actFile));

			while (expScanner.hasNextLine() && actScanner.hasNextLine()) {
				String exp = expScanner.nextLine();
				String act = actScanner.nextLine();
				assertEquals("Expected: " + exp + " Actual: " + act, exp, act);
			}
			if (expScanner.hasNextLine()) {
				fail("The expected results expect another line " + expScanner.nextLine());
			}
			if (actScanner.hasNextLine()) {
				fail("The actual results has an extra, unexpected line: " + actScanner.nextLine());
			}

			expScanner.close();
			actScanner.close();
		} catch (IOException e) {
			fail("Error reading files.");
		}
	}

	/**
	 * Tests the constructor
	 */
	@Test
	public void testIssueReader() {
		var c = new IssueWriter();
		assertNotNull(c);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.io.IssueWriter#writeIssuesToFile(java.lang.String, java.util.List)}.
	 */
	@Test
	public void testWriteIssuesToFile() {
		ArrayList<Issue> issues = new ArrayList<Issue>();
		ArrayList<String> notes = new ArrayList<String>();

		notes.add("note 1");

		issues.add(new Issue(7, "Working", "Bug", "Issue description", "owner", true, "", notes));
		issues.add(new Issue(1, "Verifying", "Bug", "Issue description", "owner2", true, "Fixed", notes));
		issues.add(new Issue(0, "New", "Bug", "Issue description", null, false, "", notes));
		issues.add(new Issue(5, "Confirmed", "Bug", "Issue description", null, true, "", notes));
		issues.add(new Issue(4, "Closed", "Bug", "Issue description", null, false, "WontFix", notes));
		issues.add(new Issue(2, "New", "Enhancement", "Issue description", null, false, "", notes));

		try {
			IssueWriter.writeIssuesToFile("test-files/actual_issue_records.txt", issues);
		} catch (IllegalArgumentException e) {
			fail("Cannot write issue records to file");
		}

		checkFiles("test-files/expected_issue_records.txt", "test-files/actual_issue_records.txt");
	}

}
