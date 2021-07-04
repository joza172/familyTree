import java.util.LinkedList;
import java.util.List;

public class Person {

    String name;
    List<Person> children;
    List<Person> ancestors;


    public Person(String name) {
        this.name = name;
        children = new LinkedList<>();
        ancestors = new LinkedList<>();
    }

    public boolean addChild(Person child){
        if(ancestors.contains(child) || child == this){
            throw new LoopException();
        }
        for(Person pers: children){
            if(pers.children.contains(child)){
                throw new LoopException();
            }
        }
        for(Person pers: ancestors){
            if(pers.children.contains(child)){
                throw new LoopException();
            }
        }

        children.add(child);
        child.addAncestor(this);
        for(Person anc: ancestors)
            child.addAncestor(anc);
        return true;
    }

    public void addAncestor(Person anc){
        ancestors.add(anc);
        for(Person child : children){
            child.addAncestor(anc);
        }
    }


    //function that prints out a Person and all of its children
    //numTabs is the number of tabs depending on the "depth" of this person in the family tree
    public String print(int numTabs){
        String output = "";
        for(int i = 0; i < numTabs; i++){
            output += "    ";
        }
        output += name + "\n";
        for(Person child: children){
            output += child.print(numTabs + 1);
        }
        return output;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Person))
            return false;

        Person c = (Person) o;

        // Compare the data members and return accordingly
        return name.equals(c.name);
    }
}