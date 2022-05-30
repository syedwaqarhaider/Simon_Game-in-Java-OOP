
package simon_game;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.LinkedList;

public class Simon extends JApplet implements MouseListener, Runnable, ActionListener {
Leaderboard leaderbord= new Leaderboard();	
    private static final long serialVersionUID = 1L;
	private boolean suspended = false;
	private int lifeCounter = 3;
	private int bestint = 0;
	private boolean correct = false;
	private int colorCounter = 0;
	private int colorChecker = 0;
	private final int RED = 1;
	private final int YELLOW = 2;
	private final int GREEN = 3;
	private final int BLUE = 4;
	private boolean colormix = true;
	private LinkedList<Integer> list;
	private Thread thread, gameThread;
	private Object object;
	private boolean isStandalone = false;
	private JPanel mainPanal = new JPanel();
	private JPanel buttonPanal = new JPanel();
	private GridLayout btnGridLayout = new GridLayout();
	private JButton btnYellow = new JButton();
	private JButton btnGreen = new JButton();
	private JButton btnRed = new JButton();
	private JButton btnBlue = new JButton();
	private JButton btnStart = new JButton();
	private JButton btnLeaderboard = new JButton();
	private JButton btnPlayer = new JButton();
        private JLabel lblMessage = new JLabel();
	private JLabel lblPlayerName = new JLabel();
	private JLabel lblScore = new JLabel();
	private JLabel lblLives = new JLabel();
	private JPanel lifePanel = new JPanel();
	private GridLayout gridLayout2 = new GridLayout();
	private JLabel life1 = new JLabel();
	private JLabel life2 = new JLabel();
	private JLabel life3 = new JLabel();
    private boolean isPlayerSet=false;

	// Get a parameter value
	public String getParameter(String key, String def) {
		return isStandalone ? System.getProperty(key, def) : (getParameter(key) != null ? getParameter(key) : def);
	}

	// Construct the simon
	public Simon() {
	}

