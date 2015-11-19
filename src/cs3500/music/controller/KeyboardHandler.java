package cs3500.music.controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.*;

/**
 * Created by AviSion on 11/19/2015.
 */
public class KeyboardHandler extends JFrame implements KeyListener {
  Map<Integer, Runnable> typed;
  Map<Integer, Runnable> pressed;
  Map<Integer, Runnable> released;

  KeyboardHandler(){
    this.typed = new TreeMap<Integer, Runnable>();
    this.pressed = new TreeMap<Integer, Runnable>();
    this.released = new TreeMap<Integer, Runnable>();
  }
  @Override
  public void keyTyped(KeyEvent e) {
    if(typed.containsKey(e.getKeyCode())){
      typed.get(e.getKeyCode()).run();
    }
  }

  @Override
  public void keyPressed(KeyEvent e) {
    if(pressed.containsKey(e.getKeyCode())){
      pressed.get(e.getKeyCode()).run();
    }

  }

  @Override
  public void keyReleased(KeyEvent e) {
    if(released.containsKey(e.getKeyCode())){
      released.get(e.getKeyCode()).run();
    }

  }

  //adds to maps
  protected void addAction(int x, String type){

  }
}
