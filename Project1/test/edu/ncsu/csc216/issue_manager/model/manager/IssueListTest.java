/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.manager;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.io.IssueReader;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * Tests the IssueList Class
 * 
 * @author Sanjana Cheerla
 *
 */
public class IssueListTest {

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#IssueList()}.
	 */
	@Test
	public void testIssueList() {
		IssueList l1 = new IssueList();
		assertNotNull(l1);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#addIssue(edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddIssue() {
		Issue.setCounter(0);
		Issue i = new Issue(IssueType.BUG, "summary", "note");
		Issue.setCounter(0);

		IssueList l1 = new IssueList();
		l1.addIssue(IssueType.BUG, "summary", "note");

		assertEquals(i.toString(), l1.getIssues().get(0).toString());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#addIssues(java.util.List)}.
	 */
	@Test
	public void testAddIssues() {
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

		Issue.setCounter(0);

		IssueList l1 = new IssueList();
		l1.addIssues(issues);

		assertEquals(issues, l1.getIssues());

		Issue.setCounter(0);

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#getIssues()}.
	 */
	@Test
	public void testGetIssues() {
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

		Issue.setCounter(0);

		IssueList l1 = new IssueList();
		l1.addIssues(issues);

		assertEquals(issues, l1.getIssues());

		Issue.setCounter(0);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#getIssuesByType(java.lang.String)}.
	 */
	@Test
	public void testGetIssuesByType() {
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

		Issue.setCounter(0);

		IssueList l1 = new IssueList();
		l1.addIssues(issues);

		assertEquals(issues, l1.getIssues());

		Issue.setCounter(0);

		ArrayList<Issue> bugList = l1.getIssuesByType("Bug");
		ArrayList<Issue> bugListExpected = new ArrayList<Issue>();
		bugListExpected.add(new Issue(2, "Confirmed", "Bug", "Issue description", "", true, "", notes2));
		bugListExpected.add(new Issue(3, "Working", "Bug", "Issue description", "owner", true, "", notes3));
		assertEquals(bugListExpected.get(0).toString(), bugList.get(0).toString());
		assertEquals(bugListExpected.get(1).toString(), bugList.get(1).toString());

		ArrayList<Issue> enhancementList = l1.getIssuesByType("Enhancement");
		ArrayList<Issue> enhancementListExpected = new ArrayList<Issue>();

		enhancementListExpected.add(new Issue(1, "New", "Enhancement", "Issue description", "", false, "", notes1));
		enhancementListExpected
				.add(new Issue(4, "Verifying", "Enhancement", "Issue description", "owner", false, "Fixed", notes4));
		enhancementListExpected
				.add(new Issue(6, "Closed", "Enhancement", "Issue description", "owner", false, "WontFix", notes5));
		assertEquals(enhancementListExpected.get(0).toString(), enhancementList.get(0).toString());
		assertEquals(enhancementListExpected.get(1).toString(), enhancementList.get(1).toString());
		assertEquals(enhancementListExpected.get(2).toString(), enhancementList.get(2).toString());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#getIssueById(int)}.
	 */
	@Test
	public void testGetIssueById() {
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

		Issue.setCounter(0);

		IssueList l1 = new IssueList();
		l1.addIssues(issues);

		assertEquals(issues, l1.getIssues());

		Issue.setCounter(0);

		assertEquals(issues.get(0).toString(), l1.getIssueById(1).toString());
		assertEquals(issues.get(1).toString(), l1.getIssueById(2).toString());
		assertEquals(issues.get(2).toString(), l1.getIssueById(3).toString());
		assertEquals(issues.get(3).toString(), l1.getIssueById(4).toString());
		assertEquals(issues.get(4).toString(), l1.getIssueById(6).toString());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#executeCommand(int, edu.ncsu.csc216.issue_manager.model.command.Command)}.
	 */
	@Test
	public void testExecuteCommand() {
		Issue i1 = new Issue(IssueType.ENHANCEMENT, "Issue description", "note 1");

		ArrayList<Issue> issues = new ArrayList<Issue>();
		issues.add(i1);

		Issue.setCounter(0);

		IssueList l1 = new IssueList();
		l1.addIssues(issues);

		Command c = new Command(CommandValue.ASSIGN, "owner", null, "note 2");
		l1.executeCommand(0, c);

		assertEquals(i1.toString(), l1.getIssues().get(0).toString());

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueList#deleteIssueById(int)}.
	 */
	@Test
	public void testDeleteIssueById() {
		ArrayList<Issue> actualIssues = IssueReader.readIssuesFromFile("test-files/issue1.txt");

		IssueList i = new IssueList();
		i.addIssues(actualIssues);

		i.deleteIssueById(1);

		assertNull(i.getIssueById(1));

		i.deleteIssueById(2);

		assertNull(i.getIssueById(2));

		i.deleteIssueById(3);

		assertNull(i.getIssueById(3));

		i.deleteIssueById(4);

		assertNull(i.getIssueById(4));

		i.deleteIssueById(6);

		assertNull(i.getIssueById(6));

	}

}
