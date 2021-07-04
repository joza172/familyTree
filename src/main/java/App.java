import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final List<Person> parents = new LinkedList<>();
    private static final List<Person> children = new LinkedList<>();

    private static void addFam(String child, String parent){

        Person newPar = new Person(parent);
        if(!parents.contains(newPar)){
            parents.add(newPar);
        }
        else{
            newPar = parents.get(parents.indexOf(newPar));
        }

        Person newChild = new Person(child);
        if(!parents.contains(newChild)){
            parents.add(newChild);
        }
        else{
            newChild = parents.get(parents.indexOf(newChild));
        }

        newPar.addChild(newChild);
        children.add(newChild);
    }

    public static String appStart(String filePath){
        File file = new File(filePath);
        String output = "";

        Scanner sc = null;
        try{
            sc = new Scanner(file);
        }
        catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }


        //reading from file
        while(sc.hasNextLine()){
            String row;
            row = sc.nextLine();
            if(row.isEmpty())break;

            String[] members = row.split(" ");
            addFam(members[0], members[1]);
        }

        for (Person parent : parents) {
            //only print out the person if he/she is the oldest in its family tree
            if (!(children.contains(parent)))
                output += parent.print(0);
        }
        parents.clear();
        children.clear();
        return output;
    }


    public static void main(String[] args){
        System.out.print("File path: ");

        //checking whether the file with set path exists
        String filePath;
        File file;
        do{
            Scanner sc = new Scanner(System.in);

            filePath = sc.nextLine();

            file = new File(filePath);
            if(!file.exists()){
                System.out.println("Path- " + filePath + " not found");
                System.out.print("File path:");
            }
        }while(!file.exists());

        System.out.println(appStart(filePath));
    }
}
