package edu.nyu.cs.javagit.api.commands;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.nyu.cs.javagit.api.Ref;
import edu.nyu.cs.javagit.utilities.CheckUtilities;

/**
 * A response data object for &lt;git-status&gt; command
 */
public abstract class GitStatusResponse implements CommandResponse {

  /**
   * List of new files that will be added next time &lt;git-commit&gt; is executed.
   */
  protected final List<File> newFilesToCommit;
  /**
   * List of files that will be deleted next time &lt;git-commit&gt; is executed.
   */
  protected final List<File> deletedFilesToCommit;
  /**
   * List of files that are modified and will be committed to git repository next time
   * &lt;git-commit&gt; is executed.
   */
  protected final List<File> modifiedFilesToCommit;
  /**
   * List of files that are in git-repository and have been deleted locally.
   */
  protected final List<File> deletedFilesNotUpdated;
  /**
   * List of files that are in git-repository and have been modified locally but not yet added to
   * git by &lt;git-add&gt; command.
   */
  protected final List<File> modifiedFilesNotUpdated;
  /**
   * List of new files that have been created locally and have not been added to repository by
   * &lt;git-add&gt; command yet.
   */
  protected final List<File> untrackedFiles;
  /**
   * Name of the branch to which HEAD is pointing to.
   */
  protected Ref branch;
  /**
   * One line message that is generated by output of &lt;git-status&gt; command sometimes and it
   * does not start with a '#'.
   */
  protected String message;
  /**
   * Error messages generated by &lt;git-status&gt; command
   */
  protected List<ErrorDetails> errors;

  public GitStatusResponse() {
    newFilesToCommit = new ArrayList<File>();
    deletedFilesToCommit = new ArrayList<File>();
    modifiedFilesToCommit = new ArrayList<File>();
    deletedFilesNotUpdated = new ArrayList<File>();
    modifiedFilesNotUpdated = new ArrayList<File>();
    untrackedFiles = new ArrayList<File>();
    errors = new ArrayList<ErrorDetails>();
  }

  /**
   * Get the name of the file from newFilesToCommit list at a given index.
   * 
   * @param index
   *          Index in the list must be positive and less than the number of new files to commit.
   * @return the name of the file.
   */
  public File getFileFromNewFilesToCommit(int index) {
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
  public File getFileFromDeletedFilesToCommit(int index) {
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
  public File getFileFromModifiedFilesToCommit(int index) {
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
  public File getFileFromDeletedFilesNotUpdated(int index) {
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
  public File getFileFromModifiedFilesNotUpdated(int index) {
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
  public File getFileFromUntrackedFiles(int index) {
    CheckUtilities.checkIntIndexInListRange(untrackedFiles, index);
    return untrackedFiles.get(index);
  }

  /**
   * Creates a copy of newFilesToCommit list and returns the iterator to this new list of files that
   * are new and to be committed next time &lt;git-commit&gt; is executed.
   * 
   * @return Iterator to the copy of the the original list.
   */
  public Iterator<File> getNewFilesToCommitIterarator() {
    return (new ArrayList<File>(newFilesToCommit)).iterator();
  }

  /**
   * Creates a copy of deletedFilesToCommit list and returns the iterator to this new list.
   * 
   * @return Iterator to the new list created.
   */
  public Iterator<File> getDeletedFilesToCommitIterator() {
    return (new ArrayList<File>(deletedFilesToCommit).iterator());
  }

  /**
   * Creates a copy of modifiedFilesToComit list and returns the iterator to this new list.
   * 
   * @return Iterator to the new list created.
   */
  public Iterator<File> getModifiedFilesToCommitIterator() {
    return (new ArrayList<File>(modifiedFilesToCommit).iterator());
  }

  /**
   * Creates a copy of deletedFilesNotUpdated list and returns the iterator to this new list.
   * 
   * @return Iterator to the new list created.
   */
  public Iterator<File> getDeletedFilesNotUpdatedIterator() {
    return (new ArrayList<File>(deletedFilesNotUpdated).iterator());
  }

  /**
   * Creates a copy of modifiedFilesNotUpdated list and returns the iterator to this new list.
   * 
   * @return Iterator to the new list created.
   */
  public Iterator<File> getModifiedFilesNotUpdatedIterator() {
    return (new ArrayList<File>(modifiedFilesNotUpdated).iterator());
  }

  /**
   * Creates a copy of untrackedFiles list and returns the iterator to this new list.
   * 
   * @return Iterator to the new list created.
   */
  public Iterator<File> getUntrackedFiles() {
    return (new ArrayList<File>(untrackedFiles).iterator());
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
  public Ref getBranch() {
    return branch;
  }

  /**
   * Returns a message that is generally produced as a side comment by &lt;git-status&gt;
   * 
   * @return Message.
   */
  public String getMessage() {
    return message;
  }

  /**
   * Returns the error message otherwise returns null;
   * 
   * @return error message
   */
  public String getError(int index) {
    if (index < errors.size()) {
      ErrorDetails errorDetails = errors.get(index);
      return errorDetails.lineNumber + ". " + errorDetails.error;
    }
    return null;
  }

  /**
   * Gets the state of the <code>GitStatusResposne</code> object returned by &lt;git-status&gt;
   * command.
   * 
   * @return true if the output processed generates any lines with error.
   */
  public boolean errorState() {
    return (errors.size() > 0);
  }

  public class ErrorDetails {
    final int lineNumber;
    final String error;

    public ErrorDetails(int lineNumber, String error) {
      this.lineNumber = lineNumber;
      this.error = error;
    }
  }
}