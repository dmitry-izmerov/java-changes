package ru.demi.java6;

import javax.tools.ToolProvider;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class JavaCompilerApiExample {

    public static void main(String[] args) throws IOException, ClassNotFoundException, NoSuchMethodException,
            InvocationTargetException, IllegalAccessException {

        var packageName = JavaCompilerApiExample.class.getPackageName();
        var packagePath = Path.of(packageName.replace('.', File.separatorChar));
        Path currentDir = Paths.get("src/main/java")
            .toAbsolutePath()
            .resolve(packagePath);

        var className = "DynamicCompiled";
        var code = String.format("""
                package %s;
                
                public class %s {
                    public static void print() {
                        System.out.println("Hi from dynamically compiled class");
                    }
                }
            """.stripIndent(), packageName, className);

        var filePath = currentDir.resolve(className + ".java");
        Files.writeString(filePath, code);
        var compiledClassesDir = Paths.get("build/classes/java/main").toAbsolutePath().toString();
        var javac = ToolProvider.getSystemJavaCompiler();
        javac.run(null, null, null, "-cp", currentDir.toString(), "-d", compiledClassesDir,
            filePath.toString());

        Class<?> clazz = Class.forName(String.join(".",packageName, className));
        clazz.getMethod("print").invoke(null);
    }
}
