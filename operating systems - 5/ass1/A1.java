package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.Scanner;

class Parser {
    String commandName;
    String[] args;

    public boolean parse(String input) {
        String[] command = input.split(" ");
        commandName = command[0];
        args = Arrays.copyOfRange(command, 1, command.length);
        return true;

    }

    public String getCommandName() {
        return commandName;
    }

    public String[] getArgs() {
        return args;
    }
}

public class Terminal {
    static boolean on = true;
    static String path = System.getProperty("user.dir");
    static FileWriter file;

    private boolean find(String[] args) {
        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("<")) {
                try {
                    file = new FileWriter(args[i + 1]);
                    return true;
                } catch (IOException e) {
                    System.out.println("no file with this name");
                }
            }
        }
        return false;
    }

    public String echo(String[] line) {
        String l = new String();
        for (String li : line) {
            l = l + li + " ";
        }
        return l;
    }

    public String touch(String f) {
        try {
            File myfile = new File(f);
            if (!myfile.isAbsolute()) {
                if (f.startsWith("../")) {
                    String curPath = path;
                    int x = curPath.lastIndexOf('\\');
                    curPath = curPath.substring(0, x);
                    f = curPath + f.substring(2);
                } else
                    f = path + '\\' + f;
                myfile = new File(f);
            }
            if (myfile.createNewFile()) {
                return ("File created: " + f);
            } else {
                return ("File already exists.");
            }
        } catch (IOException e) {
            return "failed to create the file.";
        }

    }

    public String pwd() {
        return path;
    }

    public void cp(String f1, String f2) {
        try {
            File myfile1 = new File(f1);
            File myfile_2 = new File(f2);
            if (!myfile1.isAbsolute()) {
                if (f1.startsWith("../")) {
                    String curPath = path;
                    int x = curPath.lastIndexOf('\\');
                    curPath = curPath.substring(0, x);
                    f1 = curPath + f1.substring(2);
                } else
                    f1 = path + '\\' + f1;
                myfile1 = new File(f1);
            }
            if (!myfile_2.isAbsolute()) {
                if (f2.startsWith("../")) {
                    String curPath = path;
                    int x = curPath.lastIndexOf('\\');
                    curPath = curPath.substring(0, x);
                    f2 = curPath + f2.substring(2);
                } else
                    f2 = path + '\\' + f2;
                myfile_2 = new File(f2);
            }
            Scanner read = new Scanner(myfile1);
            FileWriter myfile2 = new FileWriter(myfile_2);
            while (read.hasNextLine()) {
                myfile2.write(read.nextLine() + "\n");
            }
            myfile2.close();
        } catch (
                IOException e) {
            System.out.println("Can't find file.");
        }

    }

    public void mkdir(String... folders) {
        for (String folder : folders) {
            File file = new File(folder);
            if (!file.isAbsolute()) {
                if (folder.startsWith("../")) {
                    String curPath = path;
                    int x = curPath.lastIndexOf('\\');
                    curPath = curPath.substring(0, x);
                    folder = curPath + folder.substring(2);
                } else
                    folder = path + '\\' + folder;
                file = new File(folder);
            }
            file.mkdir();
        }
    }

    public String rm(String file) {
        File f = new File(path+"\\\\"+file);
        if (f.exists()) {
            f.delete();
            return (file + " deleted");
        } else {
            return ("File does not exist");
        }
    }

    public String cat(String file) {
        String lines = "";
        try {
            File myfile = new File(file);
            Scanner read = new Scanner(myfile);
            while (read.hasNextLine()) {
                lines = lines + read.nextLine() + "\n";
            }
        } catch (IOException e) {
            System.out.println("Can't find file.");
        }
        return lines;
    }

    public String cat(String file1, String file2) {
        String lines = new String();
        try {
            File myfile1 = new File(file1);
            File myfile2 = new File(file2);
            Scanner read1 = new Scanner(myfile1);
            while (read1.hasNextLine()) {
                lines = lines + read1.nextLine() + "\n";
            }
            Scanner read2 = new Scanner(myfile2);
            while (read2.hasNextLine()) {
                lines = lines + read2.nextLine() + "\n";
            }
        } catch (IOException e) {
            System.out.println("Can't find file.");
        }
        return lines;
    }

    public void cd() {
        path = System.getProperty("user.dir");
    }

    public void cd(String arg) {
        if (arg.equals("..")) {
            String curPath = path;
            int x = curPath.lastIndexOf('\\');
            curPath = curPath.substring(0, x);
            path = curPath;
        } else {
            File temp = new File(arg);
            if (!temp.isAbsolute()) {
                if (arg.startsWith("../")) {
                    cd("..");
                    arg = path + arg.substring(2);
                } else {
                    arg = path + '\\' + arg;
                }
                temp = new File(arg);
            }
            if (!temp.exists())
                System.out.println("This directory isn't exist");
            else if (!temp.isDirectory())
                System.out.println("This isn't directory");
            else
                path = arg;
        }
    }

    public String[] ls() {
        String[] content;
        File file = new File(path);
        content = file.list();
        Arrays.sort(content);
        return content;
    }

    public String[] ls_r() {
        String[] content = ls();
        String[] content_r = new String[content.length];
        int j = 0;
        for (int i = content.length; i > 0; i--) {
            content_r[j] = content[i - 1];
            j++;
        }
        return content_r;
    }

    private boolean isEmpty(File dir) {
        String dirs[] = dir.list();
        if (dirs.length > 0)
            return false;
        else
            return true;
    }

    public void rmdir(String directory) {
        File dir;
        if (directory.equals("*")) {
            dir = new File(path);
            try {
                File dirs[] = dir.listFiles();
                int len = dirs.length;
                for (int i = 0; i < len; i++) {
                    File temp = dirs[i];
                    if (temp.isDirectory()) {
                        if (isEmpty(temp)) {
                            temp.delete();
                            System.out.println(temp.getName() + " is deleted");
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println("Error :" + e.toString());
            }
        } else {
            dir = new File(directory);
            if (!dir.isAbsolute()) {
                if (directory.startsWith("../")) {
                    String curPath = path;
                    int x = curPath.lastIndexOf('\\');
                    curPath = curPath.substring(0, x);
                    directory = curPath + directory.substring(2);
                } else
                    directory = path + '\\' + directory;
                dir = new File(directory);
            }
            if (!dir.isDirectory())
                System.out.println("This isn't directory");
            else if (!dir.exists())
                System.out.println("This directory isn't exist");
            else {
                if (isEmpty(dir)) {
                    dir.delete();
                    System.out.println("The directory has deleted");
                } else
                    System.out.println(directory + " isn't empty");
            }
        }
    }

    void cpr(Path srcPath, Path destPath) {
        File src = new File(srcPath.toString());
        File dest = new File(destPath.toString());
        if (src.exists() && dest.exists()) {
            String srcName = srcPath.getFileName().toString();
            File newDir = new File(destPath.toString() + "\\" + srcName);
            newDir.mkdir();
            File srcList[] = src.listFiles();
            int length = srcList.length;
            if (length > 0) {
                for (int i = 0; i < length; i++) {
                    File temp = srcList[i];
                    if (temp.isDirectory()) {
                        cpr(temp.toPath(), newDir.toPath());
                    } else {
                        Path newFile = Paths.get(newDir.toString() + "\\" + temp.getName());
                        File copiedFIle = new File(newFile.toString());
                        try {
                            copiedFIle.createNewFile();
                            Files.copy(temp.toPath(), newFile, StandardCopyOption.REPLACE_EXISTING);
                        } catch (IOException e) {
                            System.out.println(e.toString());
                        }

                    }
                }
            }
        }
    }

    public void chooseCommandAction() {
        Parser p = new Parser();
        String line;
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        if (p.parse(command)) {
            if (p.getCommandName().equals("echo")) {
                if (find(p.getArgs())) {
                    line = echo(Arrays.copyOfRange(p.getArgs(), 0, p.getArgs().length - 2));
                    try {
                        file.write(line);
                        file.close();
                    } catch (IOException e) {
                        System.out.println("something went wrong");
                    }
                } else {
                    System.out.println(echo(p.getArgs()));
                }
            } else if (p.getCommandName().equals("touch")) {
                if (find(p.getArgs())) {
                    line = touch(p.getArgs()[0]);
                    try {
                        file.write(line);
                        file.close();
                    } catch (IOException e) {
                        System.out.println("something went wrong");
                    }
                } else {
                    System.out.println(touch(p.getArgs()[0]));
                }
            } else if (p.getCommandName().equals("pwd")) {
                if (find(p.getArgs())) {
                    line = pwd();
                    try {
                        file.write(line);
                        file.close();
                    } catch (IOException e) {
                        System.out.println("something went wrong");
                    }
                } else {
                    System.out.println(pwd());
                }
            } else if (p.getCommandName().equals("cp")) {
                cp(p.getArgs()[0], p.getArgs()[1]);
            } else if (p.getCommandName().equals("mkdir")) {
                mkdir(p.getArgs());
            } else if (p.getCommandName().equals("rm")) {
                if (find(p.getArgs())) {
                    line = rm(p.getArgs()[0]);
                    try {
                        file.write(line);
                        file.close();
                    } catch (IOException e) {
                        System.out.println("something went wrong");
                    }
                } else {
                    System.out.println(rm(p.getArgs()[0]));
                }
            } else if (p.getCommandName().equals("cat")) {
                if (find(p.getArgs())) {
                    String[] a = Arrays.copyOfRange(p.getArgs(), 0, p.getArgs().length - 2);
                    if (a.length == 1) {
                        line = cat(a[0]);
                    } else if (a.length == 2) {
                        line = cat(a[0], a[1]);
                    } else {
                        line = "Wrong number of arguments";
                    }
                    try {
                        file.write(line);
                        file.close();
                    } catch (IOException e) {
                        System.out.println("something went wrong");
                    }
                } else {
                    if (p.getArgs().length == 1) {
                        System.out.println(cat(p.getArgs()[0]));
                    } else if (p.getArgs().length == 2) {
                        System.out.println(cat(p.getArgs()[0], p.getArgs()[1]));
                    } else {
                        System.out.println("Wrong number of arguments");
                    }
                }
            } else if (p.getCommandName().equals("cd")) {
                if (p.getArgs().length == 0) {
                    cd();
                } else if (p.getArgs().length == 1) {
                    cd(p.getArgs()[0]);
                } else {
                    System.out.println("Wrong number of arguments");
                }
            } else if (p.getCommandName().equals("ls")) {
                if (find(p.getArgs())) {
                    String[] a = Arrays.copyOfRange(p.getArgs(), 0, p.getArgs().length - 2);
                    if (a.length == 0) {
                        String[] content = ls();
                        try {
                            for (String c : content) {
                                file.write(c + "\n");
                            }
                            file.close();
                        } catch (IOException e) {
                            System.out.println("something went wrong");
                        }
                    } else {
                        System.out.println("wrong number of arguments");
                    }
                } else {
                    if (p.getArgs().length == 0) {
                        String[] content = ls();
                        for (String c : content) {
                            System.out.println(c);
                        }
                    } else {
                        System.out.println("wrong number of arguments");
                    }
                }
            } else if (p.getCommandName().equals("ls-r")) {
                if (find(p.getArgs())) {
                    String[] a = Arrays.copyOfRange(p.getArgs(), 0, p.getArgs().length - 2);
                    if (a.length == 0) {
                        String[] content_r = ls_r();
                        try {
                            for (String c : content_r) {
                                file.write(c + "\n");
                            }
                            file.close();
                        } catch (IOException e) {
                            System.out.println("something went wrong");
                        }
                    } else {
                        System.out.println("wrong number of arguments");
                    }
                } else {
                    if (p.getArgs().length == 0) {
                        String[] content_r = ls_r();
                        for (String c : content_r) {
                            System.out.println(c);
                        }
                    } else {
                        System.out.println("wrong number of arguments");
                    }
                }

            } else if (p.getCommandName().equals("rmdir")) {
                String directory = p.getArgs()[0];
                rmdir(directory);
            } else if (p.getCommandName().equals("cp-r")) {
                Path src = Paths.get(p.getArgs()[0]);
                Path dest = Paths.get(p.getArgs()[1]);
                cpr(src, dest);
            } else if (p.getCommandName().equals("exit")) {
                on = false;
                System.out.println("System closed");
            } else {
                System.out.println("Error: Command Not found");
            }
        }
    }


    public static void main(String[] args) {
        Terminal terminal = new Terminal();
        while (on) {
            terminal.chooseCommandAction();
        }
    }
}
