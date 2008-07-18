package edu.nyu.cs.javagit.client;

import edu.nyu.cs.javagit.api.Ref;
import edu.nyu.cs.javagit.api.commands.GitBranchResponse;

/**
 * Implementation of a <code>GitBranchResponse</code>. This class adds functionality to set
 * values in a <code>GitBranchResponse</code>.
 */
public class GitBranchResponseImpl extends GitBranchResponse {
  /**
   * Constructor.
   */
  public GitBranchResponseImpl() {
    super();
  }

  /**
   * Add the branch displayed by git-branch command into the list of branches.
   * 
   * @return true after the file gets added.
   */
  public boolean addIntoBranchList(Ref branchName) {
    return branchList.add(branchName);
  }

  /**
   * Add the record displayed by git-branch command with -v option into the list of records.
   * 
   * @return True after the record gets added.
   */
  public boolean addIntoListOfBranchRecord(BranchRecord record) {
    return listOfBranchRecord.add(record);
  }

  /**
   * Sets a message about the git-branch operation that was run.
   *
   * @param message
   *          A message about the git-branch operation that was run.
   */
  public void addMessages(String message) {
    messages.append(message);
  }

  /**
   * Sets the current branch from the list of branches displayed by git-branch operation.
   * 
   * @param currentBranch
   *          The current branch from the list of branches displayed by git-branch operation.
   */
  public void setCurrentBranch(Ref currentBranch) {
    this.currentBranch = currentBranch;
  }

  /**
   * Sets the type of the response.
   * 
   * @param responseType
   *          The responseType to set to one of the three types.
   */
  public void setResponseType(responseType responseType) {
    this.responseType = responseType;
  }
}
