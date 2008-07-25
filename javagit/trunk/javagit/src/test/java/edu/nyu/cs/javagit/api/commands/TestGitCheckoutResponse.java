/*
 * ====================================================================
 * Copyright (c) 2008 JavaGit Project.  All rights reserved.
 *
 * This software is licensed using the GNU LGPL v2.1 license.  A copy
 * of the license is included with the distribution of this source
 * code in the LICENSE.txt file.  The text of the license can also
 * be obtained at:
 *
 *   http://www.gnu.org/licenses/old-licenses/lgpl-2.1.html
 *
 * For more information on the JavaGit project, see:
 *
 *   http://www.javagit.com
 * ====================================================================
 */
package edu.nyu.cs.javagit.api.commands;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import edu.nyu.cs.javagit.api.commands.GitCheckoutResponse;
import edu.nyu.cs.javagit.client.cli.CliGitCheckout;
import edu.nyu.cs.javagit.client.cli.CliGitCheckout.GitCheckoutParser;

public class TestGitCheckoutResponse extends TestCase {

  GitCheckoutParser parser;

  @Before
  public void setUp() throws Exception {
    CliGitCheckout gitCheckout = new CliGitCheckout();
    parser = gitCheckout.new GitCheckoutParser();
  }

  /**
   * Test for trying to checkout a non-existent branch from the repository. Here the error flag
   * should be set and the branch should be null as the given branch does not exist.
   */
  @Test
  public void testGitCheckoutResponseForNonExistingBranch() {
    parser.parseLine("error: pathspec 'foo03' did not match any file(s) known to git.");
    GitCheckoutResponse response = parser.getResponse();
    assertEquals("Error for non-existent branch",
        "1. error: pathspec 'foo03' did not match any file(s) known to git.", response.getError(0));
    assertEquals("branch foo03 does not exist", null, response.getBranch());
  }

  /**
   * Test for parsing fatal error generated while trying to create a new branch based on a
   * non-existent base-branch.
   */
  @Test
  public void testGitCheckoutResponseForCreatingBranchOnNonExistentBranch() {
    parser
        .parseLine("fatal: git checkout: updating paths is incompatible with switching branches/forcing");
    parser.parseLine("Did you intend to checkout 'foo04' which can not be resolved as commit?");
    GitCheckoutResponse response = parser.getResponse();
    assertEquals("Error while creating a new branch based on non-existent branch",
        "1. fatal: git checkout: updating paths is incompatible with switching branches/forcing",
        response.getError(0));
    assertEquals("New branch does not exist", null, response.getNewBranch());
  }

  /**
   * Test for parsing the output line that contains the output with "Switched to branch" and the
   * name of the branch in double quotes. The output indicates that repository has been switched to
   * an already existing another branch.
   */
  @Test
  public void testGitCheckoutSwitchToBranch() {
    parser.parseLine("Switched to branch \"foo01\"");
    GitCheckoutResponse response = parser.getResponse();
    assertEquals("Switching to branch foo01", "\"foo01\"", response.getBranch().getName());
  }

  /**
   * Test for parsing the output line that contains the output with "Switched to a new branch". The
   * output indicates that the new branch with the new name within quotes has been created and
   * switched to.
   */
  @Test
  public void testGitCheckoutResponseSwitchToNewBranch() {
    parser.parseLine("Switched to a new branch \"foo02\"");
    GitCheckoutResponse response = parser.getResponse();
    assertEquals("Switching to New branch foo02", "\"foo02\"", response.getNewBranch().getName());
  }

  /**
   * Test for checking the list of files that are added, modified or deleted but have not been
   * committed to the branch in repository.
   */
  @Test
  public void testGitCheckoutResponseAddedModifiedDeletedFiles() {
    parser.parseLine("M foobar01");
    parser.parseLine("M foobar05");
    parser.parseLine("M foobar06");
    parser.parseLine("A foobar02");
    parser.parseLine("A foobar07");
    parser.parseLine("D foobar03");
    GitCheckoutResponse response = parser.getResponse();
    assertEquals("No of files in addedFilesList", 2, response.getNumberOfAddedFiles());
    assertEquals("No of files in modifiedFileslist", 3, response.getNumberOfModifiedFiles());
    assertEquals("No of files in deletedFileslist", 1, response.getNumberOfDeletedFiles());
    assertEquals("First location - modifiedFileslist", "foobar01", response.getModifiedFile(0)
        .getName());
    assertEquals("First location - deletedFileslist", "foobar03", response.getDeletedFile(0)
        .getName());
    assertEquals("First location - addedFilesList", "foobar02", response.getAddedFile(0).getName());
  }

  @After
  public void tearDown() throws Exception {
  }

}
