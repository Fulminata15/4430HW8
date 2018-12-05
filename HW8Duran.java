import java.util.Arrays;
public class HW8Duran
{
    int [][]ableMoves =
	{
        {0,1,3},
        {0,2,5},
        {1,3,6},
        {1,4,8},
        {2,4,7},
        {2,5,9},
        {3,6,10},
        {3,7,12},
        {4,7,11},
        {4,8,13},
        {5,8,12},
        {5,9,14},
        {3,4,5},
        {6,7,8},
        {7,8,9},
        {10,11,12},
        {11,12,13},
        {12,13,14}
    };
	int []m = new int[15];
    boolean bool = false;
	int [][]board = new int [m.length - 1][3];
//=============================================================================================================	
    void go()
	{
        makeMoves(m,0);
        for (int i = 0; i < board.length-1; i++)
		{
            movePeg(board[i][0],board[i][1],board[i][2]);
        }
    }
//=============================================================================================================	
    void check(int[] m, int from, int over, int to, int checker)
	{
        if(checker == 0)
		{
            if (m[from] == 1 && m[over] == 1 && m[to] == 0 && bool == true)
			{
                m[from] = 0;
                m[over] = 0;
                m[to]   = 1;
            }
			else if (m[from] == 0 && m[over] == 1 && m[to] == 1 && bool == true) 
			{
                m[from] = 1;
                m[over] = 0;
                m[to]   = 0;
            }
        }
        else
		{
            if (m[from] == 1 && m[over] == 1 && m[to] == 0) 
			{
                m[from] = 0;
                m[over] = 0;
                m[to]   = 1;
            }
			else if (m[from] == 0 && m[over] == 1 && m[to] == 1) 
			{
                m[from] = 1;
                m[over] = 0;
                m[to]   = 0;
            }
        }
    }
//=============================================================================================================
    void movePeg(int from, int over, int to)
	{
        for (int i = 0; i < ableMoves.length; i++)
		{
            if (((from == ableMoves[i][0] && to == ableMoves[i][2]) || (from == ableMoves[i][2] && to == ableMoves[i][0]) && bool == false))
			{
                bool = true;
            }
        }
        check(m,from,over,to,0);
        display();
    }
//=============================================================================================================
    int[] Move(int[] m, int from, int over, int to)
	{
        int []temp = new int[m.length];

        for (int i = 0; i < temp.length; i++)
		{
            temp[i] = m[i];
        }
        check(temp,from,over,to,1);
        return temp;
    }
//=============================================================================================================
    void assign(int [][]to, int [][]from, int num1, int num2)
	{
        to[num1][0] = from[num2][0];
        to[num1][1] = from[num2][1];
        to[num1][2] = from[num2][2];
    }
//=============================================================================================================
    int [][] possMove(int[] m)
	{
        int empty = 0;
        int []temp = new int [m.length];
        temp = m;
        int [][]moves = new int [ableMoves.length][3];
        for (int i = 0; i < m.length; i++)
		{
            for (int j = 0; j < ableMoves.length; j++)
			{
                if ((i == ableMoves[j][0] || i == ableMoves[j][2]) && temp[i] == 0)
				{
                    if (calc(temp, j) == 2)
					{
                        assign(moves,ableMoves, empty, j);
                        empty++;
                    }
                }
            }
        }
        return moves;
    }
//=============================================================================================================
    boolean makeMoves(int[] m, int pos)
	{
        int empty = 0;
        int []temp = new int[m.length];
        temp = m;
        int filled = m.length;

        for (int i = 0; i < temp.length; i++)
		{
            if (temp[i] == 0)
			{
                filled--;
            }
        }

        int [][]moves;
        int [][]moving = new int [m.length-1][3];
        moves = possMove(temp);
        for (int i = 0; i < moves.length; i++)
		{
            if ((moves[i][0] + moves[i][1] + moves[i][2]) != 0)
			{
                empty++;
            }
        }

        int []tempo;
        if (empty != 0 && filled != 1)
		{
            for (int i = 0; i < empty; i++)
			{
                tempo = Move(temp, moves[i][0], moves[i][1], moves[i][2]);
                assign(board,moves,pos,i);
                if(makeMoves(tempo, pos+1))
				{
                    return true;
                }
            }
        }
		else if (empty == 0 && filled == 1)
		{
            return true;
        }
        return false;
    }
//=============================================================================================================
    int calc(int []temp,int checker)
	{
        return (temp[ableMoves[checker][0]] + temp[ableMoves[checker][1]] + temp[ableMoves[checker][2]]);
    }
//=============================================================================================================
    void display()
	{
        System.out.print("       " + m[0] + "    \n");
        System.out.print("      " + m[1] + " " + m[2] + "   \n");
        System.out.print("     " + m[3] + " " + m[4] + " " + m[5] + "  \n");
        System.out.print("    " + m[6] + " " + m[7] + " " + m[8] + " " + m[9] + " \n");
        System.out.print("   " + m[10] + " " + m[11] + " " + m[12] + " " + m[13] + " " + m[14] + "\n");
    }
    public static void main(String[] args)
	{
        HW8Duran pegGame = new HW8Duran();

        for (int i = 0; i<5; i++)
		{
            Arrays.fill(pegGame.m,1);
            pegGame.m[i] = 0;
			int checker = i+1;
            System.out.print("\n=======" + checker + "=======\n");
            pegGame.display();
            pegGame.go();
            Arrays.fill(pegGame.m,1);

        }
    }
}