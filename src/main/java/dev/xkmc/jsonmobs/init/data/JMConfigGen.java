package dev.xkmc.jsonmobs.init.data;

import dev.xkmc.l2library.serial.config.ConfigDataProvider;
import net.minecraft.data.DataGenerator;

public class JMConfigGen extends ConfigDataProvider {


	public JMConfigGen(DataGenerator generator) {
		super(generator, "JsonMobs Config");
	}

	@Override
	public void add(Collector collector) {
	}

}