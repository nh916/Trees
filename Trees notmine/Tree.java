
import java.util.*;
public class Tree<T> implements ITree<T>{
    private T value;
    private ArrayList<Tree<T>> children;
    public Tree(T value){
        this.value = value;
        this.children = new ArrayList<>();
    }
    public Tree(T value, ArrayList<Tree<T>> input){
        this.value = value;
        this.children = input;
    }

    @Override
    public T getItem() {
        return this.value;
    }
    public void setItem(T value){
        this.value = value;
    }
    @Override
    public ITree<T> find(T item) {
        if(value.equals(item)){
            return this;
        }
        else{
            for (Tree<T> child: children) {
                return child.find(item);
            }
            return null;
        }
//        if(children.contains(item)){
//            return new Tree<>(children.get(children.indexOf(item)));
//        }
//        else return null;
    }
    @Override
    public ITree<T> insert(T item) {
        children.add(new Tree<>(item));
        return new Tree<>(this.getItem(),children);
    }
    public Tree<T> remove(T item){
        children.remove(item);
        return new Tree<>(this.getItem(),children);
    }
    public int size(){
        return this.children.size() + 1;
    }
//    public Object[] toArray(){
//     Object[] result = new Object[size()];
//     result = this.toArray();
//     result = children.toArray()
//    }
//    public String showData() {
//        if (this.getItem() == null) {
//            return "null";
//        }
//        else {
//            return "\nData: " + getItem()
//                    + "\tLeft: " + leftChild.getItem()
//                    + "\tRight: " + rightChild.getItem()
//                    + "\tParent: " + parent.getItem();
//        }
//    }
}
