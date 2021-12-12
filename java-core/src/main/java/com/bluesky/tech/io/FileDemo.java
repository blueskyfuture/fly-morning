package com.bluesky.tech.io;

import com.bluesky.tech.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class FileDemo {

    public static void main(String[] args)throws Exception {
        FileDemo test = new FileDemo();
        test.testReadFile();
    }


    public void testReadFile() throws IOException {
        String fileName = "/Data/1001.log";
        // 读取文件内容到Stream流中，按行读取
        Stream<String> lines = Files.lines(Paths.get(fileName));

        // 随机行顺序进行数据处理
        lines.forEach(ele -> {
            System.out.println(ele);
        });
    }
}
