package com.min.springboot.jianzhi;


import com.min.springboot.entity.Tree;
import com.min.springboot.jianzhi.entity.TreeNode;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;


/**
 * @author jxm
 * @version 1.0
 * @date 2020/6/3 15:42
 * 38  118
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class shu {

    @Test
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

}
