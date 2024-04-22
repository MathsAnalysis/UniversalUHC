package me.mathanalysis.it.uhc.utils.cuboid;

/**
 * Represents directions that can be applied to certain faces and actions of a Cuboid
 */
public enum CuboidDirection {

	NORTH,
	EAST,
	SOUTH,
	WEST,
	UP,
	DOWN,
	HORIZONTAL,
	VERTICAL,
	BOTH,
	UNKNOWN;

	public CuboidDirection opposite() {
        return switch (this) {
            case NORTH -> SOUTH;
            case EAST -> WEST;
            case SOUTH -> NORTH;
            case WEST -> EAST;
            case HORIZONTAL -> VERTICAL;
            case VERTICAL -> HORIZONTAL;
            case UP -> DOWN;
            case DOWN -> UP;
            case BOTH -> BOTH;
            default -> UNKNOWN;
        };
	}
}