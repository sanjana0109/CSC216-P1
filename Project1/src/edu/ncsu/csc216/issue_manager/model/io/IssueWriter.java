package edu.ncsu.csc216.issue_manager.model.io;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.List;

import edu.ncsu.csc216.issue_manager.model.issue.Issue;

/**
 * This class writes the provided list of issues to a provided file.
 * 
 * @author Sanjana Cheerla
 *
 */
public class IssueWriter {

	/**
	 * Creates an instance of an issue writer
	 */
	public IssueWriter() {
		super();
	}

	/**
	 * writes the list of issues to the file provided using the toString() method in
	 * the Issue class.
	 * 
	 * @param fileName the file that the issues are being written to
	 * @param issues   the List of issues being written to the file
	 * @throws IllegalArgumentException with the message “Unable to save file." if
	 *                                  the file cannot be saved.
	 */
	public static void writeIssuesToFile(String fileName, List<Issue> issues) {
		try {
			PrintStream fileWriter = new PrintStream(new File(fileName));

			for (int i = 0; i < issues.size(); i++) {
				fileWriter.print(issues.get(i).toString());
			}
		} catch (IOException e) {
			throw new IllegalArgumentException("Unable to save file.");
		}

	}

}
