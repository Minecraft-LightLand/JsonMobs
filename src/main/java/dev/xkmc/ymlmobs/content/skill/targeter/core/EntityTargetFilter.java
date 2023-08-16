package dev.xkmc.ymlmobs.content.skill.targeter.core;

import dev.xkmc.ymlmobs.init.data.YMConfig;
import dev.xkmc.ymlparser.argument.Argument;
import dev.xkmc.ymlparser.compound.CompoundValue;
import dev.xkmc.ymlparser.parser.core.ParserLogger;
import dev.xkmc.ymlparser.parser.line.StringElement;
import dev.xkmc.ymlparser.type.DataType;

public class EntityTargetFilter extends CompoundValue<EntityTargetFilter, EntityTargetFilter> {

	@Argument(name = "targetplayers", optional = true, description = "targets player. Default on (configurable)")
	protected boolean targetPlayers = YMConfig.COMMON.isTargetPlayers.get();
	@Argument(name = "targetcreative", optional = true, description = "targets invulnerable. Default on (configurable)")
	protected boolean targetCreativeMode = YMConfig.COMMON.isTargetCreativeMode.get();
	@Argument(name = "targetanimals", optional = true, description = "targets animals. Default on (configurable)")
	protected boolean targetAnimals = YMConfig.COMMON.isTargetAnimals.get();
	@Argument(name = "targetcreatures", optional = true, description = "targets creatures. Default on (configurable)")
	protected boolean targetCreatures = YMConfig.COMMON.isTargetCreatures.get();
	@Argument(name = "targetmonsters", optional = true, description = "targets monsters. Default on (configurable)")
	protected boolean targetMonsters = YMConfig.COMMON.isTargetMonsters.get();
	@Argument(name = "targetgroundmobs", optional = true, description = "targets ground mobs. Default on")
	protected boolean targetGroundMobs = true;
	@Argument(name = "targetwatermobs", optional = true, description = "targets water mobs. Default on (configurable)")
	protected boolean targetWaterMobs = YMConfig.COMMON.isTargetWaterMobs.get();
	@Argument(name = "targetflyingmobs", optional = true, description = "targets flying mobs. Default on (configurable)")
	protected boolean targetFlyingMobs = YMConfig.COMMON.isTargetFlyingMobs.get();
	@Argument(name = "targetsamefaction", optional = true, description = "targets mobs of same faction. Default on (configurable)")
	protected boolean targetSameFaction = YMConfig.COMMON.isTargetSameFaction.get();
	@Argument(name = "targetowner", optional = true, description = "targets owner. Default on (configurable)")
	protected boolean targetOwner = YMConfig.COMMON.isTargetOwner.get();

	@Override
	public EntityTargetFilter build() {
		return this;
	}

	public static class Defaults implements DataType<EntityTargetFilter> {
		public EntityTargetFilter ground() {
			EntityTargetFilter ans = new EntityTargetFilter();
			ans.targetGroundMobs = true;
			ans.targetWaterMobs = false;
			ans.targetFlyingMobs = false;
			return ans;
		}

		@Override
		public String name() {
			return "EntityTargetFilter";
		}

		@Override
		public EntityTargetFilter parse(ParserLogger logger, StringElement.ListElem elem) {
			return null;
		}
	}

}
