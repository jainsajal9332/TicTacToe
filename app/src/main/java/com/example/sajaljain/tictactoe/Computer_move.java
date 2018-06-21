package com.example.sajaljain.tictactoe;



        import android.util.Pair;
        import android.widget.Button;

        import java.util.*;

public class Computer_move {



    public char matrix[][]=new char[4][4];
    public char current_symbol;
    public static char ch0,ch1,ch2;
    public Pair<Integer,Integer> pp=new Pair<Integer,Integer> (0,0);
    public static HashMap<Pair<Integer,Integer>,Integer> mp=new HashMap<Pair<Integer,Integer>,Integer> ();
    public static HashMap<Integer,Pair<Integer,Integer> > rev_mp=new HashMap<Integer,Pair<Integer,Integer> > ();
    public static int dp[][]=new int[3][1024];
    public static int dp0[][],dp1[][],dp2[][];
    public static int move_number,gamelevel=0;
    public static long priority_level;
    public  void print()
    {
        for(int i=1;i<=3;i++)
        {
            for(int j=1;j<=3;j++)
                System.out.print(this.matrix[i][j]+" | ");
            System.out.print("\n");
            System.out.println("------------");


        }
        System.out.println("\n\n\n\n");
    }
    public char change_sign(char ch)
    {
        if(ch==ch1)return ch2;
        return ch1;
    }
    public int check(char temp,int i,int j)
    {
        int b0=1,b1=1,b2=1,b3=1;
        for(int k=1;k<=3;k++)
            if(this.matrix[i][k]!=temp)
            {b0=0;break;}

        for(int k=1;k<=3&&(b0==0);k++)
            if(this.matrix[k][j]!=temp)
            {b1=0;break;}

        for(int k=1;k<=3&&(b0==0&&b1==0);k++)
            if(this.matrix[k][k]!=temp)
            {b2=0;break;}

        for(int k=1;k<=3&&(b0==0&&b1==0&&b2==0);k++)
            if(this.matrix[k][3-k+1]!=temp)
            {b3=0;break;}


        return b0|b1|b2|b3;

    }
    public int  winning_move(char temp)
    {
        int counter=0;
        for(int i=1;i<=3;i++)
        {
            for(int j=1;j<=3;j++)
            {
                if(this.matrix[i][j]==ch0)
                {
                    this.matrix[i][j]=temp;
                    int value=this.check(temp,i,j);

                    if(value!=0)
                        this.pp=new Pair(i,j);

                    counter+=value;
                    this.matrix[i][j]=ch0;
                }
            }
        }
        return counter;
    }
    public void init()
    {
        for(int i=1;i<=2;i++)
            Arrays.fill(dp[i],-1);
        {
            mp.put(new Pair(0,0),0);
            mp.put(new Pair(1,1),1);
            mp.put(new Pair(1,2),2);
            mp.put(new Pair(1,3),3);
            mp.put(new Pair(2,1),4);
            mp.put(new Pair(2,2),5);
            mp.put(new Pair(2,3),6);
            mp.put(new Pair(3,1),7);
            mp.put(new Pair(3,2),8);
            mp.put(new Pair(3,3),9);
        }
        {
            rev_mp.put(1,new Pair(1,1));
            rev_mp.put(1,new Pair(1,2));
            rev_mp.put(1,new Pair(1,3));
            rev_mp.put(1,new Pair(2,1));
            rev_mp.put(1,new Pair(2,2));
            rev_mp.put(1,new Pair(2,3));
            rev_mp.put(1,new Pair(3,1));
            rev_mp.put(1,new Pair(3,2));
            rev_mp.put(1,new Pair(3,3));
        }
        dp0=new int[4][4];
        dp1=new int[4][4];
        dp2=new int[4][4];
    }

