package core;

import model.ScheduledTask;
import model.Task;
import org.junit.Before;
import org.junit.Test;
import shared.Scheduler;

import java.lang.annotation.Target;
import java.util.List;

import static org.junit.Assert.*;

public class ProcessSchedulerTest {
    private Scheduler scheduler;

    @Before
    public void setUp() {
        this.scheduler = new ProcessScheduler();
        for (int i = 1; i <= 20; i++) {
            this.scheduler.add(new ScheduledTask(i, "test_description"));
        }
    }

    @Test
    public void myTest() {
        ProcessScheduler myScheduler = new ProcessScheduler();

        myScheduler.add(new ScheduledTask(1, "test_description"));
        myScheduler.add(new ScheduledTask(2, "test_description"));
        myScheduler.add(new ScheduledTask(3, "test_description"));

        int a = 5;
    }

    @Test
    public void testMyAdd() {
        ProcessScheduler myScheduler = new ProcessScheduler();
        myScheduler.add(new ScheduledTask(1, "test_description"));
        myScheduler.add(new ScheduledTask(2, "test_description"));
        myScheduler.add(new ScheduledTask(3, "test_description"));

//        assertEquals(1, myScheduler.first.task.getId());
//        assertEquals(2, myScheduler.first.next.task.getId());
//        assertEquals(3, myScheduler.size());
        List<Task> tasks = myScheduler.toList();
        assertEquals(3, tasks.size());
        assertEquals(1, tasks.get(0).getId());

        int a = 5;
    }

    @Test
    public void testInsertAfterMiddle() {
        ProcessScheduler myScheduler = new ProcessScheduler();
        myScheduler.add(new ScheduledTask(1, "task 1"));
        myScheduler.add(new ScheduledTask(2, "task 2"));
        myScheduler.add(new ScheduledTask(3, "task 3"));

        myScheduler.insertAfter(2, new ScheduledTask(4, "task 4"));


        Task[] tasks = myScheduler.toArray();
        assertEquals(4, myScheduler.size);
        assertEquals("task 1", tasks[0].getDescription());
        assertEquals("task 2", tasks[1].getDescription());
        assertEquals("task 4", tasks[2].getDescription());
        assertEquals("task 3", tasks[3].getDescription());

        int a = 5;
    }

    @Test
    public void testFromListToArray() {
        ProcessScheduler myScheduler = new ProcessScheduler();
        myScheduler.add(new ScheduledTask(1, "test_description"));
        myScheduler.add(new ScheduledTask(2, "test_description"));
        myScheduler.add(new ScheduledTask(3, "test_description"));

        Task[] tasks = myScheduler.toArray();
        assertEquals(3, tasks.length);
        assertEquals(1, tasks[0].getId());
        assertEquals(2, tasks[1].getId());
        assertEquals(3, tasks[2].getId());
    }

    @Test
    public void testProcessWhenEmpty() {
        ProcessScheduler myScheduler = new ProcessScheduler();


        assertEquals(null, myScheduler.process());
        assertEquals(0, myScheduler.size);
    }

    @Test
    public void testReverseAndFromListToArray() {
        ProcessScheduler myScheduler = new ProcessScheduler();
        myScheduler.add(new ScheduledTask(1, "test_description"));
        myScheduler.add(new ScheduledTask(2, "test_description"));
        myScheduler.add(new ScheduledTask(3, "test_description"));
        myScheduler.reverse();

        Task[] tasks = myScheduler.toArray();
        assertEquals(3, tasks.length);
        assertEquals(3, tasks[0].getId());
        assertEquals(2, tasks[1].getId());
        assertEquals(1, tasks[2].getId());
    }

    @Test
    public void find() {
        ProcessScheduler myScheduler = new ProcessScheduler();
        myScheduler.add(new ScheduledTask(1, "Task 1"));
        myScheduler.add(new ScheduledTask(2, "Task 2"));
        myScheduler.add(new ScheduledTask(3, "Task 3"));

        Task task = myScheduler.find(2);

        assertEquals(2, task.getId());
        assertEquals("Task 2", task.getDescription());
        assertEquals(4, task.getId());
    }

    @Test (expected = IllegalArgumentException.class)
    public void findNotFoundException() {
        ProcessScheduler myScheduler = new ProcessScheduler();
        myScheduler.add(new ScheduledTask(1, "Task 1"));
        myScheduler.add(new ScheduledTask(2, "Task 2"));
        myScheduler.add(new ScheduledTask(3, "Task 3"));

        Task task = myScheduler.find(42);
    }

    @Test
    public void testPeekOnSingleElement() {
        Scheduler scheduler = new ProcessScheduler();
        scheduler.add(new ScheduledTask(100, "test_description"));
        Task task = scheduler.peek();
        assertNotNull(task);
        assertEquals(100, task.getId());
    }

