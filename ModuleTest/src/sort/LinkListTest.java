package sort;

import org.junit.Test;

/**
 * @author 刘璞
 * @version 1.0
 * @date 2025/2/8 21:35
 */
public class LinkListTest {

    /**
     * 单链表结点
     */
    public static class Node<V>{

        V value;

        Node next;
        public Node(V value) {
            this.value = value;
        }
    }

    /**
     * 双链表结点
     */
    public static class DNode<T>{

        T value;

        DNode pre;

        DNode next;

        public DNode(T value){
            this.value = value;
        }

    }

    /**
     * 测试单链表局部
     */

    @Test
    public void testSingleListReverse(){
        Node<Integer> head = new Node<>(1);
        head.next = new Node(2);
        head.next.next= new Node(3);
        head.next.next.next = new Node(4);
        printLinkList(head);
        Node newHead = reverseSingleLinkList(head);
        printLinkList(newHead);
    }

    @Test
    public void testDoubleListReverse(){
        DNode<Integer> head = new DNode<>(1);
        head.next = new DNode(2);
        head.next.next= new DNode(3);
        head.next.next.next = new DNode(4);
        printDLinkList(head);
        DNode newHead = reverseDoubleLinkList(head);
        printDLinkList(newHead);
    }

    /**
     * 测试单链表局部反转
     */
    @Test
    public void testRangeReverseSingle(){
        Node<Integer> head = new Node<>(1);
        Node node2 = new Node(2);
        head.next = node2;
        Node node3 = new Node(3);
        head.next.next= node3;
        Node node4 = new Node(4);
        head.next.next.next = node4;
        printLinkList(head);
        rangeReverseSingle(node2, node3);
        head.next = node3;
        printLinkList(head);
    }

    private void rangeReverseSingle(Node start, Node end) {
        Node next = end.next;
        Node pre = null;
        Node cur = start;
        Node nextTemp = null;
        while(cur != next){
            nextTemp = cur.next;
            cur.next = pre;

            //开始后移
            pre = cur;
            cur = nextTemp;
        }
        start.next = next;
    }

    private DNode reverseDoubleLinkList(DNode head){
        DNode pre = null;
        DNode curr = head;
        while(curr != null){
            DNode nextTemp = curr.next;
            //前后结点开始交换
            curr.pre = nextTemp;
            curr.next = pre;
            //pre 开始后移
            pre = curr;
            //curr开始后移
            curr = nextTemp;
        }
        return pre;
    }

    private Node reverseSingleLinkList(Node<Integer> head) {
        Node pre = null;
        Node cur = head;
        while(cur != null){
            Node temp = cur.next;
            cur.next = pre;
            //pre后移
            pre = cur;
            //cur后移
            cur = temp;
        }
        return pre;
    }

    private void printDLinkList(DNode head){
        DNode curr = head;
        while(curr != null){
            System.out.print(curr.value + " ");
            curr = curr.next;
        }
        System.out.println();
    }

    private void printLinkList(Node head){
        Node curr = head;
        while(curr != null){
            System.out.print(curr.value + " ");
            curr = curr.next;
        }
        System.out.println();
    }

}
