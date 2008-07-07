package edu.nyu.cs.javagit.api;

import java.util.List;

/**
 * <code>Commit</code> represents information about a commit to a git repository.
 * 
 * TODO: Build out the class
 */
public final class Commit {

  // The name of the commit.
  private CommitName commitName;

  // commit comment
  private String comment;

  /**
   * Get a <code>Commit</code> instance for the specified HEAD commit offset.
   * 
   * @param commitOffset
   *          See {@link edu.nyu.cs.javagit.api.CommitName} for information on acceptable values of
   *          <code>commitOffset</code>.
   * @return The <code>Commit</code>.
   */
  public static Commit getHeadCommit() {
    return new Commit(CommitName.createHeadCommitName(0));
  }

  /**
   * Get a <code>Commit</code> instance for the specified HEAD commit offset.
   * 
   * @param commitOffset
   *          See {@link edu.nyu.cs.javagit.api.CommitName} for information on acceptable values of
   *          <code>commitOffset</code>.
   * @return The <code>Commit</code>.
   */
  public static Commit getHeadCommit(int commitOffset) {
    return new Commit(CommitName.createHeadCommitName(commitOffset));
  }

  /**
   * Get a <code>Commit</code> instance for the specified SHA1 name.
   * 
   * @param sha1Name
   *          See {@link edu.nyu.cs.javagit.api.CommitName} for information on acceptable values of
   *          <code>sha1Name</code>.
   * @return The <code>Commit</code>.
   */
  public static Commit getSha1Commit(String sha1Name) {
    return new Commit(CommitName.createSha1CommitName(sha1Name));
  }

  /**
   * Get a <code>Commit</code> instance for the specified commit name.
   * 
   * @param commitName
   *          The <code>CommitName</code> for this <code>Commit</code>.
   * @return The <code>Commit</code>.
   */
  public static Commit getCommit(CommitName commitName) {
    return new Commit(commitName);
  }

  /**
   * The constructor.
   * 
   * @param commitName
   *          The name of this commit.
   */
  private Commit(CommitName commitName) {
    this.commitName = commitName;
  }

  /**
   * Gets the name of this commit.
   * 
   * @return The name of this commit.
   */
  public CommitName getCommitName() {
    return commitName;
  }

  /**
   * Gets the author's comment
   * 
   * @return The author's comment.
   */
  public String getComment() {
    return comment;
  }

  /**
   * Returns differences for this commit
   * 
   * 
   * @return The list of differences (one per each git object).
   */
  public List<Diff> diff() {
    // GitDiff.diff();
    return null;
  }

  /**
   * Diffs this commit with another commit
   * 
   * @param otherCommit
   *          The commit to compare current commit to
   * 
   * @return The list of differences (one per each git object).
   */
  public List<Diff> diff(Commit otherCommit) {
    // GitDiff.diff();
    return null;
  }

}