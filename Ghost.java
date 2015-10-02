public class Ghost{
	int ghost_r;
	int ghost_c;
	int direction;
	int pacman_c;
	int pacman_r;
	int grid[][];
	int lastSpot;
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
	int moves[];
	int moves_len;
	int currentSpot;

	public void flip(){
		if(direction==UP)direction=DOWN;
		else if(direction==DOWN)direction=UP;
		else if(direction==LEFT)direction=RIGHT;
		else if(direction==RIGHT)direction=LEFT;
	}
	
		
	

	public Ghost(int r, int c, int dir,int g[][], int m[]){
		ghost_r=r;
		ghost_c=c;
		grid=g;
		direction=dir;
		lastSpot=EMPTY;
		moves=m;
		moves_len=0;
	}
	public void chase(int p_r, int p_c, int g[][]){
		pacman_r=p_r;
		pacman_c=p_c;
		grid=g;
		System.out.println("CHASE");
		if(ghost_r==pacman_r){
			if(ghost_c<=pacman_c){
				for(int i=ghost_c+1;i<pacman_c;i++){
					if(grid[ghost_r][i]==WALL)return;
					else{
						direction=RIGHT;
						}
				}
			}else if(pacman_c<=ghost_c){
				for(int i=ghost_c-1;i>pacman_c;i++){
					if(grid[ghost_r][i]==WALL)return;
					else{
						direction=LEFT;
						}
				}
			}

		}
		if(ghost_c==pacman_c){
			if(ghost_r<=pacman_r){
				for(int i=ghost_r+1;i<pacman_r;i++){
					if(grid[i][ghost_c]==WALL){
						return;
					}else{
					direction=DOWN;
				}
			}
		}
				else if(pacman_r<=ghost_r){
				for(int i=ghost_r-1;i>pacman_r;i++){
					if(grid[ghost_r][i]==WALL)return;
					else{
						direction=UP;
						}
				}
			}
				
			
		}


	}
	public boolean move(){
		if(moves_len!=moves.length){
			direction=moves[moves_len];
			moves_len++;
		}
		if(direction==UP){
			if(grid[ghost_r-1][ghost_c]==EMPTY ||
				grid[ghost_r-1][ghost_c]==COAL ||
					grid[ghost_r-1][ghost_c]==DIAMOND){
						if(lastSpot==EMPTY){
						lastSpot=grid[ghost_r-1][ghost_c];
						grid[ghost_r-1][ghost_c]=CREEPER;
						grid[ghost_r][ghost_c]=EMPTY;
						ghost_r--;
						}else{
							currentSpot=grid[ghost_r-1][ghost_c];
							grid[ghost_r-1][ghost_c]=CREEPER;
							grid[ghost_r][ghost_c]=lastSpot;
							lastSpot=currentSpot;
							ghost_r--;
						}
		}else if(grid[ghost_r-1][ghost_c]==WALL){
			flip();
		}else if(grid[ghost_r-1][ghost_c]==NOTCH){
			return true;
		}
	}	else if(direction==DOWN){
			if(grid[ghost_r+1][ghost_c]==EMPTY ||
				grid[ghost_r+1][ghost_c]==COAL ||
					grid[ghost_r+1][ghost_c]==DIAMOND){
						if(lastSpot==EMPTY){
						lastSpot=grid[ghost_r+1][ghost_c];
						grid[ghost_r+1][ghost_c]=CREEPER;
						grid[ghost_r][ghost_c]=EMPTY;
						ghost_r++;
						}else{
							currentSpot=grid[ghost_r+1][ghost_c];
							grid[ghost_r+1][ghost_c]=CREEPER;
							grid[ghost_r][ghost_c]=lastSpot;
							lastSpot=currentSpot;
							ghost_r++;
						}
		}else if(grid[ghost_r+1][ghost_c]==WALL){
			flip();
		}else if(grid[ghost_r+1][ghost_c]==NOTCH){
			return true;
		}
	}	else if(direction==LEFT){
			if(grid[ghost_r][ghost_c-1]==EMPTY ||
				grid[ghost_r][ghost_c-1]==COAL ||
					grid[ghost_r][ghost_c-1]==DIAMOND){
						if(lastSpot==EMPTY){
						lastSpot=grid[ghost_r][ghost_c-1];
						grid[ghost_r][ghost_c-1]=CREEPER;
						grid[ghost_r][ghost_c]=EMPTY;
						ghost_c--;
						}else{
							currentSpot=grid[ghost_r][ghost_c-1];
							grid[ghost_r][ghost_c-1]=CREEPER;
							grid[ghost_r][ghost_c]=lastSpot;
							lastSpot=currentSpot;
							ghost_c--;
						}
		}else if(grid[ghost_r][ghost_c-1]==WALL){
			flip();
		}else if(grid[ghost_r][ghost_c-1]==NOTCH){
			return true;
		}
	}else if(direction==RIGHT){
			if(grid[ghost_r][ghost_c+1]==EMPTY ||
				grid[ghost_r][ghost_c+1]==COAL ||
					grid[ghost_r][ghost_c+1]==DIAMOND){
						if(lastSpot==EMPTY){
						lastSpot=grid[ghost_r][ghost_c+1];
						grid[ghost_r][ghost_c+1]=CREEPER;
						grid[ghost_r][ghost_c]=EMPTY;
						ghost_c++;
						}else{
							currentSpot=grid[ghost_r][ghost_c+1];
							grid[ghost_r][ghost_c+1]=CREEPER;
							grid[ghost_r][ghost_c]=lastSpot;
							lastSpot=currentSpot;
							ghost_c++;
						}
		}else if(grid[ghost_r][ghost_c+1]==WALL){
			flip();
		}else if(grid[ghost_r][ghost_c+1]==NOTCH){
			return true;
		}
	}
	return false;

}
}
