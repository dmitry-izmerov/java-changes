package ru.demi.java8;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;
import java.util.List;

public @AnnotationsInJavaTypesExample.TypeUseAnnotation
    class AnnotationsInJavaTypesExample<@AnnotationsInJavaTypesExample.TypeParameterAnnotation T> {

    @TypeUseAnnotation String field1;
    @TypeUseAnnotation T field2;

    public static void main(@TypeUseAnnotation String[] args) {
        @TypeUseAnnotation String string = "";
        var object = (@TypeUseAnnotation Object) "some";
    }

    @TypeUseAnnotation int method(@TypeUseAnnotation List<@TypeUseAnnotation String> list) {
        return 0;
    }

    <@TypeParameterAnnotation T> @TypeUseAnnotation T parameterizedMethod(List<@TypeUseAnnotation T> arg) {
        return arg.get(0);
    }

    @Target(ElementType.TYPE_USE)
    @interface TypeUseAnnotation {}

    @Target(ElementType.TYPE_PARAMETER)
    @interface TypeParameterAnnotation {}
}
