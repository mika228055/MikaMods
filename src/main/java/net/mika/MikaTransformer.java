package net.mika;

import net.minecraft.launchwrapper.IClassTransformer;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.Type;

public class MikaTransformer implements IClassTransformer {
    @Override
    public byte[] transform(String name, String transformedName, byte[] basicClass) {
        if (name.equals("net.minecraft.client.Minecraft")) {
            System.out.println("[MikaTransformer] Founded Minecraft class! Transforming.");
            return transformMinecraftClass(basicClass);
        }
        return basicClass;
    }

    private byte[] transformMinecraftClass(byte[] basicClass) {
        // Використовуємо ASM для трансформації байт-коду класу
        System.out.println("[MinecraftTransformer] Трансформація байт-коду Minecraft.");

        // Створення нового ClassVisitor для роботи з байт-кодом
        org.objectweb.asm.ClassReader classReader = new org.objectweb.asm.ClassReader(basicClass);
        org.objectweb.asm.ClassWriter classWriter = new org.objectweb.asm.ClassWriter(classReader, 0);
        ClassVisitor classVisitor = new ClassVisitor(Opcodes.ASM5, classWriter) {

            @Override
            public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
                // Наприклад, додаємо метод у клас Minecraft
                if (name.equals("run")) {  // Перевіряємо метод run()
                    System.out.println("[MinecraftTransformer] Трансформуємо метод run()");

                    MethodVisitor mv = super.visitMethod(access, name, descriptor, signature, exceptions);
                    // Додаємо нову інструкцію на початок методу run()
                    return new MethodVisitor(Opcodes.ASM5, mv) {
                        @Override
                        public void visitInsn(int opcode) {
                            if (opcode == Opcodes.RETURN) {
                                // Перед поверненням додаємо виведення в консоль
                                mv.visitFieldInsn(Opcodes.GETSTATIC, "java/lang/System", "out", "Ljava/io/PrintStream;");
                                mv.visitLdcInsn("Minecraft launching!");
                                mv.visitMethodInsn(Opcodes.INVOKEVIRTUAL, "java/io/PrintStream", "println", "(Ljava/lang/String;)V", false);
                            }
                            super.visitInsn(opcode);
                        }
                    };
                }
                return super.visitMethod(access, name, descriptor, signature, exceptions);
            }
        };

        classReader.accept(classVisitor, 0);
        return classWriter.toByteArray(); // Повертаємо модифікований байт-код
    }
}

