package dev.xkmc.ymlmobs.init.data;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.IConfigSpec;
import net.minecraftforge.fml.config.ModConfig;
import org.apache.commons.lang3.tuple.Pair;

public class YMConfig {

	public static class Client {

		Client(ForgeConfigSpec.Builder builder) {
		}

	}

	public static class Common {

		public ForgeConfigSpec.BooleanValue isTargetSelf;
		public ForgeConfigSpec.BooleanValue isTargetPlayers;
		public ForgeConfigSpec.BooleanValue isTargetArmorStands;
		public ForgeConfigSpec.BooleanValue isTargetMarkers;
		public ForgeConfigSpec.BooleanValue isTargetCreativeMode;
		public ForgeConfigSpec.BooleanValue isTargetSpectatorMode;
		public ForgeConfigSpec.BooleanValue isTargetCitizensNPCs;
		public ForgeConfigSpec.BooleanValue isTargetAnimals;
		public ForgeConfigSpec.BooleanValue isTargetCreatures;
		public ForgeConfigSpec.BooleanValue isTargetMonsters;
		public ForgeConfigSpec.BooleanValue isTargetWaterMobs;
		public ForgeConfigSpec.BooleanValue isTargetFlyingMobs;
		public ForgeConfigSpec.BooleanValue isTargetSameFaction;
		public ForgeConfigSpec.BooleanValue isTargetOwner;
		public ForgeConfigSpec.BooleanValue isTargetNonMythic;
		public ForgeConfigSpec.BooleanValue isTargetVillagers;

		Common(ForgeConfigSpec.Builder builder) {
		}

	}

	public static final ForgeConfigSpec CLIENT_SPEC;
	public static final Client CLIENT;

	public static final ForgeConfigSpec COMMON_SPEC;
	public static final Common COMMON;

	static {
		final Pair<Client, ForgeConfigSpec> client = new ForgeConfigSpec.Builder().configure(Client::new);
		CLIENT_SPEC = client.getRight();
		CLIENT = client.getLeft();

		final Pair<Common, ForgeConfigSpec> common = new ForgeConfigSpec.Builder().configure(Common::new);
		COMMON_SPEC = common.getRight();
		COMMON = common.getLeft();
	}

	public static void init() {
		register(ModConfig.Type.CLIENT, CLIENT_SPEC);
		register(ModConfig.Type.COMMON, COMMON_SPEC);
	}

	private static void register(ModConfig.Type type, IConfigSpec<?> spec) {
		var mod = ModLoadingContext.get().getActiveContainer();
		String path = mod.getModId() + "-" + type.extension() + ".toml";
		ModLoadingContext.get().registerConfig(type, spec, path);
	}

}
