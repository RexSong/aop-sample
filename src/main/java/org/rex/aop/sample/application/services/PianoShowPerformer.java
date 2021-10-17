package org.rex.aop.sample.application.services;

import org.springframework.stereotype.Component;

@Component
public class PianoShowPerformer implements Performer {
  @Override
  public void perform() throws Exception {
    //throw new Exception("dlakd");
    System.out.println("performed");
  }
}
