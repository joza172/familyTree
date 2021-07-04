import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MainTests {
    @Test
    @DisplayName("Should throw exception if a loop or some kind of abnormality occurs")
    public void exceptionCheck(){
        Person grandFather = new Person("Nikola");
        Person father = new Person("Hrvoje");
        Person uncle = new Person("Davor");
        Person son = new Person("Josip");

        grandFather.addChild(father);
        grandFather.addChild(uncle);
        father.addChild(son);

        Assertions.assertAll(() -> Assertions.assertThrows(LoopException.class, () -> son.addChild(grandFather)),
                () -> Assertions.assertThrows(LoopException.class, () -> son.addChild(uncle)),
                () -> Assertions.assertThrows(LoopException.class, () -> son.addChild(father)),
                () -> Assertions.assertThrows(LoopException.class, () -> son.addChild(son)));
    }

    @Test
    @DisplayName("Should write out the parents independently")
    public void twoParents(){
        String output = App.appStart(".\\testFiles\\tests\\parentsIndependent");

        String correct = "";
        File file = new File(".\\testFiles\\results\\parentsIndependent");
        Scanner sc = null;
        try{
            sc = new Scanner(file);
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        //reading from file with correct answer
        while(sc.hasNextLine()){
            String row;
            row = sc.nextLine();
            if(row.isEmpty())break;

            correct += row + "\n";
        }

        Assertions.assertEquals(correct,output);
    }

    @Test
    @DisplayName("Should work for big family")
    public void bigFamily(){
        String output = App.appStart(".\\testFiles\\tests\\bigFamily");

        String correct = "";
        File file = new File(".\\testFiles\\results\\bigFamily");
        Scanner sc = null;
        try{
            sc = new Scanner(file);
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        //reading from file with correct answer
        while(sc.hasNextLine()){
            String row;
            row = sc.nextLine();
            if(row.isEmpty())break;

            correct += row + "\n";
        }

        Assertions.assertEquals(correct,output);
    }
}
