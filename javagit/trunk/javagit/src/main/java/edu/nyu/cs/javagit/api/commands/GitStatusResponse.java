package edu.nyu.cs.javagit.api.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.nyu.cs.javagit.utilities.CheckUtilities;

/**
 * A response data object for &lt;git-status&gt; command
 */
public abstract class GitStatusResponse implements CommandResponse {

  /**
   * List of new files that will be added next time &lt;git-commit&gt; is executed.
   */
  protected final List<String> newFilesToCommit;
  /**
   * List of files that will be deleted next time &lt;git-commit&gt; is executed.
   */
  protected final List<String> deletedFilesToCommit;
  /**
   * List of files that are modified and will be committed to git repository next time
   * &lt;git-commit&gt; is executed.
   */
  protected final List<String> modifiedFilesToCommit;
  /**
   * List of files that are in git-repository and have been deleted locally.
   */
  protected final List<String> deletedFilesNotUpdated;
  /**
   * List of files that are in git-repository and have been modified locally but not yet added to
   * git by &lt;git-add&gt; command.
   */
  protected final List<String> modifiedFilesNotUpdated;
  /**
   * List of new files that have been created locally and have not been added to repository by
   * &lt;git-add&gt; command yet.
   */
  protected final List<String> untrackedFiles;
  /**
   * Name of the branch to which HEAD is pointing to.
   */
  protected String branch;
  /**
   * One line message that is generated by output of &lt;git-status&gt; command sometimes and it
   * does not start with a '#'.
   */
  protected String message;
  /**
   * Error message generated by &lt;git-status&gt; command
   */
  protected String error;

  public GitStatusResponse() {
    newFilesToCommit = new ArrayList<String>();
    deletedFilesToCommit = new ArrayList<String>();
    modifiedFilesToCommit = new ArrayList<String>();
    deletedFilesNotUpdated = new ArrayList<String>();
    modifiedFilesNotUpdated = new ArrayList<String>();
    untrackedFiles = new ArrayList<String>();
  }

  /**
   * Get the name of the file from newFilesToCommit list at a given index.
   * 
   * @param index
   *          Index in the list must be positive and less than the number of new files to commit.
   * @return the name of the file.
   */
  public String getFileFromNewFilesToCommit(int index) {
    CheckUtilities.checkIntIndexInListRange(newFilesToCommit, index);
    return newFilesToCommit.get(index);
  }

  /**
   * Get the name of the deleted file that will be committed next time git-commit is executed
   * currently located at the given index in the list.
   * 
   * @param index
   *          Index in the list must be positive and less than the number of new files to commit
   * @return the name of the file.
   */
  public String getFileFromDeletedFilesToCommit(int index) {
    CheckUtilities.checkIntIndexInListRange(deletedFilesToCommit, index);
    return deletedFilesToCommit.get(index);
  }

  /**
   * Get the name of the file that is modified and added to the repository by &lt;git-add&gt;
   * command and will be committed to repository next time &lt;git-commit&gt; is executed. This file
   * is at the given index in the list of the files.
   * 
   * @param index
   *          Index in the list of files must be positive and less than the number of new files to
   *          commit
   * @return the name of the file.
   */
  public String getFileFromModifiedFilesToCommit(int index) {
    CheckUtilities.checkIntIndexInListRange(modifiedFilesToCommit, index);
    return modifiedFilesToCommit.get(index);
  }

  /**
   * Returns the name of the deleted file that is removed locally but not yet removed from
   * repository.
   * 
   * @param index
   *          Index in the list must be positive and less than the number of new files to commit
   * @return the name of the file.
   */
  public String getFileFromDeletedFilesNotUpdated(int index) {
    CheckUtilities.checkIntIndexInListRange(deletedFilesNotUpdated, index);
    return deletedFilesNotUpdated.get(index);
  }

  /**
   * Returns the name of the file that is existing in the repository and has been locally modified.
   * This file is one of the files that has been locally modified and is located at given index in
   * the list.
   * 
   * @param index
   *          Index in the list must be positive and less than the number of new files to commit
   * @return the name of the file
   */
  public String getFileFromModifiedFilesNotUpdated(int index) {
    CheckUtilities.checkIntIndexInListRange(modifiedFilesNotUpdated, index);
    return modifiedFilesNotUpdated.get(index);
  }

