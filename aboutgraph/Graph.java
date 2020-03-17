import java.util.*;
import java.util.stream.Collectors;

/**
 * algorithm
 * @author 忘忧
 * @date 2020/3/17
 * 图相关代码(公众号：算法之灵魂拷问)
 */
public class Graph {

    public static void main(String[] args) {
        final Map<String, List<String>> graph = new HashMap<>(8);
        //使用邻接表构建图
        graph.put("A", Arrays.asList("B", "C"));
        graph.put("B", Arrays.asList("A", "D", "E"));
        graph.put("C", Arrays.asList("A", "E"));
        graph.put("D", Arrays.asList("B", "E", "G", "H"));
        graph.put("E", Arrays.asList("B", "C", "D", "F"));
        graph.put("F", Arrays.asList("E", "G"));
        graph.put("G", Arrays.asList("D", "F"));
        graph.put("H", Collections.singletonList("D"));
        //开始深度优先遍历
        startBfs(graph, "A");
    }

/**
 * 启动广度优先遍历
 * @param graph 图的信息
 * @param start 开始的顶点
 */
public static void startBfs(Map<String, List<String>> graph, String start){
    //创建一个集合，用于存放已走过的顶点
    List<String> closed = new LinkedList<>();
    //输出当前顶点
    System.out.print(start);
    //将当前节点增加到已访问列表
    closed.add(start);
    //递归遍历
    bfs(graph, start, closed);
}

/**
 * 递归广度优先遍历
 * @param graph 图信息
 * @param point 当前顶点
 * @param closed 已经访问过的顶点
 */
public static void bfs(Map<String, List<String>> graph, String point, List<String> closed){
    //获取当前顶点可到达的顶点
    List<String> directAccessPoints = graph.get(point);
    if(directAccessPoints != null){
        //过滤掉已经访问过的顶点
        List<String> filter = directAccessPoints.stream()
                .filter(item -> !closed.contains(item)).collect(Collectors.toList());
        //输出未访问过的直连顶点，并将其添加到访问列表
        filter.forEach(item -> {
            System.out.print(item);
            closed.add(item);
        });
        filter.forEach(item -> {
            bfs(graph, item, closed);
        });
    }
}

    /**
     * 启动深度优先遍历
     * @param graph 图的信息
     * @param start 开始的顶点
     */
    public static void startDfs(Map<String, List<String>> graph, String start){
        //常见一个栈，用于存放当前路径，用于无路可走时的回退
        Stack<String> stack = new Stack<>();
        stack.push(start);
        //创建一个集合，用于存放已走过的顶点
        List<String> closed = new LinkedList<>();
        //递归遍历
        dfs(graph, stack, closed);
    }

    /**
     * 递归深度优先遍历
     * @param graph 图信息
     * @param stack 当前路径，用于回退
     * @param closed 已经访问过的顶点
     */
    public static void dfs(Map<String, List<String>> graph, Stack<String> stack, List<String> closed){
        //递归出口，当前已无退路，标示已经回退到了起始顶点，遍历结束
        if(stack.empty()){
            return;
        }
        //窥探当前栈顶元素，即当前所处的顶点
        String point = stack.peek();
        //输出当前顶点
        System.out.print(point);
        //将当前顶点添加到已访问过的集合里
        closed.add(point);
        //获取当前顶点可到达的顶点
        List<String> directAccessPoints = graph.get(point);
        if(directAccessPoints != null){
            directAccessPoints.stream()
                    //过滤掉已经访问过的顶点
                    .filter(item -> !closed.contains(item))
                    //遍历剩余的顶点，对剩余的顶点进行深度优先遍历
                    .forEach(item -> {
                        stack.push(item);
                        dfs(graph, stack, closed);
            });
        }
        //当前顶点已无路可去，弹栈回退
        stack.pop();
    }
}
