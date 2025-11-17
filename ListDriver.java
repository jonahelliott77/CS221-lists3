import java.util.ListIterator;

public class ListDriver {
    public static void main(String[] args) {
        IUDoubleLinkedList<Integer> list = new IUDoubleLinkedList<Integer>();
        System.out.println(list.toString());

        list.addToFront(1);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);
        list.addToFront(2);

        System.out.println(list.toString());

        ListIterator<Integer> lit = list.listIterator((int)(list.size()/2) + 1);
        lit.add(3);

        System.out.println(lit.previousIndex() + ", " + lit.nextIndex() + ", " + lit.hasNext() + ", " + lit.hasPrevious() + ", ");

//        list.addToRear(4);
        System.out.println(list.toString());
    }
}