  /**
   * Returns the name of the file at the specified index that has been created locally but has not
   * yet been added to the repository by &lt;git-add&gt;.
   * 
   * @param index
   *          Index in the list must be positive and less than the number of new files to commit
   * @return the name of the file
   */
  public String getFileFromUntrackedFiles(int index) {
    CheckUtilities.checkIntIndexInListRange(untrackedFiles, index);
    return untrackedFiles.get(index);
  }

  /**
   * Creates a copy of newFilesToCommit list and returns the iterator to this new list of files that
   * are new and to be committed next time &lt;git-commit&gt; is executed.
   * 
   * @return Iterator to the copy of the the original list.
   */
  public Iterator<String> getNewFilesToCommitIterarator() {
    return (new ArrayList<String>(newFilesToCommit)).iterator();
  }

  /**
   * Creates a copy of deletedFilesToCommit list and returns the iterator to this new list.
   * 
   * @return Iterator to the new list created.
   */
  public Iterator<String> getDeletedFilesToCommitIterator() {
    return deletedFilesToCommit.iterator();
  }

  /**
   * Creates a copy of modifiedFilesToComit list and returns the iterator to this new list.
   * 
   * @return Iterator to the new list created.
   */
  public Iterator<String> getModifiedFilesToCommitIterator() {
    return modifiedFilesToCommit.iterator();
  }

  /**
   * Creates a copy of deletedFilesNotUpdated list and returns the iterator to this new list.
   * 
   * @return Iterator to the new list created.
   */
  public Iterator<String> getDeletedFilesNotUpdatedIterator() {
    return deletedFilesNotUpdated.iterator();
  }

  /**
   * Creates a copy of modifiedFilesNotUpdated list and returns the iterator to this new list.
   * 
   * @return Iterator to the new list created.
   */
  public Iterator<String> getModifiedFilesNotUpdatedIterator() {
    return modifiedFilesNotUpdated.iterator();
  }

  /**
   * Creates a copy of untrackedFiles list and returns the iterator to this new list.
   * 
   * @return Iterator to the new list created.
   */
  public Iterator<String> getUntrackedFiles() {
    return untrackedFiles.iterator();
  }

  /**
   * Returns the size of newFilesToCommit list.
   * 
   * @return size of list.
   */
  public int getNewFilesToCommitSize() {
    return newFilesToCommit.size();
  }

  /**
   * Returns the size of deletedFilesToCommit list.
   * 
   * @return size of the list.
   */
  public int getDeletedFilesToCommitSize() {
    return deletedFilesToCommit.size();
  }

  /**
   * Returns the size of modifiedFilesToCommit list.
   * 
   * @return size of the list.
   */
  public int getModifiedFilesToCommitSize() {
    return modifiedFilesToCommit.size();
  }

  /**
   * Returns the size of deletedFilesNotUpdated list.
   * 
   * @return size of the list.
   */
  public int getDeletedFilesNotUpdatedSize() {
    return deletedFilesNotUpdated.size();
  }

  /**
   * Returns the size of modifiedFilesNotUpdated list.
   * 
   * @return size of the list.
   */
  public int getModifiedFilesNotUpdatedSize() {
    return modifiedFilesNotUpdated.size();
  }

  /**
   * Returns the size of untrackedFiles list.
   * 
   * @return size of the list.
   */
  public int getUntrackedFilesSize() {
    return untrackedFiles.size();
  }

  /**
   * Returns the name of the current branch in git-repository.
   * 
   * @return name of the branch
   */
  public String getBranch() {
    return branch;
  }

  /**
   * Returns a message that was generated as part of &lt;git-status&gt; output. Normally it's a
   * <code>String</code> that is not starting with a '#'.
   * 
   * @return message generally not starting with a '#'.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Returns the error message otherwise returns null;
   * 
   * @return error message
   */
  public String getError() {
    return error;
  }
}