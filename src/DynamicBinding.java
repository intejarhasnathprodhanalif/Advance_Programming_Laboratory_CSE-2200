public class DynamicBinding {

}

class Predator extends Animal
{
    @Override
    void makeSound()
    {
        System.out.println("Predator's Sound");
    }

    void characteristics()
    {
        System.out.println("Eating prey");
    }
}

class Prey extends Animal
{
    @Override
    void makeSound()
    {
        System.out.println("Prey's Sound");
    }

    void characteristics()
    {
        System.out.println("Getting eaten by predator");
    }

}

//dynamic binding is basically polymorphism
