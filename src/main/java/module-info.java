module McSR.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.swing;
    requires javafx.base;
    requires java.net.http;
    requires kotlinx.coroutines.core.jvm;
    requires kotlin.stdlib;
    requires kotlinx.serialization.json;

    exports io.github.dolphin2410.mcsr;
}