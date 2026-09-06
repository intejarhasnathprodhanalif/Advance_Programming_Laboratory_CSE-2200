import java.util.ArrayList;
import java.util.LinkedList;

public class LinkedListandArrayList {
    public static void main(String[] args)
    {
        LinkedList<String> namesLinkedList=new LinkedList<>();
        ArrayList<String> namesArrayList=new ArrayList<>();

        //adding an element
        namesLinkedList.add("Intejar");
        //namesLinkedList.add("Hasnath");
        namesLinkedList.add("Prodhan");
        namesLinkedList.add("Alif");

        namesArrayList.add("Irtaja");
        namesArrayList.add("Hasnath");
        //namesArrayList.add("Prodhan");
        namesArrayList.add("Oishorjo");
        //same process for both


        //getting an element
        System.out.println(namesLinkedList.get(0));
        System.out.println(namesArrayList.get(0));
        //same process

        //adding an element in the middle
        namesLinkedList.add(1, "Hasnath");
        namesArrayList.add(2, "Prodhan");
        //same process. pass the index, then the element


        //printing all the elements
        System.out.println(namesArrayList);
        System.out.println(namesLinkedList);
        //same process

    }
}
