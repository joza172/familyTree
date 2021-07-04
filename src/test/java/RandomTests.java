import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class RandomTests {
    @ParameterizedTest(name = "test{0}")
    @DisplayName("Should work with random test")
    @ValueSource(ints = {1,2,3,4})
    public void randomTests(int testNumber){
        String testPath = ".\\testFiles\\tests\\test" + testNumber;
        String resultPath = ".\\testFiles\\results\\test" + testNumber;
        String output = "";
        output = App.appStart(testPath);

        String correct = "";
        File file = new File(resultPath);
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
