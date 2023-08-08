package dev.xkmc.ymlmobs.init.loot;

import dev.xkmc.ymlmobs.init.YmlMobs;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class YMGLMProvider extends GlobalLootModifierProvider {

	public static void register() {

	}

	public YMGLMProvider(DataGenerator gen) {
		super(gen.getPackOutput(), YmlMobs.MODID);
	}

	@Override
	protected void start() {
	}

}
