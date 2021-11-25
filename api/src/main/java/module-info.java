open module api {
    requires transitive kotlinx.serialization.json;
    requires transitive kotlinx.serialization.core;
    requires transitive java.net.http;
    requires transitive kotlin.stdlib;

    exports io.github.dolphin2410.mcsr.api.cli.parser;
    exports io.github.dolphin2410.mcsr.api.cli.template;
    exports io.github.dolphin2410.mcsr.api.cli;
    exports io.github.dolphin2410.mcsr.api.loader;
    exports io.github.dolphin2410.mcsr.api.config.config;
    exports io.github.dolphin2410.mcsr.api.config.parser;
    exports io.github.dolphin2410.mcsr.api.config.extension;
    exports io.github.dolphin2410.mcsr.api.config.mappers;
    exports io.github.dolphin2410.mcsr.api.config;
    exports io.github.dolphin2410.mcsr.api.util;
    exports io.github.dolphin2410.mcsr.api.util.data;
    exports io.github.dolphin2410.mcsr.api.util.web;
    exports io.github.dolphin2410.mcsr.api.script;
    exports io.github.dolphin2410.mcsr.api.gui;
    exports io.github.dolphin2410.mcsr.api;
}