    public long  ujjwal_move(int i1,int j1)
    {
        ArrayList<Pair<Long,Pair<Integer,Integer> > > list=new ArrayList<Pair<Long,Pair<Integer,Integer> > >();


        move_number++;
        priority_level/=1000;
        long value1=0,value=0;
        if(move_number>gamelevel)
            return 0;

        int temp=0,win=0,draw=0;
        if( (temp=this.winning_move(this.change_sign(this.current_symbol) ) )>=2 )
            return 2;
        else if(temp==1)
        {
            int i0,j0;
            i0=pp.first;j0=pp.second;
            this.matrix[i0][j0]=this.current_symbol;
            this.current_symbol=this.change_sign(this.current_symbol);




            // System.out.println("UJJWAL"+move_number);this.print();
            priority_level*=1000;
            value=this.sajal_move(i0,j0);
            priority_level/=1000;

            move_number--;
            this.current_symbol=this.change_sign(this.current_symbol);
            this.matrix[i0][j0]=ch0;
            if(value==-1)
                return value;
            return (long)value;
        }
        else
        {

            for(int i=1;i<=3;i++)
            {
                for(int j=1;j<=3;j++)
                {
                    if(this.matrix[i][j]==ch0)
                    {
                        this.matrix[i][j]=this.current_symbol;
                        this.current_symbol=this.change_sign(this.current_symbol);





                        // System.out.println("UJJWAL"+move_number);this.print();

                        value=this.sajal_move(i,j);
                        this.current_symbol=this.change_sign(this.current_symbol);
                        move_number--;
                        this.matrix[i][j]=ch0;
                        if(value==-1)
                            return -1;
                        else
                        {
                            list.add(new Pair(value,new Pair(i,j)));
                            value1+=value;
                        }


                    }
                }
            }
            //System.out.println(move_number+" "+i1+" "+j1+"--->"+list);
        }





        return win*priority_level+value1;

    }
    public long sajal_move(int i1,int j1)
    {
        ArrayList<Pair<Long,Pair<Integer,Integer> > > list=new ArrayList<Pair<Long,Pair<Integer,Integer> > >();

        move_number++;
        long value1=0,value=0;
        if(move_number>gamelevel)
            return 0;

        int temp=0;
        if((temp=this.winning_move(this.current_symbol))!=0)
        {

            return priority_level;
        }
        if( ( temp=this.winning_move(this.change_sign(this.current_symbol)) )>=2)
            return -1; //Invalid move(opposite_winning_move)



        if(temp==1)
        {
            int i0,j0;
            value=0;
            i0=pp.first;j0=pp.second;
            list.add(new Pair(i0,j0));
            this.matrix[i0][j0]=this.current_symbol;
            this.current_symbol=this.change_sign(this.current_symbol);

            //System.out.println("SAJAL"+move_number); this.print();

            value=this.ujjwal_move(i0,j0);
            move_number--;
            this.current_symbol=this.change_sign(this.current_symbol);
            this.matrix[i0][j0]=ch0;
            priority_level*=1000;

            // System.out.println(i1+" "+j1+" "+move_number+"--->"+value);
            pp=new Pair(i0,j0);
            if(value==-1)
                return value;
            else if(value==2)
                return priority_level;

            return value;
        }
        else
        {


            for(int i=1;i<=3;i++)
            {
                for(int j=1;j<=3;j++)
                {
                    if(this.matrix[i][j]==ch0)
                    {
                        this.matrix[i][j]=this.current_symbol;
                        this.current_symbol=this.change_sign(this.current_symbol);


                        //System.out.println("SAJAL"+move_number);this.print();
                        value = this.ujjwal_move(i,j);
                        this.current_symbol=this.change_sign(this.current_symbol);
                        move_number--;
                        this.matrix[i][j]=ch0;
                        priority_level*=1000;


                        if(value!=-1)
                        {
                            if(value==2)
                            {
                                list.add(new Pair( priority_level,new Pair(i,j) ) );
                                value1+=priority_level;
                            }
                            else
                            {

                                list.add(new Pair( value,new Pair(i,j) ) ) ;
                                value1+=value;
                            }

                        }


                    }
                }
            }
            if(list.isEmpty()==true)
                return -1;
            else
            {
                Collections.sort(list,new Comparator<Pair<Long,Pair<Integer,Integer> > >() {
                            public int compare(Pair<Long,Pair<Integer,Integer> >p1 ,Pair<Long,Pair<Integer,Integer> >p2)
                            {
                                return p2.first.compareTo(p1.first);
                            }
                        }
                );
                int random=1;

                Random rand=new Random();
                if(move_number==1)
                    for(int i=0;i<list.size()-1;i++)
                    {
                        if(list.get(i).first.compareTo(list.get(i+1).first)==0)
                            random++;
                        else break;
                    }
                //System.out.println(random+" "+rand.nextInt(random));
                pp=list.get(rand.nextInt(random)).second;
                if(move_number==1)
                    System.out.println(i1+" "+j1+" "+move_number+"--->"+list);

            }
        }


        return value1;
    }

    public  int solve(char ch[][],char chh)
    {

        Computer_move obj=new Computer_move();
        obj.init();
        gamelevel=0;
        int move_number1,x,y;

        move_number1=0;
        Scanner sc=new Scanner(System.in);

        ch0=' ';ch1='0';ch2='X';


        for(int i=1;i<=3;i++)  for(int j=1;j<=3;j++) if(ch[i][j]==' ')gamelevel++;
        for(int i=1;i<=3;i++)  for(int j=1;j<=3;j++)obj.matrix[i][j]=ch[i][j];

            /*obj.matrix[1][1]=ch0;
            obj.matrix[1][2]=ch0;
            obj.matrix[1][3]=ch0;
            obj.matrix[2][1]=ch0;
            obj.matrix[2][2]=ch0;
            obj.matrix[2][3]=ch0;
            obj.matrix[3][1]=ch0;
            obj.matrix[3][2]=ch0;
            obj.matrix[3][3]=ch0;
            */

        // obj.print();
        pp=new Pair(0,0);
        System.out.println("YOUR MOVE--->"+gamelevel);
        obj.print();
        obj.current_symbol=chh;
        move_number=0;

        gamelevel=Math.min(6,gamelevel);
        priority_level=(long)1e15;
        obj.sajal_move(0,0);

        obj.matrix[obj.pp.first][obj.pp.second]=obj.current_symbol;



        System.out.println("MY MOVE-->"+obj.pp);
        obj.print();
        return mp.get(obj.pp);



    }

}