	// Initialize the simon
	public void init() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Component initialization
	private void jbInit() throws Exception {
		btnRed.addActionListener(this);
		btnYellow.addActionListener(this);
		btnGreen.addActionListener(this);
		btnBlue.addActionListener(this);
		btnLeaderboard.addActionListener(this);
		btnStart.addActionListener(this);
                btnPlayer.addActionListener(this);
		this.setSize(new Dimension(405, 325));
		mainPanal.setBackground(Color.white);
		mainPanal.setBorder(BorderFactory.createLineBorder(Color.black));
		mainPanal.setOpaque(true);
		mainPanal.setLayout(null);
		buttonPanal.setBackground(Color.white);
		buttonPanal.setBorder(BorderFactory.createLineBorder(Color.black));
		buttonPanal.setDebugGraphicsOptions(0);
		buttonPanal.setMaximumSize(new Dimension(32767, 32767));
		buttonPanal.setBounds(new Rectangle(124, 77, 161, 132));
		buttonPanal.setLayout(btnGridLayout);
		btnGridLayout.setColumns(2);
		btnGridLayout.setHgap(1);
		btnGridLayout.setRows(2);
		btnGridLayout.setVgap(1);
		btnRed.setBackground(new Color(255, 245, 245));
		btnRed.setFont(new java.awt.Font("Dialog", 0, 16));
		btnRed.setForeground(new Color(203, 0, 0));
		btnRed.setFocusPainted(false);
		btnRed.setHorizontalTextPosition(SwingConstants.CENTER);
		btnRed.setSelected(false);
		btnRed.addMouseListener(this);
		btnYellow.setBackground(new Color(255, 255, 245));
		btnYellow.setFont(new java.awt.Font("Dialog", 0, 16));
		btnYellow.setForeground(new Color(203, 203, 0));
		btnYellow.setFocusPainted(false);
		btnYellow.setHorizontalTextPosition(SwingConstants.CENTER);
		btnYellow.setSelected(false);
		btnYellow.addMouseListener(this);
		btnBlue.setBackground(new Color(245, 245, 255));
		btnBlue.setFont(new java.awt.Font("Dialog", 0, 16));
		btnBlue.setForeground(new Color(0, 0, 203));
		btnBlue.setFocusPainted(false);
		btnBlue.setHorizontalTextPosition(SwingConstants.CENTER);
		btnBlue.setSelected(false);
		btnBlue.addMouseListener(this);
		btnGreen.setBackground(new Color(245, 255, 245));
		btnGreen.setFont(new java.awt.Font("Dialog", 0, 16));
		btnGreen.setForeground(new Color(0, 203, 0));
		btnGreen.setFocusPainted(false);
		btnGreen.setHorizontalTextPosition(SwingConstants.CENTER);
		btnGreen.setSelected(false);
		btnGreen.addMouseListener(this);
		btnStart.setBounds(new Rectangle(15, 248, 84, 25));
		btnStart.setFont(new java.awt.Font("Dialog", 1, 12));
		btnStart.setText("START");
                
                btnPlayer.setBounds(new Rectangle(129, 248, 115, 25));
		btnPlayer.setFont(new java.awt.Font("Dialog", 1, 12));
		btnPlayer.setText("Player Name");
                
		btnLeaderboard.setBounds(new Rectangle(270, 248, 110, 25));
		btnLeaderboard.setFont(new java.awt.Font("Dialog", 1, 12));
		btnLeaderboard.setText("Leaderboard");
		lblMessage.setFont(new java.awt.Font("Dialog", 1, 20));
		lblMessage.setHorizontalAlignment(SwingConstants.CENTER);
		lblMessage.setText("");
		lblMessage.setBounds(new Rectangle(145, 205, 122, 47));
		lblPlayerName.setFont(new java.awt.Font("Dialog", 1, 12));
		lblPlayerName.setRequestFocusEnabled(true);
		lblPlayerName.setHorizontalAlignment(SwingConstants.LEFT);
		lblPlayerName.setText("Player Name:");
		lblPlayerName.setBounds(new Rectangle(220, 21, 300, 33));
		lblScore.setBounds(new Rectangle(14, 20, 82, 33));
		lblScore.setText("SCORE:   0");
		lblScore.setHorizontalAlignment(SwingConstants.LEFT);
		lblScore.setFont(new java.awt.Font("Dialog", 1, 12));
		lblScore.setRequestFocusEnabled(true);
		lblLives.setRequestFocusEnabled(true);
		lblLives.setFont(new java.awt.Font("Dialog", 1, 12));
		lblLives.setHorizontalAlignment(SwingConstants.LEFT);
		lblLives.setText("LIVES:");
		lblLives.setBounds(new Rectangle(128, 20, 45, 33));
		lifePanel.setBackground(Color.white);
		lifePanel.setBounds(new Rectangle(167, 31, 40, 11));
		lifePanel.setLayout(gridLayout2);
		gridLayout2.setColumns(3);
		gridLayout2.setHgap(2);
		gridLayout2.setVgap(2);
		life1.setBackground(Color.red);
		life1.setBorder(BorderFactory.createLineBorder(Color.black));
		life1.setOpaque(true);
		life1.setRequestFocusEnabled(true);
		life1.setText("");
		life2.setBackground(Color.red);
		life2.setBorder(BorderFactory.createLineBorder(Color.black));
		life2.setOpaque(true);
		life2.setRequestFocusEnabled(true);
		life2.setText("");
		life3.setBackground(Color.red);
		life3.setBorder(BorderFactory.createLineBorder(Color.black));
		life3.setOpaque(true);
		life3.setRequestFocusEnabled(true);
		life3.setText("");
		this.getContentPane().add(mainPanal, BorderLayout.CENTER);
		buttonPanal.add(btnRed, null);
		buttonPanal.add(btnYellow, null);
		buttonPanal.add(btnGreen, null);
		buttonPanal.add(btnBlue, null);
		mainPanal.add(lblLives, null);
		mainPanal.add(lifePanel, null);
		mainPanal.add(btnLeaderboard, null);
		mainPanal.add(lblPlayerName, null);
		mainPanal.add(lblScore, null);
		mainPanal.add(lblMessage, null);
		mainPanal.add(btnStart, null);
                mainPanal.add(btnPlayer, null);
		mainPanal.add(buttonPanal, null);
		lifePanel.add(life1, null);
		lifePanel.add(life2, null);
		lifePanel.add(life3, null);
	}

