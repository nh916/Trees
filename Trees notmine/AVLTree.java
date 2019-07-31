import java.util.*;

public class AVLTree<T extends Comparable<T>> extends BinaryTree<T> {
    private AVLTree<T> leftChild;
    private AVLTree<T> rightChild;
    private AVLTree<T> parent;
    public int balance;
    public AVLTree(T item) {
        this(item, null);
    }
    public AVLTree(T item, int balance){
        super(item);
        this.balance = balance;
    }
    public AVLTree(T item, AVLTree<T> parent){
        super(item);
        this.balance = 0;
        this.parent = parent;
        this.leftChild = null;
        this.rightChild = null;
    }
    public AVLTree<T> getLeftChild(){
        return this.leftChild;
    }
    public void setLeftChild(T item){
        this.leftChild = new AVLTree<>(item);
        this.leftChild.setParent(this);
        this.leftChild.setItem(item);
    }
    public boolean hasLeftChild(){
        return !(this.getLeftChild()==null);
    }
    public AVLTree<T> getRightChild(){
        return this.rightChild;
    }
    public void setRightChild(T item){
        this.rightChild = new AVLTree<>(item);
        this.rightChild.setParent(this);
        this.rightChild.setItem(item);
    }
    public boolean hasRightChild(){
        return !(this.getRightChild()==null);
    }
    private boolean hasParent(){
        return !(this.parent==null);
    }
    private void setParent(AVLTree<T> input){
        this.parent = input;
    }
    private AVLTree<T> getParent(){
        return this.parent;
    }
//    private int getBalance(){
//        return this.balance;
//    }
    private int getBalance() {
        if (this.getItem() == null){
            return 0;
        }
            if(!this.hasLeftChild()&&this.hasRightChild()){
                setBalance(0 - height(this.rightChild));
                return(0 - height(this.rightChild));
            }
            else if(!this.hasRightChild()&& this.hasLeftChild()){
                setBalance(height(this.leftChild));
                return(height(this.leftChild));
            }
            else{
                setBalance(height(this.leftChild) - height(this.rightChild));
                return(height(this.leftChild) - height(this.rightChild));
            }
        }
//        else{
//            if(!node.hasLeftChild() && node.hasRightChild()){
//                setBalance(0 - node.getRightChild().height());
//                return balance;
//            }
//
//            else if(!node.hasRightChild() && node.hasLeftChild()){
//                setBalance(node.getLeftChild().height());
//                return balance;
//            }
//
//            else{
//                setBalance(node.getLeftChild().height() - node.getRightChild().height());
//                return balance;
//            }
//
//        }
//    }
    private void setBalance(int input){
        this.balance = input;
    }
    public T getItem(){
       return super.getItem();
    }
    private int max(int a, int b){
        return (a > b) ? a : b;
    }
    public int height(AVLTree<T> node){
        if (node == null)
            return 0;
        else
        {
            /* compute the depth of each subtree */
            int lDepth = height(node.leftChild);
            int rDepth = height(node.rightChild);
            /* use the larger one */
            if (lDepth > rDepth)
                return (lDepth + 1);
            else
                return (rDepth + 1);
        }
    }
    public AVLTree<T> find(T item){
      return (AVLTree<T>)super.find(item);
    }

