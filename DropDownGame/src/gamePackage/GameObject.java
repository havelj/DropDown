package gamePackage;

import java.awt.geom.Point2D;

public abstract class GameObject extends Point2D {
	
	@Override
	public abstract double getX();

	@Override
	public abstract double getY();

	@Override
	public abstract void setLocation(double x, double y);
	
	public abstract int getSpeed();
	
	public abstract void setSpeed(int s);
}
