public class Strings {
    public static void  main(String[] args)
    {
        //two ways to declare
        //method-1: String s = new String("Hello");
        //method-2:
        String s = "Intejar Hasnath Prodhan Alif";

        int length=s.length();//gives the length of the string
        char letter=s.charAt(11);//gives the character at index 11
        int index=s.indexOf("a");//gives the index of 'a'. for multiple 'a', gives the index of the first 'a'
        int lastIndex=s.lastIndexOf("a");//gives the last index of 'a'

        System.out.println(length + " " + letter + " " + index + " " + lastIndex);

        s=s.toUpperCase();//all char to upper case
        System.out.println(s);
        s=s.toLowerCase();//all char to lower case
        System.out.println(s);

        String ss="     JAVA     ";

        System.out.println(ss);
        ss=ss.trim();//trims the extra spaces
        System.out.println(ss);

        ss=ss.replace("A", "O");//replaces a character with another one
        System.out.println(ss);

        if(ss.isEmpty())//checks emptyness of string
        {
            System.out.println("Empty String!!");
        }

        else if(!ss.isEmpty())
        {
            System.out.println(ss.isEmpty());//isEmpty() returns boolean value. so it will print true/false
            System.out.println("Non-empty String!!");
        }

        //check is a letter is in the string or not
        if(ss.contains("a"))
        {
            System.out.println("There is a 'a'");
        }

        else {
            System.out.println("There is no 'a' ");
        }
        //will return true/false


        //checking if two strings are equal
        if(ss.equals(s))//without s, you can pass another string inside the double quotation as argument
        {
            System.out.println("ss=s");
        }

        else {
            System.out.println("ss!=s");
        }
        //will return true/false




    }
}
