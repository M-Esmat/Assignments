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

class Terminal {
    static boolean on = true;
    static String path = System.getProperty("user.dir");
    static String separator = "\\";

    public String echo(String[] line) {
        String l = new String();
        for (String li : line) {
            l = l + li + " ";
        }
        return l;
    }

    public void touch(String f) {
        try {
            File myfile = new File(f);
            if (myfile.createNewFile()) {
                System.out.println("File created: " + f);
            } else {
                public void cp(String f1, String f2) {
                    try {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String pwd() {
        return path;
    }

            File myfile1 = new File(f1);
            Scanner read = new Scanner(myfile1);
            FileWriter myfile2 = new FileWriter(f2);
            while (read.hasNextLine()) {
                myfile2.write(read.next() + "\n");
            }
            myfile2.close();
        } catch (IOException e) {
            System.out.println("Can't find file.");
        }
    }

    public void mkdir(String... folders) {
        for (String folder : folders) {
            new File(folder).mkdir();
        }
    }

    public void rm(String file) {
        File f = new File(file);
        if (f.exists()) {
            f.delete();
            System.out.println(file + " deleted");
        } else {
            System.out.println("File does not exist");
        }
    }

    public String cat(String file) {
        String lines = new String();
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

        System.out.println("done");
    }

    public void chooseCommandAction() {
        Parser p = new Parser();
        Scanner scanner = new Scanner(System.in);
        String command = scanner.nextLine();
        if (p.parse(command)) {
            if (p.getCommandName().equals("echo")) {
                System.out.println(echo(p.getArgs()));
                System.out.println();
            } else if (p.getCommandName().equals("touch")) {
            touch(p.getArgs()[0]);
        } else if (p.getCommandName().equals("pwd")) {
                System.out.println(pwd());
        } else if (p.getCommandName().equals("cp")) {
            cp(p.getArgs()[0], p.getArgs()[0]);
        } else if (p.getCommandName().equals("mkdir")) {
            mkdir(p.getArgs());
        } else if (p.getCommandName().equals("rm")) {
            rm(p.getArgs()[0]);
        } else if (p.getCommandName().equals("cat")) {
            if (p.getArgs().length == 1) {
                System.out.println(cat(p.getArgs()[0]));
            } else if (p.getArgs().length == 2) {
                System.out.println(cat(p.getArgs()[0], p.getArgs()[1]));
            } else {
                System.out.println("Wrong number of arguments");
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
            String[] content = ls();
            for (String c : content) {
                System.out.println(c);
            }

        } else if (p.getCommandName().equals("ls-r")) {
            String[] content = ls();
            for (int i = content.length; i > 0; i--) {
                System.out.println(content[i - 1]);
            }
        } else if(p.getCommandName().equals("cp-r")) {
            Path src = Paths.get(p.getArgs()[0]);
            Path dest = Paths.get(p.getArgs()[1]);
            cpr(src,dest);
            } else if (p.getCommandName().equals("rmdir")) {
            String directory = p.getArgs()[0];
            rmdir(directory);
        } else if (p.getCommandName().equals("exit")) {
            on = false;
            System.out.println("System closed");
        } else {
            System.out.println("Error: Command Not found");
        }
    }
}



    public static void main(String[] args) {
        while (on) {
            Terminal terminal = new Terminal();
            terminal.chooseCommandAction();
        }
    }
}


