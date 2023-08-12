package dev.xkmc.ymlmobs.content.skill.execution;

import dev.xkmc.ymlmobs.content.skill.core.SkillMechanic;

import java.util.ArrayList;
import java.util.List;

public abstract class SkillModifiableData {

	private final List<SkillMechanic> base;
	private final List<SkillMechanic> additional = new ArrayList<>();

	private boolean removed = false;
	private double power;

	private boolean locked = false;

	public SkillModifiableData(List<SkillMechanic> base, double power) {
		this.base = base;
		this.power = power;
	}

	public void remove() {
		if (locked) throw new IllegalStateException("Data is locked");
		removed = true;
	}

	public void powerUp(double multiplier) {
		if (locked) throw new IllegalStateException("Data is locked");
		power *= multiplier;
	}

	public void add(SkillMechanic skill) {
		if (locked) throw new IllegalStateException("Data is locked");
		additional.add(skill);
	}

	public void lock() {
		locked = true;
	}

	public boolean isRemoved() {
		return removed;
	}

	public List<SkillMechanic> getSkills() {
		if (!removed) {
			additional.addAll(0, base);
		}
		return additional;
	}

	/**
	 * get power scaled by conditions. Skill base power is not applied yet
	 */
	public double getPower() {
		return power;
	}

	public int size() {
		int ans = additional.size();
		if (!removed) {
			ans += base.size();
		}
		return ans;
	}
}
