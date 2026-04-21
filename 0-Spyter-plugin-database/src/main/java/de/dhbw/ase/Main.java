package de.dhbw.ase;

import de.dhbw.ase.constants.CharacterDomain;
import de.dhbw.ase.constants.CharacterDomainImpl;
import de.dhbw.ase.tui.ConfigureExerciseTUI;
import de.dhbw.ase.tui.RunningExerciseTUI;
import de.dhbw.ase.tui.ShowResultsTUI;

import java.io.IOException;

public class Main {
  public static void main(String[] args) {
    CharacterDomain characterDomain = new CharacterDomainImpl();
    ConfigUIHandle configUi = new ConfigureExerciseTUI(new SimpleTerminalIO(), characterDomain, new SypterFileSystemImpl(' '));
      try {
          RunningExerciseUI runUi = new RunningExerciseTUI(characterDomain);
          ResultUI resultUI = new ShowResultsTUI(new SimpleTerminalIO());
          MainWorkflow mw = new MainWorkflow(configUi, runUi, resultUI);
          mw.runSingleInstance();
      } catch (IOException e) {
          throw new RuntimeException(e);
      }
  }
}



