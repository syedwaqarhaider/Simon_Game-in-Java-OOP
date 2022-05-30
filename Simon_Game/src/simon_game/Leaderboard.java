/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simon_game;

import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 *
 * @author CYBER-LAPTOP
 */
public class Leaderboard {
    
    private ArrayList<String> playerNames=new ArrayList<String>();
    private ArrayList<Integer> playerScores=new ArrayList<Integer>();
    
    private ArrayList<String> sortedPlayerNames=new ArrayList<String>();
    private ArrayList<Integer> sortedPlayerScores=new ArrayList<Integer>();
    
    
    public void setPlayerName(String name)
    {
        this.playerNames.add(name);
            
        
    }
    public void setScore(int score)
    {
        this.playerScores.add(score);
        
        
    }
    public void ShowLeaderboard()
    {
        JFrame frame = new JFrame("Leaderboard");
       //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       frame.setSize(500,500);
       frame.setLayout(null);
       JLabel lblText = new JLabel("Leader Board");
       lblText.setSize(150,30);
       lblText.setLocation(180,10);
       frame.getContentPane().add(lblText); 
       
       JLabel lblPlayerNameText = new JLabel("Player Name");
       lblPlayerNameText.setSize(180,30);
       lblPlayerNameText.setLocation(30,25);
       frame.getContentPane().add(lblPlayerNameText); // Adds Button to content pane of frame
       
       
       JLabel lblPlayerScoreText = new JLabel("Scores");
       lblPlayerScoreText.setSize(110,30);
       lblPlayerScoreText.setLocation(420,25);
       frame.getContentPane().add(lblPlayerScoreText); // Adds Button to content pane of frame
       frame.setVisible(true);
       SortSocresAcsendingOrder();
       updateLeaderBoard(frame);
    }

    private void updateLeaderBoard(JFrame frame) {
       
       int previousLocation=25;
       for(int i=0; i<sortedPlayerNames.size(); i++)
       {
       previousLocation+=20;    
       JLabel lblPlayerName = new JLabel(this.sortedPlayerNames.get(i));
       lblPlayerName.setSize(180,30);
       lblPlayerName.setLocation(30,previousLocation);
       frame.getContentPane().add(lblPlayerName); 
       
       
       
       
       JLabel lblPlayerScore = new JLabel(Integer.toString(this.sortedPlayerScores.get(i)));
       lblPlayerScore.setSize(110,30);
       lblPlayerScore.setLocation(420,previousLocation);
       frame.getContentPane().add(lblPlayerScore); // Adds Button to content pane of frame
       
       
       
       
       
          // System.out.println(playerNames.get(i) + "   "+ this.playerScores.get(i));
       }
       
    }

    private void SortSocresAcsendingOrder() {
        while(playerNames.size()!=0)
        {
            int max=0;
           for(int j=0; j<this.playerNames.size()-1; j++)
            {
              if(this.playerScores.get(max)>this.playerScores.get(j+1))
              {
                  max=max;
              }
              else
              {
                  max=j+1;
              }
            
            }    
            this.sortedPlayerNames.add(playerNames.remove(max));
            this.sortedPlayerScores.add(playerScores.remove(max));
            
        }
    }
    
}
