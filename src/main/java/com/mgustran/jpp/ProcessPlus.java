package com.mgustran.jpp;

import java.io.*;
import java.util.List;
import java.util.logging.Logger;


public class ProcessPlus {

    private static final Logger log = Logger.getLogger(ProcessPlus.class.getCanonicalName());

    private final ProcessPlusBase base;

    public ProcessPlus() {
        this.base = new ProcessPlusBase();
    }

    public List<String> executeCommandGetLines(final String command) {
        final ProcessBuilder builder = this.addCommandToBuilder(command);
        return this.base.executeCommandGetLines(builder);
    }

    public String executeCommandGetString(final String command) {
        return String.join("\n", this.executeCommandGetLines(command));
    }

    public void executeCommandVoid(final String command) {
        final ProcessBuilder builder = this.addCommandToBuilder(command);
        this.base.executeCommandVoid(builder);
    }

    public void executeCommandNoBlock(final String command) {
        final ProcessBuilder builder = this.addCommandToBuilder(command);
        this.base.executeCommandNoBlock(builder);
    }

    public void executeCommandWithInput(final String command, final List<InputHelper> helpers) throws IOException {
        final ProcessBuilder builder = this.addCommandToBuilder(command);
        this.base.executeCommandWithInput(builder, helpers);
    }

    private ProcessBuilder addCommandToBuilder(String command) {
        final ProcessBuilder builder = new ProcessBuilder();
        String os = System.getProperty("os.name");
        log.info(os);
        if (os.startsWith("Linux")) {
            builder.command("sh", "-c", command);
        } else {
            builder.command(command.split(" "));
        }
        return builder;
    }
}

