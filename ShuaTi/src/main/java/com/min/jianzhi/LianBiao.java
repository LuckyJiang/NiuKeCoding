package com.min.jianzhi;

import com.min.jianzhi.entity.ListNode;
import lombok.val;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/6/9 16:40
 * 36 55
 */
public class LianBiao {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(1);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(3);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node1;
        ListNode node = detectCycle(node1);
        System.out.println(node);
        Set<ListNode> hashSet = new HashSet<>();
        hashSet.add(node1);
        if(hashSet.contains(node2)){
            System.out.println("node1和node2是两个不同的对象，但却被当成了同一个对象");
        }else{
            System.out.println("node1和node2是两个不同的对象，但却被当做了不同的元素");
        }

    }


    /**
     * JZ36: 两个链表的第一个公共结点
     * @param pHead1
     * @param pHead2
     * @return
     */
    public ListNode FindFirstCommonNode(ListNode pHead1, ListNode pHead2) {
        while(pHead1 == null || pHead2 == null){
            return null;
        }
        int count1 = 0, count2 = 0;
        ListNode p1 = pHead1, p2 = pHead2;
        while (p1 != null) {
            p1 = p1.next;
            count1 ++ ;
        }
        p1 = pHead1;
        while (p2 != null) {
            p2 = p2.next;
            count2 ++ ;
        }
        p2 = pHead2;
        int diff = count1 - count2;
        ListNode result = null;
        if(diff > 0){
            while (diff >0){
                p1 = p1.next;
                diff --;
            }
        }else{
            while (diff < 0){
                p2 = p2.next;
                diff ++;
            }
        }
        while (p1 != null){
            if(p1 == p2){
                if(result == null)
                    result = p1;
            }else{
                result = null;
            }
            p1 = p1.next;
            p2 = p2.next;
        }
        return result;
    }

    /**
     * JZ55:链表中环的入口节点
     * 思路：快慢指针
     * @return
     */
    public ListNode EntryNodeOfLoop(ListNode pHead) {
        ListNode fast = pHead;
        ListNode slow = pHead;


        while (fast != null && fast.next!= null){
            fast = fast.next.next;
            slow= slow.next;
            //判断是否有环的条件1
            if(fast == slow)
                break;
        }
        //判断是否有环的条件2
        if(fast == null || fast.next == null)
            return null;
        fast = pHead;
        while (fast != slow){
            fast = fast.next;
            slow = slow.next;
        }
        return fast;

    }
    /**
     * JZ55:链表中环的入口节点
     * 思路：HashSet
     */
    public static ListNode detectCycle(ListNode head) {

        if(head == null){
            return head;
        }
        if(head.next == null){
            return head.next;
        }
        //创建新的HashSet,用于保存节点
        HashSet<ListNode> hash = new HashSet<ListNode>();
        //遍历链表
        while(head!=null){
            //判断哈希表中是否含有某节点，没有则保存，含有则返回该节点
            if(hash.contains(head)){
                return head;
            }
            //不含有，则进行保存，并移动指针
            hash.add(head);
            head=head.next;
        }
        return head;
    }

    /**
     * JZ56:删除链表中重复的结点
     */
    public static ListNode JZ56(ListNode pHead){
        if(pHead!=null || pHead.next==null)
            return pHead;

        if(pHead==null)
            return null;
        ListNode head = new ListNode(-1);
        head.next = pHead;
        ListNode pre = head;
        ListNode first = head.next;
        ListNode last = head.next.next;
        while (last != null){
            if(first.val == last.val){
                while (last != null && first.val == last.val){
                    last = last.next;
                }
                if(first.next != last){
                    pre.next = last;
                    first = last;
                }
                if(last != null)  last = last.next;
            }else{
                pre = first;
                first = last;
                last = last.next;
            }
        }
        return head.next;
    }

}
