package thread.sync.locks.ReentrantLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockEx {

    /*
        비공정 모드 락
            성능 우선: 락을 획득하는 속도가 빠르다.
            선점 가능: 새로운 스레드가 기존 대기 스레드보다 먼저 락을 획득할 수 있다.
            기아 현상 가능성: 특정 스레드가 계속해서 락을 획득하지 못할 수 있다.

        공정 모드 락
            공정성 보장: 대기 큐에서 먼저 대기한 스레드가 락을 먼저 획득한다.
            기아 현상 방지: 모든 스레드가 언젠가 락을 획득할 수 있게 보장된다.
            성능 저하: 락을 획득하는 속도가 느려질 수 있다.
     */

    private final Lock nonFairLock = new ReentrantLock();       // 비공정 모드 락
    private final Lock fairLock = new ReentrantLock(true);  // 공정 모드 락

    public void nonFairLockTest() {
        nonFairLock.lock();
        try {
            // 임계 영역
        } finally {
            nonFairLock.unlock();
        }
    }

    public void fairLockTest() {
        fairLock.lock();
        try {
            // 임계 영역
        } finally {
            fairLock.unlock();
        }
    }

}
