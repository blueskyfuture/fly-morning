package com.t.juc;

/**
 * https://www.cnblogs.com/noKing/p/9384442.html
 */
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Supplier;

public class ReentrantReadWriteLockTest2_1 {
    static final Scanner scanner = new Scanner(System.in);
    static volatile String cmd = "";
    private static ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);
    private static ReentrantReadWriteLock.ReadLock readLock = lock.readLock();
    private static ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();

    public static void main(String[] args) {
        HashMap<String, Lock> hashMap = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            hashMap.put("r" + i, readLock);
            hashMap.put("w" + i, writeLock);
        }
        for (Map.Entry<String, Lock> entry : hashMap.entrySet()) {
            new Thread(() -> func(entry::getValue, entry.getKey())).start();
        }

        while (scanner.hasNext()) {
            cmd = scanner.nextLine();
        }
    }

    public static void func(Supplier<Lock> myLockSupplier, String name) {
        String en_type = myLockSupplier.get().getClass().getSimpleName().toLowerCase().split("lock")[0];
        String zn_type = (en_type.equals("read") ? "re" : "wr");
        blockUntilEquals(() -> cmd, "lock " + en_type + " " + name);
        myLockSupplier.get().lock();
        System.out.println(name + " get " + zn_type + " lock");
        blockUntilEquals(() -> cmd, "unlock " + en_type + " " + name);
        myLockSupplier.get().unlock();
        System.out.println(name + " release " + zn_type + " lock");
    }

    private static void blockUntilEquals(Supplier<String> cmdSupplier, final String expect) {
        while (!cmdSupplier.get().equals(expect))
            quietSleep(1000);
    }

    private static void quietSleep(int mills) {
        try {
            Thread.sleep(mills);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
