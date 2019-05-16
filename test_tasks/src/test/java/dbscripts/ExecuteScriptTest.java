package dbscripts;

import org.junit.After;
import org.junit.Before;

import org.junit.Test;
import ru.job4j.dbscripts.ExecuteScript;
import ru.job4j.dbscripts.ScriptExecutor;
import ru.job4j.dbscripts.VulnerabilityScript;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class ExecuteScriptTest {
    private final ByteArrayOutputStream baos = new ByteArrayOutputStream();
    private PrintStream originalStream;
    private PrintStream newStream;
    private final String ls = System.lineSeparator();

    @Before
    public void before() {
        originalStream = System.out;
        newStream = new PrintStream(baos);
        System.setOut(newStream);
    }

    @After
    public void after() {
        System.setOut(originalStream);
    }

    @Test
    public void whenScript0WithDependency12thenExecute120() {
        VulnerabilityScript mainScript = new VulnerabilityScript(0, List.of(1, 2));
        VulnerabilityScript scriptForMainScript1 = new VulnerabilityScript(1, List.of());
        VulnerabilityScript scriptForMainScript2 = new VulnerabilityScript(2, List.of());

        Map<Integer, VulnerabilityScript> scriptsDB = new HashMap<>();
        scriptsDB.put(0, mainScript);
        scriptsDB.put(1, scriptForMainScript1);
        scriptsDB.put(2, scriptForMainScript2);

        ExecuteScript executeScript = new ScriptExecutor(scriptsDB);
        executeScript.handle(mainScript);

        String actual = baos.toString();
        String expected = new StringBuilder().append("Executing script 1")
                .append(ls)
                .append("Executing script 2")
                .append(ls)
                .append("Executing script 0")
                .append(ls).toString();

        assertThat(actual, is(expected));
    }

    @Test
    public void whenScript0WithDependency12thenExecute4532145320() {
        VulnerabilityScript mainScript = new VulnerabilityScript(0, List.of(1, 2));
        VulnerabilityScript scriptForMainScript1 = new VulnerabilityScript(1, List.of(2));
        VulnerabilityScript scriptForMainScript2 = new VulnerabilityScript(2, List.of(3));
        VulnerabilityScript scriptForMainScript3 = new VulnerabilityScript(3, List.of(4, 5));
        VulnerabilityScript scriptForMainScript4 = new VulnerabilityScript(4, List.of());
        VulnerabilityScript scriptForMainScript5 = new VulnerabilityScript(5, List.of());

        Map<Integer, VulnerabilityScript> scriptsDB = new HashMap<>();
        scriptsDB.put(0, mainScript);
        scriptsDB.put(1, scriptForMainScript1);
        scriptsDB.put(2, scriptForMainScript2);
        scriptsDB.put(3, scriptForMainScript3);
        scriptsDB.put(4, scriptForMainScript4);
        scriptsDB.put(5, scriptForMainScript5);

        ExecuteScript executeScript = new ScriptExecutor(scriptsDB);
        executeScript.handle(mainScript);

        String actual = baos.toString();
        String expected = new StringBuilder().append("Executing script 4")
                .append(ls)
                .append("Executing script 5")
                .append(ls)
                .append("Executing script 3")
                .append(ls)
                .append("Executing script 2")
                .append(ls)
                .append("Executing script 1")
                .append(ls)
                .append("Executing script 4")
                .append(ls)
                .append("Executing script 5")
                .append(ls)
                .append("Executing script 3")
                .append(ls)
                .append("Executing script 2")
                .append(ls)
                .append("Executing script 0")
                .append(ls).toString();

        assertThat(actual, is(expected));
    }

}