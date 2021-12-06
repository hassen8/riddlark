package Riddlark;

public class PlayerBase {
    static Player[] players= new Player[100];
    static int  playerNo =0;
    static int grouping = 0;
    
    public static void addPlayer(Player player){
        players[playerNo] = player;
        playerNo++;
    }
    
   public static Player checkPlayer(String pName){
       if(pName!=null){
                  for (int i = 0; i < playerNo; i++) {
                String tempName = players[i].getUname();
                if (pName.equals(tempName)) {
                    return players[i];
                }
            }
        }
        return null;
       }
   }
    
