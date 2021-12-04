package io.github.dolphin2410.mcsr.gui

import io.github.dolphin2410.mcsr.MCSR
import io.github.dolphin2410.mcsr.api.config.extension.McSRConfig
import io.github.dolphin2410.mcsr.api.util.Resettable
import io.github.dolphin2410.mcsr.api.util.ResourceManager
import io.github.dolphin2410.mcsr.config.ConfigurationManager
import io.github.dolphin2410.mcsr.gui.controllers.ScriptGenerator
import io.github.dolphin2410.mcsr.gui.controllers.ServerSetupController
import javafx.fxml.FXMLLoader
import javafx.scene.Scene


object SceneManager {
    private val homeLoader = Resettable { FXMLLoader() }
    private val serverSetupLoader = Resettable { FXMLLoader() }
    private val extraSetupLoader = Resettable { FXMLLoader() }
    private val scriptLoader = Resettable { FXMLLoader() }
    private val finishLoader = Resettable { FXMLLoader() }

    private var home = Resettable { Scene(homeLoader.get().load(ResourceManager.stream(javaClass, "/fxml/home.fxml"))) }
    private var serverSetup = Resettable { Scene(serverSetupLoader.get().load(ResourceManager.stream(javaClass, "/fxml/server_setup.fxml"))) }
    private var extraSetup = Resettable { Scene(extraSetupLoader.get().load(ResourceManager.stream(javaClass, "/fxml/extra_setup.fxml"))) }
    private var script = Resettable { Scene(scriptLoader.get().load(ResourceManager.stream(javaClass, "/fxml/script.fxml"))) }
    private var finish = Resettable { Scene(finishLoader.get().load(ResourceManager.stream(javaClass, "/fxml/finish.fxml"))) }

    fun loadHome(reset: Boolean = false) {
        if (reset) {
            homeLoader.reset()
            home.reset()
        }
        MCSR.gui.loadScene(home.get(), homeLoader.get())
    }

    fun loadServerSetup(config: McSRConfig = McSRConfig.of(), reset: Boolean = false) {
        if (reset) {
            serverSetupLoader.reset()
            serverSetup.reset()
        }
        MCSR.gui.loadScene(serverSetup.get(), serverSetupLoader.get())
        serverSetupLoader.get().getController<ScriptGenerator>().config = config
    }

    fun loadExtraSetup(config: McSRConfig, reset: Boolean = false) {
        if (reset) {
            extraSetupLoader.reset()
            extraSetup.reset()
        }
        MCSR.gui.loadScene(extraSetup.get(), extraSetupLoader.get())
        extraSetupLoader.get().getController<ScriptGenerator>().config = config
    }

    fun loadScript(config: McSRConfig, reset: Boolean = false) {
        if (reset) {
            scriptLoader.reset()
            script.reset()
        }
        MCSR.gui.loadScene(script.get(), scriptLoader.get())
        scriptLoader.get().getController<ScriptGenerator>().config = config
    }

    fun loadFinish(config: McSRConfig, reset: Boolean = false) {
        if (reset) {
            finishLoader.reset()
            finish.reset()
        }
        MCSR.gui.loadScene(finish.get(), finishLoader.get())
        finishLoader.get().getController<ScriptGenerator>().config = config
    }

    fun build(config: McSRConfig) {
        loadHome(true)
        ConfigurationManager.addConfig(config)
    }
}