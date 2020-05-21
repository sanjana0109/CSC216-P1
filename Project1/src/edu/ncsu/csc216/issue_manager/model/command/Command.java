package edu.ncsu.csc216.issue_manager.model.command;

/**
 * This class dictates what state the status of the bug or enhancement should
 * move to by encapsulating the user actions. In this class it is possible to
 * create a command and get values for the command, resolution, who created the
 * command and the notes on the command.
 * 
 * @author Sanjana Cheerla
 * 
 */
public class Command {

	/** Enumeration values for the possible command values. */
	public enum CommandValue {
		/** Assign command value */
		/** Confirm command value */
		/** Resolve command value */
		/** Verify command value */
		/** Reopen command value */
		ASSIGN, CONFIRM, RESOLVE, VERIFY, REOPEN
	}

	/** Enumeration values for the possible resolution values. */
	public enum Resolution {
		/** Fixed Resolution */
		/** Duplicate Resolution */
		/** Wont Fix Resolution */
		/** Works for me Resolution */
		FIXED, DUPLICATE, WONTFIX, WORKSFORME
	}

	/** Value for the fixed issue */
	public static final String R_FIXED = "Fixed";

	/** Value for the duplicate issue */
	public static final String R_DUPLICATE = "Duplicate";

	/** Value for the wont fix issue */
	public static final String R_WONTFIX = "WontFix";

	/** Value for the works for me issue */
	public static final String R_WORKSFORME = "WorksForMe";

	/** owner of the command */
	private String ownerId;

	/** note for why the command was executed */
	private String note;

	/** command value of command */
	private CommandValue c;

	/** resolution value of command */
	private Resolution r;

	/**
	 * Creates a command object provided the necessary fields.
	 * 
	 * @param c       the commandValue for the command being executed
	 * @param ownerId the owner id for the command being executed
	 * @param r       the resolution for the command being executed
	 * @param note    the note for the command being executed
	 * @throws IllegalArgumentException if the CommandValue is null. If CommandValue
	 *                                  is ASSIGNED and the ownerID is null or
	 *                                  empty. If the CommandValue is RESOLVE and
	 *                                  the Resolution is null. If the command has a
	 *                                  null value for note.
	 */
	public Command(CommandValue c, String ownerId, Resolution r, String note) {
		if (c == null) {
			throw new IllegalArgumentException();
		}
		if ((ownerId == null || ownerId.isEmpty()) && c.equals(CommandValue.ASSIGN)) {
			throw new IllegalArgumentException();
		}
		if (r == null && c.equals(CommandValue.RESOLVE)) {
			throw new IllegalArgumentException();
		}
		if (note == null || note.isEmpty()) {
			throw new IllegalArgumentException();
		}

		this.c = c;
		this.ownerId = ownerId;
		this.r = r;
		this.note = note;
	}

	/**
	 * Gets the command value for the command
	 * 
	 * @return command value
	 */
	public CommandValue getCommand() {
		return this.c;
	}

	/**
	 * Gets the owner id of the command
	 * 
	 * @return the owner id
	 */
	public String getOwnerId() {
		return this.ownerId;
	}

	/**
	 * Gets the resolution of the command
	 * 
	 * @return the resolution
	 */
	public Resolution getResolution() {
		return this.r;
	}

	/**
	 * Gets the note of the command
	 * 
	 * @return the note
	 */
	public String getNote() {
		return this.note;
	}

}
