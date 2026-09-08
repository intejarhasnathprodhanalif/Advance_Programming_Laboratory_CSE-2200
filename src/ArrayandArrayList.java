import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ArrayandArrayList {
    static public void main(String[] args)
    {
        //String[] friendsArray=new String[4];
        String[] friendsArray={"Intejar", "Hasnath", "Prodhan", "Alif"};
        //array has a size limit given by the user. for changing it, it has to be done inside the code
        //array can store elements of any data types i.e. primitive or object

       /* Scanner scanner = new Scanner(System.in); // this is to take user input
        int[] numbers = new int[5]; //normal array declaration
        for(int i=0; i<5; i++)
        {
            numbers[i]=scanner.nextInt();
        }*/

        //ArrayList<String> friendsArrayList=new ArrayList<>(); //this is another way of declaration
        ArrayList<String> friendsArrayList=new ArrayList<>(Arrays.asList("Intejar", "Hasnath", "Prodhan", "Alif"));
        //ArrayList does not have a size barrier. I mean defining its size is not needed.
        //One can put elements in an arraylist by "Arrays.asList()" method. Inside the bracket, put all your elements separated by comma
        //ArrayList can only store object as its elements. No primitive data type elements. But is supports wrapper types for primitives like int=Integer


        //getting an element
        System.out.println(friendsArray[1]);
        System.out.println(friendsArrayList.get(0));


        //getting the size
        System.out.println(friendsArray.length);
        System.out.println(friendsArrayList.size());


        //adding an element to arraylist
        friendsArrayList.add("Rafiad");
        System.out.println(friendsArrayList.get(4));
        //this can not be done with array because array has a fixed size

        //set an element
        //friendsArray[0]="Irtaja";
        System.out.println(friendsArray[0]);
        friendsArrayList.set(0, "Mahiya");
        System.out.println(friendsArrayList.get(0));


        //remove an element
        friendsArrayList.remove(2);
        System.out.println(friendsArrayList.get(2));
        //can't do it with array because array has a fixed size


        //print elements
        System.out.println(friendsArrayList);
        //System.out.println(friendsArray);//this will give the memory address of the array. to print the array, we must implement a loop
        for(int i=0; i<5; i++)
        {
            System.out.println(friendsArray[i]);
        }

        /*for(int i=0; i<5; i++)
        {
            System.out.println(numbers[i]);
        }*/



        //arraylist is better than array because
        //automatic resizing
        //easy adding, removing and printing elements
        //makes the work easier by predefined methods


    }
}
