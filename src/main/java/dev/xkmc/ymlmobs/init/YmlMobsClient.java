package dev.xkmc.ymlmobs.init;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(value = Dist.CLIENT, modid = YmlMobs.MODID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class YmlMobsClient {

	@SubscribeEvent
	public static void client(FMLClientSetupEvent event) {
	}

	@SubscribeEvent
	public static void clientSetup(FMLClientSetupEvent event) {
	}


	@SubscribeEvent
	public static void onResourceReload(RegisterClientReloadListenersEvent event) {
	}

}
