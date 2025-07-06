package com.cursee.more_bows_and_arrows.core;

import com.cursee.monolib.platform.Services;
import com.cursee.monolib.util.toml.Toml;
import com.cursee.more_bows_and_arrows.Constants;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class ModConfig {

    public static List<String> BANNED_BOWS = new ArrayList<>();
    public static List<String> BANNED_ARROWS = new ArrayList<>();

    private static final String FILE_SUFFIX = Constants.MOD_ID + "-server";
    private static final String CONFIG_DIR_FILEPATH = Services.PLATFORM.getGameDirectory() + File.separator + "config";

    public static void onLoad() {

        final File CONFIG_DIR = new File(CONFIG_DIR_FILEPATH);
        if (!CONFIG_DIR.isDirectory() && !CONFIG_DIR.mkdirs()) {
            throw new RuntimeException("Unable to access or create directory: " + CONFIG_DIR_FILEPATH);
        }

        handle(new File(CONFIG_DIR_FILEPATH + File.separator + FILE_SUFFIX + ".toml"));
    }

    private static final LinkedList<String> DEFAULTS = new LinkedList<>();
    private static void handle(File file) {

        if (!file.isFile()) {
            loadDefaults();
            try (PrintWriter writer = new PrintWriter(file)) {
                DEFAULTS.forEach(writer::println);
            }
            catch (Exception e) {
                System.out.println("Filed to write " + file.getAbsolutePath());
                System.out.println(e.getMessage());
            }
        }
        else {
            Toml toml = new Toml().read(file);

            BANNED_BOWS = toml.getList("banned_bows");
            BANNED_ARROWS = toml.getList("banned_arrows");
        }
    }

    private static void loadDefaults() {
        DEFAULTS.add("# banned_bows is a list of bows that will do nothing if a player attempts to use them");
        DEFAULTS.add("# banned_bows = [\"amethyst_bow\", \"obsidian_bow\"]");
        DEFAULTS.add("banned_bows = []");
        DEFAULTS.add("");
        DEFAULTS.add("# banned_arrows is a list of arrows that will do nothing if a player attempts to use them");
        DEFAULTS.add("# banned_arrows = [\"amethyst_arrow\", \"tnt_arrow\"]");
        DEFAULTS.add("banned_arrows = []");
    }
}
