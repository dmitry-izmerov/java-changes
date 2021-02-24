// you can mark the whole module as open
module ru.demi.java_changes {
// optional examples:
    requires java.logging;
    // required at compile time, but optional at runtime
    requires static java.instrument;
    // make sure that other modules who read your module also read defined transitive dependency
    requires transitive java.xml;
    exports ru.demi.java9.modules;
    provides ru.demi.java9.modules.ModuleSystem with ru.demi.java9.modules.ModuleSystemImpl;
    uses org.slf4j.Logger;
    // makes types accessible for reflection
    opens ru.demi.java2;
    opens ru.demi.java9 to java.xml;

// required for compilation:
    requires java.compiler;
    requires org.slf4j;
    requires java.net.http;
}