package edu.ncsu.csc216.issue_manager.model.issue;

import java.util.ArrayList;
import edu.ncsu.csc216.issue_manager.model.command.Command;
import edu.ncsu.csc216.issue_manager.model.command.Command.CommandValue;
import edu.ncsu.csc216.issue_manager.model.command.Command.Resolution;

/**
 * This class manages an issue, what an issue contains and how it can be moved
 * to a different state. There are getters and private setters for the fields
 * and constructors to create an Issue object. There are also Inner classes that
 * dictate how the issue shifts states.
 * 
 * @author Sanjana Cheerla
 *
 */
public class Issue {

	/** Enhancement string if the issue is an enhancement */
	public static final String I_ENHANCEMENT = "Enhancement";

	/** Bug string if the issue is a bug */
	public static final String I_BUG = "Bug";

	/** Issue state string if the issue is new */
	public static final String NEW_NAME = "New";

	/** Issue state string if the issue is working */
	public static final String WORKING_NAME = "Working";

	/** Issue state string if the issue is confirmed */
	public static final String CONFIRMED_NAME = "Confirmed";

	/** Issue state string if the issue is verifying */
	public static final String VERIFYING_NAME = "Verifying";

	/** Issue state string if the issue is closed */
	public static final String CLOSED_NAME = "Closed";

	/** the id of the issue */
	private int issueId;

	/** the current state of the issue */
	private IssueState state;

	/** Whether the issue is an enhancement or a bug */
	private IssueType issueType;

	/** summary of the issue */
	private String summary;

	/** the owner of the issue */
	private String owner;

	/** if the issue is confirmed or not */
	private boolean confirmed;

	/** the resolution for the issue */
	private Resolution resolution;

	/** the list of notes for the issue */
	private ArrayList<String> notes;

	/** the counter to keep track of issue Id's */
	private static int counter = 0;

	/** Enumeration for if the issue is a bug or an enhancement */
	public enum IssueType {
		/** Enhancement issue type */
		/** Bug issue type */
		ENHANCEMENT, BUG
	}

	/** new state of issue */
	private final NewState newState = new NewState();

	/** working state of issue */
	private final WorkingState workingState = new WorkingState();

	/** confirmed state of issue */
	private final ConfirmedState confirmedState = new ConfirmedState();

	/** verifying state of issue */
	private final VerifyingState verifyingState = new VerifyingState();

	/** closed state of issue */
	private final ClosedState closedState = new ClosedState();

