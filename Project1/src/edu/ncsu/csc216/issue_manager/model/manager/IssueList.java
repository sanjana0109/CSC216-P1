package edu.ncsu.csc216.issue_manager.model.manager;

import java.util.ArrayList;

import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.issue.Issue;
import edu.ncsu.csc216.issue_manager.model.issue.Issue.IssueType;

/**
 * A IssueList maintains a List of Issues and supports the addition of a list, a
 * collection of issues, removing an issue, searching for an issue and return
 * the entire list or sublists of the list by type.
 * 
 * @author Sanjana Cheerla
 *
 */
public class IssueList {

	ArrayList<Issue> issueList;

	/**
	 * creates an instance of the issue list
	 */
	public IssueList() {
		issueList = new ArrayList<Issue>();
		Issue.setCounter(0);
	}

	/**
	 * Adds an issue to the list
	 * 
	 * @param issueType type of issue being added
	 * @param summary   summary of the issue being added
	 * @param note      the note of the issue being added
	 * @return the issue id of the added issue
	 */
	public int addIssue(IssueType issueType, String summary, String note) {
		Issue tmp = new Issue(issueType, summary, note);
		issueList.add(tmp);
		return tmp.getIssueId();
	}

	/**
	 * Adds a list of issues to the issue list.
	 * 
	 * @param issues the list of issues being added
	 */
	public void addIssues(ArrayList<Issue> issues) {
		this.issueList.clear();

		if (issues == null || issues.isEmpty()) {
			return;
		}

		for (int i = 0; i < issues.size(); i++) {
			issueList.add(issues.get(i));
		}

		ArrayList<Integer> ids = new ArrayList<Integer>();
		for (int i = 0; i < issueList.size(); i++) {
			ids.add(issueList.get(i).getIssueId());
		}

		int max = ids.get(0);
		for (int i = 0; i < ids.size(); i++) {
			if (ids.get(i) > max) {
				max = ids.get(i);
			}
		}

		Issue.setCounter(max + 1);

	}

	/**
	 * gets all the issues in the list
	 * 
	 * @return a list of all the issues in the list, null if the issues do not exist
	 */
	public ArrayList<Issue> getIssues() {

		ArrayList<Issue> returnList = new ArrayList<Issue>();

		for (Issue i : issueList) {
			returnList.add(i);
		}

		return returnList;
	}

	/**
	 * gets all the issues by type.
	 * 
	 * @param type the type of the issues being returned, enhancement or bug.
	 * @return a list of the issues.
	 * @throws IllegalArgumentException if the type is null or empty, or the type is
	 *                                  not Bug or Enhancement
	 */
	public ArrayList<Issue> getIssuesByType(String type) {
		if (type == null || type.isEmpty()) {
			throw new IllegalArgumentException();
		}

		if (type.equals(Issue.I_BUG) || type.equals(Issue.I_ENHANCEMENT)) {
			ArrayList<Issue> returnList = new ArrayList<Issue>();

			for (int i = 0; i < issueList.size(); i++) {
				if (issueList.get(i).getIssueType().equals(type)) {
					returnList.add(issueList.get(i));
				}
			}

			return returnList;
		}

		throw new IllegalArgumentException();
	}

	/**
	 * gets an issue by the id number
	 * 
	 * @param issueId the id number of the issue
	 * @return the issue that is by the number, null if no such issue exists
	 */
	public Issue getIssueById(int issueId) {
		if (issueList != null && !issueList.isEmpty()) {
			for (int i = 0; i < issueList.size(); i++) {
				if (issueList.get(i) != null && issueList.get(i).getIssueId() == issueId) {
					return issueList.get(i);
				}
			}
		}
		return null;
	}

	/**
	 * Executes the provided command on the provided issue id
	 * 
	 * @param id the id that the command is being executed on
	 * @param c  the command being executed
	 */
	public void executeCommand(int id, Command c) {
		if (this.getIssueById(id) == null) {
			return;
		}
		this.getIssueById(id).update(c);
	}

	/**
	 * deletes the issue that exists at the provided id
	 * 
	 * @param issueId the id of the issue to be deleted
	 */
	public void deleteIssueById(int issueId) {
		if (this.getIssueById(issueId) == null) {
			return;
		}
		this.issueList.remove(this.getIssueById(issueId));
	}

}
