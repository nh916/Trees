

import java.util.*;
public class BinaryTree<T extends Comparable<T>> extends Tree<T> implements ITraversable<T>, IMeasurable, IRotatable<T> {
    protected BinaryTree<T> leftChild;
    protected BinaryTree<T> rightChild;
    public int height;
    public BinaryTree(T value){
        super(value);
        this.leftChild = null;
        this.rightChild = null;
    }

    public T getItem(){
        return super.getItem();
    }
    public void setItem(T value){
        super.setItem(value);
    }
    public void setRightChild(T right){
        this.rightChild = new BinaryTree<>(right);
    }
    public BinaryTree<T> getRightChild(){
        return this.rightChild;
    }
    public boolean hasRightChild(){
        return !(this.getRightChild() == null);
    }
    public void setLeftChild(T left){
        this.leftChild = new BinaryTree<>(left);
    }
    public BinaryTree<T> getLeftChild(){
        return this.leftChild;
    }
    public boolean hasLeftChild(){
        return !(this.getLeftChild() == null);
    }

    @Override
    public ITree<T> find(T item){
        if(this.getItem()==null){
            return null;
        }
        if(this.getItem().compareTo(item)>0) {
            if(this.leftChild.getItem()==null) return null;
            return this.leftChild.find(item);
        }
        else if(this.getItem().compareTo(item)<0) {
            if(this.getRightChild().getItem()==null) return null;
            return this.rightChild.find(item);
        }
        else return this;
    }
    //finds the place of the newly inserted node
    @Override
    public ITree<T> insert(T item){
        return add(item);
    }
    private ITree<T> add(T item){
        if(item.compareTo(this.getItem())>0){
            if(!this.hasRightChild()){
                this.setRightChild(item);
                return this.rightChild;
            }
            else return this.rightChild.insert(item);
        }
        else if(item.compareTo(this.getItem())<0) {
            if (!this.hasLeftChild()) {
                this.setLeftChild(item);
                return this.leftChild;
            } else return this.leftChild.insert(item);
        }
        else return this;
    }
    public BinaryTree<T> remove(T item){
        return new BinaryTree<>(null);
    }

    // Pre-order
    // Pre-order
    // Pre-order
    @Override
    public ArrayList<T> nlr() { // Pre-order
        ArrayList <T> result = new ArrayList<>();
        nlr(this, result);
        return result;
    }
    private void nlr(BinaryTree<T> node, ArrayList<T> nlrList){
        if (node != null)
        {
            // add node to pre-orderList
            nlrList.add(node.getItem());
            //traverse left subtree
            nlr(node.getLeftChild(),nlrList);
            //traverse right subtree
            nlr(node.getRightChild(), nlrList);
        }
    }

    // In-order
    // In-order
    // In-order
    @Override
    public ArrayList<T> lnr() { // In-order
        ArrayList <T> result = new ArrayList<>();
        lnr(this, result);
        return result;
    }
    private void lnr(BinaryTree<T> node, ArrayList<T> lnrList){
        if (node != null)
        {
            // traverse left subtree
            lnr(node.getLeftChild(),lnrList);
            // add the node to the inorderList
            lnrList.add(node.getItem());
            // traverse right subtree
            lnr(node.getRightChild(),lnrList);
        }
    }

    // Post-order
    // Post-order
    // Post-order
    @Override
    public ArrayList<T> lrn() { // Post-order
        ArrayList <T> result = new ArrayList<>();
        lrn(this, result);
        return result;
    }
    private void lrn(BinaryTree<T> node, ArrayList<T> lrnList )
    {
        if (node != null)
        {
            // traverse the left subtree
            lrn(node.getLeftChild(),lrnList);
            // traverse the right subtree
            lrn(node.getRightChild(),lrnList);
            // output node data
            lrnList.add(node.getItem());
        }
    }

    // Breadth-first
    // Breadth-first
    // Breadth-first
    @Override
    public ArrayList<T> bfs() { // Breadth-first
        ArrayList <T> result = new ArrayList<>();
        bfs(this, result);
        return result;
    }
    private void bfs(BinaryTree<T> node, ArrayList<T> bfs){
        Queue<BinaryTree<T>> queue = new LinkedList<>();
        queue.add(node);
        while (!queue.isEmpty())
        {
            BinaryTree<T> tempNode = queue.poll();
            bfs.add(tempNode.getItem());
            /*Enqueue left child */
            if (tempNode.leftChild != null) {
                queue.add(tempNode.leftChild);
            }
            /*Enqueue right child */
            if (tempNode.rightChild != null) {
                queue.add(tempNode.rightChild);
            }
        }
    }


    @Override
    public int size() {
        return(size(this));
    }
    private int size(BinaryTree<T> node) {
        if (node == null) return(0);
        else {
            return(size(node.leftChild) + 1 + size(node.rightChild));
        }
    }
    public void setHeight(int input){
        this.height =input;
    }
    @Override
    public int height(){
        return height(this);
    }
    private int height(BinaryTree<T> node) {
        if (node == null)
            return 0;
        else
        {
            /* compute the depth of each subtree */
            int lDepth = height(node.leftChild);
            int rDepth = height(node.rightChild);
            /* use the larger one */
            if (lDepth > rDepth){
                this.height = lDepth+1;
                return (lDepth + 1);
            }

            else{
                this.height = rDepth+1;
                return (rDepth + 1);
            }

        }
    }


    @Override
    public ITree<T> rotateLeft() {
        return doLeft(this);
    }
    public ITree<T> doLeftRight(BinaryTree<T> node){
        node.setLeftChild(doLeft(node.getLeftChild()).getItem());
        return doRight(node);
    }
    private ITree<T> doLeft(BinaryTree<T> node){
        BinaryTree<T> temp = node.rightChild;
//        node.setRightChild(temp.leftChild.getItem());
//        temp.setLeftChild(node.getItem());
//        return temp;
        if(temp.hasLeftChild()){
            node.setRightChild(temp.leftChild.getItem());
            temp.setLeftChild(node.getItem());
            return temp;
        }
        else{
            temp.setLeftChild(node.getItem());
            return temp;
        }
    }
    @Override
    public ITree<T> rotateRight() {
        return doRight(this);
    }
    private ITree<T> doRightLeft(BinaryTree<T> node){
        node.setRightChild(doRight(node.getRightChild()).getItem());
        return doLeft(node);
    }
    private ITree<T> doRight(BinaryTree<T> node){
        BinaryTree<T> temp = node.leftChild;
//        node.setLeftChild(temp.rightChild.getItem());
//        temp.setRightChild(node.getItem());
//        return temp;
        if(temp.hasRightChild()){
            node.setLeftChild(temp.rightChild.getItem());
            temp.setRightChild(node.getItem());
            return temp;
        }
        else{
            temp.setRightChild(node.getItem());
            return temp;
        }
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