	/**
	 * Creates an issue object with the type of issue, summary and notes.
	 * 
	 * @param issueType type of issue, bug or enhancement
	 * @param summary   summary of the issue
	 * @param note      the note of the issue
	 * @throws IllegalArgumentException if issueType, summary, or note are null or
	 *                                  empty
	 */
	public Issue(IssueType issueType, String summary, String note) {

		if (issueType == null) {
			throw new IllegalArgumentException();
		}

		if (summary == null || summary.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (note == null || note.isEmpty()) {
			throw new IllegalArgumentException();
		}

		this.setIssueId(counter);
		incrementCounter();

		this.setState(NEW_NAME);

		this.issueType = issueType;

		this.setSummary(summary);

		this.setOwner(null);

		this.confirmed = false;

		this.setResolution(null);

		ArrayList<String> notesArrayList = new ArrayList<String>();
		notesArrayList.add(note);
		this.setNotes(notesArrayList);

	}

	/**
	 * Creates an issue based on the id, state, issue type, summary, owner,
	 * confirmed, resolution and notes.
	 * 
	 * @param id         id of the issue
	 * @param state      state of the issue
	 * @param issueType  type of the issue
	 * @param summary    summary of the issue
	 * @param owner      owner of the issue
	 * @param confirmed  if the issue is confirmed or not
	 * @param resolution resolution of the issue
	 * @param notes      of the issue
	 * @throws IllegalArgumentException if id is less than 0, invalid state name,
	 *                                  invalid issue type, if summary is null or
	 *                                  empty, if state is working or verifying and
	 *                                  owner is null or empty, if state is new,
	 *                                  working or confirmed and there is a
	 *                                  resolution, if there is a confirmed
	 *                                  enhancement, if the state is working and
	 *                                  there is a null or empty owner, if there is
	 *                                  a working bug, if the state is verifying and
	 *                                  resolution is null or not "Fixed", if the
	 *                                  state is closed and there is no resolution,
	 *                                  if the notes are null or empty, if the issue
	 *                                  is an enhancement, the state is closed, and
	 *                                  the resolution is works for me.
	 */
	public Issue(int id, String state, String issueType, String summary, String owner, boolean confirmed,
			String resolution, ArrayList<String> notes) {

		if (id < 0) {
			throw new IllegalArgumentException();
		}
		if (!state.equals(NEW_NAME) && !state.equals(WORKING_NAME) && !state.equals(CONFIRMED_NAME)
				&& !state.equals(VERIFYING_NAME) && !state.equals(CLOSED_NAME)) {
			throw new IllegalArgumentException();
		}
		if (!issueType.equals(I_BUG) && !issueType.equals(I_ENHANCEMENT)) {
			throw new IllegalArgumentException();
		}
		if (summary == null || summary.isEmpty() || summary.length() == 0) {
			throw new IllegalArgumentException();
		}
		if ((state.equals(WORKING_NAME) || state.equals(VERIFYING_NAME)) && (owner == null || owner.isEmpty())) {
			throw new IllegalArgumentException();
		}
		if (resolution != null && !resolution.isEmpty()
				&& (state.equals(NEW_NAME) || state.equals(WORKING_NAME) || state.equals(CONFIRMED_NAME))) {
			throw new IllegalArgumentException();
		}
		if ((owner == null || owner.isEmpty()) && state.equals(WORKING_NAME)) {
			throw new IllegalArgumentException();
		}
		if (issueType.equals(I_BUG) && !confirmed && state.equals(WORKING_NAME)) {
			throw new IllegalArgumentException();
		}
		if ((resolution == null || !resolution.equals(Command.R_FIXED)) && state.equals(VERIFYING_NAME)) {
			throw new IllegalArgumentException();
		}
		if (state.equals(CLOSED_NAME) && (resolution == null || resolution.isEmpty())) {
			throw new IllegalArgumentException();
		}
		if (issueType.equals(I_ENHANCEMENT) && (state.equals(CONFIRMED_NAME) || confirmed)) {
			throw new IllegalArgumentException();
		}
		if (notes == null || notes.isEmpty()) {
			throw new IllegalArgumentException();
		}
		if (issueType.equals(I_ENHANCEMENT) && resolution != null && resolution.equals(Command.R_WORKSFORME)
				&& state.equals(CLOSED_NAME)) {
			throw new IllegalArgumentException();
		}

		this.setIssueId(id);
		this.setState(state);
		this.setIssueType(issueType);
		this.setSummary(summary);

		if (owner == null || owner.isEmpty()) {
			this.owner = null;
		} else {
			this.setOwner(owner);
		}
		this.setConfirmed(confirmed);
		this.setResolution(resolution);
		this.setNotes(notes);

		if (id > counter) {
			setCounter(id + 1);
		}

	}

	/**
	 * gets the issue id of the issue
	 * 
	 * @return the issueId
	 */
	public int getIssueId() {
		return this.issueId;
	}

	/**
	 * sets the issue id
	 * 
	 * @param issueId the issueId to set
	 */
	private void setIssueId(int issueId) {
		this.issueId = issueId;
	}

	/**
	 * gets the summary of the issue
	 * 
	 * @return the summary
	 */
	public String getSummary() {
		return this.summary;
	}

	/**
	 * sets the summary of the issue
	 * 
	 * @param summary the summary to set
	 */
	private void setSummary(String summary) {
		this.summary = summary;
	}

	/**
	 * gets the owner of the issue
	 * 
	 * @return the owner of the issue
	 */
	public String getOwner() {
		return this.owner;
	}

	/**
	 * sets the owner of the issue
	 * 
	 * @param owner the owner to set
	 */
	private void setOwner(String owner) {
		this.owner = owner;
	}

	/**
	 * gets if the issue is confirmed or not.
	 * 
	 * @return the confirmed
	 */
	public boolean isConfirmed() {
		return this.confirmed;
	}

	/**
	 * sets the confirmed value of issue
	 * 
	 * @param confrimed the confirmed to set
	 */
	private void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	/**
	 * gets the notes of the issue
	 * 
	 * @return the notes
	 *
	 */
	public ArrayList<String> getNotes() {
		return this.notes;
	}

	/**
	 * sets the notes of the issue
	 * 
	 * @param notes the notes to set
	 */
	private void setNotes(ArrayList<String> notes) {
		this.notes = notes;
	}

	/**
	 * sets the type of issue
	 * 
	 * @param issueType to be set
	 */
	private void setIssueType(String issueType) {
		if (issueType.equals(I_BUG)) {
			this.issueType = IssueType.BUG;
			return;
		}
		if (issueType.equals(I_ENHANCEMENT)) {
			this.issueType = IssueType.ENHANCEMENT;
			return;
		}
	}

	/**
	 * sets the state of issue
	 * 
	 * @param state to be set
	 */
	private void setState(String state) {
		if (state.equals(NEW_NAME)) {
			this.state = this.newState;
			return;
		}
		if (state.equals(WORKING_NAME)) {
			this.state = this.workingState;
			return;
		}
		if (state.equals(CONFIRMED_NAME)) {
			this.state = this.confirmedState;
			return;
		}
		if (state.equals(VERIFYING_NAME)) {
			this.state = this.verifyingState;
			return;
		}
		if (state.equals(CLOSED_NAME)) {
			this.state = this.closedState;
			return;
		}
	}

	/**
	 * sets the resolution of the issue
	 * 
	 * @param resolution to be set
	 */
	private void setResolution(String resolution) {
		if (resolution == null) {
			this.resolution = null;
			return;
		}

		if (resolution.equals(Command.R_DUPLICATE)) {
			this.resolution = Resolution.DUPLICATE;
			return;
		}
		if (resolution.equals(Command.R_FIXED)) {
			this.resolution = Resolution.FIXED;
			return;
		}
		if (resolution.equals(Command.R_WONTFIX)) {
			this.resolution = Resolution.WONTFIX;
			return;
		}
		if (resolution.equals(Command.R_WORKSFORME)) {
			this.resolution = Resolution.WORKSFORME;
			return;
		}
	}

	/**
	 * increments the counter variable
	 */
	public static void incrementCounter() {
		counter++;
	}

	/**
	 * gets the state of the issue
	 * 
	 * @return the state of issue, null if there is no state
	 */
	public String getState() {
		if (this.state == null) {
			return null;
		}
		return state.getStateName();
	}

	/**
	 * gets the type of issue
	 * 
	 * @return type of issue bug or enhancement, null otherwise
	 */
	public String getIssueType() {
		if (this.issueType.equals(IssueType.BUG)) {
			return I_BUG;
		}
		if (this.issueType.equals(IssueType.ENHANCEMENT)) {
			return I_ENHANCEMENT;
		}
		return null;
	}

	/**
	 * gets the resolution of issue
	 * 
	 * @return resolution of issue, null if the issue does not exist
	 */
	public String getResolution() {
		if (this.resolution == null) {
			return null;
		}
		if (this.resolution.equals(Resolution.DUPLICATE)) {
			return Command.R_DUPLICATE;
		}
		if (this.resolution.equals(Resolution.FIXED)) {
			return Command.R_FIXED;
		}
		if (this.resolution.equals(Resolution.WONTFIX)) {
			return Command.R_WONTFIX;
		}
		if (this.resolution.equals(Resolution.WORKSFORME)) {
			return Command.R_WORKSFORME;
		}
		return null;
	}

	/**
	 * gets the notes of the issue in a string format
	 * 
	 * @return notes of the issue
	 */
	public String getNotesString() {
		String s = "";
		for (int i = 0; i < this.notes.size(); i++) {
			s += "-";
			s += this.notes.get(i);
			s += "\n";
		}
		return s;
	}

	/**
	 * Represents the issue as a string in the form of
	 * "id,state,issue-type,summary,owner,confirmed,resolution followed by a
	 * newline, hyphen and the note for each note
	 * 
	 * @return the notes as a string
	 */
	@Override
	public String toString() {
		String s = "";
		s += "*";
		s += this.getIssueId() + "+";

		if (this.getState() == null) {
			s += "+";
		} else {
			s += this.getState() + "+";
		}

		s += this.getIssueType() + "+";

		s += this.getSummary() + "+";

		if (this.getOwner() == null) {
			s += "null+";
		} else {
			s += this.getOwner() + "+";
		}

		s += Boolean.toString(this.isConfirmed()) + "+";

		if (this.getResolution() == null) {
			s += "\n";
		} else {
			s += this.getResolution() + "\n";
		}
		s += this.getNotesString();

		return s;
	}

	/**
	 * updates the state of the issue
	 * 
	 * @param c the command that triggers the issue update
	 * @throws UnsupportedOperationException if the current state determines that
	 *                                       the command is not appropriate for the
	 *                                       FSM
	 */
	public void update(Command c) {
		state.updateState(c);
	}

	/**
	 * sets the counter variable
	 * 
	 * @param i the variable to set the counter to
	 */
	public static void setCounter(int i) {
		counter = i;
	}

	/**
	 * Interface for states in the Issue State Pattern. All concrete issue states
	 * must implement the IssueState interface. The IssueState interface should be a
	 * private interface of the Issue class.
	 * 
	 * @author Dr. Sarah Heckman (sarah_heckman@ncsu.edu)
	 */
	private interface IssueState {

		/**
		 * Update the Issue based on the given Command. An UnsupportedOperationException
		 * is throw if the Command is not a valid action for the given state.
		 * 
		 * @param command Command describing the action that will update the Issue's
		 *                state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 *                                       for the given state.
		 */
		void updateState(Command command);

		/**
		 * Returns the name of the current state as a String.
		 * 
		 * @return the name of the current state as a String.
		 */
		String getStateName();

	}

	/**
	 * New state class, implements IssueState. Can create new state objects and
	 * update state and get name of new state.
	 * 
	 * @author Sanjana Cheerla
	 *
	 */
	private class NewState implements IssueState {

		/**
		 * Update the Issue based on the given Command. An UnsupportedOperationException
		 * is throw if the Command is not a valid action for the new state.
		 * 
		 * @param command Command describing the action that will update the Issue's
		 *                state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 *                                       for the new state.
		 */
		@Override
		public void updateState(Command c) {

			if (c.getOwnerId() != null && !c.getOwnerId().isEmpty() && c.getCommand().equals(CommandValue.ASSIGN)) {
				if (issueType.equals(IssueType.BUG)) {
					throw new UnsupportedOperationException();
				}
				setState(WORKING_NAME);
				notes.add(c.getNote());
				setOwner(c.getOwnerId());
				return;
			} else if (c.getCommand().equals(CommandValue.CONFIRM) && issueType.equals(IssueType.BUG)) {
				setState(CONFIRMED_NAME);
				notes.add(c.getNote());
				setOwner(c.getOwnerId());
				setConfirmed(true);
				return;
			} else if (issueType.equals(IssueType.BUG)
					&& (c.getResolution() != null && (c.getResolution().equals(Resolution.DUPLICATE)
							|| c.getResolution().equals(Resolution.WONTFIX)
							|| c.getResolution().equals(Resolution.WORKSFORME)))
					&& c.getCommand().equals(CommandValue.RESOLVE)) {
				setState(CLOSED_NAME);
				if (c.getResolution().equals(Resolution.DUPLICATE)) {
					setResolution(Command.R_DUPLICATE);
				}
				if (c.getResolution().equals(Resolution.WONTFIX)) {
					setResolution(Command.R_WONTFIX);
				}
				if (c.getResolution().equals(Resolution.WORKSFORME)) {
					setResolution(Command.R_WORKSFORME);
				}
				notes.add(c.getNote());
				setOwner(c.getOwnerId());
				return;
			} else if (issueType.equals(IssueType.ENHANCEMENT) && (c.getResolution() != null
					&& (c.getResolution().equals(Resolution.DUPLICATE) || c.getResolution().equals(Resolution.WONTFIX)))
					&& c.getCommand().equals(CommandValue.RESOLVE)) {
				setState(CLOSED_NAME);
				if (c.getResolution().equals(Resolution.DUPLICATE)) {
					setResolution(Command.R_DUPLICATE);
				}
				if (c.getResolution().equals(Resolution.WONTFIX)) {
					setResolution(Command.R_WONTFIX);
				}

				notes.add(c.getNote());
				setOwner(c.getOwnerId());
				return;
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns the name of the new state as a String.
		 * 
		 * @return the name of the new state as a String.
		 */
		@Override
		public String getStateName() {
			return NEW_NAME;
		}

	}

	/**
	 * working state class, implements IssueState. Can create working state objects
	 * and update state and get name of working state.
	 * 
	 * @author Sanjana Cheerla
	 *
	 */
	private class WorkingState implements IssueState {

		/**
		 * Update the Issue based on the given Command. An UnsupportedOperationException
		 * is throw if the Command is not a valid action for the working state.
		 * 
		 * @param command Command describing the action that will update the Issue's
		 *                state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 *                                       for the working state.
		 */
		@Override
		public void updateState(Command c) {
			if (c.getResolution() != null && c.getResolution().equals(Resolution.FIXED)
					&& c.getCommand().equals(CommandValue.RESOLVE)) {
				setState(VERIFYING_NAME);
				setResolution(Command.R_FIXED);
				notes.add(c.getNote());
				return;
			} else if (issueType.equals(IssueType.BUG)
					&& (c.getResolution() != null && (c.getResolution().equals(Resolution.DUPLICATE)
							|| c.getResolution().equals(Resolution.WONTFIX)
							|| c.getResolution().equals(Resolution.WORKSFORME)))
					&& c.getCommand().equals(CommandValue.RESOLVE)) {
				setState(CLOSED_NAME);
				if (c.getResolution().equals(Resolution.DUPLICATE)) {
					setResolution(Command.R_DUPLICATE);
				}
				if (c.getResolution().equals(Resolution.WONTFIX)) {
					setResolution(Command.R_WONTFIX);
				}
				if (c.getResolution().equals(Resolution.WORKSFORME)) {
					setResolution(Command.R_WORKSFORME);
				}
				notes.add(c.getNote());
				return;
			} else if (issueType.equals(IssueType.ENHANCEMENT) && (c.getResolution() != null
					&& (c.getResolution().equals(Resolution.DUPLICATE) || c.getResolution().equals(Resolution.WONTFIX)))
					&& c.getCommand().equals(CommandValue.RESOLVE)) {
				setState(CLOSED_NAME);
				if (c.getResolution().equals(Resolution.DUPLICATE)) {
					setResolution(Command.R_DUPLICATE);
				}
				if (c.getResolution().equals(Resolution.WONTFIX)) {
					setResolution(Command.R_WONTFIX);
				}

				notes.add(c.getNote());
				return;
			} else {
				throw new UnsupportedOperationException();
			}
		}

		/**
		 * Returns the name of the working state as a String.
		 * 
		 * @return the name of the working state as a String.
		 */
		@Override
		public String getStateName() {
			return WORKING_NAME;
		}

	}

	/**
	 * Confirmed state class, implements IssueState. Can create confirmed state
	 * objects and update state and get name of confirmed state.
	 * 
	 * @author Sanjana Cheerla
	 *
	 */
	private class ConfirmedState implements IssueState {

		/**
		 * Update the Issue based on the given Command. An UnsupportedOperationException
		 * is throw if the Command is not a valid action for the confirmed state.
		 * 
		 * @param command Command describing the action that will update the Issue's
		 *                state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 *                                       for the confirmed state.
		 */
		@Override
		public void updateState(Command c) {
			if (c.getOwnerId() != null && !c.getOwnerId().isEmpty() && c.getCommand().equals(CommandValue.ASSIGN)) {
				setOwner(c.getOwnerId());
				setState(WORKING_NAME);
				notes.add(c.getNote());
				setOwner(c.getOwnerId());
				return;
			} else if (c.getResolution() != null && c.getResolution().equals(Resolution.WONTFIX)
					&& c.getCommand().equals(CommandValue.RESOLVE)) {
				setState(CLOSED_NAME);
				notes.add(c.getNote());
				setResolution(Command.R_WONTFIX);
				return;
			} else {
				throw new UnsupportedOperationException();
			}

		}

		/**
		 * Returns the name of the confirmed state as a String.
		 * 
		 * @return the name of the confirmed state as a String.
		 */
		@Override
		public String getStateName() {
			return CONFIRMED_NAME;
		}

	}

	/**
	 * verifying state class, implements IssueState. Can create verifying state
	 * objects and update state and get name of verifying state.
	 * 
	 * @author Sanjana Cheerla
	 *
	 */
	private class VerifyingState implements IssueState {

		/**
		 * Update the Issue based on the given Command. An UnsupportedOperationException
		 * is throw if the Command is not a valid action for the verifying state.
		 * 
		 * @param command Command describing the action that will update the Issue's
		 *                state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 *                                       for the verifying state.
		 */
		@Override
		public void updateState(Command c) {
			// s1
			if (c.getCommand().equals(CommandValue.VERIFY)) {
				setState(CLOSED_NAME);
				notes.add(c.getNote());
				return;
			}

			// s2
			if (c.getCommand().equals(CommandValue.REOPEN)) {
				setState(WORKING_NAME);
				notes.add(c.getNote());
				setResolution(null);
				return;
			}

			throw new UnsupportedOperationException();

		}

		/**
		 * Returns the name of the verifying state as a String.
		 * 
		 * @return the name of the verifying state as a String.
		 */
		@Override
		public String getStateName() {
			return VERIFYING_NAME;
		}

	}

	/**
	 * closed state class, implements IssueState. Can create closed state objects
	 * and update state and get name of closed state.
	 * 
	 * @author Sanjana Cheerla
	 *
	 */
	private class ClosedState implements IssueState {

		/**
		 * Update the Issue based on the given Command. An UnsupportedOperationException
		 * is throw if the Command is not a valid action for the closed state.
		 * 
		 * @param command Command describing the action that will update the Issue's
		 *                state.
		 * @throws UnsupportedOperationException if the Command is not a valid action
		 *                                       for the closed state.
		 */
		@Override
		public void updateState(Command c) {
			if (c.getCommand() != null && c.getCommand().equals(CommandValue.REOPEN)
					&& issueType.equals(IssueType.ENHANCEMENT) && owner != null && !owner.isEmpty()) {
				setState(WORKING_NAME);
				setResolution(null);
				notes.add(c.getNote());
				return;
			}

			if (c.getCommand() != null && c.getCommand().equals(CommandValue.REOPEN) && issueType.equals(IssueType.BUG)
					&& confirmed && owner != null && !owner.isEmpty()) {
				setState(WORKING_NAME);
				setResolution(null);
				notes.add(c.getNote());
				return;
			}
			if (c.getCommand() != null && c.getCommand().equals(CommandValue.REOPEN) && issueType.equals(IssueType.BUG)
					&& confirmed && (owner == null || owner.isEmpty())) {
				setState(CONFIRMED_NAME);
				setResolution(null);
				notes.add(c.getNote());
				return;
			}

			if (c.getCommand() != null && c.getCommand().equals(CommandValue.REOPEN)
					&& (owner == null || owner.isEmpty())) {
				setState(NEW_NAME);
				notes.add(c.getNote());
				setResolution(null);
				return;
			}

			throw new UnsupportedOperationException();

		}

		/**
		 * Returns the name of the closed state as a String.
		 * 
		 * @return the name of the closed state as a String.
		 */
		@Override
		public String getStateName() {
			return CLOSED_NAME;
		}

	}
}
