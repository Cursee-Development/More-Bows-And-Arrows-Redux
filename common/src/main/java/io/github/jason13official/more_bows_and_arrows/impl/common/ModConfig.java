package io.github.jason13official.more_bows_and_arrows.impl.common;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;
import io.github.jason13official.more_bows_and_arrows.Constants;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.arrow.ArrowType;
import io.github.jason13official.more_bows_and_arrows.impl.common.item.bow.BowType;
import io.github.jason13official.more_bows_and_arrows.impl.common.network.packet.ConfigSyncS2CPacket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;

public class ModConfig {

  private static ModConfig INSTANCE = new ModConfig();
  private static boolean SYNCED_TO_REMOTE = false;
  public Set<Item> BANNED_BOWS = new HashSet<>();
  public Set<Item> BANNED_ARROWS = new HashSet<>();
  private List<String> BANNED_BOW_STRINGS = new ArrayList<>();
  private List<String> BANNED_ARROW_STRINGS = new ArrayList<>();

  private static boolean existsAsItemInRegistry(String s) {
    return BuiltInRegistries.ITEM.containsKey(Identifier.parse(s));
  }

  public static ModConfig get() {
    return INSTANCE;
  }

  public void sync(ConfigSyncS2CPacket packet) {

    BANNED_BOWS.clear();
    packet.bowIdentifiers().forEach(id -> {
      BANNED_BOWS.add(BuiltInRegistries.ITEM.getValue(id));
    });
    BANNED_ARROWS.clear();
    packet.arrowIdentifiers().forEach(id -> {
      BANNED_ARROWS.add(BuiltInRegistries.ITEM.getValue(id));
    });

    Constants.LOG.info("{} received config synchronization for remote server play", Constants.MOD_ID);
    SYNCED_TO_REMOTE = true;
  }

  public static void unsync() {
    SYNCED_TO_REMOTE = false;
  }

  public static void load(Path configDir) {

    if (SYNCED_TO_REMOTE) {
      return;
    }

    Path file = configDir.resolve(Constants.MOD_ID + "-server.toml");

    try {
      Files.createDirectories(configDir);
    } catch (Exception e) {
      Constants.LOG.error("Failed to create config directory, using defaults", e);
      return;
    }

    try (CommentedFileConfig config = CommentedFileConfig.builder(file.toFile()).build()) {
      if (Files.exists(file)) {
        config.load();
      }

      ModConfig loaded = new ModConfig();
      loaded.BANNED_BOW_STRINGS = config.getOrElse("banned_bows", new ArrayList<>());
      loaded.BANNED_ARROW_STRINGS = config.getOrElse("banned_arrows", new ArrayList<>());

      if (!loaded.BANNED_BOW_STRINGS.isEmpty()) {
        loaded.BANNED_BOW_STRINGS.forEach(ModConfig::validateAndLogItemBan);
      }

      if (!loaded.BANNED_ARROW_STRINGS.isEmpty()) {
        loaded.BANNED_ARROW_STRINGS.forEach(ModConfig::validateAndLogItemBan);
      }

      loaded.BANNED_BOWS = loaded.BANNED_BOW_STRINGS.stream().map(s -> BuiltInRegistries.ITEM.getValue(Identifier.parse(s))).collect(Collectors.toSet());
      loaded.BANNED_ARROWS = loaded.BANNED_ARROW_STRINGS.stream().map(s -> BuiltInRegistries.ITEM.getValue(Identifier.parse(s))).collect(Collectors.toSet());
      INSTANCE = loaded;

      config.setComment("banned_bows", " Bows that cannot be used by players (must be full item ID, like \"more_bows_and_arrows:amethyst_bow\"");
      config.set("banned_bows", INSTANCE.BANNED_BOW_STRINGS);
      config.setComment("banned_arrows", " Arrows that cannot be used by players (must be full item ID, like \"more_bows_and_arrows:amethyst_arrow\"");
      config.set("banned_arrows", INSTANCE.BANNED_ARROW_STRINGS);
      config.save();
    } catch (Exception e) {
      Constants.LOG.error("Failed to load More Bows and Arrows config, using defaults", e);
      INSTANCE = new ModConfig();
    }
  }

  private static void validateAndLogItemBan(String s) {

    if (!existsAsItemInRegistry(s)) {

      Constants.LOG.info("CATCHING ILLEGAL STATE EXCEPTION: invalid item ID ????");

      Constants.LOG.info("Valid bow ids and arrow ids for banning: ");

      System.out.println(); // padding for the logs

      for (BowType bow : BowType.values()) {
        Constants.LOG.info("{}:{}", Constants.MOD_ID, bow.name().toLowerCase());
      }

      for (ArrowType arrow : ArrowType.values()) {
        Constants.LOG.info("{}:{}", Constants.MOD_ID, arrow.name().toLowerCase());

      }

      System.out.println(); // padding for the logs

      throw new IllegalStateException("Attempted to ban a non-existent or invalid item ID: " + s + " This config only supports banning items under 'more_bows_and_arrows' namespace, with the above values.");
    }

    Constants.LOG.info("Banned {}", s);
  }
}
