package ru.demi.java15;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TextBlocksExample {
    private static final Logger log = LoggerFactory.getLogger(TextBlocksExample.class);

    public static void main(String[] args) {
        var textBlock = """
            some
            long
            statement
            comes
            here
            """;
        log.info("text block example: \n{}", textBlock);

        var savedIndentation = """
                <html>
                   <body>
                       <main>Main section</main>     
                   </body>
                </html>
        """;
        log.info("text block with leading indentation: \n{}", savedIndentation);


        var withParams = """
                SELECT * from orders
                    WHERE orders.id = %d
            """;
        log.info("text block with passed parameters: \n{}", withParams.formatted(1));
    }
}
