/**
 * 
 */
package edu.ncsu.csc216.issue_manager.model.manager;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * Tests the IssueManager Class
 * 
 * @author Sanjana Cheerla
 *
 */
public class IssueManagerTest {

	/** instance of issue manager */
	private IssueManager manager;

	/**
	 * setup
	 */
	@Before
	public void setup() {
		manager = IssueManager.getInstance();
		manager.createNewIssueList();
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#getInstance()}.
	 */
	@Test
	public void testGetInstance() {
		assertNotNull(IssueManager.getInstance());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#saveIssuesToFile(java.lang.String)}.
	 */
	@Test
	public void testSaveIssuesToFile() {
		manager = IssueManager.getInstance();
		manager.loadIssuesFromFile("test-files/TS_file.txt");

		Object[][] actualArr = manager.getIssueListAsArray();
		assertEquals(5, actualArr.length);

		manager.saveIssuesToFile("test-files/TS_actual.txt");

	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#loadIssuesFromFile(java.lang.String)}.
	 */
	@Test
	public void testLoadIssuesFromFile() {
		manager = IssueManager.getInstance();
		manager.loadIssuesFromFile("test-files/TS_file.txt");

		Object[][] actualArr = manager.getIssueListAsArray();

		assertEquals(5, actualArr.length);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#createNewIssueList()}.
	 */
	@Test
	public void testCreateNewIssueList() {
		manager = IssueManager.getInstance();
		manager.createNewIssueList();

		Object[][] actualArr = manager.getIssueListAsArray();

		assertEquals(0, actualArr.length);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#getIssueListAsArray()}.
	 */
	@Test
	public void testGetIssueListAsArray() {
		manager = IssueManager.getInstance();
		manager.loadIssuesFromFile("test-files/TS_file.txt");

		Object[][] actualArr = manager.getIssueListAsArray();

		assertEquals(5, actualArr.length);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#getIssueListAsArrayByIssueType(java.lang.String)}.
	 */
	@Test
	public void testGetIssueListAsArrayByIssueType() {
		manager = IssueManager.getInstance();
		manager.loadIssuesFromFile("test-files/TS_file.txt");

		Object[][] actualArr = manager.getIssueListAsArray();

		assertEquals(5, actualArr.length);

		Object[][] actualArrBug = manager.getIssueListAsArrayByIssueType("Bug");
		Object[][] actualArrEnhancement = manager.getIssueListAsArrayByIssueType("Enhancement");

		assertEquals(2, actualArrBug.length);
		assertEquals(3, actualArrEnhancement.length);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#getIssueById(int)}.
	 */
	@Test
	public void testGetIssueById() {
		manager = IssueManager.getInstance();
		manager.loadIssuesFromFile("test-files/TS_file.txt");

		Issue i = manager.getIssueById(1);

		assertEquals("*1+New+Enhancement+Issue description+null+false+\n-Note 1\n", i.toString());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#executeCommand(int, edu.ncsu.csc216.issue_manager.model.command.Command)}.
	 */
	@Test
	public void testExecuteCommand() {
		manager = IssueManager.getInstance();
		manager.loadIssuesFromFile("test-files/TS_file.txt");

		Command c = new Command(Command.CommandValue.ASSIGN, "owner", null, "note");

		manager.executeCommand(1, c);
		Issue i = manager.getIssueById(1);

		assertEquals("*1+Working+Enhancement+Issue description+owner+false+\n-Note 1\n-note\n", i.toString());
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#deleteIssueById(int)}.
	 */
	@Test
	public void testDeleteIssueById() {
		manager = IssueManager.getInstance();
		manager.loadIssuesFromFile("test-files/TS_file.txt");

		Object[][] actualArr = manager.getIssueListAsArray();
		assertEquals(5, actualArr.length);

		manager.deleteIssueById(1);
		Object[][] actualArr1 = manager.getIssueListAsArray();
		assertEquals(4, actualArr1.length);
	}

	/**
	 * Test method for
	 * {@link edu.ncsu.csc216.issue_manager.model.manager.IssueManager#addIssueToList(edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType, java.lang.String, java.lang.String)}.
	 */
	@Test
	public void testAddIssueToList() {
		manager.addIssueToList(Issue.IssueType.BUG, "summary", "note");
		Object[][] actualArr = manager.getIssueListAsArray();
		assertEquals(1, actualArr.length);

	}
}
