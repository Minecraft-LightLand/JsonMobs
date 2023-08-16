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

		public final ForgeConfigSpec.BooleanValue isTargetSelf;
		public final ForgeConfigSpec.BooleanValue isTargetPlayers;
		public final ForgeConfigSpec.BooleanValue isTargetCreativeMode;
		public final ForgeConfigSpec.BooleanValue isTargetAnimals;
		public final ForgeConfigSpec.BooleanValue isTargetCreatures;
		public final ForgeConfigSpec.BooleanValue isTargetMonsters;
		public final ForgeConfigSpec.BooleanValue isTargetWaterMobs;
		public final ForgeConfigSpec.BooleanValue isTargetFlyingMobs;
		public final ForgeConfigSpec.BooleanValue isTargetSameFaction;
		public final ForgeConfigSpec.BooleanValue isTargetOwner;

		Common(ForgeConfigSpec.Builder builder) {
			builder.push("Targeters");
			{
				isTargetSelf = builder.comment("Entity targeters target caster itself by default, except SelfTargeter")
						.define("TargetSelf", false);
				isTargetPlayers = builder.comment("Entity targeters target players by default")
						.define("TargetPlayers", true);
				isTargetCreativeMode = builder.comment("Entity targeters target invulnerable mobs by default")
						.define("TargetCreativeMode", false);
				isTargetAnimals = builder.comment("Entity targeters target animals by default")
						.define("TargetAnimals", true);
				isTargetCreatures = builder.comment("Entity targeters target creatures by default")
						.define("TargetCreatures", true);
				isTargetMonsters = builder.comment("Entity targeters target Monsters by default")
						.define("TargetMonsters", true);
				isTargetWaterMobs = builder.comment("Entity targeters target water mobs by default")
						.define("TargetWaterMobs", true);
				isTargetFlyingMobs = builder.comment("Entity targeters target flying mobs by default")
						.define("TargetFlyingMobs", true);
				isTargetSameFaction = builder.comment("Entity targeters target mobs of sam faction by default")
						.define("TargetSameFaction", true);
				isTargetOwner = builder.comment("Entity targeters targets its owner by default")
						.define("TargetOwner", true);
			}
			builder.pop();
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
