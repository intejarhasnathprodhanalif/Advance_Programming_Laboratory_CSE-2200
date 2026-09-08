
public class ClassandObject {
    public static void main(String[] args)
    {
        //Dog dog = new Dog();
        //dog.makeSound();//abstract class example

        //Cat cat = new Cat();
        //cat.makeSound();//abstract class example

        //Plants plants = new Plants();
        //plants.consume();//interface example

        //Animals animals = new Animals();
        //animals.consume();//interface example

        //Cow cow = new Cow();
        //cow.makeSound();

        //Predator lion = new Lion();
        //lion.characteristics();//dynamic binding example

        //Prey deer = new Deer();
        //deer.characteristics();//dynamic binding example

        //Bear bear = new Bear();
        //bear.sleep();//example for functional interface

        /*Sleep sheeps = new Sleep()
        {
            @Override
            public void sleep()
            {
                System.out.println("Sheeps sleep in their sheds");
            }
        };//anonymous class

        sheeps.sleep();*/

        //Goat goat = new Goat();
        printConsume(() ->
        {
            System.out.println("Consumes grass");
        }); //lambda example

    }



    static void printConsume(Organism obj)
    {
        obj.consume();
    }
}

class Dog extends Animal
{
    @Override
    void makeSound()
    {
        System.out.println("Woof");
    }
}

class Cat extends Animal
{
    @Override
    void makeSound()
    {
        System.out.println("Meow");
    }
}

class Plants implements Organism
{
    @Override
    public void consume()
    {
        System.out.println("Consumes sunlight");
    }
}

class Animals implements Organism
{

    public void consume()
    {
        System.out.println("Consumes food");
    }
}

class Cow extends Animal
{
    void makeSound()
    {
        System.out.println("Moo");
    }
}


class Lion extends Predator
{
    void characteristics()
    {
        System.out.println("Lion eats prey");
    }
}

class Deer extends Prey
{
    void characteristics()
    {
        System.out.println("Deer gets eaten by predator");
    }
}

class Bear implements Sleep
{
    @Override
    public void sleep()
    {
        System.out.println("Bear sleeps in den");
    }
}

class Goat implements Organism
{
    @Override
    public void consume()
    {
        System.out.println("Consumes grass");
    }
}





