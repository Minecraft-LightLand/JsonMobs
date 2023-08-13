package dev.xkmc.ymlmobs.content.skill.targeter.core;

import dev.xkmc.ymlmobs.util.LevelPosYaw;

import java.util.Comparator;
import java.util.stream.Stream;

public enum PosTargetSorter {
	NONE,
	RANDOM,
	NEAREST,
	FURTHEST
}