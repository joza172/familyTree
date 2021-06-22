import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final List<Parent> parents = new LinkedList<>();
    private static final List<Parent> children = new LinkedList<>();

    private static void addFam(String child, String parent){

        Parent newPar = new Parent(parent);
        if(!parents.contains(newPar)){
            parents.add(newPar);
        }
        else{
            newPar = parents.get(parents.indexOf(newPar));
        }

        Parent newChild = new Parent(child);
        if(!parents.contains(newChild)){
            parents.add(newChild);
        }
        else{
            newChild = parents.get(parents.indexOf(newChild));
        }

        newPar.addChild(newChild);
        children.add(newChild);
    }
    public static void main(String[] args) throws FileNotFoundException {

        System.out.println("Upisi path file-a u kojem se nalazi tekst u obliku zadanom u readem file-u: ");

        File file;
        do{
            Scanner sc = new Scanner(System.in);
            String filePath;
            filePath = sc.nextLine();

            file = new File(filePath);
            if(!file.exists()){
                System.out.println("Ne valjani path file-a : " + filePath);
                System.out.println("Unesite valjani path file-a:");
            }
        }while(!file.exists());
        Scanner sc = new Scanner(file);
        String row;
        while(sc.hasNextLine()){
            row = sc.nextLine();
            if(row.isEmpty())break;

            String[] members = row.split(" ");
            addFam(members[0], members[1]);
        }

        System.out.println("Lista roditelja:");
        for(int i = 0; i < parents.size(); i++){
            if(!(children.contains(parents.get(i))))
            parents.get(i).print(0);
        }

    }
}
