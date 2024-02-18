package com.codurance.training.tasks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.io.PrintWriter;
import java.util.HashMap;

import com.codurance.training.tasks.adapter.port.in.TaskCommandController;
import com.codurance.training.tasks.adapter.port.out.InMemoryProjectRepository;
import com.codurance.training.tasks.adapter.port.in.TaskListInput;
import com.codurance.training.tasks.adapter.port.out.TaskListOutput;
import com.codurance.training.tasks.framework.TaskListApplication;
import com.codurance.training.tasks.framework.io.TaskListCommandPrinter;
import com.codurance.training.tasks.framework.io.TaskListCommandReader;
import com.codurance.training.tasks.framework.persistant.ProjectMemoryStore;
import com.codurance.training.tasks.framework.persistant.TaskMemoryStore;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static java.lang.System.lineSeparator;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public final class ApplicationTest {
    public static final String PROMPT = "> ";
    private final PipedOutputStream inStream = new PipedOutputStream();
    private final PrintWriter inWriter = new PrintWriter(inStream, true);

    private final PipedInputStream outStream = new PipedInputStream();
    private final BufferedReader outReader = new BufferedReader(new InputStreamReader(outStream));

    private Thread applicationThread;

    public ApplicationTest() throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(new PipedInputStream(inStream)));
        PrintWriter out = new PrintWriter(new PipedOutputStream(outStream), true);
        TaskListOutput taskListOutput = new TaskListCommandPrinter(out);
        TaskListInput taskListInput = new TaskListCommandReader(in);

        InMemoryProjectRepository repository = new InMemoryProjectRepository(
                new ProjectMemoryStore(new HashMap<>()),
                new TaskMemoryStore(new HashMap<>())
        );
        TaskListApplication taskListApplication = new TaskListApplication(new TaskCommandController(repository), taskListInput, taskListOutput);
        applicationThread = new Thread(taskListApplication);
    }

    @Before public void
    start_the_application() {
        applicationThread.start();
    }

    @After public void
    kill_the_application() throws IOException, InterruptedException {
        if (!stillRunning()) {
            return;
        }

        Thread.sleep(1000);
        if (!stillRunning()) {
            return;
        }

        applicationThread.interrupt();
        throw new IllegalStateException("The application is still running.");
    }

    @Test(timeout = 10000000) public void
    it_works() throws IOException, InterruptedException {
        execute("show");

        execute("add project secrets");
        execute("add task secrets S01 Eat more donuts.");
        execute("add task secrets S02 Destroy all humans.");

        execute("show");
        readLines(
            "secrets",
            "    [ ] S01: Eat more donuts.",
            "    [ ] S02: Destroy all humans.",
            ""
        );

        execute("add project training");
        execute("add task training T01 Four Elements of Simple Design");
        execute("add task training T02 SOLID");
        execute("add task training T03 Coupling and Cohesion");
        execute("add task training T04 Primitive Obsession");
        execute("add task training T05 Outside-In TDD");
        execute("add task training T06 Interaction-Driven Design");

        execute("check S01");
        execute("check T01");
        execute("check T03");
        execute("check T04");

        execute("show");
        readLines(
                "training",
                "    [x] T01: Four Elements of Simple Design",
                "    [ ] T02: SOLID",
                "    [x] T03: Coupling and Cohesion",
                "    [x] T04: Primitive Obsession",
                "    [ ] T05: Outside-In TDD",
                "    [ ] T06: Interaction-Driven Design",
                "",
                "secrets",
                "    [x] S01: Eat more donuts.",
                "    [ ] S02: Destroy all humans.",
                ""
        );

        execute("quit");
    }

    private void execute(String command) throws IOException {
        read(PROMPT);
        write(command);
    }

    private void read(String expectedOutput) throws IOException {
        int length = expectedOutput.length();
        char[] buffer = new char[length];
        outReader.read(buffer, 0, length);
        assertThat(String.valueOf(buffer), is(expectedOutput));
    }

    private void readLines(String... expectedOutput) throws IOException {
        for (String line : expectedOutput) {
            read(line + lineSeparator());
        }
    }

    private void write(String input) {
        inWriter.println(input);
    }

    private boolean stillRunning() {
        return applicationThread != null && applicationThread.isAlive();
    }
}
