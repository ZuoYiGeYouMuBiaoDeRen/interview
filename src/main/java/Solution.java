import javax.swing.tree.TreeNode;

/**
 * 描述:
 *
 * @author kongqingchao
 * @create 2021-05-23 12:05 下午
 */
public class Solution {

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    /**
     * node中是否包含p或q
     *
     * @param node
     * @param p
     * @param q
     * @return
     */
    private boolean dfs(TreeNode node, TreeNode p, TreeNode q) {
        if (this.resNode != null) {
            return false;
        }

        if (node == null) {
            return false;
        }

        boolean left = dfs(node.left, p, q);
        boolean right = dfs(node.right, p, q);

        if ((left && right) || ((left || right) && (node.val == p.val || node.val == q.val))) {
            this.resNode = node;
            return true;
        }

        return left || right || node.val == p.val || node.val == q.val;
    }

    private TreeNode resNode;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        dfs(root, p, q);
        return this.resNode;
    }

    //前一个节点
    TreeNode pre = null;
    //假设为true
    boolean ans = true;

    public boolean isValidBST(TreeNode root) {
        inOrder(root);
        return this.ans;
    }

    private void inOrder(TreeNode node) {
        //node为null或者为非二叉搜索树，快速返回
        if (node == null || !this.ans) {
            return;
        }

        //左
        inOrder(node.left);
        //根
        if (pre == null) {
            pre = node;
        } else {
            if (pre.val >= node.val) {
                this.ans = false;
            }
            pre = node;
        }
        //右
        inOrder(node.right);
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        TreeNode node = new TreeNode(5);
        node.left = new TreeNode(4);
        node.right = new TreeNode(6);
        node.right.left = new TreeNode(3);
        node.right.right = new TreeNode(7);

        boolean validBST = solution.isValidBST(node);
        System.out.println("");
    }
}
