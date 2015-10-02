import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class PacMan implements KeyListener, ActionListener{

	//ImageIcon eblock = new ImageIcon("cobblestone.png");
	ImageIcon blocks[]={new ImageIcon("cobblestone.png"),
						new ImageIcon("notch.png"),
						new ImageIcon("creeperghost.png"),
						new ImageIcon("coal.png"),
						new ImageIcon("diamond.png"),
						new ImageIcon("obsidian.png"),
						new ImageIcon("AnimationLava.gif"),
						new ImageIcon("trapdoor.png"),
						new ImageIcon("notchgif.gif"),
						new ImageIcon("notchlife.png")







	};
	JFrame window;
	JLabel block;
	int score=0;
	int lives=3;
	final int RIGHT=0;
	final int UP=1;
	final int DOWN=2;
	final int LEFT=3;
	final int NOTCH=8;
	final int EMPTY=0;
	final int WALL=5;
	final int DIAMOND=4;
	final int CREEPER=2;
	final int COAL=3;
	final int TDOOR=7;

	int grid[][]={

				{5,5,5,5,5,5,5,5,5,5,5,5,5,5,5,5},
				{0,3,3,3,3,3,3,3,3,3,3,3,3,3,3,5},
				{5,3,5,3,5,3,5,5,5,5,5,5,3,5,3,5},
				{5,3,5,3,5,3,3,4,3,3,3,3,3,5,3,5},
				{5,3,5,3,5,3,5,5,5,5,5,5,3,5,3,5},
				{5,3,5,3,5,3,3,3,3,3,3,3,3,5,3,5},
				{5,4,3,3,3,3,5,5,5,5,5,5,3,5,3,5},
				{5,5,5,3,5,3,3,3,3,3,3,3,4,5,3,5},
				{5,2,5,3,5,5,5,5,5,5,5,5,5,5,3,5},
				{5,2,0,4,3,3,3,3,8,3,3,3,3,3,3,5},
				{5,5,5,3,5,5,5,3,5,5,3,5,5,5,3,5},
				{5,5,5,3,5,4,5,3,5,5,3,5,4,5,3,5},
				{5,3,3,3,5,3,5,3,5,5,3,5,3,5,3,5},
				{5,3,5,5,5,3,3,3,3,3,3,3,3,5,3,5},
				{5,3,5,3,3,3,5,5,5,5,5,5,3,5,3,5},
				{5,3,5,3,5,5,5,6,6,6,6,5,3,5,3,5},
				{5,3,5,3,5,6,6,6,6,6,6,5,3,3,3,5},
				{5,3,5,3,5,6,6,6,6,6,5,5,3,5,3,5},
				{5,3,5,3,5,5,5,5,5,5,5,3,3,5,3,5},
				{5,3,5,3,3,3,5,4,3,3,3,3,5,5,3,5},
				{5,3,5,5,5,3,5,3,5,5,5,5,5,5,3,5},
				{5,3,3,3,3,3,3,3,3,3,3,3,3,3,3,0},
				{5,5,5,5,5,5,5,5,5,5,5,5,9,9,9,5}





	};
	JLabel pGrid[][];
	int pinky_m[]={RIGHT,RIGHT,UP};
	int blinky_m[]={DOWN,RIGHT,RIGHT};
	//int clyde_m[]={UP,RIGHT,DOWN};
	Ghost pinky= new Ghost(9,1,RIGHT,grid, pinky_m);
	Ghost blinky= new Ghost(8,1,RIGHT,grid,blinky_m);
	//Ghost clyde= new Ghost(10,1,RIGHT,grid,clyde_m);
	Timer time;
	public void actionPerformed(ActionEvent e){
		int pacman_r=getPacManRow();
		int pacman_c=getPacManCol();
		blinky.chase(getPacManRow(),getPacManCol(),grid);
		pinky.chase(getPacManRow(),getPacManCol(),grid);
	//	clyde.chase(getPacManRow(),getPacManCol(),grid);
		if(pinky.move()){
			grid[pacman_r][pacman_c]=EMPTY;
			pacman_r=9;
			pacman_c=1;
		}
		paintGrid();
		if(blinky.move()){
			grid[pacman_r][pacman_c]=EMPTY;
			pacman_r=8;
			pacman_c=1;
		}
		paintGrid();
	/*	if(clyde.move()){
			grid[pacman_r][pacman_c]=EMPTY;
			pacman_r=10;
			pacman_c=1;
		}
		paintGrid(); */
	}

	public void keyTyped(KeyEvent e){
		//System.out.println(e.getKeyCode());
		//int value=e.getKeyCode();
	}

	/*public void printGrid(){
    for(int i=0;i<23;i++){
    	for(int j=0;j<16;j++){
    		System.out.print("|"+grid[i][j]+"|");
    	}
    	System.out.println();
    }


	}*/

	public void keyPressed(KeyEvent e){
		System.out.println(e.getKeyCode());
		int value=e.getKeyCode();
        //printGrid();
		if(value==38){
			int pacman_r=getPacManRow();
			int pacman_c=getPacManCol();
			
			if(grid[pacman_r-1][pacman_c]==EMPTY || grid[pacman_r-1][pacman_c]==COAL || grid[pacman_r-1][pacman_c]==DIAMOND){
				//scoring
				if(grid[pacman_r-1][pacman_c]==COAL){
					SoundEffect.EAT.play();
					score++;
				}
				else if(grid[pacman_r-1][pacman_c]==DIAMOND){
					SoundEffect.FART.play();
					score+=10;
				}

				grid[pacman_r-1][pacman_c]=NOTCH;	
				grid[pacman_r][pacman_c]=EMPTY;
			}

			else if(grid[pacman_r-1][pacman_c]==CREEPER) {
			SoundEffect.DIE.play();
			lives--;
			if (lives==2)grid[22][12]=WALL;
			if (lives==1)grid[22][13]=WALL;
			grid[pacman_r][pacman_c]=EMPTY;
			if(lives==0){JOptionPane.showMessageDialog(null,"LOSE"); System.exit(0);}
			grid[9][8]=NOTCH;

		}
		}
//CRASH IF TOUCH WALL FIX PL0X

		paintGrid();

		if(value==37){
			int pacman_r=getPacManRow();
			int pacman_c=getPacManCol();
			if(pacman_c==0&& pacman_r==1){pacman_c=16;pacman_r=21;
				//System.out.println("k"+grid[pacman_r][pacman_c-1]);

			}
			if(grid[pacman_r][pacman_c-1]==EMPTY || grid[pacman_r][pacman_c-1]==COAL || grid[pacman_r][pacman_c-1]==DIAMOND){

				if(grid[pacman_r][pacman_c-1]==COAL){
					SoundEffect.EAT.play();
					score++;
				}
				else if(grid[pacman_r][pacman_c-1]==DIAMOND){
					SoundEffect.FART.play();
					score+=10;
				}
				//System.out.println("in");

				grid[pacman_r][pacman_c-1]=NOTCH;
				if(pacman_c==16&& pacman_r==21){ pacman_c=0;pacman_r=1;} //
				grid[pacman_r][pacman_c]=EMPTY;
			}
			else if(grid[pacman_r][pacman_c-1]==CREEPER){
			SoundEffect.DIE.play();
			lives--;
			if (lives==2)grid[22][12]=WALL;
			if (lives==1)grid[22][13]=WALL;
			grid[pacman_r][pacman_c]=EMPTY;
			if(lives==0){JOptionPane.showMessageDialog(null,"LOSE"); System.exit(0);}
			grid[9][8]=NOTCH;

		}

		}
		paintGrid();
		if(value==39){
			int pacman_r=getPacManRow();
			int pacman_c=getPacManCol();
			if(pacman_c==15 && pacman_r==21){pacman_c=-1;pacman_r=1;}
			if(grid[pacman_r][pacman_c+1]==EMPTY || grid[pacman_r][pacman_c+1]==COAL || grid[pacman_r][pacman_c+1]==DIAMOND){

				if(grid[pacman_r][pacman_c+1]==COAL){
					SoundEffect.EAT.play();
					score++;
				}
				else if(grid[pacman_r][pacman_c+1]==DIAMOND){
					SoundEffect.FART.play();
					score+=10;
				}

				grid[pacman_r][pacman_c+1]=NOTCH;
				if(pacman_c==-1 && pacman_r==1){pacman_c=15;pacman_r=21;}
				grid[pacman_r][pacman_c]=EMPTY;
		}
		else if(grid[pacman_r][pacman_c+1]==CREEPER){
			SoundEffect.DIE.play();
			lives--;
			if (lives==2)grid[22][12]=WALL;
			if (lives==1)grid[22][13]=WALL;
			grid[pacman_r][pacman_c]=EMPTY;
			if(lives==0){JOptionPane.showMessageDialog(null,"LOSE"); System.exit(0);}
			grid[9][8]=NOTCH;

		}
		}
		paintGrid();
		if(value==40){
			int pacman_r=getPacManRow();
			int pacman_c=getPacManCol();
			if(grid[pacman_r+1][pacman_c]==EMPTY || grid[pacman_r+1][pacman_c]==COAL || grid[pacman_r+1][pacman_c]==DIAMOND){

				if(grid[pacman_r+1][pacman_c]==COAL){
					SoundEffect.EAT.play();
					score++;
				}

				else if(grid[pacman_r+1][pacman_c]==DIAMOND){
					SoundEffect.FART.play();
					score+=10;
				}

				grid[pacman_r+1][pacman_c]=NOTCH;
				grid[pacman_r][pacman_c]=EMPTY;
		}
		else if(grid[pacman_r+1][pacman_c]==CREEPER){
			SoundEffect.DIE.play();
			lives--;
			if (lives==2)grid[22][12]=WALL;
			if (lives==1)grid[22][13]=WALL;
			grid[pacman_r][pacman_c]=EMPTY;
			if(lives==0){JOptionPane.showMessageDialog(null,"LOSE"); System.exit(0);}
			grid[9][8]=NOTCH;

		}
		}
		System.out.println("score "+score);
		paintGrid();
	}

	public int getPacManRow(){
		for(int i=0;i<23;i++){
			for(int j=0;j<16;j++){
				if(grid[i][j]==8)
					return i;
			}
		}
		return 0;
	}
	public int getPacManCol(){
		for(int i=0;i<23;i++){
			for(int j=0;j<16;j++){
				if(grid[i][j]==8)
					return j;
			}
		}
		return 0;
	}
	public void keyReleased(KeyEvent e){

	}

	public void paintGrid(){
		score_txt.setText(""+score);
		if(score==220){
			JOptionPane.showMessageDialog(null,"WIN");
			System.exit(0);

		}
		for(int i=0;i<23;i++){
			for(int j=0;j<16;j++){
				pGrid[i][j].setIcon(blocks[grid[i][j]]);
				window.repaint();
			}
		}
	}



	JPanel body;
	JPanel top;
	JLabel score_lbl=new JLabel("Score");
	JLabel score_txt=new JLabel("000");
	JLabel live1= new JLabel();
	JLabel live2= new JLabel();
	JLabel live3= new JLabel();
	//AudioInputStream audiIn;
	//URL url;

	public PacMan(){
		top= new JPanel();
		top.setLayout(new GridLayout(1,5));
		top.add(score_lbl);
		top.add(score_txt);
		score_txt.setOpaque(true);
		score_lbl.setOpaque(true);
		score_txt.setForeground(Color.white);
		score_txt.setBackground(Color.black);
		score_lbl.setForeground(Color.white);
		score_lbl.setBackground(Color.black);
		SoundEffect.init();
		SoundEffect.volume = SoundEffect.Volume.HIGH;

		//live1.setIcon(notch.png);
		/*top.add(live1);
		top.add(live2);
		top.add(live3);*/


		body = new JPanel();
		//ImageIcon eblock = new ImageIcon("cobblestone.png");
		window= new JFrame("PacMinecraft");
		window.setLayout(new BorderLayout());
		window.addKeyListener(this);
		body.setLayout(new GridLayout(23,16));

		//window.setLayout(new GridLayout(23,16));


		//block = new JLabel(eblock);
		pGrid = new JLabel[23][16];

		for(int i=0;i<23;i++){
			for(int j=0;j<16;j++){
				pGrid[i][j]= new JLabel(blocks[grid[i][j]]);
				body.add(pGrid[i][j]);
			}
		
		}
		//window.add(block);
		//window.setSize(500,500);
		window.add(body, BorderLayout.CENTER);
		window.add(top, BorderLayout.NORTH);
		window.pack();
		window.setVisible(true);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		time=new Timer(300,this);
		time.start();
		time.setRepeats(true);
	}
	public static void  main(String args[]){
		new PacMan();
	}


}	
