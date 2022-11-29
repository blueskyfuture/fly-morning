package com.bluesky.tech;

import com.bluesky.tech.lambda.FunctionalInterfaceFourType;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Test {
    public static void main(String[] args) throws Exception{
//        String str = "<?xml version=\"1.0\" encoding=\"UTF-8\"?><Configuration status=\"WARN\"><Appenders><RollingRandomAccessFile name=\"stdout\" immediateFlush=\"false\" append=\"true\" fileName=\"/app/log/twin.log\" filePattern=\"/app/log/twin-%i.log\"><PatternLayout pattern=\"%d %c [%t] (%F:%L) %-5p --> %m%n\"/><Policies><SizeBasedTriggeringPolicy size=\"10MB\"/></Policies><DefaultRolloverStrategy max=\"10\"/></RollingRandomAccessFile><RollingFile name=\"command\" fileName=\"/app/log/command.log\" filePattern=\"/app/log/command.log-%i.log\"><PatternLayout pattern=\"%d %c [%t] (%F:%L) %-5p --> %m%n\"/><Policies><SizeBasedTriggeringPolicy size=\"10MB\"/></Policies><DefaultRolloverStrategy max=\"10\"/></RollingFile ><RollingFile name=\"client\" fileName=\"/app/log/client.log\" filePattern=\"/app/log/client.log-%i.log\"><PatternLayout pattern=\"%d %c [%t] (%F:%L) %-5p --> %m%n\"/><Policies><SizeBasedTriggeringPolicy size=\"10MB\"/></Policies><DefaultRolloverStrategy max=\"10\"/></RollingFile ></Appenders><Loggers><AsyncLogger name=\"org.apache.kafka.common\" level=\"error\" includeLocation=\"true\" /><AsyncLogger name=\"org.apache.kafka.clients\" level=\"error\" includeLocation=\"true\" /><AsyncLogger name=\"io.advantageous.qbit.service.health\" level=\"error\" includeLocation=\"true\" /><AsyncLogger name=\"io.advantageous.qbit.meta.transformer\" level=\"error\" includeLocation=\"true\" /><AsyncLogger name=\"io.advantageous.qbit.metrics.StatServiceImpl\" level=\"error\" includeLocation=\"true\" /><AsyncLogger name=\"io.advantageous.qbit.service.health.HealthServiceImpl\" level=\"error\" includeLocation=\"true\" /><AsyncLogger name=\"io.advantageous.qbit.service.impl.ServiceQueueImpl\" level=\"error\" includeLocation=\"true\" /><AsyncLogger name=\"io.advantageous.qbit.vertx.http.server.HttpServerVertx\" level=\"error\" includeLocation=\"true\" /><AsyncLogger name=\"io.advantageous.qbit.http.server.impl.SimpleHttpServer\" level=\"error\" includeLocation=\"true\" /><AsyncLogger name=\"com.icos.icosbus.sdk\" level=\"info\" includeLocation=\"true\" /><AsyncLogger name=\"com.mongodb\" level=\"info\" includeLocation=\"true\" /><AsyncLogger name=\"com.icos.twin.attribute\" level=\"info\" includeLocation=\"true\" /><AsyncLogger name=\"com.icos.twin.client.mongo\" level=\"info\" includeLocation=\"true\" /><AsyncLogger name=\"com.icos.twin.definition\" level=\"info\" includeLocation=\"true\" /><AsyncLogger name=\"com.icos.twin.feature\" level=\"info\" includeLocation=\"true\" /><AsyncLogger name=\"com.icos.twin.interceptor\" level=\"info\" includeLocation=\"true\" /><AsyncLogger name=\"com.icos.twin.location\" level=\"info\" includeLocation=\"true\" /><AsyncLogger name=\"com.icos.twin.log\" level=\"info\" includeLocation=\"true\" /><AsyncLogger name=\"com.icos.twin.query\" level=\"info\" includeLocation=\"true\" /><AsyncLogger name=\"com.icos.twin.schema\" level=\"info\" includeLocation=\"true\" /><AsyncLogger name=\"com.icos.twin.util\" level=\"info\" includeLocation=\"true\" /><AsyncLogger name=\"com.icos.twin.command\" level=\"info\" additivity=\"true\" includeLocation=\"true\"><appender-ref ref=\"command\" /></AsyncLogger><AsyncLogger name=\"com.icos.twin.client.elassandra\" level=\"debug\" additivity=\"true\" includeLocation=\"true\"><appender-ref ref=\"client\" /></AsyncLogger><AsyncRoot level=\"info\" includeLocation=\"true\"><AppenderRef ref=\"stdout\"/></AsyncRoot></Loggers></Configuration>";
//        System.out.println("str=="+str);
//        System.out.println("test...");
        Test test = new Test();
//        List<String> sourceList = test.testReadFile3();
//        test.testReadFile4(sourceList);
        test.testReadFile5();
    }

private final static ExecutorService executors = new ThreadPoolExecutor(5, 5,
            1000L, TimeUnit.MILLISECONDS,
            new LinkedBlockingQueue<Runnable>(3),
            Executors.defaultThreadFactory(),
            new ThreadPoolExecutor.AbortPolicy());

    public List<String> testReadFile5() throws IOException {
        List<String> reList = new ArrayList<>();
        //faas
        //String fileName = "/Users/test/data/2001.log";
        //paas
        String fileName = "/Users/test/data/2002.log";
        // 读取文件内容到Stream流中，按行读取
        Stream<String> lines = Files.lines(Paths.get(fileName));
        List<String> list = lines.collect(Collectors.toList());
        int index01 = 0;
        for (String ele : list) {
            boolean isImage = ele.trim().startsWith("Image:");
            if(isImage){
                String substring = ele.substring(ele.indexOf(":")+1, ele.lastIndexOf(":")).trim();
                //System.out.println(substring);
                if(!reList.contains(substring)){
                    index01 ++;
                    reList.add(substring);
                }else {
                    System.out.println("is exist "+ele);
                }
            }
        }
        System.out.println("index01 == "+index01+",reList:"+reList.size());
        return reList;
    }

    public void testReadFile4(List<String> soureList) throws IOException {
        String fileName = "/Users/test/data/2002.log";
        // 读取文件内容到Stream流中，按行读取
        Stream<String> lines = Files.lines(Paths.get(fileName));
        List<String> list = lines.collect(Collectors.toList());
        int index01 = 0;
        for (String ele : list) {
            boolean isImage = ele.trim().startsWith("Image:");
            if(isImage){
                String substring = ele.substring(ele.indexOf(":")+1, ele.lastIndexOf(":")).trim();
                if(soureList.contains(substring)){
                    System.out.println(substring);
                    index01 ++;
                }
            }
        }
        System.out.println("======index01 == "+index01);
    }

    public List<String> testReadFile3() throws IOException {
        List<String> reList = new ArrayList<>();
        String fileName = "/Users/test/data/2001.log";
        // 读取文件内容到Stream流中，按行读取
        Stream<String> lines = Files.lines(Paths.get(fileName));
        List<String> list = lines.collect(Collectors.toList());
        int index01 = 0;
        for (String ele : list) {
            boolean isImage = ele.trim().startsWith("Image:");
            if(isImage){
                String substring = ele.substring(ele.indexOf(":")+1, ele.lastIndexOf(":")).trim();
                //System.out.println(substring);
                index01 ++;
                reList.add(substring);
            }
        }
        System.out.println("index01 == "+index01+",reList:"+reList.size());
        return reList;
    }

    public void testReadFile2() throws IOException {
        //String fileName = "D:\\data\\test\\newFile.txt";
        String fileName = "/Users/test/Desktop/1001.log";
        //String fileName = "/Users/test/data/1001.log";
        // 读取文件内容到Stream流中，按行读取
        Stream<String> lines = Files.lines(Paths.get(fileName));

        // 随机行顺序进行数据处理
        lines.filter(s->s.length()>0).forEach(ele -> {
            String result = ele.replace("\\u", "\\")
                    .replace(" ", "");
            String result2 = result.substring(ele.indexOf("基础设施")+4);
            if(result2.startsWith("\\"))
                result2 = result2.substring(1);
            if(result2.startsWith(")"))
                result2 = result2.substring(2);
            System.out.println(result);
            //System.out.println(result+"    "+result2);
            //System.out.println("特征\\基础设施\\"+result2);
        });
    }

    public void testReadFile1() throws IOException {
        //String fileName = "D:\\data\\test\\newFile.txt";
        String fileName = "/Users/test/Desktop/1001.log";
        //String fileName = "/Users/test/data/1001.log";
        // 读取文件内容到Stream流中，按行读取
        Stream<String> lines = Files.lines(Paths.get(fileName));

        // 随机行顺序进行数据处理
        lines.forEach(ele -> {
            System.out.println(ele);
        });
    }
}
