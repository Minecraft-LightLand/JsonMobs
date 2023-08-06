package dev.xkmc.jsonmobs.init.loot;

import dev.xkmc.jsonmobs.init.JsonMobs;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.GlobalLootModifierProvider;

public class JMGLMProvider extends GlobalLootModifierProvider {

	public static void register() {

	}

	public JMGLMProvider(DataGenerator gen) {
		super(gen.getPackOutput(), JsonMobs.MODID);
	}

	@Override
	protected void start() {
	}

}
