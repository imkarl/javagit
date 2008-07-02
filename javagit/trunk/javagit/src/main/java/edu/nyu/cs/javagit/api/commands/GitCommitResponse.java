package edu.nyu.cs.javagit.api.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import edu.nyu.cs.javagit.api.commands.CommandResponse;
import edu.nyu.cs.javagit.utilities.CheckUtilities;

/**
 * A response data object for the git-commit command.
 */
abstract public class GitCommitResponse implements CommandResponse {

  // TODO (jhl388): Add methods to retrieve added, copied, deleted and renamed file information.

  // The short hash name for the commit.
  protected String commitShortHashName = "";

  // The short comment for the commit.
  protected String commitShortComment = "";

  // Indicates how many files have changed in a commit.
  protected int filesChanged = 0;

  // Indicates how many lines were inserted in a commit.
  protected int linesInserted = 0;

  // Indicates how many lines were deleted in a commit.
  protected int linesDeleted = 0;

  /**
   * The list of the files added to the repository in this commit. The file name is the relative
   * path to the file from the root of the repository tree.
   */
  protected List<AddedOrDeletedFile> addedFiles;

  /**
   * A list of new files that were copied from existing files already tracked in the repository. The
   * file names are relative paths to the files from the root of the repository tree.
   */
  protected List<CopiedOrMovedFile> copiedFiles;

  /**
   * A list of the files deleted form the repository in this commit. The file name is the relative
   * path to the file from the root of the repository tree.
   */
  protected List<AddedOrDeletedFile> deletedFiles;

  /**
   * A list of files that were moved/renamed in this commit. The file name is the relative path to
   * the file from the root of the repository tree.
   */
  protected List<CopiedOrMovedFile> renamedFiles;

  /**
   * Constructor.
   * 
   * @param shortHashName
   *          The short hash name
   * @param shortComment
   */
  public GitCommitResponse(String shortHashName, String shortComment) {
    this.commitShortHashName = shortHashName;
    this.commitShortComment = shortComment;

    addedFiles = new ArrayList<AddedOrDeletedFile>();
    copiedFiles = new ArrayList<CopiedOrMovedFile>();
    deletedFiles = new ArrayList<AddedOrDeletedFile>();
    renamedFiles = new ArrayList<CopiedOrMovedFile>();
  }

  public boolean equals(Object o) {
    if (!(o instanceof GitCommitResponse)) {
      return false;
    }

    GitCommitResponse g = (GitCommitResponse) o;

    if (!CheckUtilities.checkObjectsEqual(getCommitShortHashName(), g.getCommitShortHashName())) {
      return false;
    }

    if (!CheckUtilities.checkObjectsEqual(getCommitShortComment(), g.getCommitShortComment())) {
      return false;
    }

    if (getFilesChanged() != g.getFilesChanged()) {
      return false;
    }

    if (getLinesInserted() != g.getLinesInserted()) {
      return false;
    }

    if (getLinesDeleted() != g.getLinesDeleted()) {
      return false;
    }

    if (!CheckUtilities.checkUnorderedListsEqual(addedFiles, g.addedFiles)) {
      return false;
    }

    if (!CheckUtilities.checkUnorderedListsEqual(copiedFiles, g.copiedFiles)) {
      return false;
    }

    if (!CheckUtilities.checkUnorderedListsEqual(deletedFiles, g.deletedFiles)) {
      return false;
    }

    if (!CheckUtilities.checkUnorderedListsEqual(renamedFiles, g.renamedFiles)) {
      return false;
    }

    return true;
  }

  /**
   * Get an <code>Iterator</code> with which to iterate over the added files.
   * 
   * @return An <code>Iterator</code> with which to iterate over the added files.
   */
  public Iterator<AddedOrDeletedFile> getAddedFilesIterator() {
    return addedFiles.iterator();
  }

  /**
   * Gets the short comment (description) for the commit. It is the first line of the commit
   * message.
   * 
   * @return The short comment for the commit.
   */
  public String getCommitShortComment() {
    return commitShortComment;
  }

  /**
   * Gets the short hash name for the commit. This is the first seven characters of the SHA1 hash
   * that represents the commit.
   * 
   * @return The short hash name for the commit.
   */
  public String getCommitShortHashName() {
    return commitShortHashName;
  }

  /**
   * Get an <code>Iterator</code> with which to iterate over the copied files.
   * 
   * @return An <code>Iterator</code> with which to iterate over the copied files.
   */
  public Iterator<CopiedOrMovedFile> getCopiedFilesIterator() {
    return copiedFiles.iterator();
  }

  /**
   * Get an <code>Iterator</code> with which to iterate over the deleted files.
   * 
   * @return An <code>Iterator</code> with which to iterate over the deleted files.
   */
  public Iterator<AddedOrDeletedFile> getDeletedFilesIterator() {
    return deletedFiles.iterator();
  }

