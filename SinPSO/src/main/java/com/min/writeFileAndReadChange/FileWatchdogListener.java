package com.min.writeFileAndReadChange;

/**
 * @author jxm
 * @version 1.0
 * @date 2021/2/24 17:13
 */
public class FileWatchdogListener extends FileWatchdog {

    protected FileWatchdogListener(String filename) {
        super(filename);
    }

    @Override
    protected void doOnChange() {
        System.out.println("123");
    }
}
