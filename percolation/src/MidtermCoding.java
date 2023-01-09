import java.util.Stack;

public class MidtermCoding {
    //FOR TESTING ONLY
    // public Node makeList(int[] vals){
    //     Node head = null;
    //     Node curr = null;
    //     for(int val: vals){
    //         if(head == null){
    //             head = new Node(val, null);
    //             curr = head;
    //         } else {
    //             curr.next = new Node(val, null);
    //             curr = curr.next;
    //         }
    //     }
    //     return head;
    // }
    // //FOR TESTING ONLY
    // public void printList(Node head){
    //     System.out.print('[');
    //     Node curr = head;
    //     while(curr != null){
    //         if(curr.next == null){
    //             System.out.print(curr.item);
    //         } else {
    //             System.out.print(curr.item+", ");
    //         } 
    //         curr = curr.next;
    //     }
    //     System.out.println(']');
    // }
    // public static Node findLastDup(Node front) {
    //     // returns null if there is no duplicate
    //     if(front == null){
    //         return null;
    //     }

    //     Node last = front;
    //     Node curr = front.next;
    //     Node lastDupNode = null;
    //     while(curr != null){
    //         // update last duplicate if one exists
    //         if(curr.item == last.item){
    //             lastDupNode = curr;
    //         }
    //         last = curr;
    //         curr = curr.next;
    //     }
    //     return lastDupNode;
    // } 
    // public static void removeDup(Node front) {
    //     // Does nothing if list is empty
    //     if(front == null)
    //         return;

    //     // First instance of a number
    //     Node numStart = front;
    //     Node curr = front.next;
    //     while(curr != null){
    //         if(curr.item == numStart.item){
    //             // has numberStart.next skip any duplicates of itself
    //             numStart.next = curr.next;
    //         } else {
    //             // if new number is found, set numStart to that node
    //             numStart = curr;
    //         }
    //         curr = curr.next;
    //     }

    // } 
    // public static boolean foo(){
    //     return StdRandom.bernoulli(.5);
    // }

    private Node last;

    public void printList(){
        Node curr = last.next;
        while(curr != last){
            System.out.print(curr.data+" -> ");
            curr = curr.next;
        }
        System.out.println(curr.data);
    }

    public void makeList(){
        last = new Node(10, null);
        Node curr = last;
        for(int i = 1; i < 10; i++){
            curr.next = new Node(i, null);
            curr = curr.next;
        }
        curr.next = last;
    }

    public void mystery(int m){
        Node ptr = last.next;
        while(ptr != last){
            for(int i = 0; i < m; i++){
                ptr = ptr.next;
            }
            ptr.next = ptr.next.next;
        }
    }
    public static void main(String[] args) {
        MidtermCoding mc = new MidtermCoding();
        mc.makeList();
        mc.printList();
        mc.mystery();
        mc.printList();

        
    }
}