    @Test
    public void testPeekOnMultipleElement() {
        Scheduler scheduler = new ProcessScheduler();
        scheduler.add(new ScheduledTask(73, "test_description"));
        for (int i = 1; i <= 20; i++) {
            scheduler.add(new ScheduledTask(i, "test_description"));
        }
        scheduler.add(new ScheduledTask(100, "test_description"));
        Task task = scheduler.peek();
        assertNotNull(task);
        assertEquals(73, task.getId());
    }

    @Test
    public void testRemoveFirst() {
        ProcessScheduler myScheduler = new ProcessScheduler();
        myScheduler.add(new ScheduledTask(1, "Task 1"));
        myScheduler.add(new ScheduledTask(2, "Task 2"));
        myScheduler.add(new ScheduledTask(3, "Task 3"));

        myScheduler.remove(1);
        Task[] tasks = myScheduler.toArray();

        assertEquals(2, tasks.length);

        assertEquals("Task 2", tasks[0].getDescription());
        assertEquals("Task 3", tasks[1].getDescription());
    }

    @Test
    public void testRemoveMiddle() {
        ProcessScheduler myScheduler = new ProcessScheduler();
        myScheduler.add(new ScheduledTask(1, "Task 1"));
        myScheduler.add(new ScheduledTask(2, "Task 2"));
        myScheduler.add(new ScheduledTask(3, "Task 3"));

        myScheduler.remove(2);
        Task[] tasks = myScheduler.toArray();

        assertEquals(2, tasks.length);

        assertEquals("Task 1", tasks[0].getDescription());
        assertEquals("Task 3", tasks[1].getDescription());
    }


    @Test
    public void testRemoveLast() {
        ProcessScheduler myScheduler = new ProcessScheduler();
        myScheduler.add(new ScheduledTask(1, "Task 1"));
        myScheduler.add(new ScheduledTask(2, "Task 2"));
        myScheduler.add(new ScheduledTask(3, "Task 3"));

        myScheduler.remove(3);
        Task[] tasks = myScheduler.toArray();

        assertEquals(2, tasks.length);

        assertEquals("Task 1", tasks[0].getDescription());
        assertEquals("Task 2", tasks[1].getDescription());
    }

    @Test
    public void testProcess() {
        ProcessScheduler myScheduler = new ProcessScheduler();
        myScheduler.add(new ScheduledTask(1, "Task 1"));
        myScheduler.add(new ScheduledTask(2, "Task 2"));
        myScheduler.add(new ScheduledTask(3, "Task 3"));

        assertEquals("Task 1", myScheduler.process().getDescription());
        assertEquals(2, myScheduler.size);
        assertEquals("Task 2", myScheduler.process().getDescription());
        assertEquals(1, myScheduler.size);
        assertEquals("Task 3", myScheduler.process().getDescription());
        assertEquals(0, myScheduler.size);

    }

    @Test
    public void testAddOnMultipleElement() {
        Task task = this.scheduler.peek();
        assertNotNull(task);
        assertEquals(1, task.getId());
        assertEquals(20, this.scheduler.size());

        int expectedId = 1;
        while (this.scheduler.size() > 0) {
            Task process = this.scheduler.process();
            assertNotNull(process);
            assertEquals(expectedId++, process.getId());
        }
        assertEquals(21, expectedId);
    }

    @Test
    public void testAddOnSingleElement() {
        Scheduler scheduler = new ProcessScheduler();
        assertNull(scheduler.peek());
        assertEquals(0, scheduler.size());

        scheduler.add(new ScheduledTask(1, "test_description"));

        assertNotNull(scheduler.peek());
        assertEquals(1, scheduler.peek().getId());
        assertEquals(1, scheduler.size());
    }

    @Test
    public void testPeekShouldReturnCorrectAndShouldNotRemove() {
        Scheduler scheduler = new ProcessScheduler();
        scheduler.add(new ScheduledTask(42, "test_description"));
        for (int i = 1; i <= 20; i++) {
            scheduler.add(new ScheduledTask(i, "test_description"));
        }
        Task task = scheduler.peek();
        assertNotNull(task);
        assertEquals(42, task.getId());
        assertEquals(21, scheduler.size());
        task = scheduler.peek();
        assertNotNull(task);
        assertEquals(42, task.getId());
        assertEquals(21, scheduler.size());
    }

    @Test
    public void testProcessShouldReturnCorrectAndShouldRemove() {
        Scheduler scheduler = new ProcessScheduler();
        scheduler.add(new ScheduledTask(42, "test_description"));
        for (int i = 1; i <= 20; i++) {
            scheduler.add(new ScheduledTask(i, "test_description"));
        }
        Task task = scheduler.process();
        assertNotNull(task);
        assertEquals(42, task.getId());
        assertEquals(20, scheduler.size());
        task = scheduler.process();
        assertNotNull(task);
        assertEquals(1, task.getId());
        assertEquals(19, scheduler.size());
    }
}