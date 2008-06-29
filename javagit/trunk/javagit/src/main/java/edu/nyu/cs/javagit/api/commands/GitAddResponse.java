package edu.nyu.cs.javagit.api.commands;

import java.util.ArrayList;
import java.util.List;

/**
 * Response data object obtained from git-add command. It contains a list of
 * file-action pair that contains the list of files that are added by the
 * git-add command.
 * 
 * The output can also be generated by "-n" option of git-add command ( dry run ).
 * So we have two Actions for git-add command - add - for adding a file to the
 * repository dry run - for just testing the output of the command. Dry run info
 * need to be passed back so that the receiver of this object knows that the
 * command did not actually run and was just a dry run. The git-add commands
 * that return no output will return this object with filesAdded list as
 * empty-list.
 * 
 */
public class GitAddResponse implements CommandResponse {

  public enum Action {
    ADD,
    DRY_RUN_ADD;
  }
	/**
	 * List of files produced by the git-add command line output.
	 */
	private List<String> filePathsList;

	/**
	 * List of options used by the git-add command
	 */
	private List<String> options;

	private String repositoryPath;
	
	/**
	 * Initially set to true as git-add many times does not generate any output
	 * at all. If output is generated then it needs to be set to false. This can
	 * be used as a tester whether any output was generated or not.
	 */
	private boolean noOutput;

	private boolean error;

	private String errorString;

	private Action action;

	/**
	 * Constructor
	 */
	public GitAddResponse() {
		filePathsList = new ArrayList<String>();
		options = new ArrayList<String>();
		noOutput = true;
		action = Action.ADD;
		error = false;
	}

	/**
	 * Adds a filePath and action to the list.
	 * 
	 * @param action
	 * @param filePath
	 */
	public void add(String filePath) {
		filePathsList.add(filePath);
	}

	/**
	 * Returns the size of the list. This getter method is used by the consumer
	 * of this object.
	 * 
	 * @return size of list.
	 */
	public int getFileListSize() {
		return filePathsList.size();
	}

	public String get(int index) throws IndexOutOfBoundsException {
		if (index < filePathsList.size() && index >= 0) {
			return filePathsList.get(index);
		}
		throw new IndexOutOfBoundsException(index + " is out of range");
	}

	public List<String> getOptions() {
		return options;
	}

	/**
	 * Setting all options in one go in the form of a list
	 * @param options if set as part of given list.
	 */
	public void setOptions(List<String> options) {
		this.options = options;
	}
	
	/**
	 * If options are set one by one then this method sets the option
	 * one at a time. To set multiple options, this method need to be
	 * executed multiple times.
	 * @param option GitAdd command option
	 */
	public void addOption(String option){
	  if ( options == null ){
	    options = new ArrayList<String>();
	  }
	  options.add(option);
	}
	
	/**
	 * 
	 * @param index option 
	 * @return Returns empty string if there are no options provided by
	 * GitAdd command or if the index is less than the size of the options
	 * list.
	 */
	public String getOption(int index){
	  if ( options != null && index < options.size()) {
	    return options.get(index);
	  }
	  return "";
	}

	public boolean noOutput() {
		return noOutput;
	}

	public void setNoOutput(boolean noOutput) {
		this.noOutput = noOutput;
	}

	public Action getAction() {
		return action;
	}

	public void setAction(Action action) {
		this.action = action;
	}

	public void setError(String errorString) {
		this.error = true;
		this.errorString = errorString;
	}
	
	public boolean inErrorState() {
	  return error;
	}
	
	public String getError() {
	  return errorString;
	}
	
	public String getRepositoryPath() {
	  return repositoryPath;
	}
	
	public void setRepositoryPath(String repositoryPath) {
	  this.repositoryPath = repositoryPath;
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		if (noOutput) {
			sb.append("No Output: " + noOutput + "\n");
		} else {
			if (error) {
				sb.append("ERROR: " + errorString + "\n");
			}

			if (action == Action.DRY_RUN_ADD) {
				sb.append("Mode: Dry Run\n");
			}

			for (String option : options) {
				sb.append("Option: " + option + "\n");
			}

			sb.append("\n");
			for (String filePath : filePathsList) {
				sb.append("File add: " + filePath + "\n");
			}
		}
		return sb.toString();
	}
}
