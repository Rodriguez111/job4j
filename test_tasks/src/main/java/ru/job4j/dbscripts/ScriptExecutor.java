package ru.job4j.dbscripts;


import java.util.Map;
import java.util.Stack;

/**
 * Executing script with pre-executing all it dependencies.
 */

public class ScriptExecutor implements ExecuteScript {
    private final Map<Integer, VulnerabilityScript> scriptsDB;

    public ScriptExecutor(Map<Integer, VulnerabilityScript> scriptsDB) {
        this.scriptsDB = scriptsDB;
    }

    /**
     * Extracts script and all it dependencies to stack, then calls execute method for filled stack.
     * @param script - original script to process.
     */

    @Override
    public void handle(VulnerabilityScript script) {
        Stack<Integer> resultStack = new Stack<>();
        Stack<Integer> stack = new Stack<>();
        stack.push(script.getScriptId());
        while (!stack.isEmpty()) {
            int temp = stack.pop();
            resultStack.push(temp);
            for (int i = 0; i < scriptsDB.get(temp).getDependencies().size(); i++) {
                stack.push(scriptsDB.get(temp).getDependencies().get(i));
            }
        }
       execute(resultStack);
    }

    /**
     * Takes scripts from the stack and execute them.
     * @param stack - final stack of scripts for executing.
     */
    private void execute(Stack<Integer> stack) {
        while (!stack.empty()) {
            System.out.println("Executing script " + scriptsDB.get(stack.pop()).getScriptId());
        }
    }

}
