package thread.model;


import java.util.concurrent.*;

public class UseFuture implements Callable<String> {
    private String para;

    public UseFuture(String para){
        this.para = para;
    }

    /**
     * 这里是真实的业务逻辑，其执行可能很慢
     */
    @Override
    public String call() throws Exception {
//模拟执行耗时
        Thread.sleep(5000);
        String result = this.para + "真实数据加载";
        return result;
    }

    //主控制函数
    public static void main(String[] args) throws Exception {
        String queryStr = "query";
//构造FutureTask，并且传入需要真正进行业务逻辑处理的类, 该类 一定是实现了Callable接口的类
        FutureTask<String> future = new FutureTask<String>(new UseFuture(queryStr));

//创建一个固定线程的线程池且线程数为1,
        ExecutorService executor = Executors.newFixedThreadPool(2);

//这里提交任务future,则开启线程执行RealData的call()方法执行
//submit和execute的区别： 第一点是submit可以传入 实现Callable接口 的实例对象， 第二点是submit方法有返回值
        Future<?> f1 = executor.submit(future); //单独启动一个线程去执行的

        while(true){
            if(f1.get()==null){
                System.out.println("callable已经执行完了");
                break;
            }
        }

        try {
//这里可以做额外的数据操作，也就是主程序执行其他业务逻辑
            System.out.println("处理实际的业务逻辑...");
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }
//调用获取数据方法,如果Callable()方法没有执行完成, 则依然会 进行等待
        System.out.println("future模式获得的数据：" + future.get());

        executor.shutdown();
    }

}