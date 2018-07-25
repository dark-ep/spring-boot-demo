package cn.ep.spring.boot.ch03.s13;

import org.apache.curator.framework.*;
import org.apache.curator.framework.api.transaction.*;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.data.Stat;
import org.slf4j.*;
import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@SpringBootApplication
public class App {

    private Logger logger = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        SpringApplication.run(App.class).close();
    }

    @Bean(initMethod = "start", destroyMethod = "close")
    public CuratorFramework curatorFramework(CuratorConfig config) {
        return CuratorFrameworkFactory.builder().connectString(config.getConnectString())
                .sessionTimeoutMs(config.getSessionTimeoutMs())    // 连接超时时间
                .connectionTimeoutMs(config.getConnectionTimeoutMs()) // 会话超时时间
                // 刚开始重试间隔为1秒，之后重试间隔逐渐增加，最多重试不超过三次
                .retryPolicy(new RetryNTimes(config.getRetryCount(), config.getElapsedTimeMs()))
                .build();
    }

    @Bean
    public CommandLineRunner demo(CuratorFramework curatorFramework) {
        return args -> {
            CuratorFramework client = curatorFramework.usingNamespace("test");

            if (new Random().nextBoolean()) {
                logger.info("预创建/curator/2节点");
                //增
                client.create().creatingParentsIfNeeded()//若创建节点的父节点不存在则先创建父节点再创建子节点
                        .forPath("/curator/2", "".getBytes());
            }

            Stat stat = client.checkExists().forPath("/curator/2");
            String dateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
            if (stat == null) {
                //增
                client.create().creatingParentsIfNeeded()//若创建节点的父节点不存在则先创建父节点再创建子节点
                        .forPath("/curator/2", dateTime.getBytes());
            } else {
                //改
                client.setData()
                        .forPath("/curator/2", dateTime.getBytes());
            }

            //查
            String recordOldDateTime = new String(client.getData()
                    .storingStatIn(new Stat())//在获取节点内容的同时把状态信息存入Stat对象
                    .forPath("/curator/2"));

            logger.info("record old datetime:{}", recordOldDateTime);

            String newDateTime = LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);

            // 开启事务
            try {
                CuratorTransaction transaction = client.inTransaction();
                Collection<CuratorTransactionResult> results = transaction.create().forPath("/curator/1", dateTime.getBytes())
                        .and().setData().forPath("/curator/2", dateTime.getBytes())
                        .and().delete().forPath("/curator/3")
                        .and().commit();
            } catch (Exception e) {
                logger.warn("zk事务失败");
            }

            if (client.checkExists().forPath("/curator/1") == null) {
                logger.info("不存在/curator/1节点");
            }

            //查
            String recordNewDateTime = new String(client.getData()
                    .storingStatIn(new Stat())//在获取节点内容的同时把状态信息存入Stat对象
                    .forPath("/curator/2"));

            if (Optional.of(recordOldDateTime).isPresent() && recordOldDateTime.equalsIgnoreCase(recordNewDateTime)) {
                logger.info("/curator/2节点数据没有变化");
            }

            //删
            client.delete().guaranteed()//保障机制，若未删除成功，只要会话有效会在后台一直尝试删除
                    .deletingChildrenIfNeeded()//若当前节点包含子节点
                    .withVersion(-1)//指定版本号
                    .forPath("/curator");
        };
    }

}
