package com.min.jianzhi;


import com.min.jianzhi.entity.Tree;
import com.min.jianzhi.entity.Tree;
import com.min.jianzhi.entity.TreeNode;
import com.sun.org.apache.bcel.internal.generic.IMPDEP1;
import net.bytebuddy.asm.Advice;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.*;


/**
 * @author jxm
 * @version 1.0
 * @date 2020/6/3 15:42
 * 38  118
 */

public class shu {

    public static void main(String[] args) {
        TreeNode node = new TreeNode(8);
        TreeNode node1 = new TreeNode(6);
        TreeNode node2 = new TreeNode(10);
        TreeNode node3 = new TreeNode(5);
        TreeNode node4 = new TreeNode(7);
        TreeNode node5 = new TreeNode(9);
        TreeNode node6 = new TreeNode(11);
        node.left = node1;
        node.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;
//        ArrayList<ArrayList<Integer>> print = Print(node);
        System.out.println(JZ59(node));
    }

    public void JZ38(){

        TreeNode node = new TreeNode(1);
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(1);
        TreeNode node4 = new TreeNode(1);
        node.left = node1;
        node.right = node2;
        node1.left = node3;
        node1.right = node4;
        ArrayList<ArrayList<Integer>> print = Print(node);
//        ArrayList<ArrayList<Integer>> print = JZ59(node);


    }

    /**
     * JZ38： 分治算法---递归
     * @param root
     * @return
     */
   /* public int TreeDepth(TreeNode root) {
        if(root == null)
            return 0;
        int max =0, left = 1, right = 1;
        if(root.left != null){
            left += TreeDepth(root.left);
        }
        if(root.right != null) {
            right += TreeDepth(root.right);
        }
        if(left > right){
            max = left;
        }else {
            max = right;
        }
        return max;
    }


    */

    /**
     * JZ:58:对称的二叉树
     * @param pRoot
     * @return
     */
    public boolean isSymmetrical(TreeNode pRoot) {

        if(pRoot == null)
            return true;
        return dSame(pRoot.left, pRoot.right);
    }
    public boolean dSame(TreeNode left, TreeNode right){
        if(left == null && right == null){
            return true;
        }
        if(left == null || right == null)
            return false;
        return left.val == right.val
                && dSame(left.left, right.right)
                && dSame(left.right , right.left);
    }

    /**
     * JZ:60 把二叉树打印成多行
     * @param pRoot
     * @return
     */
    public ArrayList<ArrayList<Integer>> Print(TreeNode pRoot) {
        ArrayList<ArrayList<Integer>> printList = new ArrayList<>();
        if(pRoot == null)
            return printList;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(pRoot);
        while (!queue.isEmpty()){
            int size = queue.size();
            ArrayList<Integer> list = new ArrayList<>();
            while (size != 0){
                TreeNode poll = queue.poll();
                list.add(poll.val);
                if(poll.left != null) queue.add(poll.left);
                if(poll.right != null) queue.add(poll.right);
                size --;
            }
            printList.add(list);
        }

        return printList;
    }

   /**
     * JZ118: 没有难度
     * @param root
     *//*
    public void Mirror(TreeNode root) {
        if(root == null)
            return ;
        TreeNode tem= null;
        if(root.left != null){
            tem = root.left;
        }
        if(root.right != null){
            root.left = root.right;
        }else{
            root.left = null;
        }
        root.right = tem;
        Mirror(root.left);
        Mirror(root.right);
    }*/

    /**
     * JZ62: 二叉搜索树的第K个节点
     * 知识点：二叉搜索树：是一个有序的二叉树，左子树均小于根节点，右子树均大于根节点
     * 思路：可以用中序遍历：左右根
     * @return
     */
   public TreeNode JZ62(TreeNode pRoot, int k){
       int index = 0;
       if(k <= 0 || pRoot ==  null)
           return null;
       Stack<TreeNode> stack = new Stack<>();
       while (!stack.isEmpty() || pRoot != null){
           if(pRoot == null){
               TreeNode pop = stack.pop();
               index ++;
               if(index == k)
                   return pop;
               pRoot = pop.right;
           }else{
                stack.push(pRoot);
                pRoot = pRoot.left;
           }
       }

       return  null;
   }

    /**
     * JZ66: 机器人的运动范围
     * 思路：可以采用DFS和BFS算法
     * 知识点：
     * @return
     */
    public int JZ66(int threshold, int rows, int cols){
        return -1;
    }

    /**
     * JZ59:按之字顺序打印二叉树
     */
    public static ArrayList<ArrayList<Integer> > JZ59(TreeNode pRoot) {
        ArrayList<ArrayList<Integer> > result = new ArrayList<>();
        if(pRoot == null)
            return  result;
        Stack<TreeNode> stack = new Stack<>(); //奇数进栈
        Stack<TreeNode> nodeStack = new Stack<>(); //偶数进栈
        stack.push(pRoot);
        boolean flag = true;
        while (!stack.isEmpty() || !nodeStack.isEmpty()){
            ArrayList<Integer> list = new ArrayList<>();
            if(flag){
                //遍历奇数层
                while (!stack.isEmpty()){
                    TreeNode pop1 = stack.pop();
                    list.add(pop1.val);
                    if(pop1.left!=null) nodeStack.push(pop1.left);
                    if(pop1.right !=null) nodeStack.push(pop1.right);
                }

            }else{
                //遍历偶数层
                while (!nodeStack.isEmpty()){
                    TreeNode pop = nodeStack.pop();
                    list.add(pop.val);
                    if(pop.right !=null) stack.push(pop.right);
                    if(pop.left!=null) stack.push(pop.left);
                }
            }
            flag = !flag;
            if(list.size()>0){
                result.add(list);
            }
        }
        return result;
    }



}
