

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author JOBAYER HOSSAIN
 *
 * 
 * 
 */

import javax.swing.*;


import java.awt.event.*;

import java.awt.*;


import javax.swing.JOptionPane;


import java.awt.event.ActionEvent;

import java.awt.Color;

import javax.swing.UIManager;

import javax.swing.UIManager.LookAndFeelInfo;

import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JFrame;

import javax.swing.JOptionPane;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import javax.swing.Timer;

import javax.swing.*;

import java.awt.*;

import java.awt.event.*;

import java.util.*;


public class TicTacToeMain extends JFrame implements ActionListener {

 
    private JButton [][]buttons = new JButton[3][3];
 
    private JButton playButton = new JButton("Play");
 
 
    private JButton bb = new JButton("Back");
 
    private JButton ex = new JButton("Exit");
 
 
    private JLabel statusLabel = new JLabel("");
 
    private TicTacToeAI game = null;
 
    private int human = 0;
 
    
    private int computer = 0;
 
    private boolean isPlay = false;
 
    private String []chars=new String[]{"","X","O"};

 
    private void setStatus(String s)
    
    {
  
        statusLabel.setText(s);
 
    }

 
    private void setButtonsEnabled(boolean enabled)
    {
  
        for(int i=0;i<3;i++)
        
        {
  
            for(int j=0;j<3;j++) 
            {
     
                buttons[i][j].setEnabled(enabled);
            
    if(enabled)
        
    { 
        buttons[i][j].setText(" ");
    
    
    }
   
            }
 
        }
    }

 public TicTacToeMain()

 {


     setTitle("Tic Tac Toe");
  
     setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  
     setResizable(false);

  
     JPanel centerPanel = new JPanel(new GridLayout(3,3));
  
     Font font = new Font("Arial",Font.BOLD, 96);
  
     for(int i=0;i<3;i++)
     
     {
   
         for(int j=0;j<3;j++)
             
         {
    
             buttons[i][j] = new JButton(" ");
   
             buttons[i][j].setFont(font);
   
             buttons[i][j].addActionListener(this);
   
             buttons[i][j].setFocusable(false);
   
             centerPanel.add(buttons[i][j]);
  
         }
     
     }

         
  playButton.addActionListener(this);
  

  JPanel northPanel = new JPanel();
  
  
  northPanel.add(statusLabel);
  

  JPanel southPanel = new JPanel();
  
  southPanel.add(playButton);

  southPanel.add(bb);
  
  southPanel.add(ex);
  
   bb.addActionListener(new ActionListener()
   
   { 
  
       public void actionPerformed(ActionEvent e) 
  
  { 
    
      dispose();
      
    new choosemode().setVisible(true);
    
  
  }
  
  
   }
   
   );
   
   
    ex.addActionListener(new ActionListener() 
    
    { 
  
        public void actionPerformed(ActionEvent e) { 
    
      
            JFrame f= new JFrame ("EXIT");
        
        
            if(JOptionPane.showConfirmDialog(f, "Confrom if want to exit ? ","Tic tac Toe",JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION)
        
            {
          
                System.exit(0);
        
            }
    
  
  
        } 
   
    } 
    
    );
  
  
  
    setStatus("Click 'Play' To Start");
  
    setButtonsEnabled(false);

  
    add(northPanel,"North");
  
    add(centerPanel,"Center");
  
    add(southPanel,"South");

  
    setSize(700,600);

  
  setLocationRelativeTo(null);
  
  
 
 }

 
 public static void main(String []args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException 
 
 {
    
     UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
  
     new TicTacToeMain().setVisible(true);
 }

 private void computerTurn()

 {
  
     if(!isPlay) return;

  int []pos = game.nextMove(computer);
  
  if(pos!=null)
  
  {
      
   int i = pos[0];
   
   
   int j = pos[1];
   
   buttons[i][j].setText(chars[computer]);
   
   game.setBoardValue(i,j,computer);
   
   buttons[i][j].setForeground(Color.BLUE);
   
  }
  

  checkState();
  
 }

 private void gameOver(String s) 
 
 {
 

// setStatus(s);

     JOptionPane.showMessageDialog(this, s,"TIC TAC TOE ",JOptionPane.INFORMATION_MESSAGE);
  
     setButtonsEnabled(false);
  
     isPlay = false;
 
 }

 private void checkState() 
 
 {
     
  if(game.isWin(human)) 
  
  {
  
      gameOver("Congratulations, You've Won!");
  
  }
  
 else if(game.isWin(computer)) {
  
      gameOver("Sorry, You Lose!");
  
  }
  
 else if(game.nextMove(human)==null && game.nextMove(computer)==null)
 
  {
  
  
      gameOver("Draw, Click 'Play' For Rematch!");
 
  }
 
 }

 private void click(int i,int j)

 {
     
  if(game.getBoardValue(i,j)==TicTacToeAI.EMPTY) 
  
  {
      
   buttons[i][j].setText(chars[human]);
   
   game.setBoardValue(i,j,human);
   
   
   buttons[i][j].setForeground(Color.GREEN);
   
   buttons[i][j].setBackground(Color.WHITE);
   
   checkState();
   
   computerTurn();
  
  }
 
 }

 public void actionPerformed(ActionEvent event)

 {

     if(event.getSource()==playButton) 
     
     {
   
         play();
  
     }
     
     else 
     
     {
   for(int i=0;i<3;i++)
   
   {
       
   
    for(int j=0;j<3;j++)
        
    {
     
        if(event.getSource()==buttons[i][j])
         
      click(i,j);
  
    }
 
    }
   
    }
 }
 

 private void play()

 {
     
  
     game = new TicTacToeAI();
     
  
     human = TicTacToeAI.ONE;
     
    computer = TicTacToeAI.TWO;
  
      setStatus("Your Turn");
  
 
     setButtonsEnabled(true);
  
      isPlay = true;
  

 }

}
