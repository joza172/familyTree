import java.util.LinkedList;
import java.util.List;

public class Parent {

    String name;
    List<Parent> children;
    List<Parent> ancestor;

    public Parent(String name) {
        this.name = name;
        children = new LinkedList<>();
        ancestor = new LinkedList<>();
    }

    public void addChild(Parent child){
        if(ancestor.contains(child) || child == this){
            System.out.println("Exception");
            System.exit(0);
        }
        children.add(child);
        child.addAncestor(this);
        for(Parent anc: ancestor)
            child.addAncestor(anc);
    }

    public void addAncestor(Parent anc){
        ancestor.add(anc);
        for(Parent child : children){
            child.addAncestor(anc);
        }
    }

    public void print(int numTabs){
        for(int i = 0; i < numTabs; i++){
            System.out.print("\t");
        }
        System.out.println(name);
        for(Parent child: children){
            child.print(numTabs + 1);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Parent))
            return false;

        Parent c = (Parent) o;

        // Compare the data members and return accordingly
        return name.equals(c.name);
    }
}