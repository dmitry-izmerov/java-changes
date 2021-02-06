package ru.demi.java7;

import java.util.List;

public class InvokeDynamicInstructionExample {

    public static void main(String[] args) {
        // lambda function calls are implemented in JVM with invokedynamic bytecode instruction
        // although this instruction was introduced for dynamic typed languages using JVM platform
        // you can check java bytecode instructions with running: javap -c InvokeDynamicInstruction.class
        List.of("1", "2", "3").forEach(n -> System.out.println(n));
        // Bytecode instructions for Consumer call in forEach:
        // 9: invokedynamic #19,  0        // InvokeDynamic #0:accept:()Ljava/util/function/Consumer;
        // 14: invokeinterface #23,  2     // InterfaceMethod java/util/List.forEach:(Ljava/util/function/Consumer;)V
    }
}


