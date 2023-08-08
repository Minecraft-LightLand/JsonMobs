package dev.xkmc.ymlmobs.init.registrate;

import com.tterrag.registrate.util.entry.RegistryEntry;
import dev.xkmc.ymlmobs.init.YmlMobs;
import net.minecraft.MethodsReturnNonnullByDefault;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;

@SuppressWarnings({"unsafe"})
@MethodsReturnNonnullByDefault
public class YMItems {

	public static final RegistryEntry<CreativeModeTab> TAB = YmlMobs.REGISTRATE.buildModCreativeTab(
			"jsonmobs", "Json Mobs", e -> e.icon(Items.SPAWNER::getDefaultInstance));

	public static void register() {
	}

}
