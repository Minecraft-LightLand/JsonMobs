package dev.xkmc.jsonmobs.init.registrate;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.jsonmobs.init.JsonMobs;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;

@SuppressWarnings({"unsafe"})
@MethodsReturnNonnullByDefault
public class JMItems {

	public static final RegistryEntry<CreativeModeTab> TAB = JsonMobs.REGISTRATE.buildModCreativeTab(
			"jsonmobs", "Json Mobs", e -> e.icon(Items.SPAWNER::getDefaultInstance));

	public static void register() {
	}

}
