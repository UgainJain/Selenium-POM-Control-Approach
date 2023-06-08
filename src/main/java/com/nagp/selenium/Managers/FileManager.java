package com.nagp.selenium.Managers;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;

// File manager for managing the files
public class FileManager {
    // copying the directory and moving contents to new directory
    public static void MoveAllContentsToAnotherDirectory(String relativeSrcPath, String relativeDestPath) throws IOException {
        String path = System.getProperty("user.dir");
        File fullPathSrcFile =  new File(path+relativeSrcPath);
        File fullPathDestFile =  new File(path+relativeDestPath);
        FileUtils.copyDirectory(fullPathSrcFile,fullPathDestFile);
        FileUtils.cleanDirectory(fullPathSrcFile);
    }
}
