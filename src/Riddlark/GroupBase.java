/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Riddlark;

import java.util.ArrayList;

/**
 *
 * @author hsn
 */
public class GroupBase {

    static int id = 0;
    public static ArrayList<Group> groups = new ArrayList<>();

    public static void addPlayerToGroup(Player player) {
        if (groups.isEmpty()) {
            System.out.println("Creating the first group in the list, and adding the player");
            groups.add(new Group(id, player));
            id++;
        } else {
            if(groups.get(id-1).isFull()){
                System.out.println("The "+id+" group is full, creating new group and adding the player to it with Gid " + (id+1));
            groups.add(new Group(id, player));
            id++;
            }else{
                System.out.println("Adding player to the "+id+ " group");
            groups.get(id-1).addPlayer(player);
            }
        }

    }

}
