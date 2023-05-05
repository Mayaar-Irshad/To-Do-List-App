package todoapp;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

class AppTest {

	@Test
	public void testTaskPriorityTypeConversion() {
		Task t1 = new Task();
		Task t2 = new Task();
		Task t3 = new Task();
		Task t4 = new Task();
		t1.setPriority(-1);
		t2.setPriority(0);
		t3.setPriority(1);
		t4.setPriority(2);
		assertEquals("Undefined", t1.priorityAsString(), "Int -1 should be Undefined");
		assertEquals("Low", t2.priorityAsString(), "Int 0 should be Low");
		assertEquals("Medium", t3.priorityAsString(), "Int 1 should be Medium");
		assertEquals("High", t4.priorityAsString(), "Int 2 should be High");
		
		assertEquals(t1.stringToPriority("Undefined"), -1, "String Undefined should be -1");
		assertEquals(t1.stringToPriority("Low"), 0, "String Undefined should be 0");
		assertEquals(t1.stringToPriority("Medium"), 1, "String Undefined should be 1");
		assertEquals(t1.stringToPriority("High"), 2, "String Undefined should be 2");
	}

	@Test
	public void testTaskManager() {
		TaskManager tm = new TaskManager();
		assertEquals(0, tm.getTasks().size(), "Initialized task manager should have arraylist with size 0");
		
		Task t1 = new Task();
		t1.setTitle("Test");
		tm.addTask(t1);
		assertEquals(1, tm.getTasks().size(), "Add task should increase size of arraylist to 1");
		assertTrue(t1 == tm.getTasks().get(0), "Task at index 0 should be task that was added");
		
		tm.addTask(t1);
		Task t2 = new Task();
		t2.setTitle("Test 2");
		tm.addTask(t2);
		assertEquals(3, tm.getTasks().size(), "Add task should increase size of arraylist by 1");
		assertTrue(t2 == tm.getTasks().get(2), "Task at index 2 should be task that was added");
		
		tm.deleteTask(t1);
		assertEquals(2, tm.getTasks().size(), "Delete task should decrease size of arraylist by 1");
		assertTrue(t1 == tm.getTasks().get(0), "Task at index 0 should be first task that was added");
		
		tm.deleteTask(t1);
		assertEquals(1, tm.getTasks().size(), "Delete task should decrease size of arraylist by 1");
		assertTrue(t2 == tm.getTasks().get(0), "Task at index 0 should be last task that was added");
		
		tm.deleteTask(t2);
		assertEquals(0, tm.getTasks().size(), "Delete task should decrease size of arraylist by 1");
	}
}
