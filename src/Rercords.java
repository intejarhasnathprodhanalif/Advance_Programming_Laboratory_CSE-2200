record animalsKilled(String type, int age, String weapon) {}
public class Rercords {
    public static void main(String[] args)
    {
        animalsKilled newKill = new animalsKilled("Elephant", 15, "Gun");
        System.out.println("Animal: " + newKill.type() + " Age: " + newKill.age() + " Weapon used: " + newKill.weapon());
    }
}
