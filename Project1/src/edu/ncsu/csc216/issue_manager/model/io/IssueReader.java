package edu.ncsu.csc216.issue_manager.model.io;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * This class reads issues from a file and puts them into an array list of issue
 * objects
 * 
 * @author Sanjana Cheerla
 *
 */
public class IssueReader {

	/**
	 * creates a new issue reader instance
	 */
	public IssueReader() {
		super();
	}

	/**
	 * reads the issues from the file and puts them into an array list of issue
	 * objects
	 * 
	 * @param fileName the filename that the issues are being read from
	 * @return an array list of issue objects read from the file
	 * @throws IllegalArgumentException with the message “Unable to load file.” if
	 *                                  the file cannot be loaded.
	 */
	public static ArrayList<Issue> readIssuesFromFile(String fileName) {
		Scanner fileReader;
		ArrayList<Issue> issues;
		String[] issuesArray;
		String issuesString = "\n";

		try {
			fileReader = new Scanner(new FileInputStream(fileName));
			while (fileReader.hasNextLine()) {
				issuesString += fileReader.nextLine();
				issuesString += "\n";
			}
			fileReader.close();

			System.out.println(issuesString);

			issuesArray = issuesString.split("\\r?\\n[*]", -1);

			issues = new ArrayList<Issue>();

			for (int i = 1; i < issuesArray.length; i++) {
				Issue processedIssue = processIssue(issuesArray[i]);
				if (processedIssue != null) {
//					for(int j = 0; j < issues.size(); j++) {
//						if(issues.get(j).getIssueId() == processedIssue.getIssueId()) {
//							throw new IllegalArgumentException("Unable to load file.");
//						}
//					}
					issues.add(processedIssue);
				}
			}

		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException("Unable to load file.");
		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Unable to load file.");
		}

		return issues;
	}

	/**
	 * Processes an entire issue at one time
	 * 
	 * @param line the issue being processed
	 * @return an issue object that is constructed
	 * @throws IllegalArgumentException if there is an issue creating an issue
	 *                                  object
	 */
	private static Issue processIssue(String line) {

		Issue issue = null;
		boolean b = false;
		int id;

		// first 7 = issue stuff
		try {
			if (line.isEmpty()) {
				return null;
			}

			String[] issueWithNotes = line.split("[\n]", -1);

//			// know that there are no notes
//			if (issueWithNotes.length < 2) {
//				throw new IllegalArgumentException("Invalid issue");
//			}

			String[] onlyIssue = issueWithNotes[0].split("[+]", -1);

			// know it does not have necessary information to create an issue
			if (onlyIssue.length < 7) {
				throw new IllegalArgumentException("Invalid issue");
			}

			id = Integer.parseInt(onlyIssue[0]);
			b = Boolean.parseBoolean(onlyIssue[5]);

			ArrayList<String> notes = new ArrayList<String>();

			// skip 0 because don't need to account for the issue string
			int counter = 0;

			for (int i = 1; i < issueWithNotes.length; i++) {
				if (issueWithNotes[i].startsWith("-") && !issueWithNotes[i].equals("")) {
					notes.add(counter, issueWithNotes[i].substring(1)); // error
					counter++;
				}
				if (!issueWithNotes[i].startsWith("-") && !issueWithNotes[i].equals("")) {
					String s = notes.get(counter - 1); // error, fixed possibly
					s += "\n";
					s += issueWithNotes[i];
					notes.set(counter - 1, s);
				}
			}

			issue = new Issue(id, onlyIssue[1], onlyIssue[2], onlyIssue[3], onlyIssue[4], b, onlyIssue[6], notes);

		} catch (IllegalArgumentException e) {
			throw new IllegalArgumentException("Invalid issue");
		}
		return issue;
	}

}
