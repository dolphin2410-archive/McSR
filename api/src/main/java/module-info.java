open module McSR.api {
    requires kotlinx.serialization.json;
    requires java.net.http;
    requires kotlin.stdlib;

    exports io.github.dolphin2410.mcsr.util;
    exports io.github.dolphin2410.mcsr.cli.parser;
    exports io.github.dolphin2410.mcsr.cli.template;
    exports io.github.dolphin2410.mcsr.cli;
    exports io.github.dolphin2410.mcsr.config;
    exports io.github.dolphin2410.mcsr.loader;
    exports io.github.dolphin2410.mcsr.script;
}