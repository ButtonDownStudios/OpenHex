package openhex.vec;

import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;

import openhex.vec.at.VectorData;
import openhex.vec.fin.VectorAS;

/**
 * 
 * @author MisterCavespider
 *
 */
public class Vectors {
	
	public static Vector3f toVector3f(Vector2f vec, float y) {
		return new Vector3f(vec.x, y, vec.y);
	}
	
	public static Vector2f toVector2f(Vector3f vec) {
		return new Vector2f(vec.x, vec.z);
	}
	
	/**
	 * Flat top hexagonal (cube) coordinates.
	 * @param vec
	 */
	public static @VectorData(dimensions=3) Vector<Integer> toHexVector(Vector2f vec, double size) {
		//see redblobgames
		//for some reason q and r are not in order here
		double r = vec.x * 2d/3d / size;
		double q = (-vec.x / 3d + Math.sqrt(3d)/3d * vec.y) / size;
		
		System.out.println(String.format("UNROUNDED: %f, %f", q,r));
		
		int rq = (int) Math.round(q);
		int rr = (int) Math.round(r);
		
		System.out.println(String.format("ROUNDED: %d, %d", rq,rr));
		
		return new VectorAS(rq, rr, 0);
	}
	
	/**
	 * Flat top hexagonal (cube) coordinates.
	 * Assumes size of 1.
	 * @param vec
	 * @return 
	 */
	public static @VectorData(dimensions=3) Vector<Integer> toHexVector(Vector2f vec) {
		return Vectors.toHexVector(vec, 1.0);
	}
	
	/**
	 * Flat top hexagonal (cube) coordinates.
	 * Converts 3d to 2d.
	 * @param vec
	 * @return 
	 */
	public static @VectorData(dimensions=3) Vector<Integer> toHexVector(Vector3f vec, double size) {
		return toHexVector(toVector2f(vec), size);
	}
	
	/**
	 * Flat top hexagonal (cube) coordinates.
	 * Converts 3d to 2d.
	 * Assumes size of 1.
	 * @param vec
	 * @return 
	 */
	public static @VectorData(dimensions=3) Vector<Integer> toHexVector(Vector3f vec) {
		return toHexVector(vec, 1.0);
	}
	
	public static Vector2f toVector2f(@VectorData(dimensions=3) Vector<Integer> vec, double size) {
		float x = (float) (size * 3f/2f * vec.getValues()[2]);
		float y = (float) (size * Math.sqrt(3) * (vec.getValues()[0] + vec.getValues()[2]/2.0));
		return new Vector2f(x, y);
	}
	
	public static Vector3f toVector3f(@VectorData(dimensions=3) Vector<Integer> vec, double size) {
		float x = (float) (size * 3f/2f * vec.getValues()[2]);
		float y = (float) (size * Math.sqrt(3) * (vec.getValues()[0] + vec.getValues()[2]/2.0));
		return new Vector3f(x, 0, y);
	}
	
	/*
	public static HexVector toHex(Position pos) {
        HexPosition hpos = new HexPosition(pos.x, pos.y, pos.z);
        return hpos;
    }
    
    public static Vector2f toVector2f(HexPosition pos, boolean alongXaxis) {
        float x = 1f * 3f/2f * pos.z;
        float z = 1f * FastMath.sqrt(3) * (pos.x + pos.z/2f);
        
        return new Vector2f(x, z);
    }
    
    public static Vector3f toVector3f(HexPosition pos, boolean alongXaxis) {
        Vector2f original = toVector2f(pos, alongXaxis);
        return new Vector3f(original.x, 0, original.y);
    }
    
    public static HexPosition toHex(Vector2f vec) {
        //See redblobgames
        double q = (vec.x * 2d/3d / 1d);
        double r = (-vec.x / 3d + Math.sqrt(3d)/3d * vec.y) / 1d;
        
        System.out.println("UNROUNDED: " + q + "," + (-q-r) + "," + r);
        
        int rx = (int) Math.round(q);
        int ry = (int) Math.round(-q-r);
        int rz = (int) Math.round(r);
        
        return new HexPosition(rz, ry, rx);
    }*/
	
}