	// Start the simon
	public void start() {
		if (thread == null)
			thread = new Thread(this);
		thread.start();
	}

	// Stop the simon
	public void stop() {
	}

	// Destroy the simon
	public void destroy() {
	}

	// Get Applet information
	public String getAppletInfo() {
		return "Applet Information";
	}

	// Get parameter info
	public String[][] getParameterInfo() {
		return null;
	}

	// Main method
	public static void main(String[] args) {
		Simon simon = new Simon();
		simon.isStandalone = true;
		JFrame frame = new JFrame();
		// EXIT_ON_CLOSE == 3
		frame.setDefaultCloseOperation(3);
		frame.setTitle("Simon");
		frame.getContentPane().add(simon, BorderLayout.CENTER);
		simon.init();
		simon.start();
		frame.setSize(405, 325);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		frame.setLocation((d.width - frame.getSize().width) / 2, (d.height - frame.getSize().height) / 2);
		frame.setVisible(true);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
		object = e.getSource();

		if (object == btnRed)
			btnRed.setBorder(BorderFactory.createLineBorder(Color.black));
		else if (object == btnYellow)
			btnYellow.setBorder(BorderFactory.createLineBorder(Color.black));
		else if (object == btnGreen)
			btnGreen.setBorder(BorderFactory.createLineBorder(Color.black));
		else if (object == btnBlue)
			btnBlue.setBorder(BorderFactory.createLineBorder(Color.black));
	}

	public void mouseEntered(MouseEvent e) {
		object = e.getSource();

		if (object == btnRed)
			btnRed.setBorder(BorderFactory.createLineBorder(Color.red, 2));
		else if (object == btnYellow)
			btnYellow.setBorder(BorderFactory.createLineBorder(Color.yellow, 2));
		else if (object == btnGreen)
			btnGreen.setBorder(BorderFactory.createLineBorder(Color.green, 2));
		else if (object == btnBlue)
			btnBlue.setBorder(BorderFactory.createLineBorder(Color.blue, 2));
	}

