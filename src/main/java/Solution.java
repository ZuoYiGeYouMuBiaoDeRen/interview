import javax.swing.tree.TreeNode;

/**
 * 描述:
 *
 * @author kongqingchao
 * @create 2021-05-23 12:05 下午
 */
public class Solution {

    public class TreeNode {
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
        dfs(root,p,q);
        return this.resNode;
    }
}
