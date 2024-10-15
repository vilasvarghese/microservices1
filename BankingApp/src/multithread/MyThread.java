package multithread;

public class MyThread {
    public void run() {
        // Code to be executed by the thread
        System.out.println("Running thread: " + Thread.currentThread().getName());
    }
}


/*
A thread's lifecycle typically goes through the following stages:

New: The thread is created but not yet started.
Runnable: The thread is ready to run but waiting for a CPU.
Running: The thread is currently executing instructions.
Blocked: The thread is waiting for an event (e.g., I/O, synchronization).
Waiting: The thread is waiting for another thread to signal it.
Timed Waiting: The thread is waiting for a specified amount of time.
Terminated: The thread has finished executing.
*/