	public void run() {
		if (Thread.currentThread() == thread) {
			int randomcolor;
			int time = 90;

			while (colormix) {
				switch ((int) (Math.random() * 4 + 1)) {
				case 1:
				case 4:
					for (int i = 0; i < 40 && colormix; i++) {
						randomcolor = (int) (Math.random() * 4 + 1);

						if (randomcolor == 1)
							changeColors(btnRed, Color.red, new Color(255, 245, 245), time);
						else if (randomcolor == 2)
							changeColors(btnYellow, Color.yellow, new Color(255, 255, 245), time);
						else if (randomcolor == 3)
							changeColors(btnGreen, Color.green, new Color(245, 255, 245), time);
						else if (randomcolor == 4)
							changeColors(btnBlue, Color.blue, new Color(245, 245, 255), time);
					}
					break;
				case 2:
					for (int i = 0; i < 30 && colormix; i++) {
						changeColors(btnRed, Color.red, new Color(255, 245, 245), time);
						changeColors(btnYellow, Color.yellow, new Color(255, 255, 245), time);
						changeColors(btnBlue, Color.blue, new Color(245, 245, 255), time);
						changeColors(btnGreen, Color.green, new Color(245, 255, 245), time);
					}
					break;
				case 3:
					for (int i = 0; i < 30 && colormix; i++) {
						changeColors(btnRed, Color.red, new Color(255, 245, 245), time);
						changeColors(btnGreen, Color.green, new Color(245, 255, 245), time);
						changeColors(btnBlue, Color.blue, new Color(245, 245, 255), time);
						changeColors(btnYellow, Color.yellow, new Color(255, 255, 245), time);
					}
					break;
				}
			}
		} else if (Thread.currentThread() == gameThread) {
			int temps = 500;
			int randomcolor;

			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
			}
			while (!colormix) {
				randomcolor = (int) (Math.random() * 4 + 1);
				list.add(new Integer(randomcolor));
				colorCounter++;

				correct = false;

				while (!correct && !colormix) {
					for (int i = 0; i < colorCounter; i++) {
						switch (((Integer) list.get(i)).intValue()) {
						case RED:
							changeColors(btnRed, Color.red, new Color(255, 245, 245), temps);
							break;
						case YELLOW:
							changeColors(btnYellow, Color.yellow, new Color(255, 255, 245), temps);
							break;
						case GREEN:
							changeColors(btnGreen, Color.green, new Color(245, 255, 245), temps);
							break;
						case BLUE:
							changeColors(btnBlue, Color.blue, new Color(245, 245, 255), temps);
							break;
						}
						try {
							Thread.sleep(300);
						} catch (InterruptedException e) {
						}
					}
					suspended = true;
					if (colormix)
						break;
					lblMessage.setText("GO !");
					gameThread.suspend();
					suspended = false;
					try {
						Thread.sleep(500);
					} catch (InterruptedException e) {
					}
					lblMessage.setText("");
					try {
						gameThread.sleep(500);
					} catch (InterruptedException e) {
					}
				}
			}
		}
	}

	void changeColors(JButton b, Color c1, Color c2, int time) {
		b.setBackground(c1);
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
		}
		b.setBackground(c2);
	}

	public void actionPerformed(ActionEvent e) {
		object = e.getSource();

		if (object == btnLeaderboard)
			leaderBoard();

		else if (object == btnStart) {
		if(isPlayerSet)
                {
                    if (colormix)
                        {
				colormix = !colormix;
				list = new LinkedList<Integer>();
				lblMessage.setText("");
				btnStart.setText("STOP");
				colorChecker = 0;
				btnRed.setBackground(new Color(255, 245, 245));
				btnYellow.setBackground(new Color(255, 255, 245));
				btnGreen.setBackground(new Color(245, 255, 245));
				btnBlue.setBackground(new Color(245, 245, 255));
				gameThread = new Thread(this);
				gameThread.start();
			}
                        else {
				initiate();
			}
		}
                else
                {
                    JOptionPane.showMessageDialog(this, "Set Player Name First");
                }
                }
                else if(object==btnPlayer)
                {
                    String playerName = JOptionPane.showInputDialog("What is your name? ");
                    if(!playerName.equals(""))
                    {
                        this.leaderbord.setPlayerName(playerName);
                        lblPlayerName.setText("Player Name : "+playerName);
                        this.isPlayerSet=true;
                    }
                }

		else if ((object == btnRed || object == btnYellow || object == btnGreen || object == btnBlue) && !colormix && suspended) {
			lblMessage.setText("");
			int i = ((Integer) (list.get(colorChecker))).intValue();

			if ((i == RED && object == btnRed) || (i == YELLOW && object == btnYellow) || (i == GREEN && object == btnGreen)
					|| (i == BLUE && object == btnBlue)) {
				colorChecker++;
				if (colorChecker == colorCounter) {
					lblMessage.setText("OK !");
                                       // System.out.println("total Score : "+colorCounter);
					lblScore.setText("SCORE:   " + colorCounter);
					correct = true;
					colorChecker = 0;
					gameThread.resume();
				}
			} else {
				if (lifeCounter == 3)
					life3.setBackground(Color.white);
				else if (lifeCounter == 2)
					life2.setBackground(Color.white);
				else if (lifeCounter == 1)
					life1.setBackground(Color.white);
				else
					initiate();
				if (!colormix) {
					colorChecker = 0;
					lifeCounter--;
					lblMessage.setText("ooooh !");
					correct = false;
					gameThread.resume();
				}
			}
		}
	}

	void initiate() {
		colormix = !colormix;
		suspended = false;
		lblMessage.setText("THE END");
		lifeCounter = 3;
		life1.setBackground(Color.red);
		life2.setBackground(Color.red);
		life3.setBackground(Color.red);
		lblScore.setText("SCORE:   0");
		btnStart.setText("START");
                 this.leaderbord.setScore(colorCounter - 1);
                        this.isPlayerSet=false;
                        this.lblPlayerName.setText("Player Name:");
		thread = new Thread(this);
		if (bestint < colorCounter) {
			//playerName.setText("BEST:   " + Integer.toString(colorCounter - 1));
                       
			bestint = colorCounter;
		}
		colorCounter = 0;
		colorChecker = 0;
		thread.start();
	}

	void leaderBoard() {
		this.leaderbord.ShowLeaderboard();
        }
}
