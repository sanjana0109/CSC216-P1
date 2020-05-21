package edu.ncsu.csc216.issue_manager.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.io.IssueReader;
import edu.ncsu.csc216.issue_manager.model.io.IssueWriter;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * This class controls the creation and modification of IssueLists. This class
 * can create a manager, get the instance of the manager, save issues to a file,
 * load issues from a file, create new lists, get lists as an array, get lists
 * as an array by issue type, get an issue by an id, execute a command, delete
 * an issue, add an issue to the list.
 * 
 * @author Sanjana Cheerla
 *
 */
public class IssueManager {

	private static IssueManager instance;
	private IssueList issueList;

	/**
	 * creates an issue manager
	 */
	private IssueManager() {
		issueList = new IssueList();
	}

	/**
	 * following the singleton pattern, there can only ever be one instance of an
	 * issue manager. This method gets the instance of the issue manager.
	 * 
	 * @return the instance of the IssueManager;
	 */
	public static IssueManager getInstance() {
		if (instance == null) {
			instance = new IssueManager();
		}
		return instance;
	}

	/**
	 * saves the issues to a file
	 * 
	 * @param fileName the file that the issues are being saved to
	 */
	public void saveIssuesToFile(String fileName) {
		ArrayList<Issue> issues = this.issueList.getIssues();
		IssueWriter.writeIssuesToFile(fileName, issues);
	}

	/**
	 * loads issues from a file
	 * 
	 * @param fileName the file that the issues are being loaded from
	 */
	public void loadIssuesFromFile(String fileName) {
		ArrayList<Issue> issues = IssueReader.readIssuesFromFile(fileName);
		issueList.addIssues(issues);
	}

	/**
	 * creates a new issue list
	 */
	public void createNewIssueList() {
		this.issueList = new IssueList();
	}

	/**
	 * gets the issues as a 2D array of Objects.
	 * 
	 * @return a 2D array of objects. There is one row for every list and each
	 *         column 0 to 3 contains id number, state name, issue type, and issue
	 *         summary respectively.
	 */
	public Object[][] getIssueListAsArray() {
		ArrayList<Issue> issues = issueList.getIssues();

		if (issues.isEmpty()) {
			return new Object[0][0];
		}

		Object[][] issueArr = new Object[issues.size()][4];

		for (int i = 0; i < issues.size(); i++) {
			int index = 0;
			issueArr[i][index] = issues.get(i).getIssueId();
			issueArr[i][++index] = issues.get(i).getState();
			issueArr[i][++index] = issues.get(i).getIssueType();
			issueArr[i][++index] = issues.get(i).getSummary();
		}

		return issueArr;
	}

	/**
	 * gets the issues as a 2D array of Objects by the specified type.
	 * 
	 * @param type the issue type of the issues being returned
	 * @return a 2D array of objects. There is one row for every list and each
	 *         column 0 to 3 contains id number, state name, issue type, and issue
	 *         summary respectively.
	 * @throws IllegalArgumentException if the type is null.
	 */
	public Object[][] getIssueListAsArrayByIssueType(String type) {

		if (type == null) {
			throw new IllegalArgumentException();
		}

		if (!type.equals(Issue.I_BUG) && !type.equals(Issue.I_ENHANCEMENT)) {
			return new Object[0][0];
		}

		ArrayList<Issue> issues = issueList.getIssuesByType(type);

		if (issues.isEmpty()) {
			return new Object[0][0];
		}

		Object[][] issueArr = new Object[issues.size()][4];

		for (int i = 0; i < issues.size(); i++) {
			int index = 0;
			issueArr[i][index] = issues.get(i).getIssueId();
			issueArr[i][++index] = issues.get(i).getState();
			issueArr[i][++index] = issues.get(i).getIssueType();
			issueArr[i][++index] = issues.get(i).getSummary();
		}

		return issueArr;
	}

	/**
	 * gets an issue by its id number
	 * 
	 * @param issueId the id number of the issue
	 * @return the issue found at that id number, null if it does not exist.
	 */
	public Issue getIssueById(int issueId) {
		return issueList.getIssueById(issueId);
	}

	/**
	 * executes the provided command on the provided issue
	 * 
	 * @param issueId the issue that the command acts on
	 * @param c       the command being used on the issue
	 */
	public void executeCommand(int issueId, Command c) {
		issueList.executeCommand(issueId, c);
	}

	/**
	 * deletes an issue at that id
	 * 
	 * @param issueId the id number of the issue being deleted
	 */
	public void deleteIssueById(int issueId) {
		issueList.deleteIssueById(issueId);
	}

	/**
	 * adds an issue to the list.
	 * 
	 * @param issueType type of the issue being added
	 * @param summary   summary of the issue being added
	 * @param note      note for the issued being added
	 */
	public void addIssueToList(IssueType issueType, String summary, String note) {
		issueList.addIssue(issueType, summary, note);
	}
}
