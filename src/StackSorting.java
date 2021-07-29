import java.util.Stack;

/**
 * 在原来栈的基础上排序，只能用一个栈进行辅助，不能用其他数据结构（可以和用两个栈实现队列的题比较学习）
 * https://www.lintcode.com/problem/229/description?_from=cat
 */
public class StackSorting {
    public void stackSorting(Stack<Integer> stack) {
        Stack<Integer> tmp = new Stack<>();
        while (!stack.isEmpty()) {
            // 保证在tmp中元素是按照自上而下而下递减的顺序来排的
            if (!stack.isEmpty() && (tmp.isEmpty() || tmp.peek() > stack.peek())) {
                tmp.push(stack.peek());
                stack.pop();
            } else {
                // 遇到比tmp栈顶的元素大的元素的时候，往外弹出值，直到小于tmp栈顶的元素
                int value = stack.pop();
                stack.pop();
                while (!tmp.isEmpty() && tmp.peek() <= value) {
                    stack.push(tmp.peek());
                    tmp.pop();
                }
                stack.push(value);
                while (!tmp.isEmpty()) {
                    stack.push(tmp.peek());
                    tmp.pop();
                }
            }
        }
        while (!tmp.isEmpty()) {
            stack.push(tmp.peek());
            tmp.pop();
        }
    }
}
