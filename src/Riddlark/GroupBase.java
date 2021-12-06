package Riddlark;

import java.util.ArrayList;

public class GroupBase {

    static int id = 0;
    public static ArrayList<Group> groups = new ArrayList<>();
    static String message;

    public static String addPlayerToGroup(Player player) {
        if (groups.isEmpty()) {
            System.out.println("Creating the first group in the list, and adding the player");
            groups.add(new Group(id, player));
            id++;
            message = "You've been added to group number: " + (id);
            return message;

        } else {
            if (groups.get(id - 1).isFull() || groups.get(id - 1).isInSession()) {
                System.out.println("The " + id + " group is full or In Session, creating new group and adding the player to it with Gid " + (id + 1));
                groups.add(new Group(id, player));
                id++;
                message = "You've been added to group number: " + (id);
                return message;
            } else {
                System.out.println("Adding player to the " + id + " group");
                groups.get(id - 1).addPlayer(player);
                message = "You've been added to group number: " + (id);
                return message;
            }
        }

    }

    public static int checkPlayersInGroup(int id) {
        return groups.get(id).getPlayerNo();
    }

}