    @Override
    public AVLTree<T> insert(T item){
        return add(item);
    }
    private AVLTree<T> add(T item){
        if(item.compareTo(this.getItem())>0){
            if(!this.hasRightChild()){
                this.setRightChild(item);

            }
            else return this.getRightChild().insert(item);
        }
        else if(item.compareTo(this.getItem())<0) {
            if (!this.hasLeftChild()) {
                this.setLeftChild(item);

            } else return this.getLeftChild().insert(item);
        }

        /* 2. Update height of this ancestor node */
        if(!this.hasLeftChild() && this.hasRightChild())
            this.setBalance(1+ this.rightChild.getBalance());
        else if(!this.hasRightChild() && this.hasLeftChild())
            this.setBalance(1+ this.getLeftChild().getBalance());
        else
        this.setBalance(1 + max(this.getLeftChild().getBalance(), this.getRightChild().getBalance()));

        /* 3. Get the balance factor of this ancestor
              node to check whether this node became
              unbalanced */
        int balance = this.getBalance();

        // If this node becomes unbalanced, then there
        // are 4 cases Left Left Case
        if (balance > 1 && this.getItem().compareTo(this.getLeftChild().getItem()) < 0)
            return this.rotateRight();

        // Right Right Case
        if (balance < -1 && this.getItem().compareTo(this.getRightChild().getItem()) > 0)
            return this.rotateLeft();

        // Left Right Case
        if (balance > 1 && this.getItem().compareTo(this.getLeftChild().getItem()) > 0) {
            this.setLeftChild(this.getLeftChild().rotateLeft().getItem());
            return this.rotateRight();
        }

        // Right Left Case
        if (balance < -1 && this.getItem().compareTo(this.getRightChild().getItem()) < 0) {
            this.setRightChild(this.getRightChild().rotateRight().getItem());
            return this.rotateLeft();
        }

        /* return the (unchanged) node pointer */
        return this;

    }
    public AVLTree<T> rotateLeft(){
        return doLeft(this);
    }
    private AVLTree<T> doLeft(AVLTree<T> node){
        AVLTree<T> temp = node.rightChild;
//        node.setRightChild(temp.leftChild.getItem());
//        temp.setLeftChild(node.getItem());
//        return temp;
        if(temp.hasLeftChild()){
            node.setRightChild(temp.leftChild.getItem());
            temp.setLeftChild(node.getItem());

        }
        else{
            temp.setLeftChild(node.getItem());

        }
//        AVLTree<T> temp = node.getRightChild();
//        AVLTree<T> temp2 = temp.getLeftChild();
//
//        temp.setLeftChild(node.getItem());
//        node.setRightChild(temp2.getItem());
        if(!node.hasLeftChild())
            node.balance = max(0, node.getRightChild().height() + 1);
        else if(!node.hasRightChild())
            node.balance = max(node.getLeftChild().height(),1);
        else
            node.balance = max(node.getLeftChild().height(), node.getRightChild().height() + 1);
        if(!temp.hasLeftChild())
            temp.balance = max(0, temp.getRightChild().height() + 1);
        else if(!temp.hasRightChild())
            temp.balance = max(temp.getLeftChild().height(),  1);
        else
            temp.balance = max(temp.getLeftChild().height(), temp.getRightChild().height() + 1);
        return temp;
    }
    public AVLTree<T> rotateRight(){
        return doRight(this);
    }
    private AVLTree<T> doRight(AVLTree<T> node){
        AVLTree<T> temp = node.leftChild;
//        node.setLeftChild(temp.rightChild.getItem());
//        temp.setRightChild(node.getItem());
//        return temp;
        if(temp.hasRightChild()){
            node.setLeftChild(temp.rightChild.getItem());
            temp.setRightChild(node.getItem());
        }
        else{
            temp.setRightChild(node.getItem());
        }
//        AVLTree<T> temp = node.getLeftChild();
//        AVLTree<T> temp2 = temp.getRightChild();
//
//        temp.setRightChild(node.getItem());
//        node.setLeftChild(temp2.getItem());

        if(!node.hasLeftChild())
            node.balance = max(0, node.getRightChild().height() + 1);
        else if(!node.hasRightChild())
            node.balance = max(node.getLeftChild().height(),1);
        else
            node.balance = max(node.getLeftChild().height(), node.getRightChild().height() + 1);
        if(!temp.hasLeftChild())
            temp.balance = max(0, temp.getRightChild().height() + 1);
        else if(!temp.hasRightChild())
            temp.balance = max(temp.getLeftChild().height(),  1);
        else
            temp.balance = max(temp.getLeftChild().height(), temp.getRightChild().height() + 1);
        return temp;
    }
    public void showData(){
        Stack<BinaryTree<T>> treeStack = new Stack<>();
        treeStack.push(this);
        int numOfBlanks = 32;
        boolean isRowEmpty = false;
        System.out.println("\n");

        while (!isRowEmpty) {
            Stack<BinaryTree<T>> localStack = new Stack<>();
            isRowEmpty = true;

            for (int x = 0; x < numOfBlanks; x++)
                System.out.print(" ");

            while (!treeStack.isEmpty()) {
                BinaryTree<T> temp = (BinaryTree<T>)treeStack.pop();
                if (temp != null)
                {
                    System.out.print(temp.getItem());
                    localStack.push(temp.leftChild);
                    localStack.push(temp.rightChild);

                    if (temp.leftChild != null || temp.rightChild != null)
                        isRowEmpty = false;
                }
                else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }

                for (int y = 0; y < numOfBlanks*2-2; y++)
                    System.out.print(" ");
            }
            System.out.println();
            numOfBlanks /= 2;
            while (!localStack.isEmpty())
                treeStack.push(localStack.pop());

        }
        System.out.println();
    }
//    private AVLTree<T> balance(AVLTree<T> x) {
//        if (balanceFactor(x) < -1) {
//            if (balanceFactor(x.right) > 0) {
//                x.right = rotateRight(x.right);
//            }
//            x = rotateLeft(x);
//        }
//        else if (balanceFactor(x) > 1) {
//            if (balanceFactor(x.left) < 0) {
//                x.left = rotateLeft(x.left);
//            }
//            x = rotateRight(x);
//        }
//        return x;
//    }
}
