package com.min.springboot.jianzhi;

import com.min.springboot.jianzhi.entity.ListNode;

/**
 * @author jxm
 * @version 1.0
 * @date 2020/6/9 16:40
 * 36 55
 */
public class LianBiao {

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
     * @param pHead
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

}
