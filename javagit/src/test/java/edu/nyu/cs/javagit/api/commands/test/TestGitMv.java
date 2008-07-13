package edu.nyu.cs.javagit.api.commands.test;

import java.io.File;
import java.io.IOException;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;

import edu.nyu.cs.javagit.api.JavaGitException;

import edu.nyu.cs.javagit.api.commands.GitMv;
import edu.nyu.cs.javagit.test.utilities.FileUtilities;
import edu.nyu.cs.javagit.test.utilities.HelperGitCommands;
import edu.nyu.cs.javagit.utilities.ExceptionMessageMap;

public class TestGitMv extends TestCase {
  //Repository path. Need not be valid.
  private File repoPath;
  
  @Before
  protected void setUp() {
  }
  
  @After
  protected void tearDown() throws JavaGitException {
    // delete repo directory.
    FileUtilities.removeDirectoryRecursivelyAndForcefully(repoPath);
  }

  //check if exceptions are thrown below for ivalid arguments
  public void testGitMvInvalidInput() throws IOException, JavaGitException {
    repoPath = FileUtilities.createTempDirectory("GitMvTestRepo");
    
    //source file.
    File source = new File("t2.pl");

    //destination file.
    File destination = new File("t3.pl");

    GitMv gitMv = new GitMv();
    try {
      gitMv.mv(null, source, destination);
    } catch (Exception e) {
      assertEquals("Should have null pointer exception or illegal argument exception",
          ExceptionMessageMap.getMessage("000003") + "  { variableName=[repository path] }", e
              .getMessage());
    }
    try {
      gitMv.mv(repoPath, null, destination);
    } catch (Exception e) {
      assertEquals("Should have null pointer exception or illegal argument exception",
          ExceptionMessageMap.getMessage("000003") + "  { variableName=[source] }", e.getMessage());
    }
    try {
      gitMv.mv(repoPath, source, null);
    } catch (Exception e) {
      assertEquals("Should have null pointer exception or illegal argument exception",
          ExceptionMessageMap.getMessage("000003") + "  { variableName=[destination] }", e
              .getMessage());
    }

    try {
      gitMv.mv(repoPath, null, source, destination);
    } catch (Exception e) {
      assertEquals("Should have null pointer exception or illegal argument exception",
          ExceptionMessageMap.getMessage("000003") + "  { variableName=[options] }", e.getMessage());
    }
    
    try {
      gitMv.mv(repoPath, source, destination);
    } catch (Exception e) {
      assertEquals("Should have null pointer exception or illegal argument exception",
          ExceptionMessageMap.getMessage("424000")+ "fatal: Not a git repository",
              e.getMessage());
    }
    
    HelperGitCommands.initRepo(repoPath);
    try {
      gitMv.mv(repoPath, source, destination);
    } catch (Exception e) {
      assertEquals("Should have null pointer exception or illegal argument exception",
          ExceptionMessageMap.getMessage("424000")+ "error: '"+ source.getAbsolutePath() + 
          "' is outside repository", e.getMessage());
    }
    
    source = FileUtilities.createFile(repoPath, "test.pl", "Testfile");
    try {
      gitMv.mv(repoPath, source, destination);
    } catch (Exception e) {
      assertEquals("Should have null pointer exception or illegal argument exception",
          ExceptionMessageMap.getMessage("424000")+ "error: '"+ destination.getAbsolutePath() + 
          "' is outside repository", e.getMessage());
    }
  }
}
