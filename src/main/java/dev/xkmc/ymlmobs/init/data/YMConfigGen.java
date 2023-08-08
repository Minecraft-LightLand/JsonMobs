package dev.xkmc.ymlmobs.init.data;

import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import net.minecraft.data.DataGenerator;

public class YMConfigGen extends ConfigDataProvider {


	public YMConfigGen(DataGenerator generator) {
		super(generator, "JsonMobs Config");
	}

	@Override
	public void add(Collector collector) {
	}

}