  /**
   * Gets the number of files changed in the commit.
   * 
   * @return The number of files changed in the commit.
   */
  public int getFilesChanged() {
    return filesChanged;
  }

  /**
   * Gets the number of lines inserted in the commit.
   * 
   * @return The number of lines inserted in the commit.
   */
  public int getLinesInserted() {
    return linesInserted;
  }

  /**
   * Gets the number of lines deleted in the commit.
   * 
   * @return The number of lines deleted in the commit.
   */
  public int getLinesDeleted() {
    return linesDeleted;
  }

  /**
   * Get an <code>Iterator</code> with which to iterate over the renamed files.
   * 
   * @return An <code>Iterator</code> with which to iterate over the renamed files.
   */
  public Iterator<CopiedOrMovedFile> getRenamedFilesIterator() {
    return renamedFiles.iterator();
  }

  public int hashCode() {
    /*
     * Only using the short hash name because it is already reasonably unique and if any two
     * GitCommitResponse objects have the same short hash code, there is high probability that they
     * will be equal. -- jhl388 2008.06.22
     */
    return commitShortHashName.hashCode();
  }

  /**
   * Represents a file added to or deleted from the repository for a given commit.
   */
  public class AddedOrDeletedFile {

    // TODO (jhl388): Add a method to get a javagit File instance for this file.

    // The path to the file.
    private String pathTofile;

    // The mode the file was added/deleted with.
    private String mode;

    /**
     * Constructor.
     * 
     * @param pathToFile
     *          The path to the file.
     * @param mode
     *          The mode the file was added/deleted with.
     */
    public AddedOrDeletedFile(String pathToFile, String mode) {
      this.pathTofile = pathToFile;
      this.mode = mode;
    }

    public boolean equals(Object o) {
      if (!(o instanceof AddedOrDeletedFile)) {
        return false;
      }

      AddedOrDeletedFile a = (AddedOrDeletedFile) o;

      if (!CheckUtilities.checkObjectsEqual(getPathTofile(), a.getPathTofile())) {
        return false;
      }

      if (!CheckUtilities.checkObjectsEqual(getMode(), a.getMode())) {
        return false;
      }

      return true;
    }

    /**
     * Gets the mode of the added/deleted file.
     * 
     * @return The mode of the added/deleted file.
     */
    public String getMode() {
      return mode;
    }

    /**
     * Gets the path to the file.
     * 
     * @return The path to the file.
     */
    public String getPathTofile() {
      return pathTofile;
    }

    public int hashCode() {
      return pathTofile.hashCode() + mode.hashCode();
    }
  }

  /**
   * Represents a file that was copied from an existing file already tracked in the repository or a
   * tracked file that was moved from one name/place to another.
   */
  public class CopiedOrMovedFile {

    // TODO (jhl388): Add methods to get a javagit File instance for these files.

    // The path to the file that is the source of the copied/moved file.
    private String sourceFilePath;

    // The path to the new file/location.
    private String destinationFilePath;

    // The percentage. (not sure how to read this yet, -- jhl388 2008.06.15)
    private int percentage;

    /**
     * Constructor.
     * 
     * @param sourceFilePath
     *          The path to the source file.
     * @param destinationFilePath
     *          The path to the destination file.
     * @param percentage
     *          The percentage.
     */
    public CopiedOrMovedFile(String sourceFilePath, String destinationFilePath, int percentage) {
      this.sourceFilePath = sourceFilePath;
      this.destinationFilePath = destinationFilePath;
      this.percentage = percentage;
    }

    public boolean equals(Object o) {
      if (!(o instanceof CopiedOrMovedFile)) {
        return false;
      }

      CopiedOrMovedFile c = (CopiedOrMovedFile) o;

      if (!CheckUtilities.checkObjectsEqual(getSourceFilePath(), c.getSourceFilePath())) {
        return false;
      }

      if (!CheckUtilities.checkObjectsEqual(getDestinationFilePath(), c.getDestinationFilePath())) {
        return false;
      }

      if (getPercentage() != c.getPercentage()) {
        return false;
      }

      return true;
    }

    /**
     * Gets the path to the destination file.
     * 
     * @return The path to the destination file.
     */
    public String getDestinationFilePath() {
      return destinationFilePath;
    }

    /**
     * Gets the percentage.
     * 
     * @return The percentage.
     */
    public int getPercentage() {
      return percentage;
    }

    /**
     * Gets the path to the source file.
     * 
     * @return The path to the source file.
     */
    public String getSourceFilePath() {
      return sourceFilePath;
    }

    public int hashCode() {
      return sourceFilePath.hashCode() + destinationFilePath.hashCode() + percentage;
    }
  }

}
