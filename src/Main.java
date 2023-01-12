
//ДЗ:
//        ● Добавить 4-го игрока Medic, у которого есть способность лечить после каждого
//        раунда на N-ное количество единиц здоровья только одного из членов команды,
//        имеющего здоровье менее 100 единиц. Мертвых героев медик оживлять не может,
//        и лечит он до тех пор пока жив сам. Медик не участвует в бою, но получает урон от
//        Босса
import java.util.Arrays;
import java.util.Random;
public class Main {


    public static int bossHealth = 700;
    public static int bossDamage = 50;
    public  static int[] heroesHealth = {90, 200, 250,200};
    public  static int[] heroesDamage = {20, 25, 30,0};
    public  static String[] heroesAttackType = {"Logan", "Cyclops","Wizard","Medic"};
    public  static String bossBarrier;
    public static boolean  indexMedic;



    public static void main(String[] args) {
        printStatistic();
        while (!isGameOver()){
            round();
        }
    }
    public static void Medic() {

        int heal = 100;

        for (int i = 0; i < heroesHealth.length; i++) {

            if (heroesHealth[i] < 100  && heroesHealth[i] > 0) {
                if (heroesAttackType[i].equals("Medic")){
                    break;
                }else {
                    heroesHealth[i] += heal;
                }

                System.out.println("medic heals up" + heroesAttackType[i]);
                break;

            }

        }
    }







    public static void bossTypebarrier(){
        Random random = new Random();
        int index = random.nextInt(heroesAttackType.length);
        bossBarrier = heroesAttackType[index];
        for (int i = 0; i < heroesAttackType.length; i++) {
            if (bossBarrier.equals(heroesAttackType[i])){
                System.out.println("Boss barrier equals " + bossBarrier);;
                bossHealth += heroesDamage[i];
            }
        }
    }

    public static void round(){
        bossHits();
        heroesHits();
        Medic();
        printStatistic();

    }

    public static boolean isGameOver(){
        if (bossHealth <= 0){
            System.out.println("HEROES WIN");
            return true;
        }
        boolean allHeroesDead = true;
        for (int j : heroesHealth) {
            if (j > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead){
            System.out.println("BOSS WIN");
        }
        return allHeroesDead;
    }

    public static void bossHits(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0){
                heroesHealth[i] -= bossDamage;
                if(heroesHealth[i]<0){
                    heroesHealth[i]=0;
                }
            }
        }
    }

    public static void heroesHits(){
        bossTypebarrier();
        for (int hero: heroesDamage) {
            if (bossHealth > 0){
                if (hero > bossHealth){
                    bossHealth =0;
                }
                else {
                    bossHealth -= hero;
                }
            }
        }
    }

    public static void printStatistic(){
        System.out.println();
        System.out.println("Boss health " + bossHealth + ", " + " damage [" + bossDamage + "]");

        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println("Hero " + heroesAttackType[i] +" " + "health " + heroesHealth[i] + " damage [" +heroesDamage[i]+ "]");
        }
        System.out.println("----------------------------------");
    }
}
