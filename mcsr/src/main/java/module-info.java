module mcsr {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.web;
    requires java.net.http;
    requires kotlin.stdlib;
    requires kotlinx.serialization.json;
    requires api;

    opens io.github.dolphin2410.mcsr.gui.controllers to javafx.fxml;
    exports io.github.dolphin2410.mcsr;
}