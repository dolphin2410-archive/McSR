module McSR.main {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.web;
    requires java.net.http;
    requires kotlinx.coroutines.core.jvm;
    requires kotlin.stdlib;
    requires kotlinx.serialization.json;

    opens io.github.dolphin2410.mcsr.gui.controllers to javafx.fxml;
    exports io.github.dolphin2410.mcsr;
}