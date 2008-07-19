package edu.nyu.cs.javagit.client;

import java.io.File;
import java.io.IOException;
import java.util.List;

import edu.nyu.cs.javagit.api.JavaGitException;
import edu.nyu.cs.javagit.api.commands.GitAddOptions;
import edu.nyu.cs.javagit.api.commands.GitAddResponse;

/**
 * An interface to represent the git-add command.
 */
public interface IGitAdd {

  /**
   * Adds list of files to the index.
   * 
   * @param repositoryPath File path pointing to the root of the repository
   * @param options Object containing all the options that need to be passed to &lt;git-add&gt; command.
   * @param paths <code>List</code> of file paths that are going to be added to the index.
   * @return
   * @throws JavaGitException
   *              Thrown when there is an error while running the add command.
   * @throws IOException
   *              There are many reasons for which an <code>IOException</code> may be thrown.
   *              Examples include:
   *              <ul>
   *              <li>a directory doesn't exist</li>
   *              <li>access to a file is denied</li>
   *              <li>a command is not found on the PATH</li>
   *              </ul>
   */
  public GitAddResponse add(File repositoryPath, GitAddOptions options, List<File> paths) 
    throws JavaGitException, IOException;

  /**
   * Dry run for adding a list of files to the index.
   * 
   * @param repositoryPath File path pointing to the root of the repository
   * @param paths <code>List</code> of file paths that are going to be added to the index.
   * @return
   * @throws JavaGitException
   *              Thrown when there is an error while running the add command.
   * @throws IOException
   *              There are many reasons for which an <code>IOException</code> may be thrown.
   *              Examples include:
   *              <ul>
   *              <li>a directory doesn't exist</li>
   *              <li>access to a file is denied</li>
   *              <li>a command is not found on the PATH</li>
   *              </ul>
   */
  public GitAddResponse addDryRun(File repositoryPath, List<File> paths) throws JavaGitException, IOException;

  /**
   * Adds list of files to the index in verbose mode.
   * 
   * @param repositoryPath File path pointing to the root of the repository
   * @param paths <code>List</code> of file paths that are going to be added to the index.
   * @return
   * @throws JavaGitException
   *              Thrown when there is an error while running the add command.
   * @throws IOException
   *              There are many reasons for which an <code>IOException</code> may be thrown.
   *              Examples include:
   *              <ul>
   *              <li>a directory doesn't exist</li>
   *              <li>access to a file is denied</li>
   *              <li>a command is not found on the PATH</li>
   *              </ul>
   */
  public GitAddResponse addVerbose(File repositoryPath, List<File> paths) 
    throws JavaGitException, IOException; 


  /**
   * Adds list of files to the index with Force option set.
   * 
   * @param repositoryPath File path pointing to the root of the repository
   * @param paths <code>List</code> of file paths that are going to be added to the index.
   * @return
   * @throws JavaGitException
   *              Thrown when there is an error while running the add command.
   * @throws IOException
   *              There are many reasons for which an <code>IOException</code> may be thrown.
   *              Examples include:
   *              <ul>
   *              <li>a directory doesn't exist</li>
   *              <li>access to a file is denied</li>
   *              <li>a command is not found on the PATH</li>
   *              </ul>
   */
  public GitAddResponse addWithForce(File repositoryPath, List<File> paths) 
    throws JavaGitException, IOException; 
  
}
