package com.tvd12.mmorpg.stresstest.world;

/**
 * 
 * @author sallyx (https://www.sallyx.org/sally/en/game-ai/)
 *
 */
public enum Behavior {

	NONE(0x00000), SEEK(0x00002), FLEE(0x00004), ARRIVE(0x00008), WANDER(0x00010), COHESION(0x00020),
	SEPARATION(0x00040), ALLIGNMENT(0x00080), OBSTACLE_AVOIDANCE(0x00100), WALL_AVOIDANCE(0x00200),
	FOLLOW_PATH(0x00400), PURSUIT(0x00800), EVADE(0x01000), INTERPOSE(0x02000), HIDE(0x04000), FLOCK(0x08000),
	OFFSET_PURSUIT(0x10000);

	private int __value;

	Behavior(int value) {
		__value = value;
	}

	public int get() {
		return __value;
	}

	@Override
	public String toString() {
		return name();
	}

}
